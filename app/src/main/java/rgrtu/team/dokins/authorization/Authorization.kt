package rgrtu.team.dokins.authorization


import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject
import rgrtu.team.dokin.controllers.CheckController
import rgrtu.team.dokins.MainActivity
import rgrtu.team.dokins.R


class Authorization : AppCompatActivity(), View.OnClickListener {

    lateinit var eye: ImageButton
    lateinit var login: EditText
    lateinit var pass: EditText
    lateinit var toRegister: TextView
    lateinit var toMain: Button
    private lateinit var urlAuth: String
    private lateinit var urlAccount: String
    private lateinit var token: JSONObject
    private lateinit var temp: String

    @RequiresApi(Build.VERSION_CODES.Q)
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authorization)

        urlAuth = "https://докинь.com:8080/Auth";
        urlAccount = "https://докинь.com:8080/Account"
        login = findViewById(R.id.nameTf)
        pass = findViewById(R.id.password)
        eye = findViewById(R.id.eye_btn)
        toRegister = findViewById(R.id.to_register)
        toMain = findViewById(R.id.enter_button)

        addOnClickListener()
    }

    private fun addOnClickListener() {
        toRegister.setOnClickListener(this)
        toMain.setOnClickListener(this)
        eye.setOnClickListener(this)
    }

    override fun onClick(viev: View) {
        when (viev.id) {
            eye.id -> CheckController.checkEye(pass, eye)
            toRegister.id -> onRegisterActivity()
            toMain.id -> login()
        }
    }

    private fun onRegisterActivity() {
        val intent = Intent(this, Registration::class.java)
        startActivity(intent)
        finish()
    }

    private fun login() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    private fun requestData() {
        val queue = Volley.newRequestQueue(this)
        val request = object : StringRequest(Request.Method.GET, urlAccount,
            { result ->
                println(result)
            },
            { err ->
                println(err)
            }) {
            override fun getHeaders(): MutableMap<String, String> {
                val param = HashMap<String, String>()
                param["Authorization"] = "Bearer $temp"
                return param
            }
        }

        queue.add(request)
    }

//    private fun setData(phoneNumber : String, pass : String) {
//        val request = object :StringRequest(Request.Method.POST, urlAuth,
//            { response -> println(response) },
//            { error -> println(error) })
////        {
////            override
////            fun getParams(): MutableMap<String, String>? {
////                val params = HashMap<String, String>()
////                params["phoneNumber"] = phoneNumber
////                params["password"] = pass
////                return params
////            }
////        }
//        queue.add(request)
//    }

    private fun setData2() {
        val resp: JSONObject = JSONObject()
        resp.put("phoneNumber", "81231231212")
        resp.put("password", "11111111")

        val req: JsonObjectRequest = JsonObjectRequest(Request.Method.POST, urlAuth, resp,
            { response -> println(response.toString()) },
            { err -> println(err) })

        val reqv = object : StringRequest(Request.Method.POST, urlAuth,
            { response -> println(response) },
            { err -> print(err) }) {
            override fun getPostBody(): ByteArray {
                return resp.toString().toByteArray()
            }

            override fun getPostBodyContentType(): String {
                return "application/json"
            }
        }

        val request: RequestQueue = Volley.newRequestQueue(this)
        request.add(req)
    }


    private fun auth() {
        val json: JSONObject = JSONObject()
        json.put("phoneNumber", "12377771313")
        json.put("password", "22222222")

        val req: JsonObjectRequest = JsonObjectRequest(Request.Method.POST, urlAuth, json,
            { response -> println(response) },
            { err ->
                temp = err.toString().split(" ")[3]
                print(err)
                println(temp)
            })

        val request: RequestQueue = Volley.newRequestQueue(this)
        request.add(req)
    }


}