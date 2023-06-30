package com.example.appfortop

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "StudentManagementDB"
        private const val DATABASE_VERSION = 1

        private const val TABLE_STUDENTS = "students"
        private const val COLUMN_ID = "id"
        private const val COLUMN_FIRST_NAME = "first_name"
        private const val COLUMN_LAST_NAME = "last_name"
        // Добавьте остальные столбцы таблицы согласно вашим требованиям
        private const val COLUMN_MIDDLE_NAME = "middle_name"
        private const val COLUMN_DATE_OF_BIRTH = "date_of_birth"
        private const val COLUMN_CITY = "city"
        private const val COLUMN_STREET = "street"
        private const val COLUMN_HOUSE_NUMBER = "house_number"
        private const val COLUMN_APARTMENT_NUMBER = "apartment_number"
        private const val COLUMN_EMAIL = "email"
        private const val COLUMN_PHONE_NUMBER = "phone_number"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createStudentsTable = ("CREATE TABLE $TABLE_STUDENTS ($COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "$COLUMN_FIRST_NAME TEXT, $COLUMN_LAST_NAME TEXT, "
                + "$COLUMN_MIDDLE_NAME TEXT, $COLUMN_DATE_OF_BIRTH TEXT, "
                + "$COLUMN_CITY TEXT, $COLUMN_STREET TEXT, "
                + "$COLUMN_HOUSE_NUMBER TEXT, $COLUMN_APARTMENT_NUMBER TEXT, "
                + "$COLUMN_EMAIL TEXT, $COLUMN_PHONE_NUMBER TEXT)")
        // Добавьте запросы для создания остальных таблиц

        db.execSQL(createStudentsTable)
        // Выполните запросы для создания остальных таблиц
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Обработка обновления базы данных (если требуется)
        // Здесь вы можете добавить логику для обновления структуры базы данных
        // в соответствии с новой версией приложения
    }

    fun addStudent(student: Student): Long {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COLUMN_FIRST_NAME, student.firstName)
        values.put(COLUMN_LAST_NAME, student.lastName)
        // Добавьте значения для остальных столбцов таблицы
        values.put(COLUMN_MIDDLE_NAME, student.middleName)
        values.put(COLUMN_DATE_OF_BIRTH, student.dateOfBirth)
        values.put(COLUMN_CITY, student.city)
        values.put(COLUMN_STREET, student.street)
        values.put(COLUMN_HOUSE_NUMBER, student.houseNumber)
        values.put(COLUMN_APARTMENT_NUMBER, student.apartmentNumber)
        values.put(COLUMN_EMAIL, student.email)
        values.put(COLUMN_PHONE_NUMBER, student.phoneNumber)

        return db.insert(TABLE_STUDENTS, null, values)
    }


    @SuppressLint("Range")
    fun getAllStudents(): ArrayList<Student> {
        val students = ArrayList<Student>()
        val selectQuery = "SELECT * FROM $TABLE_STUDENTS"

        val db = this.readableDatabase
        val cursor = db.rawQuery(selectQuery, null)

        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getLong(cursor.getColumnIndex(COLUMN_ID))
                val firstName = cursor.getString(cursor.getColumnIndex(COLUMN_FIRST_NAME))
                val lastName = cursor.getString(cursor.getColumnIndex(COLUMN_LAST_NAME))
                // Извлеките значения остальных столбцов таблицы
                val middleName = cursor.getString(cursor.getColumnIndex(COLUMN_MIDDLE_NAME))
                val dateOfBirth = cursor.getString(cursor.getColumnIndex(COLUMN_DATE_OF_BIRTH))
                val city = cursor.getString(cursor.getColumnIndex(COLUMN_CITY))
                val street = cursor.getString(cursor.getColumnIndex(COLUMN_STREET))
                val houseNumber = cursor.getString(cursor.getColumnIndex(COLUMN_HOUSE_NUMBER))
                val apartmentNumber = cursor.getString(cursor.getColumnIndex(COLUMN_APARTMENT_NUMBER))
                val email = cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL))
                val phoneNumber = cursor.getString(cursor.getColumnIndex(COLUMN_PHONE_NUMBER))

                val student = Student(
                    id,
                    firstName,
                    lastName,
                    middleName,
                    dateOfBirth,
                    city,
                    street,
                    houseNumber,
                    apartmentNumber,
                    email,
                    phoneNumber
                )
                students.add(student)
            } while (cursor.moveToNext())
        }

        cursor.close()
        return students
    }
}
