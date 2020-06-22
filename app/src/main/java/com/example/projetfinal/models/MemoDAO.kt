package com.example.projetfinal.models

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface MemoDAO {

    @Query("SELECT * from memo_table")
    fun getAlphabetizedWords(): LiveData<List<Memo>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(memo: Memo)

    @Query("DELETE FROM memo_table")
    suspend fun deleteAll()

    @Delete
    abstract fun delete(vararg notes: Memo?)

}