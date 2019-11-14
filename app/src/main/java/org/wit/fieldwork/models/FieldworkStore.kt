package org.wit.fieldwork.models

interface FieldworkStore {
  fun findAll(): List<FieldworkModel>
  fun create(fieldwork: FieldworkModel)
  fun update (fieldwork: FieldworkModel)
}