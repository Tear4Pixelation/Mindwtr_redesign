package com.gipflig.mindwtr_redesign

import platform.Foundation.NSDate
import platform.Foundation.NSDateFormatter

actual fun formatToday(): String = NSDateFormatter().apply {
    setLocalizedDateFormatFromTemplate("EEE d MMM yyyy")
}.stringFromDate(NSDate())
