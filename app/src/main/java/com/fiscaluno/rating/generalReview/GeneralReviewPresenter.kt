package com.fiscaluno.rating.generalReview

import com.fiscaluno.model.Course
import com.fiscaluno.model.GeneralReview
import com.fiscaluno.model.Institution
import com.fiscaluno.network.FiscalunoApi
import com.google.firebase.firestore.FirebaseFirestore
import com.stepstone.stepper.StepperLayout
import org.kodein.di.Kodein
import org.kodein.di.generic.instance
import java.util.*


/**
 * Created by Wilder on 12/10/17.
 */

class GeneralReviewPresenter(val kodein: Kodein) : GeneralReviewContract.Presenter{

    private lateinit var view: GeneralReviewContract.View
    private val api: FiscalunoApi by kodein.instance()
    private lateinit var db: FirebaseFirestore
    private val GENERAL_REVIEWS = "GeneralReview"


    override fun bindView(view: GeneralReviewContract.View) {
        this.view = view
        this.db = FirebaseFirestore.getInstance()
    }

    override fun saveGeneralReview(generalReview: GeneralReview, callback: StepperLayout.OnNextClickedCallback?) {
        val generalReviewDTO = generalReview.copy()
        val collection = db.collection(GENERAL_REVIEWS)
        generalReviewDTO.courseId = generalReview.courseInfo?.courseId!!
        generalReviewDTO.courseInfo = null
        generalReviewDTO.institutionId = generalReview.institutionId
        generalReviewDTO.rate = generalReview.rate
        generalReviewDTO.createdAt = Date()
        generalReviewDTO.studentId = generalReview.studentId
        collection.add(generalReviewDTO)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        updateInstitutionAverageRating(generalReview)
                        updateCourseAverageRating(generalReviewDTO)
                        view.success(generalReview, callback)
                    }
                    else {
                        view.error("Unable to save review")
                    }
                }
    }

    private fun updateCourseAverageRating(generalReview: GeneralReview) {
        val courseReference = db.collection("courses").document(generalReview.courseId.toString())
        courseReference
                .get().addOnCompleteListener {
                    if (it.isSuccessful) {
                        val course = it.result.toObject(Course::class.java)
                        courseReference.update(
                                mapOf("ratedByCount" to course!!.ratedByCount + 1,
                                        "averageRating" to (course.averageRating + generalReview.rate!!) / (course.ratedByCount + 1))
                        )
                    }
                }
    }

    private fun updateInstitutionAverageRating(generalReview: GeneralReview) {
        val institutionReference = db.collection("Institutions").document(generalReview.institutionId.toString())
        institutionReference
                .get().addOnCompleteListener {
                    if (it.isSuccessful) {
                        val institution = it.result.toObject(Institution::class.java)
                        institutionReference.update(
                                mapOf("ratedByCount" to institution!!.ratedByCount + 1,
                                        "averageRating" to (institution.averageRating + generalReview.rate!!) / (institution.ratedByCount + 1))
                        )
                    }
                }
    }
}
