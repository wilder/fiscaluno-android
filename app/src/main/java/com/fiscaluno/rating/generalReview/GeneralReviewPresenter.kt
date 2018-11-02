package com.fiscaluno.rating.generalReview

import android.util.Log
import com.fiscaluno.model.GeneralReview
import com.fiscaluno.network.FiscalunoApi
import com.google.firebase.firestore.FirebaseFirestore
import com.stepstone.stepper.StepperLayout
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.kodein.di.Kodein
import org.kodein.di.generic.instance


/**
 * Created by Wilder on 12/10/17.
 */

class GeneralReviewPresenter(val kodein: Kodein) : GeneralReviewContract.Presenter{

    private lateinit var view: GeneralReviewContract.View
    private val api: FiscalunoApi by kodein.instance()

    override fun bindView(view: GeneralReviewContract.View) {
        this.view = view
    }

    override fun saveGeneralReview(generalReview: GeneralReview, callback: StepperLayout.OnNextClickedCallback?) {
        api.postGeneralReview(generalReview)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    when {
                        it.code() in 400..503 -> {
                            Log.e("GeneralReviewPresenter", "unable to save review ${it.code()} " +
                                    it.message())
                            view.error("Unable to save review")
                        }

                        it.code() in 200..204 -> {
                            view.success(it.body()?.result!!, callback)
                        }
                    }
                },{
                    view.error("Unable to save review")
                })
    }
}
