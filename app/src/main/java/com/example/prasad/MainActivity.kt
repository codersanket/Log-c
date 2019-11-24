package com.example.prasad

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.text.TextUtils.isEmpty
import android.util.Patterns
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import com.google.android.gms.tasks.OnCompleteListener
import kotlinx.android.synthetic.main.activity_main.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.PhoneAuthCredential
import kotlinx.android.synthetic.main.activity_shop.*
import android.widget.Toast.makeText as makeText1


class MainActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth




 override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
     auth = FirebaseAuth.getInstance()

     btnsign.setOnClickListener {
         startActivity(Intent(this,signupactivity::class.java))
         finish()
     }
   btn_sign.setOnClickListener {
       dologin()
   }
    }

    private fun dologin(){


        if (email.text.toString().isEmpty()){
            email.error="Please Enter Email"
            email.requestFocus()
            return
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email.text.toString()).matches()){
            email.error="Please Enter Email"
            email.requestFocus()
            return
        }
        if (password.text.toString().isEmpty()){
            password.error="Please Enter Password"
            password.requestFocus()
            return
        }
        auth.signInWithEmailAndPassword(email.text.toString(), password.text.toString())
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information

                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.


                    updateUI(null)
                }


            }
    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        updateUI(currentUser)
    }
    private fun updateUI(currentUser: FirebaseUser?){
        if (currentUser !=null) {
            if (currentUser.isEmailVerified){
                startActivity(Intent(this, Main2Activity2::class.java))
        finish()
        }else {
                Toast.makeText(baseContext, "Please verify your email address.",
                    Toast.LENGTH_SHORT).show()
            }
            }
        else{
            Toast.makeText(baseContext, "Authentication failed.",
                Toast.LENGTH_SHORT).show()
        }




    }


}













