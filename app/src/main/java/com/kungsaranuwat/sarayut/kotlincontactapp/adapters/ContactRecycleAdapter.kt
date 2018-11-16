package com.kungsaranuwat.sarayut.kotlincontactapp.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.kungsaranuwat.sarayut.kotlincontactapp.R
import com.kungsaranuwat.sarayut.kotlincontactapp.models.ContactModel
import com.squareup.picasso.Picasso


class ContactRecycleAdapter (val context: Context, val contacts: List<ContactModel>, private val itemClick: (ContactModel) -> Unit) : RecyclerView.Adapter<ContactRecycleAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(context).inflate(R.layout.contact_list ,parent,false)
        return Holder(view, itemClick)
    }

    override fun getItemCount(): Int {
        return contacts.count()
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder?.binding(contacts[position], context)
    }

    inner class Holder(itemView: View?, val itemClick: (ContactModel) -> Unit) : RecyclerView.ViewHolder(itemView!!) {

        private val profileImage = itemView?.findViewById<ImageView>(R.id.profileImageView)
        private val profileName = itemView?.findViewById<TextView>(R.id.profileName)
        private val profileEmail = itemView?.findViewById<TextView>(R.id.profileEmail)

        @SuppressLint("SetTextI18n")
        fun binding(contact: ContactModel, context:Context) {

            Picasso.with(context).load(contact.picture.large).into(profileImage);
            profileName?.text = "${contact.name.title} ${contact.name.first} ${contact.name.last}"
            profileEmail?.text = contact.email

            itemView.setOnClickListener { itemClick(contact) }
        }

    }

}