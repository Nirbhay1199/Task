package com.nirbhay.task.ui.home

import android.app.Application
import androidx.lifecycle.*
import com.nirbhay.task.database.DB
import com.nirbhay.task.database.Repository
import com.nirbhay.task.database.TheProject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel (application: Application) : AndroidViewModel(application){

    val allItems: LiveData<List<TheProject>>

    private val repository: Repository
    init {
        val dao = DB.getDatabase(application).getDaoItem()
        repository = Repository(dao)
        allItems = repository.allItems
    }

    fun deleteItem(item: TheProject) = viewModelScope.launch(Dispatchers.IO){
        repository.delete(item)
    }

    fun insertItem(item: TheProject) = viewModelScope.launch(Dispatchers.IO){
        repository.insert(item)
    }
}