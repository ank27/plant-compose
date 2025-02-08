package com.example.plantcompose.utils

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter
import java.time.Instant

/**
 * Class uses custom adapter to convert [Instant] type to [String] and vice-versa.
 */
internal object CustomGsonBuilder {
    private val instantAdapter = object : TypeAdapter<Instant>() {
        override fun write(writer: JsonWriter, value: Instant) {
            writer.value(value.toString())
        }

        override fun read(reader: JsonReader) = Instant.parse(reader.nextString())
    }.nullSafe()

    fun build(): Gson = GsonBuilder()
        .registerTypeAdapter(Instant::class.java, instantAdapter)
        .create()
}
