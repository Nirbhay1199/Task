package com.nirbhay.task.database

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.Dao
import kotlinx.coroutines.flow.Flow

@Dao
interface Dao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: TheProject)

    @Delete
    suspend fun delete(item: TheProject)

    @Query("Select * from yes_thats_me order by id ASC")
    fun getAllItems(): LiveData<List<TheProject>>


}