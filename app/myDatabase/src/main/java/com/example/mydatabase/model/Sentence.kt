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
    var numberOfDownloads: Int = 0
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