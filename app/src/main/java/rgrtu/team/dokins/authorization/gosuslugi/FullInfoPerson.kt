package rgrtu.team.dokins.authorization.gosuslugi

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.content.Intent
import android.os.Bundle
import android.text.format.DateUtils
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject
import rgrtu.team.dokin.controllers.CheckController
import rgrtu.team.dokins.MainActivity
import rgrtu.team.dokins.R
import java.util.*


class FullInfoPerson : AppCompatActivity(), View.OnClickListener {

    lateinit var signIn: TextView
    lateinit var eye: ImageButton
    lateinit var currentDateTime: TextView
    //private lateinit var urlAuth: String
    private lateinit var urlAccount: String
    var dateAndTime = Calendar.getInstance()

    lateinit var nameTf: EditText
    lateinit var middleNameTf: EditText
    lateinit var lastNameTf: EditText
    lateinit var pass : EditText
    lateinit var phone: EditText
    lateinit var email: EditText
    lateinit var repeatPass: EditText

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_full_info_person)

        eye = findViewById(R.id.eye_register)
        pass = findViewById(R.id.password)
        signIn = findViewById(R.id.to_sign)
        currentDateTime = findViewById(R.id.date)
        phone = findViewById(R.id.phone)
        email = findViewById(R.id.email)
        nameTf = findViewById(R.id.nameTf)
        middleNameTf = findViewById(R.id.middle_nameTf)
        lastNameTf = findViewById(R.id.last_nameTf)
        repeatPass = findViewById(R.id.repeat_password)

        setInitialDateTime()
        CheckController.phoneMask(phone)
        addOnClickListener()
    }

    private fun addOnClickListener() {
        currentDateTime.setOnClickListener(this)
        eye.setOnClickListener(this)
        signIn.setOnClickListener(this)
    }

    override fun onClick(viev: View) {
        when (viev.id) {
            eye.id -> CheckController.checkEye(pass, eye)
            signIn.id -> toSignIn()
            currentDateTime.id -> setDate()
        }
    }

    private fun toSignIn() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    fun setDate() {
        DatePickerDialog(
            this, d,
            dateAndTime.get(Calendar.YEAR),
            dateAndTime.get(Calendar.MONTH),
            dateAndTime.get(Calendar.DAY_OF_MONTH)
        )
            .show()
    }

    private fun setInitialDateTime() {
        currentDateTime.text = DateUtils.formatDateTime(
            this,
            dateAndTime.timeInMillis,
            DateUtils.FORMAT_SHOW_DATE or DateUtils.FORMAT_SHOW_YEAR
        )
    }

    // установка обработчика выбора даты
    var d =
        OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
            dateAndTime.set(Calendar.YEAR, year)
            dateAndTime.set(Calendar.MONTH, monthOfYear)
            dateAndTime.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            setInitialDateTime()
        }



    private fun registrationNewPerson() {
        val resp: JSONObject = JSONObject()
        resp.put("name", "Andrey")
        resp.put("middlename", "")
        resp.put("lastname", "Bebra")
        resp.put("phoneNumber", "12377771313")
        resp.put("dateOfBirth", "2022-12-04")
        resp.put("email", "user1234@example.com")
        resp.put("password", "22222222")
        resp.put("confirmPassword", "22222222")

        val req: JsonObjectRequest = JsonObjectRequest(
            Request.Method.POST, urlAccount, resp,
            { response -> println(response) },
            { err -> println(err) })

        val request: RequestQueue = Volley.newRequestQueue(this)
        request.add(req)
    }
}