package com.fiscaluno.presenter

import android.util.Log
import com.fiscaluno.contracts.SelectInstitutionContract
import com.fiscaluno.model.Institution
import com.fiscaluno.network.FiscalunoApi
import com.google.firebase.firestore.FirebaseFirestore
import org.kodein.di.Kodein
import org.kodein.di.generic.instance

/**
 * Created by Wilder on 16/07/17.
 */

class SelectInstitutionPresenter(val kodein: Kodein) : SelectInstitutionContract.Presenter {

    private val api: FiscalunoApi by kodein.instance()
    var view: SelectInstitutionContract.View? = null
    private var db: FirebaseFirestore = FirebaseFirestore.getInstance()
    private val INSTITUTIONS = "Institutions"

    override fun bindView(view: SelectInstitutionContract.View) {
        this.view = view
    }

    override fun loadMainInstitutions() {
        val collection = db.collection(INSTITUTIONS)
        collection
                .get()
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val institutions = task.result?.toObjects(Institution::class.java)
                        view?.updateInstitutionList(institutions)
                        view?.setupInstitutionAutocomplete(institutions)
                    } else {
                        Log.w("loadMainInstitutions", "Error getting documents.", task.getException())
                    }
                }
    }

}
