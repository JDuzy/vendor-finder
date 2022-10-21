package com.juanduzac.vendorlust.domain.model

data class OpeningHoursInDay(
    val id: Long?,
    val opensAt: String?,
    val closesAt: String?,
    val closesLate: Boolean?,
)
