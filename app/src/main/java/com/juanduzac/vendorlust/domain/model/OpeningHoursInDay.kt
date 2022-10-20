package com.juanduzac.vendorlust.domain.model

data class OpeningHoursInDay(
    val id: Int?,
    val opensAt: String?,
    val closesAt: String?,
    val closesLate: Boolean?,
)
