package com.dpashko.transitionapp.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Countries(val list: List<Country> = emptyList()) : Parcelable
