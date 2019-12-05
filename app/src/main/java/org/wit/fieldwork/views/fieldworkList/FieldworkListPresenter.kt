package org.wit.fieldwork.views.fieldworkList


import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import org.wit.fieldwork.activities.SettingsActivity
import org.wit.fieldwork.models.FieldworkModel
import org.wit.fieldwork.views.BasePresenter
import org.wit.fieldwork.views.BaseView
import org.wit.fieldwork.views.VIEW

class FieldworkListPresenter(view: BaseView) : BasePresenter(view) {

    fun doAddFieldwork() {
        view?.navigateTo(VIEW.FIELDWORK)
    }

    fun doEditFieldwork(fieldwork: FieldworkModel) {
        view?.navigateTo(VIEW.FIELDWORK, 0, "fieldwork_edit", fieldwork)
    }

    fun doShowFieldworksMap() {
        view?.navigateTo(VIEW.MAPS)
    }

    fun loadFieldworks() {
        doAsync {
            val fieldworks = app.fieldworks.findAll()
            uiThread {
                view?.showFieldworks(fieldworks)
            }
        }
    }

    fun doLogout(){
        view?.finish()
    }

    fun doSettings(){
        view?.navigateTo(VIEW.SETTINGS)
    }
}
/*
import org.jetbrains.anko.intentFor
import org.wit.fieldwork.models.FieldworkModel

import org.jetbrains.anko.startActivity
import org.jetbrains.anko.startActivityForResult
import org.wit.fieldwork.views.map.FieldworkMapsView
import org.wit.fieldwork.activities.SettingsActivity
import org.wit.fieldwork.main.MainApp
import org.wit.fieldwork.views.fieldwork.FieldworkView


class FieldworkListPresenter(val view: FieldworkListView) {

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
        view.startActivity<FieldworkMapsView>()
    }

    fun doLogout(){
        view.finish()
    }

    fun doSettings(){
        view.startActivityForResult<SettingsActivity>(0)
    }
}*/