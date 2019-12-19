/*package org.wit.fieldwork.models.room

import android.content.Context
import androidx.room.Room
import org.wit.fieldwork.models.FieldworkModel
import org.wit.fieldwork.models.FieldworkStore

class FieldworkStoreRoom(val context: Context) : FieldworkStore {

    var dao: FieldworkDao

    init {
        val database = Room.databaseBuilder(context, Database::class.java, "room_sample.db")
            .fallbackToDestructiveMigration()
            .build()
        dao = database.fieldworkDao()
    }

    override fun findAll(): List<FieldworkModel> {
        return dao.findAll()
    }

    override fun findById(id: Long): FieldworkModel? {
        return dao.findById(id)
    }

    override fun create(fieldwork: FieldworkModel) {
        dao.create(fieldwork)
    }

    override fun update(fieldwork: FieldworkModel) {
        dao.update(fieldwork)
    }

    override fun delete(fieldwork: FieldworkModel) {
        dao.deleteFieldwork(fieldwork)
    }
}*/