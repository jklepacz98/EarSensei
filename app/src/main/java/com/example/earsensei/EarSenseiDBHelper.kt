package com.example.earsensei

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns
import android.util.Log
import kotlin.collections.ArrayList

class EarSenseiDBHelper(context: Context): SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {

    companion object{
        const val DB_VERSION  = 2
        const val DB_NAME  = "EarSensei.db"

        const val TABLE_TEST = "test_table"
        const val KEY_ID = "_id"
        const val BASE_NOTE_COL = "BASE_NOTE"
        const val CORRECT_ANSWER_COL = "CORRECT_ANSWER"
        const val USER_ANSWER_COL = "USER_ANSWER"
        const val DATE_COL = "DATE"
    }

    object BasicComand{
        const val SQL_CREATE_TABLE =
                "CREATE TABLE ${TABLE_TEST} (" +
                "${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "${BASE_NOTE_COL} TEXT," +
                "${CORRECT_ANSWER_COL} TEXT, " +
                "${USER_ANSWER_COL} TEXT, " +
                "${DATE_COL} INTEGER);"
        const val SQL_DELETE_TABLE = "DROP TABLE IF EXISTS ${TABLE_TEST}"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(BasicComand.SQL_CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db?.execSQL(BasicComand.SQL_DELETE_TABLE)
        onCreate(db)
    }

    fun createContentValues(baseNote: String, correctAnswer: String, userAnswer: String, date: Int) : ContentValues{
        val contentValues : ContentValues = ContentValues()
        contentValues.put(BASE_NOTE_COL, baseNote)
        contentValues.put(CORRECT_ANSWER_COL, correctAnswer)
        contentValues.put(USER_ANSWER_COL, userAnswer)
        contentValues.put(DATE_COL, date)
        return contentValues
    }

    fun addContentValues(contentValues : ContentValues) : Boolean{
        val db : SQLiteDatabase = this.writableDatabase
        val insert : Long = db.insert(TABLE_TEST, null, contentValues)
        db.close()
        return insert != -1L

    }

    @SuppressLint("Range")
    fun readAllData() : ArrayList<TestModel>{
        val testModelList : ArrayList<TestModel> = ArrayList<TestModel>()
        val selectQuery : String = "SELECT * FROM " + TABLE_TEST
        val db : SQLiteDatabase = this.readableDatabase
        var cursor: Cursor? = null

        try {
            cursor = db?.rawQuery(selectQuery, null)
        }catch (e: SQLException){
            db.execSQL(selectQuery)
            return ArrayList()
        }
        var id: Int
        var baseNote: String
        var correctAnswer : String
        var userAnswer: String
        var date: Int

        if(cursor.moveToFirst()){
            do {
                id = cursor.getInt(cursor.getColumnIndex(KEY_ID))
                baseNote = cursor.getString(cursor.getColumnIndex(BASE_NOTE_COL))
                correctAnswer = cursor.getString(cursor.getColumnIndex(CORRECT_ANSWER_COL))
                userAnswer = cursor.getString(cursor.getColumnIndex(USER_ANSWER_COL))
                date = cursor.getInt(cursor.getColumnIndex(DATE_COL))
                val testModel : TestModel = TestModel(id, baseNote, correctAnswer, userAnswer, date)
                testModelList.add(testModel)
            } while (cursor.moveToNext())
        }
        return testModelList
    }

}