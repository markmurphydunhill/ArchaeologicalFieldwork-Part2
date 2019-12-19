package org.wit.fieldwork.main

import android.app.Application
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.wit.fieldwork.models.FieldworkStore
//import org.wit.fieldwork.models.FieldworkJSONStore
//import org.wit.fieldwork.models.FieldworkMemStore
//import org.wit.fieldwork.models.UserJSONStore
//import org.wit.fieldwork.models.UserStore
import org.wit.fieldwork.models.firebase.FieldworkFireStore


class MainApp : Application(), AnkoLogger {

    lateinit var fieldworks: FieldworkStore
    //lateinit var users: UserStore

    override fun onCreate() {
        super.onCreate()
        //fieldworks = FieldworkStoreRoom(applicationContext)
        //users = UserJSONStore(applicationContext)
        fieldworks = FieldworkFireStore(applicationContext)
        info("Fieldwork started")
    }
}