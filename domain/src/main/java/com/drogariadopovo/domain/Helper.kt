package com.drogariadopovo.domain

import java.math.BigInteger
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.text.SimpleDateFormat
import java.util.*

class Helper {

    companion object {

        fun convertPassMd5(pass: String): String {
            var mPass = pass
            var password: String? = null
            val mdEnc: MessageDigest
            try {
                mdEnc = MessageDigest.getInstance("MD5")
                mdEnc.update(mPass.toByteArray(), 0, mPass.length)
                mPass = BigInteger(1, mdEnc.digest()).toString(16)
                while (mPass.length < 32) {
                    mPass = "0$mPass"
                }
                password = mPass
            } catch (e1: NoSuchAlgorithmException) {
                e1.printStackTrace()
            }

            return password!!.toUpperCase()
        }

        @JvmStatic
        fun formatDate(date: String?): String?{
            if(date.isNullOrBlank())
                return null
            val dateObj = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault()).parse(date)
            return SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(dateObj)
        }

    }

}