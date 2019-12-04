package org.wit.fieldwork.activities

import android.content.Intent
import org.jetbrains.anko.intentFor
import org.wit.fieldwork.R
import org.wit.fieldwork.helpers.readImage
import org.wit.fieldwork.helpers.showImagePicker
import org.wit.fieldwork.main.MainApp
import org.wit.fieldwork.models.FieldworkModel
import org.wit.fieldwork.models.Location

class FieldworkPresenter(val view: FieldworkView) {

    val IMAGE_REQUEST = 1
    val IMAGE2_REQUEST = 3
    val IMAGE3_REQUEST = 4
    val IMAGE4_REQUEST = 5
    val LOCATION_REQUEST = 2

    var fieldwork = FieldworkModel()
    var location = Location(52.245696, -7.139102, 15f)
    var app: MainApp
    var edit = false;

    init {
        app = view.application as MainApp
        if (view.intent.hasExtra("fieldwork_edit")) {
            edit = true
            fieldwork = view.intent.extras?.getParcelable<FieldworkModel>("fieldwork_edit")!!
            view.showFieldwork(fieldwork)
        }
    }

    fun doAddOrSave(title: String, description: String) {
        fieldwork.title = title
        fieldwork.description = description
        if (edit) {
            app.fieldworks.update(fieldwork)
        } else {
            app.fieldworks.create(fieldwork)
        }
        view.finish()
    }

    fun doCancel() {
        view.finish()
    }

    fun doDelete() {
        app.fieldworks.delete(fieldwork)
        view.finish()
    }

    fun doSelectImage() {
        showImagePicker(view, IMAGE_REQUEST)
    }

   /* fun doSetLocation() {
        if (fieldwork.zoom != 0f) {
            location.lat = fieldwork.lat
            location.lng = fieldwork.lng
            location.zoom = fieldwork.zoom
        }
        view.startActivityForResult(view.intentFor<EditLocationView>().putExtra("location", location), LOCATION_REQUEST)
    }*/

    fun doActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        when (requestCode) {
            IMAGE_REQUEST -> {
                fieldwork.image1 = data.data.toString()
                view.showFieldwork(fieldwork)
            }
            LOCATION_REQUEST -> {
                location = data.extras?.getParcelable<Location>("location")!!
                fieldwork.lat = location.lat
                fieldwork.lng = location.lng
                fieldwork.zoom = location.zoom
            }
        }
    }
}