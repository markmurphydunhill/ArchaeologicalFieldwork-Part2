package org.wit.fieldwork.models

interface FieldworkStore {
  fun findAll(): List<FieldworkModel>
  fun create(placemark: FieldworkModel)
  //fun update(placemark: PlacemarkModel)
}