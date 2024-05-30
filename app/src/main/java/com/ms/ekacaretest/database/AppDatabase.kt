package com.ms.ekacaretest.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [PersonDetails::class],
    version = 1
)
abstract class AppDatabase: RoomDatabase() {
    abstract fun getPersonDao(): PersonDao
}