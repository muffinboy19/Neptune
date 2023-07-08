package com.example.login_screen

import android.text.InputType
import android.text.method.PasswordTransformationMethod
import android.widget.EditText
import android.widget.ImageButton
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.firebase.auth.GoogleAuthProvider

interface goog{

    fun text_changer(casa :EditText,micasa:ImageButton){
        micasa.setOnClickListener{
            val inputType = casa.inputType
            if(inputType== InputType.TYPE_TEXT_VARIATION_PASSWORD){
                casa.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                casa.transformationMethod = null
                micasa.setImageResource(R.drawable.baseline_remove_red_eye_24)
            }
            else{
                casa.inputType= InputType.TYPE_TEXT_VARIATION_PASSWORD
                casa.transformationMethod = PasswordTransformationMethod()
                micasa.setImageResource(R.drawable.closed_eyes)
            }
            casa.setSelection(casa.text.length)
        }
    }




}