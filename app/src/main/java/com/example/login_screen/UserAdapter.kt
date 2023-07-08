package com.example.login_screen

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth

class UserAdapter(val context: Context,val userList : ArrayList<User>): RecyclerView.Adapter<UserAdapter.UserViewHodler>() {

    class UserViewHodler(itemView: View) :RecyclerView.ViewHolder(itemView){
        val name_user = itemView.findViewById<TextView>(R.id.name)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHodler {
        val view = LayoutInflater.from(context).inflate(R.layout.user_layout,parent,false)
        return UserViewHodler(view)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: UserViewHodler, position: Int) {
        val current_user  = userList[position]
        holder.name_user.text = current_user.name
        holder.itemView.setOnClickListener{
            val intent = Intent(context,chat::class.java)
            intent.putExtra("name",current_user.name)
            intent.putExtra("uid",current_user.uid)
            context.startActivity(intent)



        }

    }

}