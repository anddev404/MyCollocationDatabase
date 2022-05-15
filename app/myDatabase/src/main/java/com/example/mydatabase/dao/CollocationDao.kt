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

    @Query("select * from collocations where relation='V:mod:A' AND kollokation LIKE '%back' OR relation='V:mod:A' AND kollokation LIKE '%out' OR relation='V:mod:A' AND kollokation LIKE '%off' OR relation='V:mod:A' AND kollokation LIKE '%away' OR relation='V:mod:A' AND kollokation LIKE '%down' OR relation='V:mod:A' AND kollokation LIKE '%over' OR relation='V:mod:A' AND kollokation LIKE '%after' OR relation='V:mod:A' AND kollokation LIKE '%up' OR relation='V:mod:A' AND kollokation LIKE '%on' OR relation='V:mod:A' AND kollokation LIKE '%in' OR relation='V:mod:A' AND kollokation LIKE '%for' OR relation='V:mod:A' AND kollokation LIKE '%into' ORDER BY lemmahits DESC")
    fun getPhrasalVerb(): List<Collocation>

    @Query("SELECT * FROM collocations WHERE basisword = :word AND isChecked=1")
    fun getByWordOnlyGreen(word: String): List<Collocation>

    @Query("SELECT * FROM collocations WHERE isChecked=1")
    fun getAllGreen(): List<Collocation>

    @Query("SELECT * FROM collocations WHERE id = :id")
    fun getById(id: Int): Collocation?

    @Query("SELECT * FROM collocations WHERE  basisword = :word AND relation = :relation")
    fun getByWordIdAndRelation(word: String, relation: String): List<Collocation>

    @Update
    fun update(collocation: Collocation): Int

    @Query("SELECT COUNT(*) FROM collocations")
    fun getNumberOfCollocations(): Int

    @Query("select * from collocations where kollokation LIKE '%'||:words||'%' ORDER BY lemmahits DESC")
    fun searchInCollocations(words: String): List<Collocation>

    @Query("select * from collocations where translatedCollocationTranslo LIKE '%'||:words||'%'")
    fun searchInTranslations(words: String): List<Collocation>

}