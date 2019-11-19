package org.wit.fieldwork.activities



import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.CheckBox
import kotlinx.android.synthetic.main.activity_fieldwork.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.toast
import org.wit.fieldwork.R
import org.wit.fieldwork.models.FieldworkModel
import org.wit.fieldwork.main.MainApp
import org.jetbrains.anko.intentFor
import org.wit.fieldwork.helpers.readImage
import org.wit.fieldwork.helpers.readImageFromPath
import org.wit.fieldwork.helpers.showImagePicker
import org.wit.fieldwork.models.Location

class FieldworkActivity : AppCompatActivity(), AnkoLogger {

    var fieldwork = FieldworkModel()
    val IMAGE1_REQUEST = 1
    val IMAGE2_REQUEST = 3
    val LOCATION_REQUEST = 2

    lateinit var app: MainApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fieldwork)
        app = application as MainApp

        toolbarAdd.title = title
        setSupportActionBar(toolbarAdd)
       info("Fieldwork Activity started..")

        var edit = false


        if (intent.hasExtra("fieldwork_edit")) {
            edit = true

            fieldwork = intent.extras?.getParcelable<FieldworkModel>("fieldwork_edit")!!
            fieldworkTitle.setText(fieldwork.title)
            fieldworkDescription.setText(fieldwork.description)
            //checkBox.(fieldwork.visited)
            fieldworkImage1.setImageBitmap(readImageFromPath(this, fieldwork.image1))
            fieldworkImage2.setImageBitmap(readImageFromPath(this, fieldwork.image2)
            )
            if (fieldworkImage1 != null) {
                chooseImage1.setText(R.string.update_image1)
            }
            if (fieldworkImage2 != null) {
                chooseImage2.setText(R.string.update_image2)
            }
            btnAdd.setText(R.string.save_fieldwork)
        }

            btnAdd.setOnClickListener() {
            fieldwork.title = fieldworkTitle.text.toString()
            fieldwork.description = fieldworkDescription.text.toString()
            // if (fieldwork.title.isNotEmpty()) {
            //   app.fieldworks.create(fieldwork.copy())
            // info("add Button Pressed: ${fieldwork}")
            if (fieldwork.title.isEmpty()) {
                  toast(R.string.enter_fieldwork_title)
            } else {
                if (edit) {
                    app.fieldworks.update(fieldwork.copy())

                } else {
                    app.fieldworks.create(fieldwork.copy())
                }
            }
            // info("add Button Pressed: $fieldmarkTitle")
            setResult(AppCompatActivity.RESULT_OK)
            finish()
            }

        placemarkLocation.setOnClickListener {
            val location = Location(52.245696, -7.139102, 15f)
            if (fieldwork.zoom != 0f) {
                location.lat =  fieldwork.lat
                location.lng = fieldwork.lng
                location.zoom = fieldwork.zoom
            }
            startActivityForResult(intentFor<MapActivity>().putExtra("location", location), LOCATION_REQUEST)
        }

       /* btnDel.setOnClickListener {isChecked-->
            info ("Delete button Pressed")
        }*/


        chooseImage1.setOnClickListener {
            showImagePicker(this, IMAGE1_REQUEST)

        }

        chooseImage2.setOnClickListener {
            showImagePicker(this, IMAGE2_REQUEST)

        }


        checkBox.setOnClickListener(View.OnClickListener {


            if (checkBox.isChecked) {
                info ("Check Box Checked")
                fieldwork.visited = true
            } else {
                info ("Check Box is not Checked")
                fieldwork.visited = false
            }
        })



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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            IMAGE1_REQUEST -> {
                if (data != null) {
                    fieldwork.image1 = data.getData().toString()
                    fieldworkImage1.setImageBitmap(readImage(this, resultCode, data))
                    chooseImage1.setText(R.string.change_fieldwork_image)
                }
            }
            IMAGE2_REQUEST -> {
                if (data != null) {
                    fieldwork.image2 = data.getData().toString()
                    fieldworkImage2.setImageBitmap(readImage(this, resultCode, data))
                    chooseImage2.setText(R.string.change_fieldwork_image)
                }
            }
            LOCATION_REQUEST -> {
                if (data != null) {
                    val location = data.extras?.getParcelable<Location>("location")!!
                    fieldwork.lat = location.lat
                    fieldwork.lng = location.lng
                    fieldwork.zoom = location.zoom
                }
            }
        }
    }

}
