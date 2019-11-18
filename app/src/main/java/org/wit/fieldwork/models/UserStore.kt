package org.wit.fieldwork.models

interface UserStore {
  //fun findAll(): List<FieldworkModel>
  //fun create(fieldwork: FieldworkModel)
  //fun update (fieldwork: FieldworkModel)
  fun createUser(user: UserModel)
  fun findAllUsers(): List<UserModel>


}