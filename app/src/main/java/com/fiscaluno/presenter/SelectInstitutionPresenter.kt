package com.fiscaluno.presenter

import android.util.Log
import com.fiscaluno.contracts.SelectInstitutionContract
import com.fiscaluno.data.FiscalunoApi
import com.fiscaluno.model.Institution
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.kodein.di.Kodein
import org.kodein.di.generic.instance

/**
 * Created by Wilder on 16/07/17.
 */

class SelectInstitutionPresenter(val kodein: Kodein) : SelectInstitutionContract.Presenter {

    private val api: FiscalunoApi by kodein.instance()
    var view: SelectInstitutionContract.View? = null

    override fun bindView(view: SelectInstitutionContract.View) {
        this.view = view
    }

    override fun loadMainInstitutions() {
        api.findInstitutions()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    when {
                        it.code() == 401 ->
                            //TODO: view.badRequest("Login expirado")
                            Log.e("SelectInstPresenter", "unable to authenticate user - 401")
                        it.code() == 500 ->
                            //TODO: view.badRequest("Não foi possível buscar as aulas.\nTente novamente mais tarde.")
                            Log.e("SelectInstPresenter", "unable to authenticate user - 500")
                        else -> {
                            view?.updateInstitutionList(it.body())
                            view?.setupInstitutionAutocomplete(it.body())
                        }
                    }
                },{
                    Log.e("SelectInstPresenter", "unable to authenticate user - ${it.message}")
                    //view.badRequest("Não foi possível buscar as aulas.\nTente novamente mais tarde.")
                })
    }

    override fun searchInstitutions() {
        //TODO: Change to list of Institutions and get Institutions from Web Service

    }
}
