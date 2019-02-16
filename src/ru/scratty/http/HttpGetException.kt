package ru.scratty.http

class HttpGetException(code: Int, message: String): Exception("Response code: $code $message")