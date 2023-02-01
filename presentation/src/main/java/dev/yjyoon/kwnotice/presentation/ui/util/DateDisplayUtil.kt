package dev.yjyoon.kwnotice.presentation.ui.util

import java.time.LocalDate
import java.time.temporal.ChronoUnit

object DateDisplayUtil {
    fun LocalDate.toRelativeDateString(): String {
        val today = LocalDate.now()

        return when {
            plusYears(1).isBeforeOrEqual(today) -> {
                "${ChronoUnit.YEARS.between(this, today)}년 전"
            }
            plusMonths(1).isBeforeOrEqual(today) -> {
                "${ChronoUnit.MONTHS.between(this, today)}달 전"
            }
            plusWeeks(1).isBeforeOrEqual(today) -> {
                "${ChronoUnit.WEEKS.between(this, today)}주 전"
            }
            plusDays(1).isBeforeOrEqual(today) -> {
                "${ChronoUnit.DAYS.between(this, today)}일 전"
            }
            else -> {
                "오늘"
            }
        }
    }

    private fun LocalDate.isBeforeOrEqual(other: LocalDate) = isEqual(other) || isBefore(other)
}
