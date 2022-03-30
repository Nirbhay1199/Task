package com.nirbhay.task.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [TheProject::class], version = 1)
abstract class DB : RoomDatabase(){

    abstract fun getDaoItem(): Dao

    companion object{
        private var INSTANCE: DB? = null

        fun getDatabase(context: Context): DB{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DB::class.java,
                    "yes_thats_me_db"
                ).build()
                INSTANCE = instance
                INSTANCE!!
            }
        }
    }
}