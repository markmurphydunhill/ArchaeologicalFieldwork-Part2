package org.wit.fieldwork.views

import android.content.Intent

import android.os.Parcelable
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import org.jetbrains.anko.AnkoLogger
import org.wit.fieldwork.activities.SettingsActivity

import org.wit.fieldwork.models.FieldworkModel
import org.wit.fieldwork.views.editLocation.EditLocationView
import org.wit.fieldwork.views.map.FieldworkMapsView
import org.wit.fieldwork.views.fieldwork.FieldworkView
import org.wit.fieldwork.views.fieldworkList.FieldworkListView

val IMAGE1_REQUEST = 1
val LOCATION_REQUEST = 2
val IMAGE2_REQUEST = 3
val IMAGE3_REQUEST = 4
val IMAGE4_REQUEST = 5

enum class VIEW {
    LOCATION, FIELDWORK, MAPS, LIST, SETTINGS
}

open abstract class BaseView() : AppCompatActivity(), AnkoLogger {

    var basePresenter: BasePresenter? = null

    fun navigateTo(view: VIEW, code: Int = 0, key: String = "", value: Parcelable? = null) {
        var intent = Intent(this, FieldworkListView::class.java)
        when (view) {
            VIEW.LOCATION -> intent = Intent(this, EditLocationView::class.java)
            VIEW.FIELDWORK -> intent = Intent(this, FieldworkView::class.java)
            VIEW.MAPS -> intent = Intent(this, FieldworkMapsView::class.java)
            VIEW.LIST -> intent = Intent(this, FieldworkListView::class.java)
            VIEW.SETTINGS -> intent = Intent(this, SettingsActivity::class.java)
        }
        if (key != "") {
            intent.putExtra(key, value)
        }
        startActivityForResult(intent, code)
    }

    fun initPresenter(presenter: BasePresenter): BasePresenter {
        basePresenter = presenter
        return presenter
    }

    fun init(toolbar: Toolbar) {
        toolbar.title = title
        setSupportActionBar(toolbar)
    }

    override fun onDestroy() {
        basePresenter?.onDestroy()
        super.onDestroy()
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (data != null) {
            basePresenter?.doActivityResult(requestCode, resultCode, data)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        basePresenter?.doRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    open fun showFieldwork(fieldwork: FieldworkModel) {}
    open fun showFieldworks(fieldworks: List<FieldworkModel>) {}
    open fun showProgress() {}
    open fun hideProgress() {}
}

