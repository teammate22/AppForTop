import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.appfortop.R
import com.example.appfortop.Student

class StudentsAdapter(private val students: List<Student>) :
    RecyclerView.Adapter<StudentsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_student, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val student = students[position]
        holder.bind(student)
    }

    override fun getItemCount(): Int {
        return students.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(student: Student) {
            // Привязка данных студента к элементам пользовательского интерфейса
            itemView.findViewById<TextView>(R.id.txtFirstName).text = student.firstName
            itemView.findViewById<TextView>(R.id.txtLastName).text = student.lastName
            // Привязка остальных данных студента
            itemView.findViewById<TextView>(R.id.txtMiddleName).text = student.middleName
            itemView.findViewById<TextView>(R.id.txtDateOfBirth).text = student.dateOfBirth
            itemView.findViewById<TextView>(R.id.txtCity).text = student.city
            itemView.findViewById<TextView>(R.id.txtStreet).text = student.street
            itemView.findViewById<TextView>(R.id.txtHouseNumber).text = student.houseNumber
            itemView.findViewById<TextView>(R.id.txtApartmentNumber).text = student.apartmentNumber
            itemView.findViewById<TextView>(R.id.txtEmail).text = student.email
            itemView.findViewById<TextView>(R.id.txtPhoneNumber).text = student.phoneNumber

            // Добавьте обработчики событий, если необходимо
            itemView.setOnClickListener {
                // Обработка нажатия на элемент списка
            }
        }
    }
}
