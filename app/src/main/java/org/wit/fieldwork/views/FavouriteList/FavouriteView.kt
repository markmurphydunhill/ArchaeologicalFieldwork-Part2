package org.wit.fieldwork.views.FavouriteList

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_fieldwork_list.*
import kotlinx.android.synthetic.main.activity_fieldwork_list.toolbar
import kotlinx.android.synthetic.main.activity_fieldwork_maps.*
import org.jetbrains.anko.doAsync
import org.wit.fieldwork.R
import org.wit.fieldwork.models.FieldworkModel
import org.wit.fieldwork.views.BaseView
import org.wit.fieldwork.views.fieldworkList.FieldworkAdapter
import org.wit.fieldwork.views.fieldworkList.FieldworkListener

class FavouriteView :  BaseView(), FavouriteListener {

    lateinit var presenter: FavouritePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.favourite_list)
        setSupportActionBar(toolbar)
        presenter = initPresenter(FavouritePresenter(this)) as FavouritePresenter
        // init(toolbar, false)
        super.init(toolbar, true)
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        presenter.loadFavourites()
    }
    override fun showFavourites(fieldworks: List<FieldworkModel>) {
        recyclerView.adapter = FavouriteAdapter(fieldworks, this)
        recyclerView.adapter?.notifyDataSetChanged()
    }
     override fun onCreateOptionsMenu(menu: Menu?): Boolean {
         menuInflater.inflate(R.menu.menu_main, menu)
         return super.onCreateOptionsMenu(menu)
     }

    /*
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
         when (item?.itemId) {
             R.id.item_add -> doAsync { presenter.doAddFieldwork()}
             R.id.item_map -> doAsync{ presenter.doShowFieldworksMap()}
             R.id.item_logout -> presenter.doLogout()
             R.id.item_settings -> presenter.doSettings()
         }
         return super.onOptionsItemSelected(item)
     }*/

    override fun onFavouriteClick(fieldwork: FieldworkModel) {
        presenter.doEditFieldwork(fieldwork)
    }


    /* override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
         presenter.loadFieldworks()
         super.onActivityResult(requestCode, resultCode, data)
     }*/
}
