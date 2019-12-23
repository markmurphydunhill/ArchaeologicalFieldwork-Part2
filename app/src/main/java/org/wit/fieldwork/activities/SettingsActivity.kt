

package org.wit.fieldwork.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_fieldwork_list.*
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
        //super.init(toolbar, true);
        var totalFieldworks = 0
        var totalVisited = 0
        var totalFavourite = 0

        val fieldworkList = app.fieldworks.findAll()  as ArrayList<FieldworkModel>

        for (fieldwork in fieldworkList) {
            totalFieldworks++

        }

        for (fieldwork in fieldworkList) {
            if (fieldwork.visited == true) {
                totalVisited++
            }
        }

        for (fieldwork in fieldworkList) {
            if (fieldwork.favourite == true) {
                totalFavourite++
            }
        }


        totalFieldworksSoFar.text = ("Total Fieldworks added:  $totalFieldworks")
        totalVisistedSoFar.text = ("Total Fieldworks visited:  $totalVisited")
        totalFavouriteSoFar.text = ("Total Favourite Fieldworks:  $totalFavourite")


         }


    }

