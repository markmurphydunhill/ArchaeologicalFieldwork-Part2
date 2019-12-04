package org.wit.fieldwork.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.toast
import org.wit.fieldwork.R
import org.wit.fieldwork.main.MainApp
import org.wit.fieldwork.models.UserModel
import kotlinx.android.synthetic.main.activity_login.toolbarAdd
import org.jetbrains.anko.startActivityForResult
import org.wit.fieldwork.views.fieldworkList.FieldworkListView


class LoginActivity : AppCompatActivity(), AnkoLogger {

    lateinit var app: MainApp
    var user = UserModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login)
        info("Placemark Activity started..")
        app = application as MainApp
        toolbarAdd.title = title

        btnSignUp.setOnClickListener() {
            info("SignUp Button Pressed")
            user.email = email.text.toString()
            user.password = password.text.toString()

            if (user.email.isEmpty()) {
                toast(R.string.enter_email)
            } else {

                app.users.createUser(user.copy())
                startActivityForResult<FieldworkListView>(0)

            finish()
        }

        }

       btnLogin.setOnClickListener() {
           info("SignUp Button Pressed")
           user.email = email.text.toString()
           user.password = password.text.toString()

           val userList = app.users.findAllUsers() as ArrayList<UserModel>

           var foundUser: UserModel? = userList.find { p -> p.email == user.email }
           if (foundUser != null && foundUser.password == user.password) {
               startActivityForResult<FieldworkListView>(0)
               info(user)
               finish()
           } else {
               toast(R.string.enter_valid_user)
           }
       }
    }
}