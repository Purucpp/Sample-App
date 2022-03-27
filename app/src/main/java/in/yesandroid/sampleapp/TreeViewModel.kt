package com.yesandroid.tree


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TreeViewModel(application: Application): AndroidViewModel(application) {

    val readAllData: LiveData<List<Tree>>
    private val repository: TreeRepository

    init {
        val treeDao = TreeDatabase.getDatabase(
            application
        ).treeDao()
        repository = TreeRepository(treeDao)
        readAllData = repository.readAllData
    }

    fun addTree(tree: Tree){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addTree(tree)
        }
    }




    fun getCount(): LiveData<Int?>? {
        return repository.getCount()
    }


    fun getAge(): LiveData<Int?>? {
        return repository.getAge()
    }

    fun getMedian(): LiveData<Float?>? {
        return repository.getMedian()
    }
}