package com.example.pelacakkontak.util

import java.time.ZoneId
import java.time.format.DateTimeFormatter

// Timezone is needed to format Instant
// See https://java2blog.com/format-instant-to-string-java/
val FORMATTER: DateTimeFormatter =
    DateTimeFormatter.ofPattern("yyyy-MM-dd").withZone(ZoneId.of("Asia/Jakarta"))
