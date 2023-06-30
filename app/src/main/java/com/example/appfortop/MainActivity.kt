package com.example.appfortop

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var addStudentButton: Button
    private var studentsAdapter: RecyclerView.Adapter<*>? = null

    private lateinit var studentsList: ArrayList<Student>

    private lateinit var databaseHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.adapter = studentsAdapter
        studentsAdapter = StudentsAdapter()
        recyclerView = findViewById(R.id.studentsRecyclerView)
        addStudentButton = findViewById(R.id.addStudentButton)

        studentsList = ArrayList()

        databaseHelper = DatabaseHelper(this)



        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = studentsAdapter

        addStudentButton.setOnClickListener {
            val newStudent = Student(
                studentId = 1,
                firstName = "John",
                lastName = "Doe",
                middleName = "Middle",
                dateOfBirth = "1990-01-01",
                city = "City",
                street = "Street",
                houseNumber = "123",
                apartmentNumber = "456",
                email = "john.doe@example.com",
                phoneNumber = "123456789"
            )
            addStudentToDatabase(newStudent)

        }

        loadStudentsFromDatabase()
    }

    private fun student(
        firstName: String,
        lastName: String,
        middleName: String,
        dateOfBirth: String,
        city: String,
        street: String,
        houseNumber: String,
        apartmentNumber: String,
        email: String,
        phoneNumber: String
    ) {

    }

    private fun loadStudentsFromDatabase() {
        val students = databaseHelper.getAllStudents()

        studentsList.clear()
        studentsList.addAll(students)
        studentsAdapter.notifyDataSetChanged()
    }

    private fun addStudentToDatabase(student: Student) {
        val insertedId = databaseHelper.addStudent(student)

        if (insertedId != -1L) {
            // Успешное добавление студента в базу данных
            loadStudentsFromDatabase()
        } else {
            // Ошибка при добавлении студента в базу данных
        }
    }
}
