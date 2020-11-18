package com.example.shopping.api.request

import android.util.Patterns

class SignupRequest(
    val email: String?,
    val passworkd: String?,
    val name: String?
) {
    fun isNotValidEmail() =
        email.isNullOrBlank() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()
    fun isNotValidPassword() =
        passworkd.isNullOrBlank() || passworkd.length !in 8..20
    fun isNotValidName() =
        name.isNullOrBlank() || name.length !in 2..20

}