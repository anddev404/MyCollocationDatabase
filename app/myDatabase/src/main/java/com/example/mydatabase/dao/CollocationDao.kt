package com.example.mydatabase.dao

import androidx.room.*
import com.example.mydatabase.model.Collocation

@Dao
interface CollocationDao {

    @Insert
    fun insert(sentence: Collocation): Long

    @Query("SELECT * FROM collocations WHERE basisword = :word")
    fun getByWord(word: String): List<Collocation>

    @Query("SELECT * FROM collocations WHERE  basisword = :word AND relation = :relation")
    fun getByWordIdAndRelation(word: String, relation: String): List<Collocation>

    @Update
    fun update(collocation: Collocation):Int

    @Query("SELECT COUNT(*) FROM collocations")
    fun getNumberOfCollocations(): Int

}