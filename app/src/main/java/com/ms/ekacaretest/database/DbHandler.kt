package com.ms.ekacaretest.database

import android.content.Context
import androidx.room.Room

object DbHandler {

    fun getDatabase(context: Context) : PersonDao {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "personDB"
        ).build().getPersonDao()
    }

}