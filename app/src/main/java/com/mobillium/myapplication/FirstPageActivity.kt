package com.mobillium.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class FirstPageActivity : AppCompatActivity() {
    private  val SplashTime:Long =3000
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first_page)
        Handler().postDelayed( {
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        },SplashTime)

    }
}