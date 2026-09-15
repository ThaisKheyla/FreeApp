package com.example.freeapp.presentation.view.forgotPassword

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.freeapp.R

enum class PasswordRecoveryStep(
    @StringRes val titleRes: Int,
    @StringRes val descriptionRes: Int,
    @StringRes val buttonTextRes: Int,
    @DrawableRes val image: Int
) {
    PHONE(
        titleRes = R.string.forgot_password_phone_title,
        descriptionRes = R.string.forgot_password_phone_description,
        buttonTextRes = R.string.common_continue,
        image = R.drawable.illustration
    ),

    EMAIL(
        titleRes = R.string.forgot_password_email_title,
        descriptionRes = R.string.forgot_password_email_description,
        buttonTextRes = R.string.common_continue,
        image = R.drawable.illustration
    ),

    SMS_CODE(
        titleRes = R.string.forgot_password_sms_code_title,
        descriptionRes = R.string.forgot_password_sms_code_description,
        buttonTextRes = R.string.forgot_password_verify_button,
        image = R.drawable.illustration__1_
    ),

    EMAIL_CODE(
        titleRes = R.string.forgot_password_email_code_title,
        descriptionRes = R.string.forgot_password_email_code_description,
        buttonTextRes = R.string.forgot_password_verify_button,
        image = R.drawable.illustration__1_
    ),

    NEW_PASSWORD(
        titleRes = R.string.forgot_password_new_password_title,
        descriptionRes = R.string.forgot_password_new_password_description,
        buttonTextRes = R.string.forgot_password_reset_button,
        image = R.drawable.illustration__2_
    ),

    SUCCESS(
        titleRes = R.string.forgot_password_success_title,
        descriptionRes = R.string.forgot_password_success_description,
        buttonTextRes = R.string.forgot_password_login_button,
        image = R.drawable.illustration__3_
    );

    fun previous(): PasswordRecoveryStep {
        return when (this) {
            PHONE -> PHONE
            EMAIL -> PHONE
            SMS_CODE -> PHONE
            EMAIL_CODE -> EMAIL
            NEW_PASSWORD -> SMS_CODE
            SUCCESS -> NEW_PASSWORD
        }
    }
}