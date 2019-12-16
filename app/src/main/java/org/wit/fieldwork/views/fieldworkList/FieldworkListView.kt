package org.wit.fieldwork.views.fieldworkList


import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_fieldwork_list.*
import org.jetbrains.anko.doAsync
import org.wit.fieldwork.R
import org.wit.fieldwork.models.FieldworkModel
import org.wit.fieldwork.views.BaseView

class FieldworkListView :  BaseView(), FieldworkListener {

    lateinit var presenter: FieldworkListPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fieldwork_list)
        setSupportActionBar(toolbar)

        presenter = initPresenter(FieldworkListPresenter(this)) as FieldworkListPresenter
        init(toolbar, false)
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        presenter.loadFieldworks()
    }

    override fun showFieldworks(fieldworks: List<FieldworkModel>) {
        recyclerView.adapter = FieldworkAdapter(fieldworks, this)
        recyclerView.adapter?.notifyDataSetChanged()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.item_add -> doAsync { presenter.doAddFieldwork()}
            R.id.item_map -> doAsync{ presenter.doShowFieldworksMap()}
            R.id.item_logout -> presenter.doLogout()
            R.id.item_settings -> presenter.doSettings()
            R.id.item_favourites -> presenter.doLoadFavourites()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onFieldworkClick(fieldwork: FieldworkModel) {
        presenter.doEditFieldwork(fieldwork)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        presenter.loadFieldworks()
        super.onActivityResult(requestCode, resultCode, data)
    }
}
