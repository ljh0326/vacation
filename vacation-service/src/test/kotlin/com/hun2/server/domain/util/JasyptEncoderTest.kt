package com.hun2.server.domain.util

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.Test

@Suppress("TestFunctionName", "NonAsciiCharacters")
internal class JasyptEncoderTest {

    @Test
    fun 인코딩(){
        val value = "password"
        val encodingResult = JasyptEncoder.encode(value)
        println(encodingResult)
        val decodingResult = JasyptEncoder.decode(encodingResult)
        println(decodingResult)

        assertThat(value).isEqualTo(decodingResult)
    }

    @Test
    fun 디코딩(){
        val value = "iPQHzcdQh/0+2jgqyONxHupAxLk814EuP5aTtywFKuE="
        val decodingResult = JasyptEncoder.decode(value)
        println(decodingResult)

        assertThat(decodingResult).isEqualTo("password")
    }
}