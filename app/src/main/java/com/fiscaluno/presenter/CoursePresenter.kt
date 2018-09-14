package com.fiscaluno.presenter

import android.util.Log
import com.fiscaluno.contracts.CourseContract
import com.fiscaluno.network.FiscalunoApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.kodein.di.Kodein
import org.kodein.di.generic.instance

class CoursePresenter(val kodein: Kodein) : CourseContract.Presenter {

    private val api: FiscalunoApi by kodein.instance()
    private lateinit var view: CourseContract.View

    override fun bindView(view: CourseContract.View) {
        this.view = view
    }

    override fun findInstitutionCourses(institutionId: String) {
        api.findCourses(institutionId = institutionId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    when {
                        it.code() == 401 ->
                            //TODO: view.badRequest("Login expirado")
                            Log.e("CoursePresenter", "unable to authenticate user - 401")
                        it.code() == 500 ->
                            //TODO: view.badRequest("Não foi possível buscar as aulas.\nTente novamente mais tarde.")
                            Log.e("CoursePresenter", "unable to authenticate user - 500")
                        else -> {
                            view.showInstitutionCourses(it.body()?.result)
                        }
                    }
                },{
                    Log.e("CoursePresenter", "unable to authenticate user - ${it.message}")
                    //view.badRequest("Não foi possível buscar as aulas.\nTente novamente mais tarde.")
                })
    }


}
