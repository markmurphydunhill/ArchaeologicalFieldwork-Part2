package org.wit.fieldwork.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_fieldwork_list.*
import org.wit.fieldwork.R

import kotlinx.android.synthetic.main.activity_splash.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.startActivityForResult
import org.wit.fieldwork.main.MainApp
import java.lang.Exception

class SplashActivity : AppCompatActivity(), AnkoLogger {
    lateinit var app: MainApp
    private val splashTime = 6000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        setSupportActionBar(toolbar)

           Handler().postDelayed({
               startActivityForResult<LoginActivity>(0)
            finish()
        },splashTime.toLong())


    }
    }
