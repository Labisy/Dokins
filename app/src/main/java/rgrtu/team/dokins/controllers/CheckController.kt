package rgrtu.team.dokin.controllers

import android.telephony.PhoneNumberFormattingTextWatcher
import android.text.InputType
import android.widget.EditText
import android.widget.ImageButton
import rgrtu.team.dokins.R


class CheckController {

    companion object {
        var flag = true

        fun checkEye(pass: EditText, eye: ImageButton) {
            if (flag) {
                pass.inputType = InputType.TYPE_CLASS_TEXT
                eye.setBackgroundResource(R.drawable.eye_off)
                flag = false
            } else {
                pass.inputType = 129
                eye.setBackgroundResource(R.drawable.eye)
                flag = true
            }
        }

        fun phoneMask(phone: EditText) {
            phone.addTextChangedListener(PhoneNumberFormattingTextWatcher())
        }

//        fun isEmail(email: EditText) {
//            if (!isEmailValid(email.text.toString())) {
//                CustomT oast().Show_Toast(
//                    getActivity(), loginView,
//                    "Your Email Id is Invalid."
//                )
//            }
//        }
//
//        fun isEmailValid(email: CharSequence?): Boolean {
//            return Patterns.EMAIL_ADDRESS.matcher(email).matches()
//        }
    }
}