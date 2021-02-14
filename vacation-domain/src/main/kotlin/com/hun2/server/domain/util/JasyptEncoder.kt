package com.hun2.server.domain.util

import org.bouncycastle.jce.provider.BouncyCastleProvider
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor

object JasyptEncoder {

    private val pbeEnc = StandardPBEStringEncryptor().apply {
        setProvider(BouncyCastleProvider())
        setPassword("xoxo")
        setAlgorithm("PBEWithSHA256And128BitAES-CBC-BC")
    }

    fun encode(value: String): String {
        return pbeEnc.encrypt(value)
    }

    fun decode(value: String): String {
        return pbeEnc.decrypt(value)
    }
}