package com.kc_hsu.mviarchdemo.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kc_hsu.mviarchdemo.R
import com.kc_hsu.mviarchdemo.data.Users

class MainAdapter(
    private val users: ArrayList<Users.User>
) : RecyclerView.Adapter<MainAdapter.DataViewHolder>() {

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(user: Users.User) {

            itemView.findViewById<TextView>(R.id.textViewUserName).text = user.firstName
            itemView.findViewById<TextView>(R.id.textViewUserEmail).text = user.email
            Glide.with(itemView.findViewById<ImageView>(R.id.imageViewAvatar).context)
                .load(user.avatar)
                .into(itemView.findViewById(R.id.imageViewAvatar))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DataViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        )

    override fun getItemCount(): Int = users.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) =
        holder.bind(users[position])

    fun addData(list: List<Users.User>) {
        users.addAll(list)
    }
}