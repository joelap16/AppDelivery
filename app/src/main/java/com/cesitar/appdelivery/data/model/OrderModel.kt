package com.cesitar.appdelivery.data.model

data class OrderModel(
    val uid : String?,
    val enterprise : String,
    val client : String,
    val documentNumber : String,
    val address : String,
    val hourDelivered : String,
    val articles : String //then change to list (optional)
) {
}