package com.drogariadopovo.treinamento.util

import android.content.Context
import android.net.ConnectivityManager

class Helper {
    companion object {
        fun isConnected(context: Context) : Boolean{
            val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            return activeNetworkInfo != null && activeNetworkInfo.isConnected
        }

        fun getStates() : ArrayList<String>{
            return arrayListOf("Rio de Janeiro", "São Paulo")
        }

        fun convertDateForApi(date: String) : String {
            return date.substring(6,date.length) + "-" + date.substring(3,5) + "-" + date.substring(0,2)
        }

        fun convertDateForApp(date : String) : String{
            return date.substring(8,date.length) + "/" + date.substring(5,7) + "/" + date.substring(0,4)
        }
    }
}