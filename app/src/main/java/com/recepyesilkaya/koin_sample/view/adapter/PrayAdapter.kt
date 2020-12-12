package com.recepyesilkaya.koin_sample.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.recepyesilkaya.koin_sample.R
import com.recepyesilkaya.koin_sample.data.model.Pray
import kotlinx.android.synthetic.main.item_pray.view.*

interface SelectPrayCallback {
    fun onItemClick(pray: Pray)
}

class PrayAdapter(private val selectPrayCallback: SelectPrayCallback) :
    RecyclerView.Adapter<PrayAdapter.PrayViewHolder>() {

    private var prayList = ArrayList<Pray>()

    class PrayViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PrayViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_pray, parent, false)
        return PrayViewHolder(view)
    }

    override fun onBindViewHolder(holder: PrayViewHolder, position: Int) {
        val position = prayList[position]
        holder.itemView.tvPrayName.text = position.MiladiTarihUzun

        holder.itemView.cvPrayItem.animation =
            AnimationUtils.loadAnimation(holder.itemView.context, R.anim.anim)

        holder.itemView.setOnClickListener { selectPrayCallback.onItemClick(position) }
    }

    override fun getItemCount(): Int = prayList.size

    fun updatePrayList(prayListX: List<Pray>) {
        prayList.clear()
        prayList.addAll(prayListX)
        notifyDataSetChanged()
    }
}