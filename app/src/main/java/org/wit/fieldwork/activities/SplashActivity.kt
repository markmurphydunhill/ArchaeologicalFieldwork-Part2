package org.wit.fieldwork.activities

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_fieldwork_list.*
import org.wit.fieldwork.R
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.startActivityForResult
import org.wit.fieldwork.main.MainApp
import org.wit.fieldwork.views.login.LoginView


class SplashActivity : AppCompatActivity(), AnkoLogger {
    lateinit var app: MainApp
    private val splashTime = 4000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        setSupportActionBar(toolbar)

           Handler().postDelayed({
               startActivityForResult<LoginView>(0)
            finish()
        },splashTime.toLong())


    }
    }
