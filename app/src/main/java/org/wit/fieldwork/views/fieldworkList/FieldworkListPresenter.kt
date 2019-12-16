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

    fun doLoadFavourites(){
        view?.navigateTo(VIEW.FAVOURITE)

    }

    fun doLogout(){
        view?.navigateTo(VIEW.LOGIN)
    }

    fun doSettings(){
        view?.navigateTo(VIEW.SETTINGS)
    }
}
