

package org.wit.fieldwork.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.toast
import org.wit.fieldwork.R
import org.wit.fieldwork.main.MainApp
import org.wit.fieldwork.models.FieldworkModel
import android.content.Intent
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class
import kotlinx.android.synthetic.main.activity_settings.*
import org.jetbrains.anko.startActivityForResult

var totalFieldworks = 0
var totalVisited = 0


class SettingsActivity : AppCompatActivity(), AnkoLogger {

    lateinit var app: MainApp
    var fieldwork = FieldworkModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_settings)
        info("Settings Activity started..")
        app = application as MainApp
        var totalFieldworks = 0
        var totalVisited = 0
        val fieldworkList = app.fieldworks.findAll() as ArrayList<FieldworkModel>
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



    }


