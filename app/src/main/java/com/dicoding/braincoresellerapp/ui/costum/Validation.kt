package com.dicoding.braincoresellerapp.ui.costum

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.util.Patterns
import com.dicoding.braincoresellerapp.R
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class Validation @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : TextInputEditText(context, attrs) {
    init {
        addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val input = s.toString()
                when(inputType){
                    Password -> {
                        if (input.length < 8) {
                            (parent.parent as? TextInputLayout)?.error = context.getString(R.string.error_password_too_short)
                        } else {
                            (parent.parent as? TextInputLayout)?.error = null
                        }

                    }

                    Email -> {
                        if(!Patterns.EMAIL_ADDRESS.matcher(input).matches()){
                            (parent.parent as? TextInputLayout)?.error = context.getString(R.string.error_invalid_email)
                        } else {
                            (parent.parent as? TextInputLayout)?.error = null
                        }
                    }
                }

            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }

    companion object{
        const val Password = 0x00000081
        const val Email = 0x00000021
    }
}