package com.ms.ekacaretest

import com.ms.ekacaretest.database.PersonDetails
import android.app.Application
import com.ms.ekacaretest.database.DbHandler

object DetailsRepository {

    suspend fun fetchData(context: Application): List<PersonDetails> {
        return DbHandler.getDatabase(context = context).getAllDetails()
    }

    suspend fun setData(context: Application, personDetails: PersonDetails): Long {
        return DbHandler.getDatabase(context = context).insertData(personData = personDetails)
    }

}