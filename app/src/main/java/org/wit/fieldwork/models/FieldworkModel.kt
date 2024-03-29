package org.wit.fieldwork.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import androidx.room.Embedded

@Parcelize
@Entity
data class FieldworkModel(@PrimaryKey(autoGenerate = true) var id: Long = 0,
                          var fbId : String = "",
                          var title: String = "",
                          var description: String = "",
                          var image1: String = "",
                          var image2: String = "",
                          var image3: String ="",
                          var image4: String = "",
                          var visited: Boolean = false,
                          var favourite: Boolean = false,
                          //var rating: Float,
                          @Embedded var location : Location = Location()): Parcelable

@Parcelize
data class Location(var lat: Double = 0.0,
                    var lng: Double = 0.0,
                    var zoom: Float = 0f) : Parcelable