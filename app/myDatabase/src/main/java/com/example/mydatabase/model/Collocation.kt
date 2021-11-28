package com.example.mydatabase.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

@Entity(tableName = "collocations")
class Collocation(

    @ColumnInfo(name = "kollokation", defaultValue = "")
    var collocation: String = "",

    @ColumnInfo(defaultValue = "")
    var relation: String = "",

    @ColumnInfo(name = "lemmahits", defaultValue = "0")
    var frequency: Int = 0,

    @ColumnInfo(defaultValue = "")
    var basisword: String = "",

    @ColumnInfo(defaultValue = "0")
    var isChecked: Boolean = false,

    @ColumnInfo(defaultValue = "0")
    var numberOfDownloads: Int = 0,
    
    @ColumnInfo(defaultValue = "0")
    var idSentences: Int = 0
) {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    @Ignore
    var sentences: Sentence? = null


    companion object {

        val RELATION_V_obj_N = "V:obj:N"
        val RELATION_V_prep_N = "V:prep:N"
        val RELATION_V_obj_1_2_N = "V:obj1+2:N"
        val RELATION_V_obj_prep_N = "V:obj+prep:N"
        val RELATION_V_subj_N = "V:subj:N"
        val RELATION_V_sc_V = "V:sc:V"
        val RELATION_N_mod_A = "N:mod:A"
        val RELATION_N_prep_N = "N:prep:N"
        val RELATION_N_nn_N = "N:nn:N"
        val RELATION_V_mod_A = "V:mod:A"
        val RELATION_A_mod_A = "A:mod:A"
        val RELATION_UNKNOWN = "UNKNOWN"

    }
}



