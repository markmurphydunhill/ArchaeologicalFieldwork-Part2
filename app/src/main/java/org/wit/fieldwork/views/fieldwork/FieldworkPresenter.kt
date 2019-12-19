package org.wit.fieldwork.views.fieldwork

import android.annotation.SuppressLint
import android.content.Intent
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_fieldwork.*
import org.jetbrains.anko.*


import org.wit.fieldwork.helpers.showImagePicker
import org.wit.fieldwork.models.Location
import org.wit.fieldwork.models.FieldworkModel
import org.wit.fieldwork.views.*
import org.wit.fieldwork.R
import org.wit.fieldwork.helpers.checkLocationPermissions
import org.wit.fieldwork.helpers.createDefaultLocationRequest
import org.wit.fieldwork.helpers.isPermissionGranted
//import org.wit.fieldwork.models.location
import org.wit.fieldwork.views.BasePresenter
import org.wit.fieldwork.views.BaseView

class FieldworkPresenter(view: BaseView) : BasePresenter(view), AnkoLogger {

    var fieldwork = FieldworkModel()
    var defaultLocation = Location(52.245696, -7.139102, 15f)
    var edit = false;
    var map: GoogleMap? = null
    var locationService: FusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(view)
    val locationRequest = createDefaultLocationRequest()
    init {
        if (view.intent.hasExtra("fieldwork_edit")) {
            edit = true
            fieldwork = view.intent.extras?.getParcelable<FieldworkModel>("fieldwork_edit")!!
            view.showFieldwork(fieldwork)
        } else {
            // fieldwork.lat = defaultLocation.lat
            //fieldwork.lng = defaultLocation.lng
           /* if (checkLocationPermissions(view)) {
                doSetCurrentLocation()
            }*/
        }
    }

    /*@SuppressLint("MissingPermission")
    fun doSetCurrentLocation() {
        locationService.lastLocation.addOnSuccessListener {
            locationUpdate(Location(it.latitude, it.longitude))
        }
    }


    override fun doRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        if (isPermissionGranted(requestCode, grantResults)) {
            doSetCurrentLocation()
        } else {
            locationUpdate(defaultLocation)
        }
    }

    fun doConfigureMap(m: GoogleMap) {
        map = m
        locationUpdate(fieldwork.location)
    }*/

    fun locationUpdate(location:Location) {
        // fieldwork.lat = lat
        // fieldwork.lng = lng
        // fieldwork.zoom = 15f
        fieldwork.location = location
        fieldwork.location.zoom = 15f
        map?.clear()
        map?.uiSettings?.setZoomControlsEnabled(true)
        val options = MarkerOptions().title(fieldwork.title).position(LatLng(fieldwork.location.lat, fieldwork.location.lng))
        map?.addMarker(options)
        map?.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(fieldwork.location.lat, fieldwork.location.lng), fieldwork.location.zoom))
        view?.showFieldwork(fieldwork)
    }

   /* @SuppressLint("MissingPermission")
    fun doResartLocationUpdates() {
        var locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult?) {
                if (locationResult != null && locationResult.locations != null) {
                    val l = locationResult.locations.last()
                    locationUpdate(Location(l.latitude, l.longitude))
                }
            }
        }
        if (!edit) {
            locationService.requestLocationUpdates(locationRequest, locationCallback, null)
        }
    }
*/


    fun doAddOrSave(title: String, description: String) {
        fieldwork.title = title
        fieldwork.description = description
        doAsync {
            if (edit) {
                app.fieldworks.update(fieldwork)
            } else {
                app.fieldworks.create(fieldwork)
            }
            uiThread {
                view?.finish()
            }
        }
    }

    fun doCancel() {
        view?.finish()
    }

    fun doDelete() {
        doAsync {
            app.fieldworks.delete(fieldwork)
            uiThread {
                view?.finish()
            }
        }
    }

    fun doSelectImage1() {
        view?.let {
            showImagePicker(view!!, IMAGE1_REQUEST)
        }
    }
    fun doSelectImage2() {
        view?.let {
            showImagePicker(view!!, IMAGE2_REQUEST)
        }
    }
    fun doSelectImage3() {
        view?.let {
            showImagePicker(view!!, IMAGE3_REQUEST)
        }
    }
    fun doSelectImage4() {
        view?.let {
            showImagePicker(view!!, IMAGE4_REQUEST)
        }
    }

    fun doSetLocation() {
        view?.navigateTo(VIEW.LOCATION, LOCATION_REQUEST, "location", Location(fieldwork.location.lat, fieldwork.location.lng, fieldwork.location.zoom))
    }

    override fun doActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        when (requestCode) {
            IMAGE1_REQUEST -> {
                fieldwork.image1 = data.data.toString()
                view?.showFieldwork(fieldwork)
            }
            IMAGE2_REQUEST -> {
                fieldwork.image2 = data.data.toString()
                view?.showFieldwork(fieldwork)
            }
            IMAGE3_REQUEST -> {
                fieldwork.image3 = data.data.toString()
                view?.showFieldwork(fieldwork)
            }
            IMAGE4_REQUEST -> {
                fieldwork.image4 = data.data.toString()
                view?.showFieldwork(fieldwork)
            }
            LOCATION_REQUEST -> {
                val location = data.extras?.getParcelable<Location>("location")!!
                //fieldwork.lat = location.lat
                //fieldwork.lng = location.lng
                //fieldwork.zoom = location.zoom
                fieldwork.location = location
                locationUpdate(location)
            }
        }
    }







}


