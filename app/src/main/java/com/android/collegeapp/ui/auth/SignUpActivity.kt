package com.android.collegeapp.ui.auth

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.android.collegeapp.MainActivity
import com.android.collegeapp.R
import com.android.collegeapp.ui.auth.AuthConstants.FB_USER
import com.android.collegeapp.ui.auth.AuthConstants.FB_USER_EMAIL
import com.android.collegeapp.ui.auth.AuthConstants.FB_USER_NAME
import com.android.collegeapp.ui.auth.AuthConstants.FB_USER_PASSWORD
import com.android.collegeapp.util.toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        init()

        btn_signup.setOnClickListener { validateUser() }
    }

    private fun init() {
        auth = FirebaseAuth.getInstance()
        databaseReference = FirebaseDatabase.getInstance().reference
    }

    private fun createUser(name: String, email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    uploadUserToRTDB(name, email, password)
                } else {
                    toast("${task.exception!!.message}")
                }
            }.addOnFailureListener { toast(it.message!!) }
    }

    private fun uploadUserToRTDB(name: String, email: String, password: String) {
        val dbRef = databaseReference.child(FB_USER)
        val key = dbRef.push().key

        val user: HashMap<String, String> = HashMap()
        user[FB_USER_NAME] = name
        user[FB_USER_EMAIL] = email
        user[FB_USER_PASSWORD] = password

        dbRef.child(key!!).setValue(user)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    openMainActivity()
                } else {
                    toast(task.exception!!.message!!)
                }
            }.addOnFailureListener { toast(it.message!!) }
    }

    private fun validateUser() {
        val name = et_signup_name.text.toString().trim()
        val email = et_signup_email.text.toString().trim()
        val password = et_signup_password.text.toString().trim()

        when {
            name.isEmpty() -> {
                et_signup_name.apply {
                    error = getString(R.string.error_required)
                    requestFocus()
                }
            }
            email.isEmpty() -> {
                et_signup_email.apply {
                    error = getString(R.string.error_required)
                    requestFocus()
                }
            }
            password.isEmpty() -> {
                et_signup_password.apply {
                    error = getString(R.string.error_required)
                    requestFocus()
                }
            }
            else -> {
                createUser(name, email, password)
            }
        }
    }

    private fun openMainActivity() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

}