package com.fiscaluno.presenter

import android.util.Log
import com.fiscaluno.contracts.SearchContract
import com.fiscaluno.contracts.SelectInstitutionContract
import com.fiscaluno.model.SearchFilter
import com.fiscaluno.network.FiscalunoApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.kodein.di.Kodein
import org.kodein.di.generic.instance

class SearchPresenter(val kodein: Kodein) : SearchContract.Presenter {

    private val api: FiscalunoApi by kodein.instance()
    private lateinit var view: SearchContract.View

    override fun bindView(view: SearchContract.View) {
        this.view = view
    }

    override fun searchCourse(searchFilter: SearchFilter, page: Int, pageSize: Int) {
        api.findCourses()
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
                            view.displayCourses(it.body()?.result)
                        }
                    }
                },{
                    Log.e("SelectInstPresenter", "unable to authenticate user - ${it.message}")
                    //view.badRequest("Não foi possível buscar as aulas.\nTente novamente mais tarde.")
                })
    }

    override fun searchInstitution(searchFilter: SearchFilter, page: Int, pageSize: Int) {
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
                            view.displayInstitutions(it.body()?.result)
                        }
                    }
                },{
                    Log.e("SelectInstPresenter", "unable to authenticate user - ${it.message}")
                    //view.badRequest("Não foi possível buscar as aulas.\nTente novamente mais tarde.")
                })
    }


}