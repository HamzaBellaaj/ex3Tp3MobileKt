package com.isetr.tp3ex3

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    var email:String,
    val password:String,
): Parcelable