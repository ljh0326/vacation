package com.hun2.server.service.util.impl

import com.hun2.server.domain.util.JacksonUtil
import com.hun2.server.domain.util.JacksonUtil.objectMapper
import com.hun2.server.domain.util.toDate
import com.hun2.server.service.exceptoin.InvalidAuthException
import com.hun2.server.service.exceptoin.InvalidParamException
import com.hun2.server.service.query.user.UserQueryService
import com.hun2.server.service.util.AuthTokenService
import com.hun2.server.service.util.DateTimeService
import io.jsonwebtoken.CompressionCodecs
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.io.Encoders
import io.jsonwebtoken.jackson.io.JacksonDeserializer
import io.jsonwebtoken.security.Keys
import io.jsonwebtoken.jackson.io.JacksonSerializer
import mu.KotlinLogging
import org.apache.commons.codec.binary.Base64
import org.springframework.stereotype.Service
import java.util.UUID

data class UserToken(val email: String, val password: String)

@Suppress("UNCHECKED_CAST")
@Service
class AuthTokenServiceImpl(
    private val dateTimeService: DateTimeService,
    private val userQueryService: UserQueryService
): AuthTokenService {

    private val logger = KotlinLogging.logger {}

    companion object {
        private const val CLAIM_VALUE_ISSUER = "hun2Server"
        private const val SIGNING_KEY_STRING =
            "c09ZRXRvY0t5Z3puY3NSWHlBTHY0amlyUEJoRjNLUzJYWHMvT2hqYTdlYkZ6cVhhSGNpbCsvZ0tRUHRmWnAyRjVPWEJFVXk2aDRVNAp3Y0czcjJFTVR3PT0="
        private val SIGNING_KEY = Keys.hmacShaKeyFor(Base64.decodeBase64(SIGNING_KEY_STRING))
        private val SIGNING_ALGORITHM = SignatureAlgorithm.HS512
        private val COMPRESSION_CODEC = CompressionCodecs.DEFLATE
        private const val CLAIM_KEY_PAYLOAD = "PAYLOAD"
    }

    override fun createToken(payload: UserToken): String {
        val now = dateTimeService.getNowLocalDateTime()
        return Jwts.builder()
            .setIssuer(CLAIM_VALUE_ISSUER)
            .setExpiration(now.plusMinutes(15L).toDate())
            .setIssuedAt(now.toDate())
            .setId(UUID.randomUUID().toString())
            .claim(CLAIM_KEY_PAYLOAD, payload)
            .signWith(
                SIGNING_KEY,
                SIGNING_ALGORITHM
            )
            .serializeToJsonWith(JacksonSerializer(objectMapper))
            .base64UrlEncodeWith(Encoders.BASE64URL)
            .compressWith(COMPRESSION_CODEC)
            .compact()
    }

    override fun destructToken(token: String): UserToken {
        val jws = try {
            Jwts.parserBuilder()
                .setSigningKey(SIGNING_KEY)
                .deserializeJsonWith(JacksonDeserializer(objectMapper))
                .base64UrlDecodeWith(Decoders.BASE64URL)
                .setClock { dateTimeService.getNowDate() }
                .build()
                .parseClaimsJws(token)
        } catch (e: ExpiredJwtException) {
            logger.error { "Already expired Jwt token." }
            throw ExpiredJwtException(e.header, e.claims, e.message)
        }

        val claims = jws.body

        if (claims.issuer != CLAIM_VALUE_ISSUER) {
            logger.error { "Invalid issuer. Must be $CLAIM_VALUE_ISSUER. issuer=${claims.issuer}" }
            throw InvalidAuthException("Invalid issuer.")
        }

        val map = claims[CLAIM_KEY_PAYLOAD] as? Map<String, Any?> ?: throw InvalidParamException("Payload not found.")
        val email = map[UserToken::email.name] ?: throw InvalidParamException("Value of email must not null.")
        val password = map[UserToken::password.name] ?: throw InvalidParamException("Value of password must not null.")

        return UserToken(
            ((email as? String)?.toString()) ?: throw ClassCastException("Value of email should be String value."),
            ((password as? String)?.toString()) ?: throw ClassCastException("Value of password should be String value.")
        )

    }

    override fun checkValidate(payload: UserToken): Boolean {
        return userQueryService.exist(payload.email, payload.password)
    }
}