package com.example.mydatabase.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "words")
class Word(
    var word: String = "",
    var translations: String = "",
    var pronunciation: String = "",
    var partOfSpeech: Int = 0,
    var greenCollocationsCount: Int = 0,
    var greenSentencesCount: Int = 0,
    var favourite: Boolean? = false
) {


    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    companion object {
        val UNKNOWN = 0
        val NOUN = 1
        val ADJECTIVE = 2
        val VERB = 3
    }
}