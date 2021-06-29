package io.luxus.adofai.data.source.remote.converter

import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import java.io.IOException
import java.lang.reflect.Type


class JsonTextConverterFactory private constructor() : Converter.Factory() {

    companion object {
        fun create() = JsonTextConverterFactory()
    }

    override fun responseBodyConverter(
        type: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit): Converter<ResponseBody, *> {
        return JsonTextResponseBodyConverter()
    }

    internal class JsonTextResponseBodyConverter : Converter<ResponseBody, String> {

        @Throws(IOException::class)
        override fun convert(responseBody: ResponseBody): String {
            val result = responseBody.string()

            val from = result.indexOf('(')
            if (from != -1) {
                val to = result.lastIndexOf(')')
                if (from < to) {
                    return result.substring(from + 1, to)
                }
            }

            return result
        }

    }

}