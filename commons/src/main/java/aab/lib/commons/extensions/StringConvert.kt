package aab.lib.commons.extensions

import android.text.TextUtils
import android.util.Patterns
import com.google.i18n.phonenumbers.PhoneNumberUtil
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber.CountryCodeSource
import java.text.DateFormatSymbols
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

object StringConvert {

    val dateTimeFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
    val dateTimeTFormatter: DateTimeFormatter =  DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")
    val longDateTimeFormatter: DateTimeFormatter =  DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSS")
    val dateFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

    fun String.toDateTime(formatter: DateTimeFormatter = dateTimeFormatter) : LocalDateTime = LocalDateTime.parse(this, formatter)

    fun String.toStringDateTime(formatter: DateTimeFormatter = dateTimeFormatter, showTime: Boolean = false): String {

        //fun Int.twoDigitsString() = if (this < 10) "0${this}" else "$this"

        val d = this.toDateTime(formatter)
        return if (showTime)
            "${d.dayOfMonth.twoDigitsString()}.${d.monthValue.twoDigitsString()}.${d.year} ${d.hour.twoDigitsString()}:${d.minute.twoDigitsString()}:${d.second}"
        else
            "${d.dayOfMonth.twoDigitsString()}.${d.monthValue.twoDigitsString()}.${d.year}"
    }

    fun String.toDate(formatter: DateTimeFormatter = dateTimeFormatter) : LocalDate = LocalDate.parse(this, formatter)

    fun String.toPhoneFormat(countryIso: String = CountryCodeSource.UNSPECIFIED.name, carrierCode: String = "") : String {
        return try {
            val phoneNumberUtil = PhoneNumberUtil.getInstance()
            val phone: PhoneNumber = phoneNumberUtil.parse(this, countryIso )
            val number = phoneNumberUtil.formatNationalNumberWithCarrierCode(phone, carrierCode)
            number.replace("8 ", "+7 ")
        } catch (e: Exception) {
            this
        }
    }

    fun String.isPhone(countryIso: String) : Boolean {
        return try {
            val phoneUtil = PhoneNumberUtil.getInstance()
            val phone = phoneUtil.parse(this, countryIso)
            phoneUtil.isPossibleNumber(phone)
        } catch (e: Exception) {
            false
        }
    }

    fun String.isEmail() : Boolean {
        return !TextUtils.isEmpty(this) && Patterns.EMAIL_ADDRESS.matcher(this).matches()
    }

    fun LocalDate.month(locale: String = "RU"): String {
        val dfs = DateFormatSymbols(Locale(locale))
        val months = dfs.months
        return months[this.monthValue-1]
    }
}
