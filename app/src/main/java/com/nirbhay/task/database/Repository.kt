package com.nirbhay.task.database

import androidx.lifecycle.LiveData

class Repository (private val dao: Dao){

    val allItems: LiveData<List<TheProject>> = dao.getAllItems()

    suspend fun insert(item: TheProject){
        dao.insert(item)
    }

    suspend fun delete(item: TheProject){
        dao.delete(item)
    }
}