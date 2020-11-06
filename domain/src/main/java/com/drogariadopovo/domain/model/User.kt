package com.drogariadopovo.domain.model

data class User(
        val id : Int,
        val profileImage : String?,
        val email : String,
        val name : String,
        val branch : Branch?,
        val role : Role?,
        val allowNotification : Boolean = true,
        val points : Int = 0,
        val lastLogin : String?,
        val isAdmin : Boolean,
        val registerDate : String,
        val updatedAt : String?,
        val forgotPasswordToken : String?,
        val forgotPasswordExpiration : String?,
        val phone: String?
)