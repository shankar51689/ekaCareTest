package com.ms.ekacaretest

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ms.ekacaretest.database.PersonDetails
import com.ms.ekacaretest.databinding.RecyclerViewBinding

class PersonListAdapter(private var personList: List<PersonDetails>): RecyclerView.Adapter<PersonListAdapter.PersonViewHolder>() {

    private var localList: List<PersonDetails> = personList

    inner class PersonViewHolder(val binding: RecyclerViewBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        return PersonViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.recycler_view, parent, false))
    }

    override fun getItemCount(): Int {
        return localList.size
    }

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {

        with(holder.binding) {
            localList[position].let {
                tvName.text      = it.name
                tvAge.text       = it.age
                tvAddress.text   = it.address
                tvDob.text       = it.dob
            }
        }

    }

    fun updateData(updatedList: List<PersonDetails>) {
        localList = updatedList
        notifyItemMoved(0,personList.size)
    }
}