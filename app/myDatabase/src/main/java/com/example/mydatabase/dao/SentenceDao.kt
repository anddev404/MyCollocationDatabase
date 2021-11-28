package com.example.mydatabase.dao

import androidx.room.*
import com.example.mydatabase.model.Sentence

@Dao
interface SentenceDao {

    //TODO czy insert wpakuje takze id do obiektu czy tylko go zwraca
    @Update
    fun insert(sentence: Sentence): Long

    @Query("SELECT * FROM Sentence.sentences WHERE id = :sentenceId")
    fun getById(sentenceId: Int): Sentence?

    @Update
    fun update(sentence: Sentence): Long


    @Query("SELECT COUNT(*) FROM Sentence.sentences")
    fun getNumberOfSentences(): Int

}