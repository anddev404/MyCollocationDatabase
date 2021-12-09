package com.example.mydatabase.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "sentences")
class Sentence(

    @ColumnInfo(
        name = "beispielsatz",
        defaultValue = ""
    ) var sentencesAllInOne: String = "",

    @ColumnInfo(defaultValue = "")
    var translationAllInOne: String = "",

    @ColumnInfo(defaultValue = "0")
    var isChecked: Boolean = false,

    @ColumnInfo(defaultValue = "0")
    var isChecked1: Boolean = false,

    @ColumnInfo(defaultValue = "0")
    var isChecked2: Boolean = false,

    @ColumnInfo(defaultValue = "0")
    var isChecked3: Boolean = false,

    @ColumnInfo(defaultValue = "0")
    var isChecked4: Boolean = false,

    @ColumnInfo(defaultValue = "0")
    var isChecked5: Boolean = false,

    @ColumnInfo(defaultValue = "")
    var sentence1: String = "",

    @ColumnInfo(defaultValue = "")
    var sentence2: String = "",

    @ColumnInfo(defaultValue = "")
    var sentence3: String = "",

    @ColumnInfo(defaultValue = "")
    var sentence4: String = "",

    @ColumnInfo(defaultValue = "")
    var sentence5: String = "",
) {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0


    @Ignore
    var sentencesAfterSplitting: ArrayList<String> = arrayListOf()

    @Ignore
    var translations: ArrayList<String> = arrayListOf()

    fun splitSentences() {

        //TODO rozdzielenie sentencesAllInOne(pobrane z bazy) do sentencesAfterSplitting(do obiektów String). Rozdzielenie wykonane za pomocą znaku splitChar

    }

    fun mergeTranslations() {

        //TODO  połączenie wielu tłumaczeń w celu zapisu do bazy w jednym Stringu, znak rozdzielajacy to splitChar

    }

    companion object {
        val splitChar = "@!@"
    }
}