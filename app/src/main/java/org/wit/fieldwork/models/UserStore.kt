package org.wit.fieldwork.models

interface UserStore {

  fun createUser(user: UserModel)
  fun findAllUsers(): List<UserModel>


}