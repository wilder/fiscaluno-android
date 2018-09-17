package com.fiscaluno.serializers

import com.fiscaluno.model.Course
import com.fiscaluno.model.Institution
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.google.gson.JsonParseException

import java.lang.reflect.Type

class CourseSerializer : JsonDeserializer<Course> {
    @Throws(JsonParseException::class)
    override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): Course {

        val jsonObject = json.asJsonObject

        val course = Course()
        course.averageRating = jsonObject.get("course_average_rating").asFloat
        course.ratedByCount = jsonObject.get("course_rated_by_count").asInt
        course.name = jsonObject.get("course_name").asString
        course.courseType = jsonObject.get("course_type").asString

        course.coursePeriods = jsonObject.get("course_periods").asJsonArray.asJsonArray
                .map { it.asString }

        course.monthlyValueRange = jsonObject.get("course_monthly_value_range").asJsonArray
                .map { it.asFloat }

        course.timeToGraduateRange = jsonObject.get("course_time_to_graduate_range").asJsonArray
                .map { it.asInt }


        val institution = Institution()
        institution.id = jsonObject.get("institution_id").asString
        institution.name = jsonObject.get("institution_name")?.asString
        institution.imageUri = jsonObject.get("institution_image_url")?.asString.toString()

        course.institution = institution

        return course
    }
}
