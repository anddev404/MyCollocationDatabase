package com.example.mydatabase.model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.example.mydatabase.model.Sentence

@Entity(tableName = "Collocation")
class Collocation(var wordId: Int, var relation: String, var collocation: String) {

    var translation: String? = null
    var isChecked: Boolean = false
    var numberOfDownloads = 0

    @Ignore
    var sentences = ArrayList<Sentence>()

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

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
    }
}