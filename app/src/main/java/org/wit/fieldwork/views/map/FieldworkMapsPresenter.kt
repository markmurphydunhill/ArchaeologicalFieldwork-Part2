package org.wit.fieldwork.views.map
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import org.wit.fieldwork.models.FieldworkModel
import org.wit.fieldwork.views.BasePresenter
import org.wit.fieldwork.views.BaseView

class FieldworkMapsPresenter(view: BaseView) : BasePresenter(view) {

    fun doPopulateMap(map: GoogleMap, fieldworks: List<FieldworkModel>) {
        map.uiSettings.setZoomControlsEnabled(true)
        fieldworks.forEach {
            val loc = LatLng(it.lat, it.lng)
            val options = MarkerOptions().title(it.title).position(loc)
            map.addMarker(options).tag = it.id
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(loc, it.zoom))
        }
    }

    fun doMarkerSelected(marker: Marker) {
        val tag = marker.tag as Long
        val fieldwork = app.fieldworks.findById(tag)
        if (fieldwork != null) view?.showFieldwork(fieldwork)

    }

    fun loadFieldworks() {
        view?.showFieldworks(app.fieldworks.findAll())
    }
}
/*
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import org.wit.fieldwork.main.MainApp

class FieldworkMapsPresenter(val view: FieldworkMapsView) {

    var app: MainApp

    init {
        app = view.application as MainApp
    }

    fun doPopulateMap(map: GoogleMap) {
        map.uiSettings.setZoomControlsEnabled(true)
        map.setOnMarkerClickListener(view)
        app.fieldworks.findAll().forEach {
            val loc = LatLng(it.lat, it.lng)
            val options = MarkerOptions().title(it.title).position(loc)
            map.addMarker(options).tag = it.id
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(loc, it.zoom))
        }
    }

    fun doMarkerSelected(marker: Marker) {
        val tag = marker.tag as Long
        val fieldwork = app.fieldworks.findById(tag)
        if (fieldwork != null) view.showFieldwork(fieldwork)
    }
}*/