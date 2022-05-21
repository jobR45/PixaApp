package com.anypli.pixabayapp.data.database

import android.content.Context
import androidx.room.Database

import androidx.room.Room

object DataBaseBuilder {

    fun getRepoDataBase(context: Context): DataBase {
        return Room.databaseBuilder(context, DataBase::class.java, "database").build()
    }
}