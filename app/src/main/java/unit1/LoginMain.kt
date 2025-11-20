package unit1

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.cse227etp.R
import com.google.firebase.auth.FirebaseAuth
import unit2.CanvasMain

class LoginMain : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var mail: EditText
    private lateinit var password: EditText
    private lateinit var loginBtn: Button
    private lateinit var registerBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login_main)

        auth=FirebaseAuth.getInstance()
        mail=findViewById(R.id.username)
        password=findViewById(R.id.password)
        loginBtn=findViewById(R.id.loginBtn)
        registerBtn=findViewById(R.id.RegisterBtn)

        registerBtn.setOnClickListener {
            startActivity(Intent(this, SignUpMain::class.java))
        }

        loginBtn.setOnClickListener {
            val emailText=mail.text.toString()
            val passwordText=password.text.toString()

            if(emailText.isNotEmpty() && passwordText.isNotEmpty()){
                LoginUser(emailText,passwordText)
            }
            else{
                Toast.makeText(this, "CHECK AGAIN SOME FIELDS ARE EMPTY", Toast.LENGTH_LONG).show()
            }
        }
    }
    private fun LoginUser(email:String, password:String){
        auth.signInWithEmailAndPassword(email,password)
            .addOnCompleteListener(this){task->
                if(task.isSuccessful){
                    Toast.makeText(this, "LOGIN SUCCESSFUL", Toast.LENGTH_LONG).show()
                    startActivity(Intent(this, CanvasMain::class.java))
                    finish()
                }
                else{
                    Toast.makeText(this, "Login Failed", Toast.LENGTH_LONG).show()
                }
            }
    }
}