/*package org.wit.fieldwork.models


import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.toast
import org.wit.fieldwork.helpers.*
import java.util.*

private val JSON_FILE = "users.json"
private val gsonBuilder = GsonBuilder().setPrettyPrinting().create()
private val listType = object : TypeToken<java.util.ArrayList<UserModel>>() {}.type



class UserJSONStore : UserStore, AnkoLogger {

    val context: Context
    var users = mutableListOf<UserModel>()



    constructor (context: Context) {
        this.context = context
        if (exists(context, JSON_FILE)) {
            deserialize()
        }
    }

    override fun createUser(user: UserModel) {

        users.add(user)
        info(user)
        serialize()
    }

    override fun findAllUsers(): MutableList<UserModel>{
        return users
    }

    private fun serialize() {
        val jsonString = gsonBuilder.toJson(users, listType)
        write(context, JSON_FILE, jsonString)
    }

    private fun deserialize() {
        val jsonString = read(context, JSON_FILE)
        users = Gson().fromJson(jsonString, listType)
    }
}*/