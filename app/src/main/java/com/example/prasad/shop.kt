@file:Suppress("NAME_SHADOWING")

package com.example.prasad

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.core.Tag
import kotlinx.android.synthetic.main.activity_shop.*
import java.util.regex.Pattern

class shop : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop)
        auth= FirebaseAuth.getInstance()

        button123.setOnClickListener {
            signUpUser()
        }




    }
    private fun signUpUser(){
        if (email2.text.toString().isEmpty()){
            email2.error="Please Enter Email"
            email2.requestFocus()
            return
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email2.text.toString()).matches()){
            email2.error="Please Enter Email"
            email2.requestFocus()
           return
        }
        if (pass2.text.toString().isEmpty()){
            pass2.error="Please Enter Password"
            pass2.requestFocus()
        return
        }
        auth.createUserWithEmailAndPassword(email2.text.toString(), pass2.text.toString())
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    user?.sendEmailVerification()
                        ?.addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                startActivity(Intent(this,MainActivity::class.java))
                            }
                        }
                    // Sign in success, update UI with the signed-in user's information


                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(baseContext,"SignUp Failed.Try Again For Sometime",Toast.LENGTH_SHORT).show()

                }

            }

    }
}

