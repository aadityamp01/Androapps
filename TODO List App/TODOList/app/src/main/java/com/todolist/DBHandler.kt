package com.todolist

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import androidx.core.content.contentValuesOf

/**
 * Create a helper object to create, open, and/or manage a database.
 * This method always returns very quickly.  The database is not actually
 * created or opened until one of getWritableDatabase or
 * getReadableDatabase is called.
 *
 * //@param context to use for locating paths to the the database
 * //@param name of the database file, or null for an in-memory database
 * //@param factory to use for creating cursor objects, or null for the default
 * //@param version number of the database (starting at 1); if the database is older,
 *     #onUpgrade will be used to upgrade the database; if the database is
 *     newer, #onDowngrade will be used to downgrade the database
 */
class SqliteOpenHelper(context: Context) :
    SQLiteOpenHelper( context, DATABASE_NAME,
        null, DATABASE_VERSION
    ) {

    companion object {
        // We use this companion object to create const, to create static vars
        // which are available throughout the app
        private const val DATABASE_VERSION = 1 // This DATABASE Version
        private const val DATABASE_NAME = "TODOList.db" // Name of the DATABASE
        private const val TABLE_TODO = "TaskTable" // Table Name

        private const val KEY_ID = "_id"
        private const val KEY_TASK = "task"
        private const val KEY_DESCRIPTION = "description"
    }

    /**
     * This override function is used to execute a when the class is ""Called once where the database tables are created.""
     */
    override fun onCreate(db: SQLiteDatabase?) {
        val createTodoListTable = ("CREATE TABLE " +
                TABLE_TODO + "("
                + KEY_ID + " INTEGER PRIMARY KEY," +
                KEY_TASK + " TEXT," +
                KEY_DESCRIPTION + "TEXT," + ")") // Create TODOList Table Query.
        db?.execSQL(createTodoListTable) // Executing the create table query.
    }

    /**
     * This function is called when the database version is changed.
     */
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_TODO") // It drops the existing history table
        onCreate(db) // Calls the onCreate function so all the updated table will be created.
    }

    /**
     * Function is used to insert the date in Database TODOTable.
     */
    fun addTasks(tsk: TDataModel): Long {
        val db = this.writableDatabase
        // Create and/or open a database that will be used for reading and writing.

        val values = ContentValues() // Creates an empty set of values using the default initial size
        values.put(KEY_TASK, tsk.Task) // Putting the value to the column along with the value.
        values.put(KEY_DESCRIPTION, tsk.Description)

        val success = db.insert(TABLE_TODO, null, values) // Insert query is return

        db.close() // Database is closed after insertion.
        return success
    }

    /**
     * Function returns the list of history table data.
     */
    fun viewTask(): ArrayList<TDataModel> {
        val list: ArrayList<TDataModel> = ArrayList<TDataModel>() // ArrayList is initialized

        val selectQuery = "SELECT * FROM $TABLE_TODO"

        val db = this.readableDatabase

        var cursor: Cursor? = null

        // Move the cursor to the next row.
        try {
            cursor = db.rawQuery(selectQuery, null)
        }catch (e: SQLiteException){
            db.execSQL(selectQuery)  //execute a sql query
            return ArrayList()
        }

        var id: Int
        var task: String
        var description: String

        if(cursor.moveToFirst()){
            do{
                id = cursor.getInt(cursor.getColumnIndex(KEY_ID))
                task = cursor.getString(cursor.getColumnIndex(KEY_TASK))
                description = cursor.getString(cursor.getColumnIndex(KEY_DESCRIPTION))

                val tsk = TDataModel(Id = id, Task = task, Description = description)
                list.add(tsk)
            }while (cursor.moveToNext())
        }

        cursor.close() // Cursor is closed after its used.
        return list // List is returned.
    }

    fun updateTask(tsk: TDataModel): Int{
        val db = this.writableDatabase

        val values = ContentValues() // Creates an empty set of values using the default initial size
        values.put(KEY_TASK, tsk.Task) // Putting the value to the column along with the value.
        values.put(KEY_DESCRIPTION, tsk.Description)

        //Updating Row
        val success = db.update(TABLE_TODO, values, KEY_ID + "=" + tsk.Id, null)

        db.close() // Database is closed after insertion.
        return success
    }

    fun deleteTask(tsk: TDataModel): Int{
        val db = this.writableDatabase

        val values = ContentValues() // Creates an empty set of values using the default initial size
        values.put(KEY_ID, tsk.Id) // Putting the value to the column along with the value.

        //Updating Row
        val success = db.delete(TABLE_TODO, KEY_ID + "=" + tsk.Id, null)

        db.close() // Database is closed after insertion.
        return success
    }

}