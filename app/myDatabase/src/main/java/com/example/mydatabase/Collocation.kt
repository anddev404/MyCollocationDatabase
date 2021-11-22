package com.example.mydatabase

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "Collocation")
class Collocation(var wordId: Int, var relation: Int, var collocation: String) {

    var translation: String? = null
    var isChecked: Boolean = false
    var numberOfDownloads = 0

    @Ignore
    var sentences = ArrayList<Sentence>()

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    companion object {
        val Relation_V_obj_N = "V:obj:N"
        val Relation_V_prep_N = "V:prep:N"
        val Relation_V_obj_1_2_N = "V:obj1+2:N"
        val Relation_V_obj_prep_N = "V:obj+prep:N"
        val Relation_V_subj_N = "V:subj:N"
        val Relation_V_sc_V = "V:sc:V"
        val Relation_N_mod_A = "N:mod:A"
        val Relation_N_prep_N = "N:prep:N"
        val Relation_N_nn_N = "N:nn:N"
        val Relation_V_mod_A = "V:mod:A"
        val Relation_A_mod_A = "A:mod:A"
    }
}