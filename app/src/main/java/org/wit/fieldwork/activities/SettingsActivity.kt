

package org.wit.fieldwork.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.wit.fieldwork.R
import org.wit.fieldwork.main.MainApp
import org.wit.fieldwork.models.FieldworkModel
import kotlinx.android.synthetic.main.activity_settings.*
import kotlinx.android.synthetic.main.activity_settings.toolbarAdd
import org.jetbrains.anko.doAsync


class SettingsActivity : AppCompatActivity(), AnkoLogger {

    lateinit var app: MainApp
    var fieldwork = FieldworkModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_settings)
        info("Settings Activity started..")
        app = application as MainApp
        toolbarAdd.title = title
        var totalFieldworks = 0
        var totalVisited = 0
        //val fieldworkList = app.fieldworks.findAll() as ArrayList<FieldworkModel>
        //val fieldworkList = doAsync { app.fieldworks.findAll() as ArrayList<FieldworkModel>}
        //val fieldworkList = findAllFieldworks()
        //val first = fieldworkList[0]

        info ("HELLO " )


        for (fieldwork in fieldworkList) {
            totalFieldworks++

        }
        for (fieldwork in fieldworkList) {
            if (fieldwork.visited == true)
            {
            totalVisited++
        }
         }
        info("Total Fieldworks: $totalFieldworks")
        info("Total Visited Fieldworks: $totalVisited")

        totalFieldworksSoFar.text = ("Total Archaelogical Fieldworks added:  $totalFieldworks")
        totalVisistedSoFar.text = ("Total Archaelogical Fieldworks visited:  $totalVisited")

    }

     fun findAllFieldworks(){

        doAsync { app.fieldworks.findAll() as ArrayList<FieldworkModel>}
        return
    }


    }

