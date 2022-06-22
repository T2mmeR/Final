package com.example.midtermapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.example.midtermapplication.DashboardActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        auth = FirebaseAuth.getInstance()

        signUpButton.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
            finish()
        }

        loginButtom.setOnClickListener {
            doLogin()
        }


    }

    private fun doLogin() {

        if (emailEditTextLogin.text.toString().isEmpty()) {
            emailEditTextLogin.error = "Please enter email"
            emailEditTextLogin.requestFocus()
            return
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(emailEditTextLogin.text.toString()).matches()) {
            emailEditTextLogin.error = "Please enter valid email"
            emailEditTextLogin.requestFocus()
            return
        }

        if (passwordEditTextLogin.text.toString().isEmpty()) {
            passwordEditTextLogin.error = "Please enter password"
            return
        }

        auth.signInWithEmailAndPassword(
            emailEditTextLogin.text.toString(),
            passwordEditTextLogin.text.toString()
        )
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    Toast.makeText(
                        baseContext, "Login failed. Make sure your email and password is correct",
                        Toast.LENGTH_SHORT
                    ).show()
                    updateUI(null)
                }
            }

    }


    public override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        updateUI(currentUser)
    }


    private fun updateUI(currentUser: FirebaseUser?) {
        if (currentUser != null) {
            startActivity(Intent(this, DashboardActivity::class.java))
            Toast.makeText(
                baseContext, "Login Successfull",
                Toast.LENGTH_SHORT
            ).show()
        } else {
            Toast.makeText(
                baseContext, "Login failed. Make sure your email and password is correct",
                Toast.LENGTH_SHORT
            ).show()
        }
    }


}
