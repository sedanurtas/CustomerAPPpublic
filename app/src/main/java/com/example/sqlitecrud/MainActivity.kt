package com.example.sqlitecrud

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sqlitecrud.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private  lateinit var db : CustomersDataBaseHelper
    private lateinit var  customersAdapter: CustomersAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = CustomersDataBaseHelper(this)
        customersAdapter = CustomersAdapter(db.getAllCustomers(),this)

        binding.customersRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.customersRecyclerView.adapter = customersAdapter

        binding.addButton.setOnClickListener {

            val intent = Intent(this, AddCustomerActivity::class.java)
            startActivity(intent)

        }


    }

    override fun onResume() {
        super.onResume()
        customersAdapter.refreshData(db.getAllCustomers())
    }



    }
