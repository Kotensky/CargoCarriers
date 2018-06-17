package com.workhardapp.presenters

import com.kotensky.cargocarriers.model.entities.CarrierEntity
import com.kotensky.cargocarriers.model.room.dao.CarrierDao
import com.kotensky.cargocarriers.view.MVPViews.CarriersView
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class CarriersPresenter @Inject constructor(val carrierDao: CarrierDao) {

    private val compositeDisposable = CompositeDisposable()
    var view: CarriersView? = null

    fun loadCarriers() {
        compositeDisposable.add(carrierDao.getAllCarrier()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    view?.showCarriers(it)
                }, {

                })
        )
    }


    fun addCarrier(carrierEntity: CarrierEntity) {
        compositeDisposable.add(Completable.fromAction { carrierDao.insertCarrier(carrierEntity) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    view?.onSuccessAddTask()
                }, {

                }))
    }

    fun editCarrier(carrierEntity: CarrierEntity) {
        compositeDisposable.add(Completable.fromAction { carrierDao.updateCarrier(carrierEntity) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    view?.onSuccessEditCarrier()
                }, {

                }))
    }

    fun deleteCarrier(carrierEntity: CarrierEntity) {
        compositeDisposable.add(Completable.fromAction { carrierDao.deleteCarrier(carrierEntity) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    view?.onSuccessDeleteCarrier()
                }, {

                }))
    }

    fun destroy() {
        compositeDisposable.dispose()
        view = null
    }

}