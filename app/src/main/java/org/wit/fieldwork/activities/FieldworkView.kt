package org.wit.fieldwork.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_fieldwork.*
import kotlinx.android.synthetic.main.activity_fieldwork.fieldworkTitle
import kotlinx.android.synthetic.main.card_fieldwork.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.toast
import org.wit.fieldwork.R
import org.wit.fieldwork.helpers.readImageFromPath
import org.wit.fieldwork.models.FieldworkModel

class FieldworkView : AppCompatActivity(), AnkoLogger {

    lateinit var presenter: FieldworkPresenter
    var fieldwork = FieldworkModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fieldwork)
        toolbarAdd.title = title
        setSupportActionBar(toolbarAdd)

        presenter = FieldworkPresenter(this)

        btnAdd.setOnClickListener {
            if (fieldworkTitle.text.toString().isEmpty()) {
                toast(R.string.enter_fieldwork_title)
            } else {
                presenter.doAddOrSave(fieldworkTitle.text.toString(), fieldworkDescription.text.toString())
            }
        }

       // chooseImage.setOnClickListener { presenter.doSelectImage() }

        chooseImage1.setOnClickListener {
            presenter.doSelectImage()

        }
/*
        chooseImage2.setOnClickListener {
            presenter.doSelectImage()
1
        }

        chooseImage3.setOnClickListener {
            presenter.doSelectImage()

        }

        chooseImage4.setOnClickListener {
            presenter.doSelectImage()

        }*/


       /* placemarkLocation.setOnClickListener { presenter.doSetLocation() }*/

        btnDel.setOnClickListener {
            presenter.doDelete()
        }




    }

    fun showFieldwork(fieldwork: FieldworkModel) {
        fieldworkTitle.setText(fieldwork.title)
        fieldworkDesc.setText(fieldwork.description)

        //checkbox
        fieldworkImage1.setImageBitmap(readImageFromPath(this, fieldwork.image1))
        fieldworkImage2.setImageBitmap(readImageFromPath(this, fieldwork.image2))
        fieldworkImage3.setImageBitmap(readImageFromPath(this, fieldwork.image3))
        fieldworkImage4.setImageBitmap(readImageFromPath(this, fieldwork.image4))
        if (fieldworkImage1 != null) {
            chooseImage1.setText(R.string.update_image1)
        }
        if (fieldworkImage2 != null) {
            chooseImage2.setText(R.string.update_image2)
        }
        if (fieldworkImage3 != null) {
            chooseImage3.setText(R.string.update_image3)
        }
        if (fieldworkImage4 != null) {
            chooseImage4.setText(R.string.update_image4)
        }

        btnAdd.setText(R.string.save_fieldwork)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_fieldwork, menu)
        if (presenter.edit) menu.getItem(0).setVisible(true)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
          /*  R.id.item_delete -> {
                presenter.doDelete()
            }*/
            R.id.item_cancel -> {
                presenter.doCancel()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (data != null) {
            presenter.doActivityResult(requestCode, resultCode, data)
        }
    }
}

/*
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import kotlinx.android.synthetic.main.activity_fieldwork.*
import kotlinx.android.synthetic.main.activity_fieldwork.fieldworkTitle
import kotlinx.android.synthetic.main.card_fieldwork.*
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

class FieldworkView : AppCompatActivity(), AnkoLogger {

    var fieldwork = FieldworkModel()
    val IMAGE1_REQUEST = 1
    val IMAGE2_REQUEST = 3
    val IMAGE3_REQUEST = 4
    val IMAGE4_REQUEST = 5
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
            fieldworkDesc.setText(fieldwork.description)
            //checkBox(fieldwork.visited)
            fieldworkImage1.setImageBitmap(readImageFromPath(this, fieldwork.image1))
            fieldworkImage2.setImageBitmap(readImageFromPath(this, fieldwork.image2))
            fieldworkImage3.setImageBitmap(readImageFromPath(this, fieldwork.image3))
            fieldworkImage4.setImageBitmap(readImageFromPath(this, fieldwork.image4))
            if (fieldworkImage1 != null) {
                chooseImage1.setText(R.string.update_image1)
            }
            if (fieldworkImage2 != null) {
                chooseImage2.setText(R.string.update_image2)
            }
            if (fieldworkImage3 != null) {
                chooseImage3.setText(R.string.update_image3)
            }
            if (fieldworkImage4 != null) {
                chooseImage4.setText(R.string.update_image4)
            }
            btnAdd.setText(R.string.save_fieldwork)
        }

            btnAdd.setOnClickListener() {
            fieldwork.title = fieldworkTitle.text.toString()
            //fieldwork.description = fieldworkDescription.text.toString()

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




        chooseImage1.setOnClickListener {
            showImagePicker(this, IMAGE1_REQUEST)

        }

        chooseImage2.setOnClickListener {
            showImagePicker(this, IMAGE2_REQUEST)

        }

        chooseImage3.setOnClickListener {
            showImagePicker(this, IMAGE3_REQUEST)

        }

        chooseImage4.setOnClickListener {
            showImagePicker(this, IMAGE4_REQUEST)

        }

       btnDel.setOnClickListener {
            app.fieldworks.delete(fieldwork)
            finish()
            info ("Delete button Pressed")
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
                    chooseImage1.setText(R.string.update_image1)
                }
            }
            IMAGE2_REQUEST -> {
                if (data != null) {
                    fieldwork.image2 = data.getData().toString()
                    fieldworkImage2.setImageBitmap(readImage(this, resultCode, data))
                    chooseImage2.setText(R.string.update_image2)
                }
            }
            IMAGE3_REQUEST -> {
                if (data != null) {
                    fieldwork.image3 = data.getData().toString()
                    fieldworkImage3.setImageBitmap(readImage(this, resultCode, data))
                    chooseImage3.setText(R.string.update_image3)
                }
            }
            IMAGE4_REQUEST -> {
                if (data != null) {
                    fieldwork.image4 = data.getData().toString()
                    fieldworkImage4.setImageBitmap(readImage(this, resultCode, data))
                    chooseImage4.setText(R.string.update_image4)
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
*/