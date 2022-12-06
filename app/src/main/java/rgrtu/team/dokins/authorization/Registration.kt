package rgrtu.team.dokins.authorization


import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
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
import rgrtu.team.dokins.R
import java.util.*

class Registration : AppCompatActivity(), View.OnClickListener {

    lateinit var signIn: TextView
    lateinit var eye: ImageButton

    //private lateinit var urlAuth: String
    private lateinit var urlAccount: String

    lateinit var nameTf: EditText
    lateinit var pass: EditText
    lateinit var phone: EditText
    lateinit var repeatPass: EditText

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        eye = findViewById(R.id.eye_register)
        pass = findViewById(R.id.password)
        signIn = findViewById(R.id.to_sign)
        phone = findViewById(R.id.phone)
        nameTf = findViewById(R.id.nameTf)
        repeatPass = findViewById(R.id.repeat_password)

        CheckController.phoneMask(phone)
        addOnClickListener()
    }

    private fun addOnClickListener() {
        eye.setOnClickListener(this)
        signIn.setOnClickListener(this)
    }

    override fun onClick(viev: View) {
        when (viev.id) {
            eye.id -> CheckController.checkEye(pass, eye)
            signIn.id -> toSignIn()
        }
    }

    private fun toSignIn() {
        val intent = Intent(this, Authorization::class.java)
        startActivity(intent)
        finish()
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