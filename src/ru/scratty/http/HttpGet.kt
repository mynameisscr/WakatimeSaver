package ru.scratty.http

import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.security.cert.X509Certificate
import javax.net.ssl.HttpsURLConnection
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

class HttpGet(var url: HttpUrl) {

    //Need call if have "javax.net.ssl.SSLHandshakeException: java.security.cert.CertificateException: No X509TrustManager implementation available"
    fun trustAllCerts() {
        val trustAllCerts = arrayOf<TrustManager>(object : X509TrustManager {
            override fun checkClientTrusted(p0: Array<out X509Certificate>?, p1: String?) {
            }

            override fun checkServerTrusted(p0: Array<out X509Certificate>?, p1: String?) {
            }

            override fun getAcceptedIssuers(): Array<X509Certificate>? {
                return null
            }
        })

        val sslContext = SSLContext.getInstance("SSL")
        sslContext.init(null, trustAllCerts, null)
        HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.socketFactory)
    }

    fun execute(): Response {
        val connection = url.build().openConnection() as HttpURLConnection
        connection.requestMethod = "GET"

        val responseCode = connection.responseCode

        val inputStream = if (responseCode == HttpURLConnection.HTTP_OK) {
            connection.inputStream
        } else {
            connection.errorStream
        }

        val response = StringBuilder()

        val reader = BufferedReader(InputStreamReader(inputStream))
        reader.lines().forEach {
            response.appendln(it)
        }
        reader.close()

        return Response(responseCode, response.toString())
    }
}