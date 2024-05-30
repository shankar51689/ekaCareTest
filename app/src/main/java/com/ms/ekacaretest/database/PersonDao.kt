package com.ms.ekacaretest.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PersonDao{

    @Insert
    suspend fun insertData(personData: PersonDetails): Long

    @Query("select * from person_table")
    suspend fun getAllDetails(): List<PersonDetails>

}