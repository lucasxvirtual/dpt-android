package com.drogariadopovo.domain.model

data class Voucher(
        val id : Int,
        val title : String,
        val image : String?,
        val expirationDate : String?,
        val used : Boolean,
        val user: Int
)