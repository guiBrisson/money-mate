package utils.extensions

import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

fun Instant.currentDate(): LocalDateTime {
    return toLocalDateTime(TimeZone.currentSystemDefault())
}