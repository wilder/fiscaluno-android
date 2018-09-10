package com.fiscaluno.serializers;

import com.fiscaluno.model.Course;
import com.fiscaluno.model.Institution;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

public class CourseSerializer implements JsonDeserializer<Course> {
    @Override
    public Course deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        JsonObject jsonObject = json.getAsJsonObject();

        Course course = new Course();
        course.setAverageRating(jsonObject.get("course_average_rating").getAsFloat());
        course.setRatedByCount(jsonObject.get("course_rated_by_count").getAsInt());
        course.setName(jsonObject.get("course_name").getAsString());
        course.setCourseType(jsonObject.get("course_type").getAsString());

        Institution institution = new Institution();
        institution.setId(jsonObject.get("institution_id").getAsString());
        institution.setName(jsonObject.get("institution_name").getAsString());
        institution.setImageUri(jsonObject.get("institution_image_url").getAsString());

        course.setInstitution(institution);

        return course;
    }
}
