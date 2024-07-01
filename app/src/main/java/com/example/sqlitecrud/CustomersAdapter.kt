package com.example.sqlitecrud

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class CustomersAdapter(private var customers: List<Customer>, private val context: Context) : RecyclerView.Adapter<CustomersAdapter.CustomerViewHolder>() {

    private val db: CustomersDataBaseHelper = CustomersDataBaseHelper(context)

    class CustomerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
        val contentTextView: TextView = itemView.findViewById(R.id.contentTextView)
        val updateButton: ImageView = itemView.findViewById(R.id.updateButton)
        val deleteButton: ImageView = itemView.findViewById(R.id.deleteButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.customer_item, parent, false)
        return CustomerViewHolder(view)
    }

    override fun getItemCount(): Int {
        return customers.size
    }

    override fun onBindViewHolder(holder: CustomerViewHolder, position: Int) {
        val customer = customers[position]
        holder.titleTextView.text = customer.title
        holder.contentTextView.text = customer.content


        holder.updateButton.setOnClickListener {
            val intent=Intent(holder.itemView.context, UpdateCustomerActivity::class.java).apply {
                putExtra("customer_id",customer.id)
            }
            holder.itemView.context.startActivity(intent)
        }
        holder.deleteButton.setOnClickListener{
            db.deleteCustomer(customer.id)
            refreshData(db.getAllCustomers())
            Toast.makeText(holder.itemView.context,"Customer deleted",Toast.LENGTH_SHORT).show()
        }

    }

    fun refreshData(newCustomers: List<Customer>) {
        customers = newCustomers
        notifyDataSetChanged()
    }
}
