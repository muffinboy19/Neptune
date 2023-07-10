package com.example.login_screen

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.renderscript.Sampler.Value
import android.view.Menu
import android.view.MenuItem
import android.widget.Adapter
import android.widget.ImageView
import android.widget.SearchView
import android.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class Homescreen : AppCompatActivity() {
    private lateinit var auth:FirebaseAuth
    private lateinit var rc: RecyclerView
    private lateinit var userList: ArrayList<User>
    private lateinit var kapa :UserAdapter
    private lateinit var mdref :DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_homescreen)


        userList = ArrayList()
        kapa = UserAdapter(this, userList)
        auth = FirebaseAuth.getInstance()
        rc = findViewById(R.id.rc)
        rc.layoutManager = LinearLayoutManager(this)
        rc.adapter = kapa
        mdref = FirebaseDatabase.getInstance().getReference()

//        val ss = findViewById<SearchView>(R.id.ss)
//        ss.setOnQueryTextListener(object :SearchView.OnQueryTextListener{
//            override fun onQueryTextSubmit(p0: String?): Boolean {
//                performSearch(query)
//                return true
//            }
//
//            override fun onQueryTextChange(p0: String?): Boolean {
//                TODO("Not yet implemented")
//                return true
//            }
//        })

        val lgot = findViewById<ImageView>(R.id.logt)
        lgot.setOnClickListener{
            geees(this)
        }

        kapa.notifyDataSetChanged()
        mdref.child("user").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                userList.clear()
                for (posSanpshot in snapshot.children) {
                    val cu = posSanpshot.getValue(User::class.java)
                    if(auth.currentUser?.uid == cu?.uid){

                    }else{
                        userList.add(cu!!)
                    }

                }
                kapa.notifyDataSetChanged()

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }


        })
    }

    private fun performSearch(query: Any) {

    }

    fun geees(context: Context): Boolean {

                auth.signOut()
                val intent = Intent(context,sigin::class.java)
                startActivity(intent)
                finish()
                return true


        }
    }

