package kr.or.lightsalt.kotloid

import android.os.Build
import kr.or.lightsalt.longDatePattern
import kr.or.lightsalt.monthAndDayPattern
import org.joda.time.LocalDate
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

val bestDateFormat: DateFormat by lazy {
	val locale = Locale.getDefault()
	SimpleDateFormat(if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2)
		android.text.format.DateFormat.getBestDateTimePattern(locale, "yMdE")
	else longDatePattern, locale)
}

val bestMonthDayFormat: DateFormat by lazy {
	val locale = Locale.getDefault()
	SimpleDateFormat(if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2)
		android.text.format.DateFormat.getBestDateTimePattern(locale, "MdE")
	else monthAndDayPattern, locale)
}

val Date.bestDate: String
	get() = (if (LocalDate(time).year == LocalDate().year) bestMonthDayFormat else bestDateFormat)
			.format(this)

val LocalDate.bestDate: String
	get() = (if (year == LocalDate().year) bestMonthDayFormat else bestDateFormat).format(toDate())
