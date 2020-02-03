package fr.isen.ricciardi.androidtoolbox.Classes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fr.isen.ricciardi.androidtoolbox.R
import kotlinx.android.synthetic.main.recycler_view_contact_cell.view.*
import kotlinx.android.synthetic.main.recycler_view_contact_cell.view.nameTextView
import kotlinx.android.synthetic.main.recycler_view_contact_cell_webservice.view.*

class UserAdapter(val users: ArrayList<UserWS>):RecyclerView.Adapter<UserAdapter.UserViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserAdapter.UserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_contact_cell_webservice, parent,false)
        return UserAdapter.UserViewHolder(view)
    }

    override fun getItemCount(): Int {
        return users.count()
    }

    override fun onBindViewHolder(holder: UserAdapter.UserViewHolder, position: Int) {
        val user = users[position]
        holder.bind(user)
    }

    class UserViewHolder(val view: View): RecyclerView.ViewHolder(view){
        fun bind(user: UserWS){
            view.nameTextView.text= "${user.name?.first} ${user.name?.last}"
            view.adressTextView.text = user.location.toString()
            view.mailTextView.text = user.email

        }
    }

}