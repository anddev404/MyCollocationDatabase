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
    var favourite: Boolean? = false,
    var group: Int? = null,
    var time1: Int? = null,
    var time2: Int? = null,
    var time3: Int? = null,
    var time4: Int? = null,
    var time5: Int? = null,
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