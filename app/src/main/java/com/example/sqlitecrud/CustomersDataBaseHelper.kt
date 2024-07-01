package com.example.sqlitecrud

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class CustomersDataBaseHelper(context: Context) :SQLiteOpenHelper(context , DATABASE_NAME, null , DATABASE_VERSION){

    companion object{
        private const val DATABASE_NAME = "notesapp.db"
        private const val DATABASE_VERSION = 1
        private const val TABLE_NAME = "Customer"
        private const val  COLUMN_ID = "id"
        private const val COLUMN_TITLE = "title"
        private const val COLUMN_CONTENT = "content"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTableQuery ="CREATE TABLE $TABLE_NAME($COLUMN_ID INTEGER PRIMARY KEY, $COLUMN_TITLE TEXT, $COLUMN_CONTENT TEXT)"
        db?.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val dropTableQuery="DROP TABLE IF EXISTS $TABLE_NAME"
        db?.execSQL(dropTableQuery)
        onCreate(db)
    }

    fun insertCustomer(customer:Customer){
        val db=writableDatabase
        val values=ContentValues().apply {
            put(COLUMN_TITLE, customer.title)
            put(COLUMN_CONTENT, customer.content)
        }
        db.insert(TABLE_NAME,null,values)

    }

   fun getAllCustomers(): List<Customer> {
       val  customersList = mutableListOf<Customer>()
       val db = readableDatabase
       val query = "SELECT * FROM $TABLE_NAME"
       val cursor = db.rawQuery(query,null)

       while (cursor.moveToNext()){
           val id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID))
           val title = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TITLE))
           val content = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CONTENT))

           val customer = Customer(id, title, content)
           customersList.add(customer)

       }
       cursor.close()
       return customersList


   }

    fun updateCustomer(customer: Customer){
        val db=writableDatabase
        val values=ContentValues().apply {
            put(COLUMN_TITLE,customer.title)
            put(COLUMN_CONTENT,customer.content)
        }
        val whereClause="$COLUMN_ID=?"
        val whereArgs= arrayOf(customer.id.toString())
        db.update(TABLE_NAME,values, whereClause, whereArgs)
    }

    fun getCustomerByID(noteID : Int): Customer{
        val db = readableDatabase
        val query = "SELECT * FROM $TABLE_NAME WHERE $COLUMN_ID=$noteID"
        val cursor =db.rawQuery(query,null)
        cursor.moveToFirst()

        val id=cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID))
        val title=cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TITLE))
        val content=cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CONTENT))

        cursor.close()
        return Customer(id,title,content)


    }
    fun deleteCustomer(noteID: Int){
        val db = writableDatabase
        val whereClause ="$COLUMN_ID = ?"
        val whereArgs =  arrayOf(noteID.toString())
        db.delete(TABLE_NAME,whereClause,whereArgs)

    }




}