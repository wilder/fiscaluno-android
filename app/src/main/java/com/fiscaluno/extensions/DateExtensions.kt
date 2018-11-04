package com.fiscaluno.extensions

import java.text.SimpleDateFormat
import java.util.*


fun Date.format() : String = SimpleDateFormat("dd/MM/yyyy").format(this)