package com.fiscaluno.extensions

import android.net.Uri

/**
 * Created by Wilder on 16/07/17.
 */

fun String?.toUri() : Uri{
    return Uri.parse(this)
}