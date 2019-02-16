package ru.scratty

import java.io.*
import kotlin.streams.toList

object ApiKeyHelper {

    private const val PATH_TO_API_KEY = "api_key.txt"

    fun readApiKey() = readFile(PATH_TO_API_KEY)

    fun saveApiKey(key: String) {
        writeFile(PATH_TO_API_KEY, key)
    }

    private fun readFile(path: String): String {
        val file = File(path)
        if (!file.exists()) {
            return ""
        }

        val reader = BufferedReader(InputStreamReader(FileInputStream(path)))
        val lines = reader.lines().toList()

        return if (lines.count() > 0) {
            lines[0]
        } else {
            ""
        }
    }

    private fun writeFile(path: String, data: String) {
        val writer = BufferedWriter(OutputStreamWriter(FileOutputStream(path)))
        writer.write(data)
        writer.flush()
        writer.close()
    }
}