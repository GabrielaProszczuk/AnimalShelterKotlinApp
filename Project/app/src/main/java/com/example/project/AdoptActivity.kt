package com.example.project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import org.w3c.dom.Text

class AdoptActivity : AppCompatActivity() {
    internal lateinit var buttonPrevious: TextView
    internal lateinit var thankyou: TextView
    internal lateinit var firstname: EditText
    internal lateinit var surname: EditText
    internal lateinit var phone: EditText
    internal lateinit var email: EditText
    internal lateinit var application: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adopt)

        firstname = findViewById(R.id.editTextTextName)
        surname = findViewById(R.id.editTextSurname)
        phone = findViewById(R.id.editTextPhone)
        email = findViewById(R.id.editTextTextEmailAddress)
        application = findViewById(R.id.editTextTextMultiLine)

        thankyou = findViewById(R.id.thanksTextView)
        buttonPrevious = findViewById(R.id.previousButton)
        buttonPrevious.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                thankyou.text = ""
                finish()
            }
        })
    }
    fun send(view: View){
        if(firstname.text.toString().trim().isNotEmpty() && surname.text.toString().trim().isNotEmpty() &&
            phone.text.toString().trim().isNotEmpty() && email.text.toString().trim().isNotEmpty() &&
            application.text.toString().trim().isNotEmpty() ){
            thankyou.text = "Thank you for your application, we will contact you!"
        }else{
            thankyou.text = "Please, fill all credentials to send an application."
        }

    }
}