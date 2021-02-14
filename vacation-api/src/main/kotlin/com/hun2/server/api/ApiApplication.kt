package com.hun2.server.api

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer
import java.util.*

@EnableConfigurationProperties
@SpringBootApplication(
    scanBasePackages = [
        "com.hun2.server"
    ]
)
class ApiApplication : SpringBootServletInitializer() {
    init {
        TimeZone.setDefault(TimeZone.getTimeZone("KST"))
    }
}

fun main(args: Array<String>) {
    val application = SpringApplication(ApiApplication::class.java)
    application.run(*args)
}
