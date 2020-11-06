package br.com.guiadeacessibilidade.util

import android.content.Context
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Build
import java.util.*

class LanguageHelper {

    companion object {
        fun changeLocale(context : Context, res: Resources, locale: String) {

            val config = Configuration(res.configuration)

            when (locale) {
                "en" -> config.setLocale(Locale("en"))
                "es" -> config.setLocale(Locale("es"))
                "pt" -> config.setLocale(Locale("pt"))
            }

            res.updateConfiguration(config, res.displayMetrics)
        }

        fun getLanguage(context: Context, res: Resources) : String{
            val config = Configuration(res.configuration)
            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                config.locales[0].language
            } else {
                config.locale.language
            }
        }
    }

}