package org.wit.fieldwork.views.FavouriteList

import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import org.wit.fieldwork.activities.SettingsActivity
import org.wit.fieldwork.models.FieldworkModel
import org.wit.fieldwork.views.BasePresenter
import org.wit.fieldwork.views.BaseView
import org.wit.fieldwork.views.VIEW
import java.util.ArrayList
import org.jetbrains.anko.info
class FavouritePresenter(view: BaseView) : BasePresenter(view), AnkoLogger {

    /* fun doAddFieldwork() {
         view?.navigateTo(VIEW.FIELDWORK)
     }*/

    fun doEditFieldwork(fieldwork: FieldworkModel) {
        view?.navigateTo(VIEW.FIELDWORK, 0, "fieldwork_edit", fieldwork)
    }
/*
    fun doShowFieldworksMap() {
        view?.navigateTo(VIEW.MAPS)
    }*/

    fun loadFavourites() {
        doAsync {
            val allFieldworks = app.fieldworks.findAll()
            val favourites = ArrayList<FieldworkModel>()

            for (fieldwork in allFieldworks) {
                if (fieldwork.favourite == true) {
                    favourites.add(fieldwork)
                    info ("Hello title")
                }

                uiThread {
                    view?.showFavourites(favourites)
                }
            }
        }
    }



}