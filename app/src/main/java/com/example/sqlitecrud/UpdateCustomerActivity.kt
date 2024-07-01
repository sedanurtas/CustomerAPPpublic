package com.example.sqlitecrud

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.sqlitecrud.databinding.ActivityUpdateCustomerBinding




class UpdateCustomerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateCustomerBinding
    private lateinit var db:CustomersDataBaseHelper
    private var customerId : Int= -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =ActivityUpdateCustomerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = CustomersDataBaseHelper(this)

        customerId = intent.getIntExtra("note_id",-1)
        if (customerId==-1){
            finish()
            return
        }

        val customer = db.getCustomerByID(customerId)
        binding.updateTitleEditText.setText(customer.title)
        binding.updateContentEditText.setText(customer.content)

        binding.updateSaveButton.setOnClickListener {
            val newTitle = binding.updateTitleEditText.text.toString()
            val newContent = binding.updateContentEditText.text.toString()
            val updateCustomer = Customer(customerId,newTitle,newContent)
            db.updateCustomer(updateCustomer)
            finish()
            Toast.makeText(this,"Changes saved",Toast.LENGTH_SHORT).show()
        }

        }
    }