/*package org.wit.fieldwork.views.fieldwork

import android.annotation.SuppressLint
import android.content.Intent
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_fieldwork.*
import org.jetbrains.anko.*


import org.wit.fieldwork.helpers.showImagePicker
import org.wit.fieldwork.models.Location
import org.wit.fieldwork.models.FieldworkModel
import org.wit.fieldwork.views.*
import org.wit.fieldwork.R
import org.wit.fieldwork.helpers.checkLocationPermissions
import org.wit.fieldwork.helpers.createDefaultLocationRequest
import org.wit.fieldwork.helpers.isPermissionGranted
import org.wit.fieldwork.models.location
import org.wit.fieldwork.views.BasePresenter
import org.wit.fieldwork.views.BaseView

class FieldworkPresenter(view: BaseView) : BasePresenter(view), AnkoLogger {

    var fieldwork = FieldworkModel()
    var defaultLocation = Location(52.245696, -7.139102, 15f)
    var edit = false;
    var map: GoogleMap? = null
    var locationService: FusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(view)
    val locationRequest = createDefaultLocationRequest()
    init {
        if (view.intent.hasExtra("fieldwork_edit")) {
            edit = true
            fieldwork = view.intent.extras?.getParcelable<FieldworkModel>("fieldwork_edit")!!
            view.showFieldwork(fieldwork)
        } else {
            // fieldwork.lat = defaultLocation.lat
            //fieldwork.lng = defaultLocation.lng
            if (checkLocationPermissions(view)) {
                doSetCurrentLocation()
            }
        }
    }



    fun doAddOrSave(title: String, description: String) {
        fieldwork.title = title
        fieldwork.description = description
        doAsync {
            if (edit) {
                app.fieldworks.update(fieldwork)
            } else {
                app.fieldworks.create(fieldwork)
            }
            uiThread {
                view?.finish()
            }
        }
    }

    fun doCancel() {
        view?.finish()
    }

    fun doDelete() {
        doAsync {
            app.fieldworks.delete(fieldwork)
            uiThread {
                view?.finish()
            }
        }
    }

    fun doSelectImage1() {
        view?.let {
            showImagePicker(view!!, IMAGE1_REQUEST)
        }
    }
    fun doSelectImage2() {
        view?.let {
            showImagePicker(view!!, IMAGE2_REQUEST)
        }
    }
    fun doSelectImage3() {
        view?.let {
            showImagePicker(view!!, IMAGE3_REQUEST)
        }
    }
    fun doSelectImage4() {
        view?.let {
            showImagePicker(view!!, IMAGE4_REQUEST)
        }
    }

    fun doSetLocation() {
        view?.navigateTo(VIEW.LOCATION, LOCATION_REQUEST, "location", Location(fieldwork.location.lat, fieldwork.location.lng, fieldwork.location.zoom))
    }

    override fun doActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        when (requestCode) {
            IMAGE1_REQUEST -> {
                fieldwork.image1 = data.data.toString()
                view?.showFieldwork(fieldwork)
            }
            IMAGE2_REQUEST -> {
                fieldwork.image2 = data.data.toString()
                view?.showFieldwork(fieldwork)
            }
            IMAGE3_REQUEST -> {
                fieldwork.image3 = data.data.toString()
                view?.showFieldwork(fieldwork)
            }
            IMAGE4_REQUEST -> {
                fieldwork.image4 = data.data.toString()
                view?.showFieldwork(fieldwork)
            }
            LOCATION_REQUEST -> {
                val location = data.extras?.getParcelable<Location>("location")!!
                //fieldwork.lat = location.lat
                //fieldwork.lng = location.lng
                //fieldwork.zoom = location.zoom
                fieldwork.location = location
                locationUpdate(location)
            }
        }
    }

    fun doConfigureMap(m: GoogleMap) {
        map = m
        locationUpdate(fieldwork.location)
    }



    fun locationUpdate(location:Location) {
       // fieldwork.lat = lat
       // fieldwork.lng = lng
       // fieldwork.zoom = 15f
        fieldwork.location = location
        fieldwork.location.zoom = 15f
        map?.clear()
        map?.uiSettings?.setZoomControlsEnabled(true)
        val options = MarkerOptions().title(fieldwork.title).position(LatLng(fieldwork.location.lat, fieldwork.location.lng))
        map?.addMarker(options)
        map?.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(fieldwork.location.lat, fieldwork.location.lng), fieldwork.location.zoom))
        view?.showFieldwork(fieldwork)
    }


    override fun doRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        if (isPermissionGranted(requestCode, grantResults)) {
            doSetCurrentLocation()
        } else {
            locationUpdate(defaultLocation)
        }
    }


    @SuppressLint("MissingPermission")
    fun doSetCurrentLocation() {
        locationService.lastLocation.addOnSuccessListener {
            locationUpdate(Location(it.latitude, it.longitude))
        }
    }

    @SuppressLint("MissingPermission")
    fun doResartLocationUpdates() {
        var locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult?) {
                if (locationResult != null && locationResult.locations != null) {
                    val l = locationResult.locations.last()
                    locationUpdate(Location(l.latitude, l.longitude))
                }
            }
        }
        if (!edit) {
            locationService.requestLocationUpdates(locationRequest, locationCallback, null)
        }
    }


}
/*
import android.content.Intent
import org.jetbrains.anko.intentFor
import org.wit.fieldwork.helpers.showImagePicker
import org.wit.fieldwork.main.MainApp
import org.wit.fieldwork.models.FieldworkModel
import org.wit.fieldwork.models.Location
import org.wit.fieldwork.views.editLocation.EditLocationView
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
    fun doSetLocation() {
        if (fieldwork.zoom != 0f) {
            location.lat = fieldwork.lat
            location.lng = fieldwork.lng
            location.zoom = fieldwork.zoom
        }
        view.startActivityForResult(view.intentFor<EditLocationView>().putExtra("location", location), LOCATION_REQUEST)
    }
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
}*/