package com.fiscaluno.rating.detailedReview

import com.fiscaluno.model.DetailedReview
import com.google.gson.annotations.SerializedName

data class DetailedReviewBody(
        @SerializedName("institution_id") val institutionId: Int,
        @SerializedName("course_id") val courseId: Int,
        @SerializedName("detailed_reviews") val detailedReviews: List<DetailedReview>
)