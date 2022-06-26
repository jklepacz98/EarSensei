package com.example.earsensei


import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns
import com.example.earsensei.dbmodels.QuizRecordModel

class EarSenseiDBHelper(context: Context): SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {

    companion object{
        const val DB_VERSION  = 10
        const val DB_NAME  = "EarSensei.db"
        const val TABLE_NAME = "records_table"
        const val KEY_ID = "_id"
        const val QUIZ_TYPE_COL = "QUIZ_TYPE"
        const val BASE_NOTE_COL = "BASE_NOTE"
        const val CORRECT_ANSWER_COL = "CORRECT_ANSWER"
        const val USER_ANSWER_COL = "USER_ANSWER"
        const val DATE_COL = "DATE"
    }







    object BasicComand{
        const val SQL_CREATE_INTERVALS_TABLE =
            "CREATE TABLE ${TABLE_NAME} (" +
                    "${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "${QUIZ_TYPE_COL} TEXT," +
                    "${BASE_NOTE_COL} TEXT," +
                    "${CORRECT_ANSWER_COL}  TEXT, " +
                    "${USER_ANSWER_COL}  TEXT, " +
                    "${DATE_COL} INTEGER);"
        const val SQL_DELETE_INTERVALS_TABLE = "DROP TABLE IF EXISTS ${TABLE_NAME}"

    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(BasicComand.SQL_CREATE_INTERVALS_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        dropAllTables(db)
        db?.execSQL(BasicComand.SQL_DELETE_INTERVALS_TABLE)
        onCreate(db)
    }

    fun dropAllTables(db: SQLiteDatabase?){
        val tables : List<String> = getAllTableNames(db)
        // call DROP TABLE on every table name
        for (table in tables) {
            val dropQuery = "DROP TABLE IF EXISTS $table"
            db!!.execSQL(dropQuery)
        }
    }

    fun getAllTableNames(db: SQLiteDatabase?) : List<String>{
        val c : Cursor = db!!.rawQuery("SELECT name FROM sqlite_master WHERE type='table'", null)
        val tables: MutableList<String> = ArrayList()
        while (c.moveToNext()) {
            tables.add(c.getString(0))
        }
        val filteredTables : List<String> = tables.filter { it != "sqlite_sequence" }
        return filteredTables
    }


    fun createIntervalsContentValues(baseNote: String, type: String, correctAnswer: String, userAnswer: String, date: Long) : ContentValues{
        val contentValues : ContentValues = ContentValues()
        contentValues.put(QUIZ_TYPE_COL, baseNote)
        contentValues.put(BASE_NOTE_COL, baseNote)
        contentValues.put(CORRECT_ANSWER_COL, correctAnswer)
        contentValues.put(USER_ANSWER_COL, userAnswer)
        contentValues.put(DATE_COL, date)
        return contentValues
    }


    fun addIntervalsContentValues(contentValues : ContentValues) : Boolean{
        val db : SQLiteDatabase = this.writableDatabase
        val insert : Long = db.insert(TABLE_NAME, null, contentValues)
        db.close()
        return insert != -1L
    }


    @SuppressLint("Range")
    fun readAllIntervalsData() : ArrayList<QuizRecordModel>{
        val quizRecordModelLists : ArrayList<QuizRecordModel> = ArrayList<QuizRecordModel>()
        val selectQuery : String = "SELECT * FROM " + TABLE_NAME
        val db : SQLiteDatabase = this.readableDatabase
        var cursor: Cursor? = null

        try {
            cursor = db?.rawQuery(selectQuery, null)
        }catch (e: SQLException){
            db.execSQL(selectQuery)
            return ArrayList()
        }
        var id: Int
        var quizType: String
        var baseNote: String
        var correctAnswer : String
        var userAnswer: String
        var date: Int

        if(cursor.moveToFirst()){
            do {
                id = cursor.getInt(cursor.getColumnIndex(KEY_ID))
                quizType = cursor.getString(cursor.getColumnIndex(QUIZ_TYPE_COL))
                baseNote = cursor.getString(cursor.getColumnIndex(BASE_NOTE_COL))
                correctAnswer = cursor.getString(cursor.getColumnIndex(CORRECT_ANSWER_COL))
                userAnswer = cursor.getString(cursor.getColumnIndex(USER_ANSWER_COL))
                date = cursor.getInt(cursor.getColumnIndex(DATE_COL))
                val quizRecordModel : QuizRecordModel = QuizRecordModel(id, quizType, baseNote, correctAnswer, userAnswer, date)
                quizRecordModelLists.add(quizRecordModel)
            } while (cursor.moveToNext())
        }
        return quizRecordModelLists
    }






}