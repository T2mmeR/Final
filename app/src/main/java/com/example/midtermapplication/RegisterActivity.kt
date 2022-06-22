package com.example.midtermapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        auth = FirebaseAuth.getInstance()

        registerButton.setOnClickListener {
            register()
        }

        backToLoginButton.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }


    private fun register() {

        if (emailEditTextRegister.text.toString().isEmpty()) {
            emailEditTextRegister.error = "Please enter email"
            emailEditTextRegister.requestFocus()
            return
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(emailEditTextRegister.text.toString()).matches()) {
            emailEditTextRegister.error = "Please enter valid email"
            emailEditTextRegister.requestFocus()
            return
        }

        if (passwordEditTextRegister.text.toString().isEmpty()) {
            passwordEditTextRegister.error = "Please enter password"
            return
        }


        auth.createUserWithEmailAndPassword(
            emailEditTextRegister.text.toString(),
            passwordEditTextRegister.text.toString()
        )
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    startActivity(Intent(this, LoginActivity::class.java))
                    finish()
                } else {
                    Toast.makeText(
                        baseContext, "Registration failed. Try again",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

    }


}
