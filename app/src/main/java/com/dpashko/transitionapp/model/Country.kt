package com.dpashko.transitionapp.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Country(val id: Long = 0, val preview: String = "", val background: String = "") :
    Parcelable
