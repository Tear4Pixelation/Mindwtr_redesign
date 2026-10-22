package com.gipflig.mindwtr_redesign

import java.time.LocalDate
import java.time.format.DateTimeFormatter

actual fun formatToday(): String = LocalDate.now().format(DateTimeFormatter.ofPattern("EEE d MMM yyyy"))
