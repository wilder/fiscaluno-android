package com.fiscaluno.presenter

import android.util.Log
import com.fiscaluno.contracts.ReviewsContract
import com.fiscaluno.network.FiscalunoApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.kodein.di.Kodein
import org.kodein.di.generic.instance

/**
 * Created by Wilder on 30/07/17.
 */
class InstitutionDetailPresenter(val kodein: Kodein) : ReviewsContract.Presenter {

    private val api: FiscalunoApi by kodein.instance()
    private var view: ReviewsContract.View? = null

    override fun bindView(view: ReviewsContract.View) {
        this.view = view
    }

    override fun loadDetailedReviews(institutionId: String) {
        api.getDetailedReviews(institutionId = institutionId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    when {
                        it.code() == 401 ->
                            //TODO: view.badRequest("Login expirado")
                            Log.e("InstDetailPresenter", "unable to authenticate user - 401")
                        it.code() == 500 ->
                            //TODO: view.badRequest("Não foi possível buscar as aulas.\nTente novamente mais tarde.")
                            Log.e("InstDetailPresenter", "unable to authenticate user - 500")
                        else -> {
                            val detailedReviews = it.body()?.result
                            view?.setupDetailedReviews(detailedReviews)
                        }
                    }
                },{
                    Log.e("InstDetailPresenter", "unable to authenticate user - ${it.message}")
                    //view.badRequest("Não foi possível buscar as aulas.\nTente novamente mais tarde.")
                })
    }

    override fun loadGeneralReviews(institutionId: String) {
        api.getGeneralReviews(institutionId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    when {
                        it.code() == 401 ->
                            //TODO: view.badRequest("Login expirado")
                            Log.e("InstDetailPresenter", "unable to authenticate user - 401")
                        it.code() == 500 ->
                            //TODO: view.badRequest("Não foi possível buscar as aulas.\nTente novamente mais tarde.")
                            Log.e("InstDetailPresenter", "unable to authenticate user - 500")
                        else -> {
                            val generalReviews = it.body()?.result
                            view?.setupGeneralReviews(generalReviews)
                        }
                    }
                },{
                    Log.e("InstDetailPresenter", "unable to authenticate user - ${it.message}")
                    //view.badRequest("Não foi possível buscar as aulas.\nTente novamente mais tarde.")
                })
    }

}