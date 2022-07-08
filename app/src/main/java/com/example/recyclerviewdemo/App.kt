package com.example.recyclerviewdemo

import android.app.Application
import com.example.recyclerviewdemo.model.UsersService

class App: Application() {

    val usersService = UsersService()

}