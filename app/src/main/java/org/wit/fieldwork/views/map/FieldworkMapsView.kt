package org.wit.fieldwork.views.map

import android.os.Bundle
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.Marker
import org.wit.fieldwork.R
import kotlinx.android.synthetic.main.activity_fieldwork_maps.*
import kotlinx.android.synthetic.main.content_fieldwork_maps.*
import org.wit.fieldwork.helpers.readImageFromPath
import org.wit.fieldwork.models.FieldworkModel
import org.wit.fieldwork.views.BaseView

class FieldworkMapsView : BaseView(), GoogleMap.OnMarkerClickListener {

    lateinit var presenter: FieldworkMapsPresenter
    lateinit var map : GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fieldwork_maps)
        super.init(toolbar)

        presenter = initPresenter (FieldworkMapsPresenter(this)) as FieldworkMapsPresenter

        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync {
            map = it
            map.setOnMarkerClickListener(this)
            presenter.loadFieldworks()
        }
    }

    override fun showFieldwork(fieldwork: FieldworkModel) {
        currentTitle.text = fieldwork.title
        currentDescription.text = fieldwork.description
        currentImage.setImageBitmap(readImageFromPath(this, fieldwork.image1))
    }

    override fun showFieldworks(fieldworks: List<FieldworkModel>) {
        presenter.doPopulateMap(map, fieldworks)
    }

    override fun onMarkerClick(marker: Marker): Boolean {
        presenter.doMarkerSelected(marker)
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mapView.onSaveInstanceState(outState)
    }
}
/*
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.Marker
import org.wit.fieldwork.R
import kotlinx.android.synthetic.main.activity_fieldwork_maps.*
import kotlinx.android.synthetic.main.content_fieldwork_maps.*
import org.wit.fieldwork.helpers.readImageFromPath
import org.wit.fieldwork.models.FieldworkModel
import org.wit.fieldwork.views.map.FieldworkMapsPresenter


class FieldworkMapsView : AppCompatActivity(), GoogleMap.OnMarkerClickListener {

    lateinit var presenter: FieldworkMapsPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fieldwork_maps)
        setSupportActionBar(toolbar)
        presenter = FieldworkMapsPresenter(this)

        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync {
            presenter.doPopulateMap(it)
        }
    }

    fun showFieldwork(fieldwork: FieldworkModel) {
        currentTitle.text = fieldwork.title
        currentDescription.text = fieldwork.description
        currentImage.setImageBitmap(readImageFromPath(this, fieldwork.image1))

    }

    override fun onMarkerClick(marker: Marker): Boolean {
        presenter.doMarkerSelected(marker)
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mapView.onSaveInstanceState(outState)
    }
}

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import org.wit.fieldwork.R
import kotlinx.android.synthetic.main.activity_fieldwork_maps.*
import kotlinx.android.synthetic.main.card_fieldwork.*
import kotlinx.android.synthetic.main.content_fieldwork_maps.*
import org.wit.fieldwork.helpers.readImageFromPath
import org.wit.fieldwork.main.MainApp

class FieldworkMapsView : AppCompatActivity(), GoogleMap.OnMarkerClickListener {

    lateinit var map: GoogleMap
    lateinit var app: MainApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fieldwork_maps)
        setSupportActionBar(toolbar)
        app = application as MainApp
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync {
            map = it
            configureMap()
        }
    }

    fun configureMap() {
        map.uiSettings.setZoomControlsEnabled(true)
        app.fieldworks.findAll().forEach {
            val loc = LatLng(it.lat, it.lng)
            val options = MarkerOptions().title(it.title).position(loc)
            map.addMarker(options).tag = it.id
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(loc, it.zoom))
            map.setOnMarkerClickListener(this)
        }
    }

    override fun onMarkerClick(marker: Marker): Boolean {
        val tag = marker.tag as Long
        val fieldwork = app.fieldworks.findById(tag)
        currentTitle.text = fieldwork!!.title
        currentDescription.text = fieldwork!!.description
        currentImage.setImageBitmap(readImageFromPath(this, fieldwork.image1))
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mapView.onSaveInstanceState(outState)
    }
}
*/