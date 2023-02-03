package com.albertson.codetest.acronymfinder.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.albertson.codetest.acronymfinder.databinding.ListItemAcronymResultsBinding
import com.albertson.codetest.acronymfinder.network.model.Lf

class MainAdapter : RecyclerView.Adapter<MainViewHolder>() {

    var results: List<Lf> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val binding = ListItemAcronymResultsBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(results[position])
    }

    override fun getItemCount(): Int = results.size
}

class MainViewHolder(private val viewBinding: ListItemAcronymResultsBinding) :
    RecyclerView.ViewHolder(viewBinding.root) {

    fun bind(lf: Lf) {
        viewBinding.item = lf
    }
}