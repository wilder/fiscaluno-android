package com.fiscaluno.rating.detailedReview

import android.util.Log
import com.fiscaluno.contracts.DetailedReviewContract
import com.fiscaluno.model.DetailedReview
import com.fiscaluno.model.GeneralReview
import com.fiscaluno.network.FiscalunoApi
import com.google.firebase.firestore.FirebaseFirestore
import com.stepstone.stepper.StepperLayout
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.kodein.di.Kodein
import org.kodein.di.generic.instance
import java.util.*

/**
 * Created by Wilder on 25/07/17.
 */
class DetailedReviewPresenter(kodein: Kodein) : DetailedReviewContract.Presenter {

    private val DETAILED_REVIEW_TYPES_REFERENCE = "DetailedReviewTypes"
    private val TAG = "DetailedReviewPresenter"
    private val api: FiscalunoApi by kodein.instance()
    private lateinit var view: DetailedReviewContract.View
    private lateinit var db: FirebaseFirestore

    override fun bindView(view: DetailedReviewContract.View) {
        this.view = view
        db = FirebaseFirestore.getInstance()
    }

    /**
     * load detailed review types from firestore
     */
    override fun loadReviewTypes() {
        val reviews  = java.util.ArrayList<DetailedReview>()

        db.collection(DETAILED_REVIEW_TYPES_REFERENCE)
                .get()
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        reviews.clear()
                        task.result.toObjects(DetailedReviewTypes::class.java)
                                .forEachIndexed {
                                    index, detailedReviewTypes ->
                                    var review = DetailedReview()
                                    review.description = detailedReviewTypes.description!!
                                    review.type = index+1
                                    reviews.add(review)
                        }
                    }
                    view?.setupDetailedReviewsList(reviews)
                }
    }

    override fun saveDetailedReviews(detailedReviews: List<DetailedReview>,
                                     generalReview: GeneralReview,
                                     callback: StepperLayout.OnCompleteClickedCallback?) {

        val detailedReviewBody =
                DetailedReviewBody(
                        generalReview.institutionId!!,
                        generalReview.courseInfo!!.courseId!!,
                        detailedReviews
                )

        api.postDetailedReview(generalReview.id!!, detailedReviewBody)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    when {
                        it.code() in 400..503 -> {
                            Log.e(TAG, "unable to save review ${it.code()} " +
                                    it.message())
                            view.error("Unable to save review")
                        }

                        it.code() in 200..204 -> {
                            view.success(callback)
                        }
                    }
                },{
                    view.error("Unable to save review")
                })
    }

}