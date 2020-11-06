package br.com.guiadeacessibilidade.util

import java.math.BigInteger
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

class PasswordHelper{

    fun convertPassMd5(pass: String): String {
        var pass = pass
        var password: String? = null
        val mdEnc: MessageDigest
        try {
            mdEnc = MessageDigest.getInstance("MD5")
            mdEnc.update(pass.toByteArray(), 0, pass.length)
            pass = BigInteger(1, mdEnc.digest()).toString(16)
            while (pass.length < 32) {
                pass = "0$pass"
            }
            password = pass
        } catch (e1: NoSuchAlgorithmException) {
            e1.printStackTrace()
        }

        return password!!.toUpperCase()
    }
}