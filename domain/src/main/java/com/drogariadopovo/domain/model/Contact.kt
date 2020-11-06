package com.drogariadopovo.domain.model

data class Contact(
    val id : Int,
    val message : String,
    val created_at : String,
    val user : Int?
)