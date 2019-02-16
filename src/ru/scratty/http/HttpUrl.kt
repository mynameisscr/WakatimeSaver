package ru.scratty.http

import java.net.URL

class HttpUrl(
    var scheme: String = "https",
    var host: String = "",
    var port: Int  = -1,
    var path: String = "",
    var parameters: Map<String, String> = emptyMap()
) {

    fun build(): URL {
        val sb = StringBuilder().apply {
            append("$scheme://$host")
            if (port >= 0) {
                append(":$port")
            }

            if (path.isNotEmpty() && !path.startsWith('/')) {
                append("/")
            }
            append(path)

            if (parameters.isNotEmpty()) {
                append('?')
                parameters.forEach { name, value ->
                    append("$name=$value&")
                }
                deleteCharAt(length - 1)
            }
        }

        return URL(sb.toString())
    }
}