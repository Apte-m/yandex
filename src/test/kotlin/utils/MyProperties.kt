package utils

import java.io.InputStream
import java.util.*

class MyProperties {
    private val props = Properties()

    init {
        val stream: InputStream = javaClass.classLoader.getResourceAsStream("application.properties")
        props.load(stream)
    }

    fun getUrl(): String {
        return props.getProperty("url")
    }

    fun getPath(): String {
        return props.getProperty("path")
    }
    fun getBaseUrl(): String {
        return props.getProperty("base.url")
    }
    fun getToken(): String {
        return props.getProperty("token")
    }
}