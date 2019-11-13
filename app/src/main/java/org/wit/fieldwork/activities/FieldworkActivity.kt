package org.wit.fieldwork.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_fieldwork.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.toast
import org.wit.fieldwork.R
import org.wit.fieldwork.models.FieldworkModel
import org.wit.fieldwork.main.MainApp

class FieldworkActivity : AppCompatActivity(), AnkoLogger {

    var fieldwork = FieldworkModel()
    lateinit var app: MainApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fieldwork)
        app = application as MainApp
       info("Fieldwork Activity started..")

        btnAdd.setOnClickListener() {
            fieldwork.title = fieldworkTitle.text.toString()
            fieldwork.description = fieldworkDescription.text.toString()
            if (fieldwork.title.isNotEmpty()) {
                app.fieldworks.add(fieldwork.copy())
                info("add Button Pressed: ${fieldwork}")
                for (i in app.fieldworks.indices) {
                    info("Fieldwork[$i]:${app.fieldworks[i]}")
                }
                setResult(AppCompatActivity.RESULT_OK)
                finish()
            }
            else {
                toast ("Please Enter a title")
            }
        }

    }
}
