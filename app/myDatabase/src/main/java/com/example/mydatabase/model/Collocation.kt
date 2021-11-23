package com.example.mydatabase.model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

@Entity(tableName = "Collocation")
class Collocation(var wordId: Int, var relation: String, var collocation: String) {

    var translation: String? = null
    var isChecked: Boolean = false
    var numberOfDownloads = 0

    @Ignore
    var sentences = arrayListOf<Sentence>()

    @Ignore//needed to parse JSON, after parsing "examples" is converting to "sentences"
    var examples = arrayListOf<String>()

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
        val RELATION_UNKNOWN = "UNKNOWN"

        fun convertExamplesToSentences(list: ArrayList<Collocation>) {
            for (l in list) {
                for (e in l.examples) {
                    l.sentences.add(Sentence(l.id, e))

                }

            }
        }

        private fun getStringDog(): String {
            return "[{},{\"id\":768357,\"collocation\":\"dog bark\",\"relation\":\"V:subj:N\",\"basisword\":\"dog\",\"examples\":[\"Everybody laughed , and the \\u003cb\\u003edogs\\u003c/b\\u003e began to \\u003cb\\u003ebark\\u003c/b\\u003e .\",\"Then the \\u003cb\\u003edogs\\u003c/b\\u003e out in the yard began to \\u003cb\\u003ebark\\u003c/b\\u003e .\",\"Examples If you bark at a dog , the \\u003cb\\u003edog\\u003c/b\\u003e will \\u003cb\\u003ebark\\u003c/b\\u003e back .\"]},{\"id\":768358,\"collocation\":\"wild dog\",\"relation\":\"N:nn:N\",\"basisword\":\"dog\",\"examples\":[\"The mercenary \\u003cb\\u003eWild\\u003c/b\\u003e \\u003cb\\u003eDog\\u003c/b\\u003e also appears and attacks the agents , forcing the agents to defend themselves .\",\"\\u003cb\\u003eWild\\u003c/b\\u003e \\u003cb\\u003eDog\\u003c/b\\u003e begins detonating the castle and is planning to fly away on a helicopter .\",\"\\u003cb\\u003eWild\\u003c/b\\u003e \\u003cb\\u003eDog\\u003c/b\\u003e is quite willing to use deadly force against his equally violent opponents .\"]},{\"id\":768359,\"collocation\":\"breed of dog\",\"relation\":\"N:prep:N\",\"basisword\":\"dog\",\"examples\":[\"In other animals The condition has been diagnosed in all \\u003cb\\u003ebreeds\\u003c/b\\u003e of \\u003cb\\u003edogs\\u003c/b\\u003e .\",\"Plot Dogs 101 features 5 \\u003cb\\u003ebreeds\\u003c/b\\u003e of \\u003cb\\u003edogs\\u003c/b\\u003e per episode .\",\"Labrador refers to the Canadian place rather than the \\u003cb\\u003ebreed\\u003c/b\\u003e of \\u003cb\\u003edog\\u003c/b\\u003e .\"]},{\"id\":768360,\"collocation\":\"pet dog\",\"relation\":\"N:mod:A\",\"basisword\":\"dog\",\"examples\":[\"Do not adopt strange dogs and do not \\u003cb\\u003epet\\u003c/b\\u003e \\u003cb\\u003edogs\\u003c/b\\u003e .\",\"Yes ; run over trying to save a \\u003cb\\u003epet\\u003c/b\\u003e \\u003cb\\u003edog\\u003c/b\\u003e .\",\"You have seen a little boy with a \\u003cb\\u003epet\\u003c/b\\u003e \\u003cb\\u003edog\\u003c/b\\u003e .\"]},{\"id\":768361,\"collocation\":\"mad dog\",\"relation\":\"N:mod:A\",\"basisword\":\"dog\",\"examples\":[\"As you make haste to shoot a \\u003cb\\u003emad\\u003c/b\\u003e \\u003cb\\u003edog\\u003c/b\\u003e .\",\"Boy , you stare at me as if I were a \\u003cb\\u003emad\\u003c/b\\u003e \\u003cb\\u003edog\\u003c/b\\u003e !\",\"Boy , you stare at me as if I were a \\u003cb\\u003emad\\u003c/b\\u003e \\u003cb\\u003edog\\u003c/b\\u003e .\"]},{\"id\":768362,\"collocation\":\"dog howl\",\"relation\":\"V:subj:N\",\"basisword\":\"dog\",\"examples\":[\"Then , thank goodness , we heard \\u003cb\\u003edogs\\u003c/b\\u003e \\u003cb\\u003ehowling\\u003c/b\\u003e .\",\"The passenger asked a native why the \\u003cb\\u003edog\\u003c/b\\u003e was \\u003cb\\u003ehowling\\u003c/b\\u003e .\",\"The still angry \\u003cb\\u003edogs\\u003c/b\\u003e could be heard \\u003cb\\u003ehowling\\u003c/b\\u003e outside .\"]},{\"id\":768363,\"collocation\":\"prairie dog\",\"relation\":\"N:nn:N\",\"basisword\":\"dog\",\"examples\":[\"The wagon was found encamped on the \\u003cb\\u003ePrairie\\u003c/b\\u003e \\u003cb\\u003eDog\\u003c/b\\u003e .\",\"Despite the title , the video actually features a \\u003cb\\u003eprairie\\u003c/b\\u003e \\u003cb\\u003edog\\u003c/b\\u003e .\",\"Buck stepped in the hole of a \\u003cb\\u003eprairie\\u003c/b\\u003e \\u003cb\\u003edog\\u003c/b\\u003e and went down .\"]},{\"id\":768364,\"collocation\":\"hunting dog\",\"relation\":\"N:nn:N\",\"basisword\":\"dog\",\"examples\":[\"Fantastic family dog as well as excellent \\u003cb\\u003ehunting\\u003c/b\\u003e \\u003cb\\u003edogs\\u003c/b\\u003e .\",\"The purpose of the breed was to serve as a \\u003cb\\u003ehunting\\u003c/b\\u003e \\u003cb\\u003edog\\u003c/b\\u003e .\",\"Recreation and culture Union Springs hosts annual field trials for \\u003cb\\u003ehunting\\u003c/b\\u003e \\u003cb\\u003edogs\\u003c/b\\u003e .\"]},{\"id\":768365,\"collocation\":\"big dog\",\"relation\":\"N:mod:A\",\"basisword\":\"dog\",\"examples\":[\"Fear did not enter the \\u003cb\\u003ebig\\u003c/b\\u003e \\u003cb\\u003edog\\u003c/b\\u003e 's soul .\",\"Have you got any \\u003cb\\u003ebig\\u003c/b\\u003e \\u003cb\\u003edogs\\u003c/b\\u003e in the yard ?\",\"From out of the trees on the cliff side appeared a \\u003cb\\u003ebig\\u003c/b\\u003e black \\u003cb\\u003edog\\u003c/b\\u003e .\"]},{\"id\":768366,\"collocation\":\"newfoundland dog\",\"relation\":\"N:nn:N\",\"basisword\":\"dog\",\"examples\":[\"These last two were quite small ones , -- the smaller not being larger than a big \\u003cb\\u003eNewfoundland\\u003c/b\\u003e \\u003cb\\u003edog\\u003c/b\\u003e .\",\"Then we have four cats and four kittens , and a great big \\u003cb\\u003eNewfoundland\\u003c/b\\u003e \\u003cb\\u003edog\\u003c/b\\u003e .\",\"The delight of the \\u003cb\\u003eNewfoundland\\u003c/b\\u003e \\u003cb\\u003edog\\u003c/b\\u003e appears to be in the preservation of the lives of the human race .\"]},{\"id\":768367,\"collocation\":\"stray dog\",\"relation\":\"N:mod:A\",\"basisword\":\"dog\",\"examples\":[\"Over the river , near the \\u003cb\\u003estray\\u003c/b\\u003e \\u003cb\\u003edogs\\u003c/b\\u003e \\\" home .\",\"Annie successfully escapes , running into a friendly \\u003cb\\u003estray\\u003c/b\\u003e \\u003cb\\u003edog\\u003c/b\\u003e .\",\"When we drive out of town some \\u003cb\\u003estray\\u003c/b\\u003e \\u003cb\\u003edog\\u003c/b\\u003e will follow us .\"]},{\"id\":768368,\"collocation\":\"to like dog\",\"relation\":\"V:obj:N\",\"basisword\":\"dog\",\"examples\":[\"The more I see of dogs , the better I \\u003cb\\u003elike\\u003c/b\\u003e \\u003cb\\u003edogs\\u003c/b\\u003e .\",\"By my soul , but I 've a mind to pistol you \\u003cb\\u003elike\\u003c/b\\u003e \\u003cb\\u003edogs\\u003c/b\\u003e .\",\"And draw my legs after me \\u003cb\\u003elike\\u003c/b\\u003e a lame \\u003cb\\u003eDog\\u003c/b\\u003e ?\"]},{\"id\":768369,\"collocation\":\"black dog\",\"relation\":\"N:mod:A\",\"basisword\":\"dog\",\"examples\":[\"At the heels of the horse followed the little \\u003cb\\u003eblack\\u003c/b\\u003e \\u003cb\\u003edog\\u003c/b\\u003e .\",\"The devil appeared as a handsome man , and as a \\u003cb\\u003eblack\\u003c/b\\u003e \\u003cb\\u003edog\\u003c/b\\u003e !\",\"From out of the trees on the cliff side appeared a big \\u003cb\\u003eblack\\u003c/b\\u003e \\u003cb\\u003edog\\u003c/b\\u003e .\"]},{\"id\":768370,\"collocation\":\"old dog\",\"relation\":\"N:mod:A\",\"basisword\":\"dog\",\"examples\":[\"Call the boys -- I 'm an \\u003cb\\u003eold\\u003c/b\\u003e \\u003cb\\u003edog\\u003c/b\\u003e .\",\"And now let 's look at the \\u003cb\\u003eold\\u003c/b\\u003e \\u003cb\\u003edog\\u003c/b\\u003e .\",\"Why do you strike me , you \\u003cb\\u003eold\\u003c/b\\u003e \\u003cb\\u003edog\\u003c/b\\u003e !\"]},{\"id\":768371,\"collocation\":\"barking of dog\",\"relation\":\"N:prep:N\",\"basisword\":\"dog\",\"examples\":[\"There was the throb of drums pulsating through the long-drawn yell , the screams of women , the barking of \\u003cb\\u003edogs\\u003c/b\\u003e ; and a moment later , like some devilish benediction , the bells of \\u003cb\\u003eBarking\\u003c/b\\u003e Church pealed out , mellow and jangling , in an exultation of blood .\",null,null]},{\"id\":768373,\"collocation\":\"dog bite\",\"relation\":\"V:subj:N\",\"basisword\":\"dog\",\"examples\":[\"Charley the \\u003cb\\u003edog\\u003c/b\\u003e runs to the rescue , \\u003cb\\u003ebiting\\u003c/b\\u003e into the monster 's leg .\",\"In fact , such techniques often cause a \\u003cb\\u003edog\\u003c/b\\u003e to become shy or even \\u003cb\\u003ebite\\u003c/b\\u003e out of fear .\",\"The witness ' \\u003cb\\u003edog\\u003c/b\\u003e attacked , \\u003cb\\u003ebiting\\u003c/b\\u003e the murderer on the left calf .\"]},{\"id\":768372,\"collocation\":\"dog run\",\"relation\":\"V:subj:N\",\"basisword\":\"dog\",\"examples\":[\"On goes the swing ; and then a little black \\u003cb\\u003edog\\u003c/b\\u003e comes \\u003cb\\u003erunning\\u003c/b\\u003e up .\",\"The farm is also a favorite \\u003cb\\u003edog\\u003c/b\\u003e walking and \\u003cb\\u003erunning\\u003c/b\\u003e location .\",\"As we approached , the \\u003cb\\u003edogs\\u003c/b\\u003e \\u003cb\\u003eran\\u003c/b\\u003e very fast .\"]},{\"id\":768374,\"collocation\":\"dog eat\",\"relation\":\"V:subj:N\",\"basisword\":\"dog\",\"examples\":[\"Jake ( the \\u003cb\\u003edog\\u003c/b\\u003e ) likes to dig up gardens and \\u003cb\\u003eeat\\u003c/b\\u003e bones .\",\"Also , \\u003cb\\u003edogs\\u003c/b\\u003e have occasionally been \\u003cb\\u003eeaten\\u003c/b\\u003e as an emergency food supply .\",\"The \\u003cb\\u003edog\\u003c/b\\u003e \\u003cb\\u003eeats\\u003c/b\\u003e the cock , and thus death comes into the world .\"]},{\"id\":768375,\"collocation\":\"dog breed\",\"relation\":\"N:nn:N\",\"basisword\":\"dog\",\"examples\":[\"The king shepherd is breed of dog developed in the 1990s using three different \\u003cb\\u003edog\\u003c/b\\u003e \\u003cb\\u003ebreeds\\u003c/b\\u003e .\",\"The club was formed to register , show , educate and promote the breeding of uncommon \\u003cb\\u003edog\\u003c/b\\u003e \\u003cb\\u003ebreeds\\u003c/b\\u003e .\",\"The following breed lists are based on genetic research , not traditional beliefs about \\u003cb\\u003edog\\u003c/b\\u003e \\u003cb\\u003ebreeds\\u003c/b\\u003e .\"]},{\"id\":768376,\"collocation\":\"to kill dog\",\"relation\":\"V:obj:N\",\"basisword\":\"dog\",\"examples\":[\"When we get nothing by hunting , we \\u003cb\\u003ekill\\u003c/b\\u003e the \\u003cb\\u003edogs\\u003c/b\\u003e .\",\"Doctors from town and a trained nurse and enough medicine to \\u003cb\\u003ekill\\u003c/b\\u003e a \\u003cb\\u003edog\\u003c/b\\u003e !\",\"Doctors from town , and a trained nurse , and enough medicine to \\u003cb\\u003ekill\\u003c/b\\u003e a \\u003cb\\u003edog\\u003c/b\\u003e .\"]},{\"id\":768377,\"collocation\":\"dog growl\",\"relation\":\"V:subj:N\",\"basisword\":\"dog\",\"examples\":[\"The \\u003cb\\u003edogs\\u003c/b\\u003e had strong , white teeth and \\u003cb\\u003egrowled\\u003c/b\\u003e fiercely .\",\"At sight of the negro the \\u003cb\\u003edog\\u003c/b\\u003e \\u003cb\\u003egrowled\\u003c/b\\u003e softly and crouched against my skirt .\",\"The \\u003cb\\u003edog\\u003c/b\\u003e \\u003cb\\u003egrowled\\u003c/b\\u003e below and rushed into the forest .\"]},{\"id\":768378,\"collocation\":\"isle of dog\",\"relation\":\"N:prep:N\",\"basisword\":\"dog\",\"examples\":[\"The line continued to carry goods services to the \\u003cb\\u003eIsle\\u003c/b\\u003e of \\u003cb\\u003eDogs\\u003c/b\\u003e until the 1960s .\",\"The two roads still exist , running down the centre and west side of the \\u003cb\\u003eIsle\\u003c/b\\u003e of \\u003cb\\u003eDogs\\u003c/b\\u003e respectively .\",\"But the road names refer to a ancient service at the far end of the \\u003cb\\u003eIsle\\u003c/b\\u003e of \\u003cb\\u003eDogs\\u003c/b\\u003e from the station .\"]},{\"id\":768379,\"collocation\":\"dog come\",\"relation\":\"V:subj:N\",\"basisword\":\"dog\",\"examples\":[\"You saw me and spoke to me to-day when the \\u003cb\\u003edog\\u003c/b\\u003e \\u003cb\\u003ecame\\u003c/b\\u003e at me .\",\"You had better keep the \\u003cb\\u003edogs\\u003c/b\\u003e \\u003cb\\u003ecoming\\u003c/b\\u003e as fast as you can .\",\"On goes the swing ; and then a little black \\u003cb\\u003edog\\u003c/b\\u003e \\u003cb\\u003ecomes\\u003c/b\\u003e running up .\"]},{\"id\":768380,\"collocation\":\"faithful dog\",\"relation\":\"N:mod:A\",\"basisword\":\"dog\",\"examples\":[\"You must go back and guard the house , and be a \\u003cb\\u003efaithful\\u003c/b\\u003e \\u003cb\\u003edog\\u003c/b\\u003e .\",\"The eyes have a look of dumb devotion like those of a \\u003cb\\u003efaithful\\u003c/b\\u003e \\u003cb\\u003edog\\u003c/b\\u003e .\",\"My little trunk has to wait outside in the hall like a \\u003cb\\u003efaithful\\u003c/b\\u003e \\u003cb\\u003edog\\u003c/b\\u003e .\"]},{\"id\":768381,\"collocation\":\"other dog\",\"relation\":\"N:mod:A\",\"basisword\":\"dog\",\"examples\":[\"As if in response comes a rush of \\u003cb\\u003eother\\u003c/b\\u003e \\u003cb\\u003edogs\\u003c/b\\u003e .\",\"Yet Finn 's dogs are not quite as \\u003cb\\u003eother\\u003c/b\\u003e \\u003cb\\u003edogs\\u003c/b\\u003e .\",\"How shall I dare to appear before the \\u003cb\\u003eother\\u003c/b\\u003e \\u003cb\\u003edogs\\u003c/b\\u003e ?\"]}]"
        }

        fun fixListAfterGsonParsing(list: ArrayList<Collocation>) {

            for (c in list) {
                if (c.relation == null) {
                    c.relation = RELATION_UNKNOWN
                }
                if (c.collocation == null) {
                    c.collocation = ""
                }
                if (c.sentences == null) {
                    c.sentences = arrayListOf()
                }
                if (c.examples == null) {
                    c.examples = arrayListOf()
                } else {
                    c.examples = ArrayList(c.examples.filterNotNull())

                }
            }
        }

        fun getListOfCollocationDog(): ArrayList<Collocation> {

            var collocations: ArrayList<Collocation>
            try {
                collocations =
                    Gson().fromJson(
                        getStringDog(),
                        object : TypeToken<List<Collocation?>?>() {}.type
                    )
                Collocation.fixListAfterGsonParsing(collocations)
                Collocation.convertExamplesToSentences(collocations)

//                setExampleTranslations(collocations)
            } catch (e: Exception) {
                return ArrayList<Collocation>()
            }
            return collocations

        }
    }
}

