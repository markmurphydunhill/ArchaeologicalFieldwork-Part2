package org.wit.fieldwork.models.firebase

import android.content.Context
import android.graphics.Bitmap
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.storage.StorageReference
import org.jetbrains.anko.AnkoLogger
import org.wit.fieldwork.helpers.readImageFromPath
import org.wit.fieldwork.models.FieldworkModel
import org.wit.fieldwork.models.FieldworkStore
import java.io.ByteArrayOutputStream
import java.io.File

class FieldworkFireStore(val context: Context) : FieldworkStore, AnkoLogger {

    val fieldworks = ArrayList<FieldworkModel>()
    lateinit var userId: String
    lateinit var db: DatabaseReference
    lateinit var st: StorageReference

    override fun findAll(): List<FieldworkModel> {
        return fieldworks
    }

    override fun findById(id: Long): FieldworkModel? {
        val foundFieldwork: FieldworkModel? = fieldworks.find { p -> p.id == id }
        return foundFieldwork
    }

    override fun create(fieldwork: FieldworkModel) {
        val key = db.child("users").child(userId).child("fieldworks").push().key
        key?.let {
            fieldwork.fbId = key
            fieldworks.add(fieldwork)
            db.child("users").child(userId).child("fieldworks").child(key).setValue(fieldwork)
           // updateImage1(fieldwork)
        }
    }

    override fun update(fieldwork: FieldworkModel) {
        var foundFieldwork: FieldworkModel? = fieldworks.find { p -> p.fbId == fieldwork.fbId }
        if (foundFieldwork != null) {
            foundFieldwork.title = fieldwork.title
            foundFieldwork.description = fieldwork.description
            foundFieldwork.image1 = fieldwork.image1
            foundFieldwork.image2 = fieldwork.image2
            foundFieldwork.image3 = fieldwork.image3
            foundFieldwork.image4 = fieldwork.image4
            foundFieldwork.location = fieldwork.location
            //foundFieldwork.lat = fieldwork.lat
            //foundFieldwork.lng = fieldwork.lng
            //foundFieldwork.zoom = fieldwork.zoom
            foundFieldwork.visited = fieldwork.visited
            foundFieldwork.favourite = fieldwork.favourite
        }

        db.child("users").child(userId).child("fieldworks").child(fieldwork.fbId).setValue(fieldwork)
       /* if ((fieldwork.image1.length) > 0 && (fieldwork.image1[0] != 'h')) {
            updateImage1(fieldwork)
        }*/

    }

    override fun delete(fieldwork: FieldworkModel) {
        db.child("users").child(userId).child("fieldworks").child(fieldwork.fbId).removeValue()
        fieldworks.remove(fieldwork)
    }

    override fun clear() {
        fieldworks.clear()
    }

    fun fetchFieldworks(fieldworksReady: () -> Unit) {
        val valueEventListener = object : ValueEventListener {
            override fun onCancelled(dataSnapshot: DatabaseError) {
            }
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                dataSnapshot!!.children.mapNotNullTo(fieldworks) { it.getValue<FieldworkModel>(FieldworkModel::class.java) }
                fieldworksReady()
            }
        }
        userId = FirebaseAuth.getInstance().currentUser!!.uid
        db = FirebaseDatabase.getInstance().reference
        fieldworks.clear()
        db.child("users").child(userId).child("fieldworks").addListenerForSingleValueEvent(valueEventListener)
    }

    fun updateImage1(fieldwork: FieldworkModel) {
        if (fieldwork.image1 != "") {
            val fileName = File(fieldwork.image1)
            val imageName = fileName.getName()

            var imageRef = st.child(userId + '/' + imageName)
            val baos = ByteArrayOutputStream()
            val bitmap = readImageFromPath(context, fieldwork.image1)

            bitmap?.let {
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
                val data = baos.toByteArray()
                val uploadTask = imageRef.putBytes(data)
                uploadTask.addOnFailureListener {
                    println(it.message)
                }.addOnSuccessListener { taskSnapshot ->
                    taskSnapshot.metadata!!.reference!!.downloadUrl.addOnSuccessListener {
                        fieldwork.image1 = it.toString()
                        db.child("users").child(userId).child("fieldworks").child(fieldwork.fbId).setValue(fieldwork)
                    }
                }
            }
        }
    }
}
