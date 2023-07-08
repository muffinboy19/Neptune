package com.example.login_screen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import org.w3c.dom.Text
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.security.spec.PSSParameterSpec

class register : AppCompatActivity(),goog {
    private lateinit var auth: FirebaseAuth
    private lateinit var mDataBase: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        val login_text = findViewById<TextView>(R.id.login_text)
        val register_butto = findViewById<AppCompatButton>(R.id.register_button)
        login_text.setOnClickListener {
            val intent :Intent = Intent(this@register,sigin::class.java)
            startActivity(intent)
            finish()
        }
        val nameofuser = findViewById<EditText>(R.id.nameofuser)
        val email_text= findViewById<EditText>(R.id.email)
        val eye2  = findViewById<ImageButton>(R.id.eye2)
        val eye3  = findViewById<ImageButton>(R.id.eye3)
        val pass_tt  = findViewById<EditText>(R.id.password)
        val rpass_tt  = findViewById<EditText>(R.id.repassowrd)
        eye3.setOnClickListener{
            text_changer(pass_tt,eye3)
        }
        eye2.setOnClickListener{
            text_changer(rpass_tt,eye2)
        }
        auth = FirebaseAuth.getInstance()


        if(pass_tt.text.toString()==rpass_tt.text.toString()){
               register_butto.setOnClickListener{
                   if(email_text.text.isEmpty() || pass_tt.text.isEmpty() || rpass_tt.text.isEmpty()){
                       Toast.makeText(this,"Please fill all the credentials",Toast.LENGTH_SHORT).show()
                   }
                   else{
                       createUserWithEmailAndPassowrd(nameofuser.text.toString(),email_text.text.toString(),pass_tt.text.toString(),)
                   }
               }
           }
            else{
                Toast.makeText(this,"Password is not Matching",Toast.LENGTH_SHORT).show()
           }





    }
    private fun createUserWithEmailAndPassowrd(name:String,email:String,Password:String){

        auth.createUserWithEmailAndPassword(email,Password)
            .addOnCompleteListener{task->
                if(task.isSuccessful){
                    val user:FirebaseUser? = auth.currentUser
                    addUserToTheDataBase(name,email,auth.currentUser!!.uid)
                    Toast.makeText(this,"Welcome",Toast.LENGTH_SHORT).show()
                    val intent = Intent(this,Homescreen::class.java)
                    startActivity(intent)
                    finish()
                }
                else{
                    Toast.makeText(this,"Sign-up failed",Toast.LENGTH_SHORT).show()
                }
        }

    }
    fun addUserToTheDataBase(name: String, email: String, uid: String){

        mDataBase = FirebaseDatabase.getInstance().getReference()
        mDataBase.child("user").child(uid).setValue(User(name,email,uid))


    }



}