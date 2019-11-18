package org.wit.fieldwork.models

interface FieldworkStore {
  fun findAll(): List<FieldworkModel>
  fun create(fieldwork: FieldworkModel)
  fun update (fieldwork: FieldworkModel)
  //fun login(user: UserModel)
  //fun findAllUsers(): List<UserModel>

}