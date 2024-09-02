package com.vitoraguiardf.bobinabanking.utils.datetime

import android.annotation.SuppressLint
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date

@SuppressLint("SimpleDateFormat")
class TimeUtils: Date {
    constructor()
    constructor(date: Date) : super(date.time)
    constructor(date: Long) : super(date)

    override fun toString(): String {
        return this.toBrazilianDate()
    }

    fun after(): Boolean {
        return super.after(TimeUtils())
    }

    fun before(): Boolean {
        return super.before(TimeUtils())
    }

    fun toBrazilianDate(): String {
        return formatBrazilianDate.format(this)
    }

    fun toBrazilianTime(): String {
        return formatBrazilianTime.format(this)
    }

    fun toAmericanDate(): String {
        return formatAmericanDate.format(this)
    }

    fun toAmericanTime(): String {
        return formatAmericanTime.format(this)
    }

    fun toDateStamp(): String {
        return formatDateStamp.format(this)
    }

    fun toHours(): String {
        return formatHours.format(this)
    }

    fun toFullHours(): String {
        return formatFullHours.format(this)
    }

    fun toTimeStamp(): String {
        return formatTimeStamp.format(this)
    }

    fun toPreciseTimeStamp(): String {
        return formatPreciseTimeStamp.format(this)
    }

    fun year(): Int {
        return formatOnlyYear.format(this).toInt()
    }

    fun month(): Int {
        return formatOnlyMonth.format(this).toInt()
    }

    fun day(): Int {
        return formatOnlyDay.format(this).toInt()
    }

    fun date(): Date {
        return Date(this.time)
    }

    object offset {
        fun days(offsetDays: Int): TimeUtils {
            val calendar = Calendar.getInstance()
            calendar.add(Calendar.DAY_OF_MONTH, offsetDays)
            return TimeUtils(calendar.time)
        }

        fun days(date: TimeUtils?, offsetDays: Int): TimeUtils {
            val calendar = Calendar.getInstance()
            calendar.time = date
            calendar.add(Calendar.DAY_OF_MONTH, offsetDays)
            return TimeUtils(calendar.time)
        }

        fun months(offsetDays: Int): TimeUtils {
            val calendar = Calendar.getInstance()
            calendar.add(Calendar.MONTH, offsetDays)
            return TimeUtils(calendar.time)
        }

        fun years(offsetDays: Int): TimeUtils {
            val calendar = Calendar.getInstance()
            calendar.add(Calendar.YEAR, offsetDays)
            return TimeUtils(calendar.time)
        }
    }

    /**
     * Formatação de Saída: retorna um valor de data atual formatado
     */
    object instance {
        fun brazilianDate(): String {
            return TimeUtils().toBrazilianDate()
        }

        fun brazilianTime(): String {
            return TimeUtils().toBrazilianTime()
        }

        fun americanDate(): String {
            return TimeUtils().toAmericanDate()
        }

        fun americanTime(): String {
            return TimeUtils().toAmericanTime()
        }

        fun dateStamp(): String {
            return TimeUtils().toDateStamp()
        }

        fun timeStamp(): String {
            return TimeUtils().toTimeStamp()
        }

        fun preciseTimeStamp(): String {
            return TimeUtils().toPreciseTimeStamp()
        }

        fun year(): Int {
            return TimeUtils().year()
        }

        fun month(): Int {
            return TimeUtils().month()
        }

        fun day(): Int {
            return TimeUtils().day()
        }
    }

    /**
     * Formatação de Entrada: recebe String com data formatada
     */
    object from {
        fun pattern(source: String, pattern: String?): TimeUtils {
            return parse(source, SimpleDateFormat(pattern))
        }

        fun brazilianDate(date: String): TimeUtils {
            return parse(date, formatBrazilianDate)
        }

        fun brazilianTime(date: String): TimeUtils {
            return parse(date, formatBrazilianTime)
        }

        fun americanDate(date: String): TimeUtils {
            return parse(date, formatAmericanDate)
        }

        fun americanTime(date: String): TimeUtils {
            return parse(date, formatAmericanTime)
        }

        fun timeStamp(date: String): TimeUtils {
            return parse(date, formatTimeStamp)
        }

        fun laravel(date: String): TimeUtils {
            return parse(date, formatLaravel)
        }

        private fun parse(date: String, format: SimpleDateFormat): TimeUtils {
            try {
                return TimeUtils(format.parse(date)!!.time)
            } catch (e: ParseException) {
                throw RuntimeException(e)
            }
        }

        private fun parseOrNull(date: String, format: SimpleDateFormat): TimeUtils? {
            return try {
                format.parse(date) as TimeUtils
            } catch (ignored: ParseException) {
                null
            }
        }
    }

    /**
     * Formatação de Saída: retorna String de data formatada
     */
    object to {
        fun pattern(date: Date, pattern: String?): String {
            return format(date, SimpleDateFormat(pattern))
        }

        fun brazilianDate(dateTime: Date): String {
            return format(dateTime, formatBrazilianDate)
        }

        fun brazilianTime(dateTime: Date?): String {
            return if (dateTime != null) format(dateTime, formatBrazilianTime)
            else "null"
        }

        fun americanDate(dateTime: Date): String {
            return format(dateTime, formatAmericanDate)
        }

        fun americanTime(dateTime: Date): String {
            return format(dateTime, formatAmericanTime)
        }

        fun time(dateTime: Date): String {
            return format(dateTime, formatTimeStamp)
        }

        fun timeStamp(dateTime: Date): String {
            return format(dateTime, formatTimeStamp)
        }

        fun year(dateTime: Date): Int {
            return format(dateTime, formatOnlyYear).toInt()
        }

        fun month(dateTime: Date): Int {
            return format(dateTime, formatOnlyMonth).toInt()
        }

        fun day(dateTime: Date): Int {
            return format(dateTime, formatOnlyDay).toInt()
        }

        private fun format(date: Date, format: SimpleDateFormat): String {
            return format.format(date)
        }
    }

    companion object {
        val formatBrazilianDate = SimpleDateFormat("dd/MM/yyyy")
        val formatBrazilianTime = SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
        val formatAmericanDate = SimpleDateFormat("yyyy-MM-dd")
        val formatAmericanTime = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        val formatDateStamp = SimpleDateFormat("yyyyMMdd")
        val formatHours = SimpleDateFormat("HH:mm")
        val formatFullHours = SimpleDateFormat("HH:mm:ss")
        val formatTimeStamp = SimpleDateFormat("yyyyMMddHHmmss")
        val formatPreciseTimeStamp = SimpleDateFormat("yyyyMMddHHmmssSSS")
        val formatLaravel = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
        val formatOnlyYear = SimpleDateFormat("yyyy")
        val formatOnlyMonth = SimpleDateFormat("MM")
        val formatOnlyDay = SimpleDateFormat("dd")
    }
}