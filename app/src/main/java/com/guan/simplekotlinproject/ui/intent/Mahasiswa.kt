package com.guan.simplekotlinproject.ui.intent

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Mahasiswa(
    val name: String?,
    val clas: String?,
    val age: Int?,
    val email: String?,
    val city: String?
): Parcelable