package com.dpashko.transitionapp.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Countries(
    @SerializedName("countries") val list: List<Country> = emptyList()
) : Parcelable
