package com.example.aplikasipemesanan

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PesananListAdapter internal constructor(context: Context): RecyclerView.Adapter<PesananListAdapter.PesananViewHolder>() {
    private val inflater: LayoutInflater = LayoutInflater.from(context)

    private var pesanan = emptyList<Pesanan>()

    inner class PesananViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView) {
        val pesananItemView: TextView = itemView.findViewById(R.id.textView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PesananViewHolder {
        val itemView = inflater.inflate(R.layout.recyclerview_item, parent, false)
        return PesananViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PesananViewHolder, position: Int) {
        val current = pesanan[position]
        holder.pesananItemView.text = current.pesanan
    }

    internal fun setPesanan(pesanan: List<Pesanan>) {
        this.pesanan = pesanan
        notifyDataSetChanged()
    }

    override fun getItemCount() = pesanan.size

}