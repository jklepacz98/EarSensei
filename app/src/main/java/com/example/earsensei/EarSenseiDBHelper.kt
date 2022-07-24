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

class EarSenseiDBHelper(context: Context) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {

    companion object {
        const val DB_VERSION = 15
        const val DB_NAME = "EarSensei.db"
    }

    object Records {
        const val TABLE_NAME = "quiz_table"
        const val KEY_ID = "_id"
        const val QUIZ_TYPE_COL = "QUIZ_TYPE"
        const val BASE_NOTE_COL = "BASE_NOTE"
        const val CORRECT_ANSWER_COL = "CORRECT_ANSWER"
        const val USER_ANSWER_COL = "USER_ANSWER"
        const val DATE_COL = "DATE"
    }

    object Progress {
        const val TABLE_NAME = "progress_table"
        const val KEY_ID = "_id"
        const val QUIZ_TYPE_COL = "QUIZ_TYPE"
        const val QUESTION_COL = "QUESTION"
        const val DATE_COL = "DATE"
    }


    object BasicComand {
        const val SQL_CREATE_QUIZ_TABLE =
            "CREATE TABLE ${Records.TABLE_NAME} (" +
                    "${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "${Records.QUIZ_TYPE_COL} TEXT," +
                    "${Records.BASE_NOTE_COL} TEXT," +
                    "${Records.CORRECT_ANSWER_COL}  TEXT, " +
                    "${Records.USER_ANSWER_COL} TEXT, " +
                    "${Records.DATE_COL} INTEGER);"
        const val SQL_CREATE_PROGRESS_TABLE =
            "CREATE TABLE ${Progress.TABLE_NAME} (" +
                    "${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "${Progress.QUIZ_TYPE_COL} TEXT," +
                    "${Progress.QUESTION_COL} TEXT UNIQUE," +
                    "${Progress.DATE_COL}  INTEGER);"
    //todo
    //const val SQL_DELETE_QUIZ_TABLE = "DROP TABLE IF EXISTS ${Records.TABLE_NAME}"

    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(BasicComand.SQL_CREATE_QUIZ_TABLE)
        db?.execSQL(BasicComand.SQL_CREATE_PROGRESS_TABLE)
    }

