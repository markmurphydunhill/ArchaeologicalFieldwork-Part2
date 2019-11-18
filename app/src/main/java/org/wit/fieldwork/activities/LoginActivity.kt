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
import android.content.Intent
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class
import org.jetbrains.anko.startActivityForResult


class LoginActivity : AppCompatActivity(), AnkoLogger {

    lateinit var app: MainApp
    var user = UserModel()
  //  var homer = ("homer", "secret")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login)
        info("Placemark Activity started..")
        app = application as MainApp
        user.email = "homer"
        user.password = "secret"
        app.users.createUser(user.copy())


        btnLogin.setOnClickListener() {
            info("Login Button Pressed")
            startActivityForResult<FieldworkListActivity>(0)




          /*  user.email = email.text.toString()
            user.password = password.text.toString()

            if (user.email.isEmpty()) {
                toast(R.string.enter_fieldwork_title)
            } else {

                    app.fieldworks.login(user.copy())



            // info("add Button Pressed: $fieldmarkTitle")
            //setResult(AppCompatActivity.RESULT_OK)
            //finish()
        }*/

        }
    }
}