package com.kotensky.cargocarriers.view.MVPViews

import com.kotensky.cargocarriers.model.entities.CarrierEntity

interface CarriersView {

    fun showCarriers(carriersList: List<CarrierEntity>?)

    fun onSuccessAddTask()

    fun onSuccessDeleteCarrier()

    fun onSuccessEditCarrier()
}