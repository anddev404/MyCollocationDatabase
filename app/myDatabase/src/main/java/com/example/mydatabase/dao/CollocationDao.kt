package com.example.mydatabase.dao

import androidx.room.*
import com.example.mydatabase.model.Collocation
import com.example.mydatabase.model.Sentence

@Dao
interface CollocationDao {

    @Insert
    fun insert(sentence: Collocation): Long

    @Query("SELECT * FROM collocations WHERE basisword = :word")
    fun getByWord(word: String): List<Collocation>

    @Query("SELECT * FROM collocations WHERE id = :id")
    fun getById(id: Int): Collocation?

    @Query("SELECT * FROM collocations WHERE  basisword = :word AND relation = :relation")
    fun getByWordIdAndRelation(word: String, relation: String): List<Collocation>

    @Update
    fun update(collocation: Collocation): Int

    @Query("SELECT COUNT(*) FROM collocations")
    fun getNumberOfCollocations(): Int

    @Query("select * from collocations where kollokation LIKE '%'||:words||'%'")
    fun searchInCollocations(words: String): List<Collocation>

    @Query("select * from collocations where translatedCollocationTranslo LIKE '%'||:words||'%'")
    fun searchInTranslations(words: String): List<Collocation>

}