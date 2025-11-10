package me.lakhtin.themecalibration

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import org.jetbrains.compose.resources.StringResource
import themecalibration.calibrationapp.generated.resources.Res
import themecalibration.calibrationapp.generated.resources.de
import themecalibration.calibrationapp.generated.resources.en
import themecalibration.calibrationapp.generated.resources.ru

enum class AppLocale(
    val code: String,
    val stringRes: StringResource
) {
    English("en", Res.string.en),
    Russian("ru", Res.string.ru),
    Deutsch("de", Res.string.de)
}

// commonMain
interface LocaleProvider {
    fun getLocale(): String
}

fun String?.toAppLocale(
): AppLocale = when (this) {
    "ru" -> AppLocale.Russian
    "de" -> AppLocale.Deutsch
    else -> AppLocale.English
}

@Composable
fun rememberAppLocale(provider: LocaleProvider): AppLocale {
    val locale = provider.getLocale()
    return remember(locale) {
        locale.toAppLocale()
    }
}
