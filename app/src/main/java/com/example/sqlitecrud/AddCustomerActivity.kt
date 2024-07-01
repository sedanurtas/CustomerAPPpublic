package com.example.sqlitecrud

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.sqlitecrud.databinding.ActivityAddCustomerBinding


class AddCustomerActivity : AppCompatActivity() {

    private lateinit var binding:ActivityAddCustomerBinding
    private lateinit var db:CustomersDataBaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddCustomerBinding.inflate(layoutInflater)
        setContentView(binding.root)


        db = CustomersDataBaseHelper(this)

        binding.saveButton.setOnClickListener{

            val title = binding.titleEditText.text.toString()
            val content = binding.contentEditText.text.toString()
            val customer = Customer(0 ,title,content)
            db.insertCustomer(customer)
            finish()
            Toast.makeText(this ,"Customer Saved",Toast.LENGTH_SHORT).show()

        }

        }
    }
