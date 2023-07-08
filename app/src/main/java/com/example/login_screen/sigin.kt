package com.example.login_screen

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.text.method.PasswordTransformationMethod
import android.util.Log
import android.widget.Button
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.GoogleAuthProvider
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import androidx.core.content.res.ResourcesCompat
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.material.tabs.TabLayout.TabGravity
import kotlinx.coroutines.handleCoroutineException


class sigin : AppCompatActivity(),goog{

    companion object {
        const val RC_SIGN_IN = 123
    }

    private lateinit var auth:FirebaseAuth
    private lateinit var emai_text: EditText
    private lateinit var pass_text :EditText
    private lateinit var loginb :AppCompatButton
    private lateinit var google :ImageButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.news)

        val facebook = findViewById<ImageButton>(R.id.facebook)
        google= findViewById<ImageButton>(R.id.google)
        val apple = findViewById<ImageButton>(R.id.apple)
        emai_text = findViewById(R.id.email)
        pass_text = findViewById(R.id.password)
        val rr = findViewById<TextView>(R.id.login_text)
        loginb = findViewById(R.id.register_button)
        val eye = findViewById<ImageButton>(R.id.eye3)


        auth = FirebaseAuth.getInstance()


        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_GAMES_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        val client = GoogleSignIn.getClient(this,gso)
        google.setOnClickListener{
            val signInIntent = client.signInIntent
            startActivityForResult(signInIntent, RC_SIGN_IN)
        }


        loginb.setOnClickListener{

            val email = emai_text.text.toString()
            val passwordb= pass_text.text.toString()
            singInWithEmailnPassword(email, passwordb)
        }
        rr.setOnClickListener {
            val intent: Intent = Intent(this@sigin,register::class.java)
            startActivity(intent)
            finish()
        }
        eye.setOnClickListener{
            text_changer(pass_text,eye)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode== RC_SIGN_IN){
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try{
                val account = task.getResult(ApiException::class.java)
                firebaseAuthWithGoogle(account)
            }
            catch (e:ApiException){
                Toast.makeText(this,"Failed", Toast.LENGTH_SHORT).show()
//                Log.w(TAG,"Google sign in failed",e)
            }
        }
    }



    private fun singInWithEmailnPassword(email: String, passwordb: String) {
            auth.signInWithEmailAndPassword(email,passwordb)
                .addOnCompleteListener(this){task->
                    if(task.isSuccessful){
                        val user: FirebaseUser? = auth.currentUser
                        Toast.makeText(this,"Welcome",Toast.LENGTH_SHORT).show()
                        val intent = Intent(this,Homescreen::class.java)
                        startActivity(intent)
                    }
                    else{
                        Toast.makeText(this@sigin,"Authentication failed: ${task.exception.toString()}",Toast.LENGTH_SHORT).show()

                    }
                }
    }

     private fun firebaseAuthWithGoogle(account: GoogleSignInAccount?) {
         val credential = GoogleAuthProvider.getCredential(account?.idToken, null)
         auth.signInWithCredential(credential)
             .addOnCompleteListener(this) { task ->
                 if (task.isSuccessful) {
                     val user = auth.currentUser
                     Toast.makeText(this,"Welcome",Toast.LENGTH_SHORT).show()
                     val intent = Intent(this,Homescreen::class.java)
                     startActivity(intent)
                 } else {
                     Toast.makeText(this,"Sorry account was not created",Toast.LENGTH_SHORT).show()
//                     Log.w(TAG, "signInWithCredential:failure", task.exception)
                 }
             }
     }
    }






