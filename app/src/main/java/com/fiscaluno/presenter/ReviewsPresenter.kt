package com.fiscaluno.presenter

import android.util.Log
import com.fiscaluno.contracts.ReviewsContract
import com.fiscaluno.model.Course
import com.fiscaluno.model.GeneralReview
import com.fiscaluno.network.FiscalunoApi
import com.google.firebase.firestore.FirebaseFirestore
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.kodein.di.Kodein
import org.kodein.di.generic.instance

/**
 * Created by Wilder on 30/07/17.
 */
class ReviewsPresenter(val kodein: Kodein) : ReviewsContract.Presenter {

    private val api: FiscalunoApi by kodein.instance()
    private var view: ReviewsContract.View? = null
    private var db: FirebaseFirestore = FirebaseFirestore.getInstance()
    private val GENERAL_REVIEWS = "GeneralReview"

    override fun bindView(view: ReviewsContract.View) {
        this.view = view
    }

    override fun loadDetailedReviews(institutionId: Int, courseId: Int?) {
        api.getDetailedReviews(institutionId, courseId)
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

    override fun loadGeneralReviews(institutionId: Int, courseId: Int?) {

        val db = db.collection(GENERAL_REVIEWS)
                .whereEqualTo("institutionId", institutionId)

        if (courseId != null) db.whereEqualTo("courseId", courseId)

        db.get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val reviews = task.result?.toObjects(GeneralReview::class.java)
                    Log.d("generalReviews", reviews.toString())
                    view?.setupGeneralReviews(reviews)
                } else {
                    Log.w("generalReviews", "Error getting documents.", task.getException())
                }
            }


    }

}