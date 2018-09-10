package com.fiscaluno.presenter

import android.util.Log
import com.fiscaluno.contracts.MainContract
import com.fiscaluno.network.FiscalunoApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.kodein.di.Kodein
import org.kodein.di.generic.instance

/**
 * Created by Wilder on 14/08/17.
 */
class MainPresenter(val kodein: Kodein) : MainContract.Presenter {

    private val api: FiscalunoApi by kodein.instance()
    lateinit var view: MainContract.View

    override fun bindView(view: MainContract.View) {
        this.view = view
    }

    /**
     * Loads the top rated Institutions
     */
    override fun loadTopInstitutions() {
        //TODO: Load top Institutions
        api.findInstitutions(sortBy = "rate", pageSize = 4)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    when {
                        it.code() == 401 ->
                            //TODO: view.badRequest("Login expirado")
                            Log.e("MainPresenter", "unable to authenticate user - 401")
                        it.code() == 500 ->
                            //TODO: view.badRequest("Não foi possível buscar as aulas.\nTente novamente mais tarde.")
                            Log.e("MainPresenter", "unable to authenticate user - 500")
                        else -> {
                            val institutions = it.body()?.result
                            view.showTopInstitutions(institutions)
                        }
                    }
                },{
                    Log.e("MainPresenter", "unable to authenticate user - ${it.message}")
                    //view.badRequest("Não foi possível buscar as aulas.\nTente novamente mais tarde.")
                })

    }

    override fun loadTopCourses() {
        api.findCourses(sortBy = "rate", pageSize = 4)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    when {
                        it.code() == 401 ->
                            //TODO: view.badRequest("Login expirado")
                            Log.e("MainPresenter", "unable to authenticate user - 401")
                        it.code() == 500 ->
                            //TODO: view.badRequest("Não foi possível buscar as aulas.\nTente novamente mais tarde.")
                            Log.e("MainPresenter", "unable to authenticate user - 500")
                        else -> {
                            val courses = it.body()?.result
                            view.showTopCourses(courses)
                        }
                    }
                },{
                    Log.e("MainPresenter", "unable to authenticate user - ${it.message}")
                    //view.badRequest("Não foi possível buscar as aulas.\nTente novamente mais tarde.")
                })
    }

}