    fun prepareBasicQuestions(){
        //todo
        var intervalQuestions : List<String> = listOf(
            MusicTerminology.Intervals.MAJOR_3RD,
            MusicTerminology.Intervals.PERFECT_5TH,
            MusicTerminology.Intervals.OCATVE,
        )
        intervalQuestions.forEach{
            val contentValues = createProgressContentValues(QuizType.INTERVALS, it, 0)
            addProgressContentValues(contentValues)
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        dropAllTables(db)
        //todo
        //db?.execSQL(BasicComand.SQL_DELETE_QUIZ_TABLE)
        onCreate(db)
    }

    private fun dropAllTables(db: SQLiteDatabase?) {
        val tables: List<String> = getAllTableNames(db)
        // call DROP TABLE on every table name
        for (table in tables) {
            val dropQuery = "DROP TABLE IF EXISTS $table"
            db!!.execSQL(dropQuery)
        }
    }

    private fun getAllTableNames(db: SQLiteDatabase?): List<String> {
        val c: Cursor = db!!.rawQuery("SELECT name FROM sqlite_master WHERE type='table'", null)
        val tables: MutableList<String> = ArrayList()
        while (c.moveToNext()) {
            tables.add(c.getString(0))
        }
        val filteredTables: List<String> = tables.filter { it != "sqlite_sequence" }
        return filteredTables
    }


    fun createQuizContentValues(
        baseNote: String,
        type: String,
        correctAnswer: String,
        userAnswer: String,
        date: Long
    ): ContentValues {
        val contentValues: ContentValues = ContentValues()
        contentValues.put(Records.QUIZ_TYPE_COL, type)
        contentValues.put(Records.BASE_NOTE_COL, baseNote)
        contentValues.put(Records.CORRECT_ANSWER_COL, correctAnswer)
        contentValues.put(Records.USER_ANSWER_COL, userAnswer)
        contentValues.put(Records.DATE_COL, date)
        return contentValues
    }

    fun createProgressContentValues(
        type: String,
        question: String,
        date: Long
    ): ContentValues{
        val contentValues: ContentValues = ContentValues()
        contentValues.put(Progress.QUIZ_TYPE_COL, type)
        contentValues.put(Progress.QUESTION_COL, question)
        contentValues.put(Progress.DATE_COL, date)
        return contentValues
    }

    fun addQuizContentValues(contentValues: ContentValues): Boolean {
        val db: SQLiteDatabase = this.writableDatabase
        val insert: Long = db.insert(Records.TABLE_NAME, null, contentValues)
        db.close()
        return insert != -1L
    }

    fun addProgressContentValues(contentValues: ContentValues) : Boolean {
        val db: SQLiteDatabase = this.writableDatabase
        val insert: Long = db.insert(Progress.TABLE_NAME, null,contentValues)
        db.close()
        return insert != -1L
    }

    @SuppressLint("Range")
    fun readAllQuizData(): ArrayList<QuizRecordModel> {
        val quizRecordModelLists: ArrayList<QuizRecordModel> = ArrayList<QuizRecordModel>()
        val selectQuery: String = "SELECT * FROM " + Records.TABLE_NAME
        val db: SQLiteDatabase = this.readableDatabase
        var cursor: Cursor? = null

        try {
            cursor = db?.rawQuery(selectQuery, null)
        } catch (e: SQLException) {
            db.execSQL(selectQuery)
            return ArrayList()
        }
        var id: Int
        var quizType: String
        var baseNote: String
        var correctAnswer: String
        var userAnswer: String
        var date: Int

        if (cursor.moveToFirst()) {
            do {
                id = cursor.getInt(cursor.getColumnIndex(Records.KEY_ID))
                quizType = cursor.getString(cursor.getColumnIndex(Records.QUIZ_TYPE_COL))
                baseNote = cursor.getString(cursor.getColumnIndex(Records.BASE_NOTE_COL))
                correctAnswer = cursor.getString(cursor.getColumnIndex(Records.CORRECT_ANSWER_COL))
                userAnswer = cursor.getString(cursor.getColumnIndex(Records.USER_ANSWER_COL))
                date = cursor.getInt(cursor.getColumnIndex(Records.DATE_COL))
                val quizRecordModel: QuizRecordModel =
                    QuizRecordModel(id, quizType, baseNote, correctAnswer, userAnswer, date)
                quizRecordModelLists.add(quizRecordModel)
            } while (cursor.moveToNext())
        }
        return quizRecordModelLists
    }

    //todo
    //sprawdzic czy progress dziala

    @SuppressLint("Range")
    fun readAllProgressData() : ArrayList<ProgressModel>{
        val progressModels : ArrayList<ProgressModel> = ArrayList<ProgressModel>()
        val selectQuery: String = "SELECT * FROM " + Progress.TABLE_NAME
        val db: SQLiteDatabase = this.readableDatabase
        var cursor: Cursor? = null
        try {
            cursor = db?.rawQuery(selectQuery, null)
        } catch (e: SQLException) {
            db.execSQL(selectQuery)
            return ArrayList()
        }
        var id: Int
        var type: String
        var question: String
        var date: Int

        if (cursor.moveToFirst()){
            do{
                id = cursor.getInt(cursor.getColumnIndex(Progress.KEY_ID))
                type = cursor.getString(cursor.getColumnIndex(Progress.QUIZ_TYPE_COL))
                question = cursor.getString(cursor.getColumnIndex(Progress.QUESTION_COL))
                date = cursor.getInt(cursor.getColumnIndex(Progress.DATE_COL))
                val progressModel: ProgressModel = ProgressModel(id, type, question, date)
                progressModels.add(progressModel)
            }while (cursor.moveToNext())
        }
        return progressModels
    }


}