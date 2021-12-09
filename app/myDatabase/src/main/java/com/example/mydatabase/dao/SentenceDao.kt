package com.example.mydatabase.dao

import androidx.room.*
import com.example.mydatabase.model.Sentence
import com.example.mydatabase.model.Word

@Dao
interface SentenceDao {
    //   @Query("SELECT * FROM words")
//    fun getAll(): List<Word>
    @Query("SELECT * FROM sentences WHERE translationAllInOne=''")
    fun getRest(): List<Sentence>

    //TODO czy insert wpakuje takze id do obiektu czy tylko go zwraca
    @Insert
    fun insert(sentence: Sentence): Long

    @Query("SELECT * FROM sentences WHERE id = :sentenceId")
    fun getById(sentenceId: Int): Sentence?

    @Update
    fun update(sentence: Sentence): Int


    @Query("SELECT COUNT(*) FROM sentences")
    fun getNumberOfSentences(): Int

}