package com.yan.ahtloginscreen000.utils

import java.util.regex.Pattern

object Helper {

    /**Username can contain only alphanumeric characters, cannot be empty, and must be less than 30 character */
    fun validateUsername(username: String?): Boolean {
        return Pattern.matches("^([a-zA-Z0-9]){1,30}$", username)
    }

    /**Password can contain only alphanumeric characters, cannot be empty, and must be less than 16 character */
    fun validatePassword(password: String?): Boolean {
        return Pattern.matches("^([a-zA-Z0-9]){1,16}$", password)
    }

}