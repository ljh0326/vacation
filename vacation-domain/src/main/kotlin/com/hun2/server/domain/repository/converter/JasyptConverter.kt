package com.hun2.server.domain.repository.converter

import com.hun2.server.domain.util.JasyptEncoder
import javax.persistence.AttributeConverter
import javax.persistence.Converter

@Converter
class JasyptConverter : AttributeConverter<String, String> {
    override fun convertToDatabaseColumn(attribute: String?): String? {
        return attribute?.let { JasyptEncoder.encode(it) }
    }

    override fun convertToEntityAttribute(dbData: String?): String? {
        return dbData?.let { JasyptEncoder.decode(it) }
    }
}