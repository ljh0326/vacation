package com.hun2.server.service.util.impl

import com.hun2.server.service.util.DateTimeService
import org.springframework.stereotype.Service
import java.time.ZoneId
import java.time.ZonedDateTime
import java.util.Date

@Service
class DateTimeServiceImpl: DateTimeService {
    override fun getNowDate() = Date()
    override fun getNowZonedDateTime(): ZonedDateTime = ZonedDateTime.now(ZoneId.of("Asia/Seoul"))
}