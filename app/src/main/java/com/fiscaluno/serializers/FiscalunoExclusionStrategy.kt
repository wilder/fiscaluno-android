package com.fiscaluno.serializers

import com.fiscaluno.model.Review
import com.google.gson.ExclusionStrategy
import com.google.gson.FieldAttributes

class FiscalunoExclusionStrategy : ExclusionStrategy {

    override fun shouldSkipClass(arg0: Class<*>): Boolean = false

    override fun shouldSkipField(f: FieldAttributes): Boolean =
            f.declaringClass == Review::class.java && f.name == "createdAt"

}