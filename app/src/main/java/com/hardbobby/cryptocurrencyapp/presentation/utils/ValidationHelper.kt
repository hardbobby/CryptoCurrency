package com.hardbobby.cryptocurrencyapp.presentation.utils

import android.util.Patterns
import androidx.annotation.StringRes
import com.afollestad.vvalidator.field.input.InputLayoutField
import com.hardbobby.cryptocurrencyapp.R

object ValidationHelper {

    fun InputLayoutField.assertNotEmpty(@StringRes stringRes: Int = -1) {
        if (stringRes != -1) isNotEmpty().description(stringRes)
        else isNotEmpty().description("Masukkan ${this.view.hint}")
    }

    fun InputLayoutField.assertEmail() {
        assert(this.editText.context.getString(R.string.msg_error_hint_invalid_email)) {
            Patterns.EMAIL_ADDRESS.matcher(it.getText()).matches()
        }
    }
}