package org.wit.fieldwork.views.map

import android.os.Bundle
import com.bumptech.glide.Glide
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
        super.init(toolbar, true)

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
        //currentImage.setImageBitmap(readImageFromPath(this, fieldwork.image1))
        Glide.with(this).load(fieldwork.image1).into(currentImage);
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