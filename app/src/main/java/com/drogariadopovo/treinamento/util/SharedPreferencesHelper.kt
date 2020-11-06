package br.com.guiadeacessibilidade.util

import android.content.Context
import android.content.SharedPreferences

open class SharedPreferencesHelper(context: Context){
    private final var PREF_ID : String = "APP_PREFERENCES"
    private var settings : SharedPreferences? = null
    private var editor : SharedPreferences.Editor? = null

    init {
        settings = context.getSharedPreferences(PREF_ID, Context.MODE_PRIVATE)
        editor = settings?.edit()
    }

    fun putString(key : String, string : String){
        editor?.putString(key, string)
        editor?.apply()
    }

    fun getString(key : String) : String? {
        return settings?.getString(key, null)
    }

    open fun erase(){
        editor?.clear()?.apply()
    }

}