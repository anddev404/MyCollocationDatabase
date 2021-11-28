package com.example.mydatabase.dao

import androidx.room.*
import com.example.mydatabase.model.Sentence

@Dao
interface SentenceDao {

    @Query("SELECT * FROM Sentence.sentences WHERE id = :sentenceId")
    fun getById(sentenceId: Int): Sentence?

    @Update
    fun update(sentence: Sentence): Int


    @Query("SELECT COUNT(*) FROM Sentence.sentences")
    fun getNumberOfSentences(): Int

}