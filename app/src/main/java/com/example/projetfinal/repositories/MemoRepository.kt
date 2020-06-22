package com.example.projetfinal.repositories

import androidx.lifecycle.LiveData
import com.example.projetfinal.di.DIApplication
import com.example.projetfinal.models.Memo
import com.example.projetfinal.models.MemoDAO
import javax.inject.Inject

class MemoRepository() {
    @Inject
    lateinit var memoDao: MemoDAO

    suspend fun insert(memo: Memo) {
        memoDao.insert(memo)
    }

    fun getAllMemos(): LiveData<List<Memo>>{
        return memoDao.getAlphabetizedWords()
    }
    fun deleteMemo(memo: Memo) {
        memoDao.delete(memo)
    }
    init {
        DIApplication.getAppComponent()?.inject(this)
    }
}