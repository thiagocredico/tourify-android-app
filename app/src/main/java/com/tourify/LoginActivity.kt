package com.tourify

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var usernameEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var rememberMeCheckBox: CheckBox
    private lateinit var loginButton: Button
    private lateinit var signupTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        usernameEditText = findViewById(R.id.username_edit_text)
        passwordEditText = findViewById(R.id.password_edit_text)
        rememberMeCheckBox = findViewById(R.id.remember_me_check_box)
        loginButton = findViewById(R.id.login_button)
        signupTextView = findViewById(R.id.signup_text_view)


        // Load the saved username and password
        usernameEditText.setText(FileHandler.readData("username", this))
        passwordEditText.setText(FileHandler.readData("password", this))
        rememberMeCheckBox.isChecked = FileHandler.readData("rememberMe", this).toBoolean()


        // Click listener for Login button
        loginButton.setOnClickListener {
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()

            if (validateCredentials(username, password)) {
                if(rememberMeCheckBox.isChecked){
                    FileHandler.writeData("username", username, this)
                    FileHandler.writeData("password", password, this)
                    FileHandler.writeData("rememberMe", "true", this)
                } else {
                    FileHandler.writeData("username", "", this)
                    FileHandler.writeData("password", "", this)
                    FileHandler.writeData("rememberMe", "false", this)
                }

                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }


            // Click listener for Sign Up button
            signupTextView.setOnClickListener {
                startActivity(Intent(this, SignupActivity::class.java))
            }
        }
    }

    // Function to validate the username and password
    private fun validateCredentials(username: String, password: String): Boolean {
        if(username.isEmpty() || password.isEmpty()){
            Toast.makeText(this, "Missing Information", Toast.LENGTH_SHORT).show()
            return false
        }

        return true
    }
}