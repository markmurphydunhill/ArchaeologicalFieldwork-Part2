package org.wit.fieldwork.activities

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_fieldwork_list.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.startActivityForResult
import org.wit.fieldwork.R
import org.wit.fieldwork.models.FieldworkModel


class FieldworkListActivity : AppCompatActivity(), FieldworkListener {

    lateinit var presenter: FieldworkListPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fieldwork_list)
        toolbar.title = title
        setSupportActionBar(toolbar)

        presenter = FieldworkListPresenter(this)
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter =
            FieldworkAdapter(presenter.getFieldworks(), this)
        recyclerView.adapter?.notifyDataSetChanged()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.item_add -> presenter.doAddFieldwork()
            R.id.item_map -> presenter.doShowFieldworksMap()
            R.id.item_logout -> presenter.doLogout()
            R.id.item_settings -> presenter.doSettings()
            /*    R.id.item_add -> startActivityForResult<FieldworkView>(0)
            }
                when (item?.itemId){
                R.id.item_logout -> finish()
            }
            when (item?.itemId) {
                R.id.item_settings -> startActivityForResult<SettingsActivity>(0)
            }
                when (item?.itemId){
                R.id.item_map -> startActivity<FieldworkMapsActivity>()
            }*/
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onFieldworkClick(fieldwork: FieldworkModel) {
        presenter.doEditFieldwork(fieldwork)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        recyclerView.adapter?.notifyDataSetChanged()
        super.onActivityResult(requestCode, resultCode, data)
    }
}
/*import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_fieldwork_list.*
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.startActivityForResult
import org.wit.fieldwork.R
import org.wit.fieldwork.main.MainApp
import org.wit.fieldwork.models.FieldworkModel



class FieldworkListActivity : AppCompatActivity(), FieldworkListener {

    lateinit var app: MainApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fieldwork_list)
        app = application as MainApp

        toolbar.title = title
        setSupportActionBar(toolbar)

        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = FieldworkAdapter(app.fieldworks.findAll(),this)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.item_add -> startActivityForResult<FieldworkView>(0)
        }
        when (item?.itemId){
            R.id.item_logout -> finish()
        }
        when (item?.itemId) {
            R.id.item_settings -> startActivityForResult<SettingsActivity>(0)
        }
        when (item?.itemId){
            R.id.item_map -> startActivity<FieldworkMapsActivity>()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onFieldworkClick(fieldwork: FieldworkModel) {
        startActivityForResult(intentFor<FieldworkView>().putExtra("fieldwork_edit", fieldwork), 0)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        recyclerView.adapter?.notifyDataSetChanged()
        super.onActivityResult(requestCode, resultCode, data)
    }
}





*/