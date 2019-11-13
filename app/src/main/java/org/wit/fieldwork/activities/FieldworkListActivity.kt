package org.wit.fieldwork.activities

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
//import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.recyclerview.widget.LinearLayoutManager
//import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_fieldwork_list.*
import org.jetbrains.anko.intentFor
//import kotlinx.android.synthetic.main.card_fieldwork.view.*
import org.jetbrains.anko.startActivityForResult
import org.wit.fieldwork.R
import org.wit.fieldwork.main.MainApp
import org.wit.fieldwork.models.FieldworkModel

//import org.wit.fieldwork.models.FieldworkModel


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
            R.id.item_add -> startActivityForResult<FieldworkActivity>(0)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onFieldworkClick(fieldwork: FieldworkModel) {
        startActivityForResult(intentFor<FieldworkActivity>().putExtra("fieldwork_edit", fieldwork), 0)
    }
}





