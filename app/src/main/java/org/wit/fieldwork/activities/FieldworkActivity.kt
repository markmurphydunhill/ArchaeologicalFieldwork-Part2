package org.wit.fieldwork.activities



import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_fieldwork.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.toast
import org.wit.fieldwork.R
import org.wit.fieldwork.models.FieldworkModel
import org.wit.fieldwork.main.MainApp
import mu.KotlinLogging
import org.wit.fieldwork.models.FieldworkJSONStore

class FieldworkActivity : AppCompatActivity(), AnkoLogger {

    var fieldwork = FieldworkModel()
   // val fieldwork = FieldworkJSONStore
    //val logger = KotlinLogging.logger {}

    lateinit var app: MainApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fieldwork)
        app = application as MainApp

        toolbarAdd.title = title
        setSupportActionBar(toolbarAdd)
       info("Fieldwork Activity started..")

        if (intent.hasExtra("fieldwork_edit")) {
            fieldwork = intent.extras?.getParcelable<FieldworkModel>("fieldwork_edit")!!
            fieldworkTitle.setText(fieldwork.title)
            fieldworkDescription.setText(fieldwork.description)
        }

        btnAdd.setOnClickListener() {
            fieldwork.title = fieldworkTitle.text.toString()
            fieldwork.description = fieldworkDescription.text.toString()
            if (fieldwork.title.isNotEmpty()) {
                app.fieldworks.create(fieldwork.copy())
                info("add Button Pressed: ${fieldwork}")
             /*   for (i in app.fieldworks.indices) {
                    info("Fieldwork[$i]:${app.fieldworks[i]}")
                }*/
                setResult(AppCompatActivity.RESULT_OK)
                finish()
            }
            else {
                toast ("Please Enter a title")
            }
        }

    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_fieldwork, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.item_cancel -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

}
