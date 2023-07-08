package com.example.login_screen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.renderscript.Sampler.Value
import android.view.Menu
import android.view.MenuItem
import android.widget.Adapter
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
        val toolbar: androidx.appcompat.widget.Toolbar? =
            findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar!!)
        rc = findViewById(R.id.rc)
        rc.layoutManager = LinearLayoutManager(this)
        rc.adapter = kapa
        mdref = FirebaseDatabase.getInstance().getReference()

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



    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.logout){
            auth.signOut()
            val intent = Intent(this,sigin::class.java)
            startActivity(intent)
            finish()
            return true
        }
        return true
    }
}