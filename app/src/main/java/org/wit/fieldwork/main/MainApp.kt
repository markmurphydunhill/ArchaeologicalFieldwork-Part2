package org.wit.fieldwork.main

import android.app.Application
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.wit.fieldwork.models.FieldworkStore
import org.wit.fieldwork.models.FieldworkJSONStore
import org.wit.fieldwork.models.FieldworkMemStore


class MainApp : Application(), AnkoLogger {

    lateinit var fieldworks: FieldworkStore

    override fun onCreate() {
        super.onCreate()
        fieldworks = FieldworkJSONStore(applicationContext)
        info("Placemark started")
    }
}