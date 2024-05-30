package com.ms.ekacaretest.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "person_table"
)
data class PersonDetails(
    var name: String?,
    var age: String?,
    @ColumnInfo(name = "DOB")
    var dob: String?,
    var address: String?

) {
    @PrimaryKey(autoGenerate = true)
    var id:Int = 0
}