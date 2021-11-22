package com.example.mydatabase.dao

import androidx.room.*
import com.example.mydatabase.model.Sentence

@Dao
interface SentenceDao {

    @Query("SELECT * FROM Sentence")
    fun getAll(): List<Sentence>

    @Query("SELECT * FROM Sentence WHERE id = :sentenceId")
    fun getById(sentenceId: Int): Sentence

    @Query("SELECT * FROM Sentence WHERE collocationId = :collocationId")
    fun getByCollocationId(collocationId: Int): List<Sentence>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(sentence: Sentence): Long


    @Update
    fun update(sentence: Sentence): Int


    @Delete
    fun delete(sentence: Sentence): Int

    @Query("DELETE FROM Sentence WHERE id = :sentenceId")
    fun deleteById(sentenceId: Int): Int

    @Query("SELECT COUNT(*) FROM Sentence")
    fun getNumberOfSentences(): Int

}