/**
 * Copied from https://github.com/google/iosched/blob/master/shared/src/main/java/com/google/samples/apps/iosched/shared/domain/UseCase.kt
 */
package com.example.plantcompose.utils

import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn

private const val NUMBER_OF_THREADS = 4

interface Scheduler {

    fun execute(task: () -> Unit)

    fun postToMainThread(task: () -> Unit)

    fun postDelayedToMainThread(delay: Long, task: () -> Unit)
}

/**
 * Runs tasks in a [ExecutorService] with a fixed thread of pools
 */
internal object AsyncScheduler : Scheduler {

    private val executorService: ExecutorService = Executors.newFixedThreadPool(NUMBER_OF_THREADS)

    override fun execute(task: () -> Unit) {
        executorService.execute(task)
    }

    override fun postToMainThread(task: () -> Unit) {
        if (isMainThread()) {
            task()
        } else {
            val mainThreadHandler = Handler(Looper.getMainLooper())
            mainThreadHandler.post(task)
        }
    }

    private fun isMainThread(): Boolean {
        return Looper.getMainLooper().thread === Thread.currentThread()
    }

    override fun postDelayedToMainThread(delay: Long, task: () -> Unit) {
        val mainThreadHandler = Handler(Looper.getMainLooper())
        mainThreadHandler.postDelayed(task, delay)
    }
}

/**
 * A shim [Scheduler] that by default handles operations in the [AsyncScheduler].
 */
object DefaultScheduler : Scheduler {

    private var delegate: Scheduler = AsyncScheduler

    /**
     * Sets the new delegate scheduler, null to revert to the default async one.
     */
    fun setDelegate(newDelegate: Scheduler?) {
        delegate = newDelegate ?: AsyncScheduler
    }

    override fun execute(task: () -> Unit) {
        delegate.execute(task)
    }

    override fun postToMainThread(task: () -> Unit) {
        delegate.postToMainThread(task)
    }

    override fun postDelayedToMainThread(delay: Long, task: () -> Unit) {
        delegate.postDelayedToMainThread(delay, task)
    }
}

/**
 * Executes business logic synchronously or asynchronously using a [Scheduler].
 */
abstract class UseCase<in P, R> {

    companion object {
        private const val LOG_TAG = "UseCase"
    }

    private val taskScheduler = DefaultScheduler

    /** Executes the use case asynchronously and places the [Result] in a MutableLiveData
     *
     * @param parameters the input parameters to run the use case with
     * @param result the MutableLiveData where the result is posted to
     *
     */
    operator fun invoke(parameters: P, result: MutableLiveData<Result<R>>) {
        try {
            taskScheduler.execute {
                try {
                    execute(parameters).let { useCaseResult ->
                        result.postValue(Result.success(useCaseResult))
                    }
                } catch (e: Exception) {
                    Log.e(LOG_TAG, e.toString())
                    result.postValue(Result.failure(e))
                }
            }
        } catch (e: Exception) {
            Log.e(LOG_TAG, e.toString())
            result.postValue(Result.failure(e))
        }
    }

    /** Executes the use case asynchronously and returns a [Result] in a new LiveData object.
     *
     * @return an observable [LiveData] with a [Result].
     *
     * @param parameters the input parameters to run the use case with
     */
    operator fun invoke(parameters: P): LiveData<Result<R>> {
        val liveCallback: MutableLiveData<Result<R>> = MutableLiveData()
        this(parameters, liveCallback)
        return liveCallback
    }

    /** Executes the use case synchronously  */
    fun executeNow(parameters: P): Result<R> {
        return try {
            Result.success(execute(parameters))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    /**
     * Override this to set the code to be executed.
     */
    @Throws(RuntimeException::class)
    protected abstract fun execute(parameters: P): R

    open fun dispose() {}
}


abstract class FlowUseCase<in P, R>(private val coroutineDispatcher: CoroutineDispatcher) {
    operator fun invoke(parameters: P): Flow<Result<R>> = execute(parameters)
        .catch { e -> emit(Result.failure(Exception(e))) }
        .flowOn(coroutineDispatcher)

    protected abstract fun execute(parameters: P): Flow<Result<R>>
}


operator fun <R> UseCase<Unit, R>.invoke(): LiveData<Result<R>> = this(Unit)
operator fun <R> UseCase<Unit, R>.invoke(result: MutableLiveData<Result<R>>) = this(Unit, result)
