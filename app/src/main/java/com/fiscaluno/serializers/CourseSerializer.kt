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

        val institution = Institution()
        institution.id = jsonObject.get("institution_id").asString
        institution.name = jsonObject.get("institution_name")?.asString
        institution.imageUri = jsonObject.get("institution_image_url")?.asString.toString()

        course.institution = institution

        return course
    }
}
