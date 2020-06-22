package com.example.projetfinal.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.projetfinal.database.MemoRoomDatabase
import com.example.projetfinal.models.Memo
import com.example.projetfinal.repositories.MemoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MemoViewModel(application: Application) : AndroidViewModel(application) {

    private var repository: MemoRepository?=null

    var allMemos: LiveData<List<Memo>>?=null

    fun init( mainRepository: MemoRepository) {
        this.repository = mainRepository
        allMemos = repository!!.getAllMemos()
    }


    fun insert(memo: Memo) = viewModelScope.launch(Dispatchers.IO) {
        repository?.insert(memo)
    }


    fun delete(memo: Memo) = viewModelScope.launch(Dispatchers.IO) {
        repository?.deleteMemo(memo)
    }
}