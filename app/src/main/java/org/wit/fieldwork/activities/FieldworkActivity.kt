package org.wit.fieldwork.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_fieldwork.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.toast
import org.wit.fieldwork.R
import org.wit.fieldwork.models.FieldworkModel

class FieldworkActivity : AppCompatActivity(), AnkoLogger {

    var fieldwork = FieldworkModel()
    val fieldworks = ArrayList<FieldworkModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fieldwork)
       info("Fieldwork Activity started..")

        btnAdd.setOnClickListener() {
            fieldwork.title = fieldworkTitle.text.toString()
            if (fieldwork.title.isNotEmpty()) {
                fieldworks.add(fieldwork.copy())
                info("add Button Pressed: ${fieldwork}")
                for (i in fieldworks.indices) {
                    info("Fieldowrk[$i]:${this.fieldworks[i]}")
                }
            }
            else {
                toast ("Please Enter a title")
            }
        }

    }
}
