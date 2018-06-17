package com.kotensky.cargocarriers.view.activities

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.afollestad.materialdialogs.MaterialDialog
import com.kotensky.cargocarriers.R
import com.kotensky.cargocarriers.application.CarrierApplication
import com.kotensky.cargocarriers.listeners.OnCarrierRecyclerClickListener
import com.kotensky.cargocarriers.model.entities.CarrierEntity
import com.kotensky.cargocarriers.view.MVPViews.CarriersView
import com.kotensky.cargocarriers.view.adapters.CarrierListAdapter
import com.workhardapp.presenters.CarriersPresenter
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity(), CarriersView, OnCarrierRecyclerClickListener {

    @Inject
    lateinit var carriersPresenter: CarriersPresenter

    private val carrierList = ArrayList<CarrierEntity>()
    private var adapter: CarrierListAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        CarrierApplication.applicationComponent.inject(this)

        setContentView(R.layout.activity_main)


        adapter = CarrierListAdapter(carrierList, this)
        carriers_recycler.layoutManager = LinearLayoutManager(this)
        carriers_recycler.adapter = adapter

        carriersPresenter.view = this


        val carrierEntity = CarrierEntity()
        val phones = ArrayList<String>()
        val directions = ArrayList<String>()
        phones.add("+380632783883")
        phones.add("+380632783883")
        directions.add("Польща")
        directions.add("Нідерланди")
        carrierEntity.companyName = "Стас і КО"
        carrierEntity.basePlace = "Вінниця"
        carrierEntity.lastCallDate = Date()
        carrierEntity.phones = phones
        carrierEntity.directions = directions
        carrierEntity.description = "Оч нраіца"
        carrierEntity.contactPerson = "Стас"

        carriersPresenter.addCarrier(carrierEntity)



        carriersPresenter.loadCarriers()
    }

    override fun showCarriers(carriersList: List<CarrierEntity>?) {
        this.carrierList.clear()
        carriersList?.let { this.carrierList.addAll(it) }
        adapter?.notifyDataSetChanged()
    }

    override fun onSuccessAddTask() {
        carriersPresenter.loadCarriers()
    }

    override fun onSuccessDeleteCarrier() {
        carriersPresenter.loadCarriers()
    }

    override fun onSuccessEditCarrier() {
        carriersPresenter.loadCarriers()
    }

    override fun onClickCall(position: Int) {
        val phones = carrierList[position].phones
        if (phones == null || phones.isEmpty()) {
            return
        }
        if (phones.size == 1) {
            carrierList[position].lastCallDate = Date()
            carriersPresenter.editCarrier(carrierList[position])
            val intent = Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phones[0], null))
            startActivity(intent)
        } else {
            MaterialDialog.Builder(this)
                    .title(R.string.call_dialog_title)
                    .items(phones)
                    .itemsCallbackSingleChoice(-1, MaterialDialog.ListCallbackSingleChoice { dialog, view, which, text ->
                        carrierList[position].lastCallDate = Date()
                        carriersPresenter.editCarrier(carrierList[position])
                        val intent = Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", text.toString(), null))
                        startActivity(intent)
                        true
                    })
                    .show()
        }
    }

}
