package io.luxus.adofai.data.source.remote.service.factory

import android.util.Log
import com.google.gson.JsonArray
import com.google.gson.JsonParser
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import java.io.IOException
import java.lang.reflect.Type


class JsonTextConverterFactory private constructor() : Converter.Factory() {

    companion object {
        private val TAG = JsonTextConverterFactory::class.java.simpleName
        fun create() = JsonTextConverterFactory()
    }

    override fun responseBodyConverter(
        type: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit): Converter<ResponseBody, *> {
        return JsonTextResponseBodyConverter()
    }

    internal class JsonTextResponseBodyConverter : Converter<ResponseBody, List<JsonArray>> {

        @Throws(IOException::class)
        override fun convert(responseBody: ResponseBody): List<JsonArray> {

            var result = responseBody.string()

            val from = result.indexOf('(')
            if (from != -1) {
                val to = result.lastIndexOf(')')
                if (from < to) {
                    result = result.substring(from + 1, to)
                }
            }

            return try {
                val json = JsonParser().parse(result).asJsonObject
                val rows = json["table"].asJsonObject["rows"].asJsonArray

                val rowsData = ArrayList<JsonArray>()
                for (element in rows) {
                    rowsData.add(element.asJsonObject["c"].asJsonArray)
                }

                rowsData
            } catch (t: Throwable) {
                Log.e(TAG, "failed to parse JSON", t)
                listOf()
            }
        }

    }

}