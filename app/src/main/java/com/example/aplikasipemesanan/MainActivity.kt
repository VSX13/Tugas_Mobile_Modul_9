package com.example.aplikasipemesanan

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    private lateinit var pesananViewModel: PesananViewModel
    private val newPesananActivityRequestCode = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = PesananListAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        pesananViewModel = ViewModelProvider(this).get(PesananViewModel::class.java)
        pesananViewModel.allPesanan.observe(this, Observer { pesanan -> pesanan?.let { adapter.setPesanan(it) } })
        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            val intent = Intent(this@MainActivity, NewPesananActivity::class.java)
            startActivityForResult(intent, newPesananActivityRequestCode)
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == newPesananActivityRequestCode && resultCode == Activity.RESULT_OK) {
            data?.getStringExtra(NewPesananActivity.EXTRA_REPLY)?.let {
                val pesanan = Pesanan(it)
                pesananViewModel.insert(pesanan)
            }
        } else {
            Toast.makeText(
                applicationContext,
                R.string.empty_not_saved,
                Toast.LENGTH_LONG).show()
        }
    }
}