package com.kotensky.cargocarriers.view.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kotensky.cargocarriers.R
import com.kotensky.cargocarriers.listeners.OnCarrierRecyclerClickListener
import com.kotensky.cargocarriers.model.entities.CarrierEntity
import kotlinx.android.synthetic.main.carrier_list_item.view.*
import java.text.SimpleDateFormat
import java.util.*

class CarrierListAdapter(
        private val carrierList: ArrayList<CarrierEntity>,
        private var onCarrierRecyclerClickListener: OnCarrierRecyclerClickListener)
    : RecyclerView.Adapter<CarrierListAdapter.ViewHolder>() {

    private var dateFormat = SimpleDateFormat("HH:mm dd.MM.yyyy", Locale.getDefault())

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
            ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.carrier_list_item, parent, false))

    override fun getItemCount(): Int = carrierList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val carrier = carrierList[position]
        if (carrier.lastCallDate != null) {
            holder.itemView?.last_call_date_txt?.text = holder.itemView?.last_call_date_txt?.context?.getString(
                    R.string.last_call_temp, dateFormat.format(carrier.lastCallDate))
        }
        holder.itemView?.last_call_date_txt?.visibility =
                if (carrier.lastCallDate == null)
                    View.GONE
                else
                    View.VISIBLE
        holder.itemView?.company_name_txt?.text = carrier.companyName
        holder.itemView?.contact_person_txt?.text = carrier.contactPerson
        holder.itemView?.description_txt?.text = carrier.description
        holder.itemView?.base_place_txt?.text = carrier.basePlace
        holder.itemView?.directions_txt?.text = holder.itemView?.directions_txt?.context?.getString(
                R.string.directions_temp, carrier.directions.toString())
        holder.itemView?.call_img?.setOnClickListener{
            onCarrierRecyclerClickListener.onClickCall(position)
        }

    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}