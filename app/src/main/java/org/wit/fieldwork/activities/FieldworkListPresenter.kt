package org.wit.fieldwork.activities

import androidx.core.app.ActivityCompat.startActivityForResult
import org.jetbrains.anko.intentFor
import org.wit.fieldwork.models.FieldworkModel

import org.jetbrains.anko.intentFor
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.startActivityForResult
import org.wit.fieldwork.main.MainApp


class FieldworkListPresenter(val view: FieldworkListActivity) {

    var app: MainApp

    init {
        app = view.application as MainApp
    }

    fun getFieldworks() = app.fieldworks.findAll()

    fun doAddFieldwork() {
        view.startActivityForResult<FieldworkView>(0)
    }

    fun doEditFieldwork(fieldwork: FieldworkModel) {
        view.startActivityForResult(view.intentFor<FieldworkView>().putExtra("fieldwork_edit", fieldwork), 0)
    }

    fun doShowFieldworksMap() {
        view.startActivity<FieldworkMapsActivity>()
    }

    fun doLogout(){
        view.finish()
    }

    fun doSettings(){
        view.startActivityForResult<SettingsActivity>(0)
    }
}