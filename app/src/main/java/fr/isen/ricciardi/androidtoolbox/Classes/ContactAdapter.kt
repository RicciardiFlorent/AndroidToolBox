package fr.isen.ricciardi.androidtoolbox.Classes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fr.isen.ricciardi.androidtoolbox.R
import kotlinx.android.synthetic.main.recycler_view_contact_cell.view.*

class ContactAdapter(val contacts: ArrayList<ContactModel>):RecyclerView.Adapter<ContactAdapter.ContactViewHolder>(){
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ContactAdapter.ContactViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_contact_cell, parent,false)
        return ContactViewHolder(view)
    }

    override fun getItemCount(): Int {
        return contacts.count()
    }

    override fun onBindViewHolder(holder: ContactAdapter.ContactViewHolder, position: Int) {
        val contact = contacts[position]
        holder.bind(contact)
    }

    class ContactViewHolder(val view: View): RecyclerView.ViewHolder(view){
        fun bind(contact: ContactModel){
            view.nameTextView.text= contact.displayName
        }
    }
}

