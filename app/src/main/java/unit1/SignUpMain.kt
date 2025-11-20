package unit1

import android.content.Intent
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

class SignUpMain : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var mail: EditText
    private lateinit var password: EditText
    private lateinit var signUpBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sign_up_main)

        auth=FirebaseAuth.getInstance()

        mail=findViewById(R.id.username)
        password=findViewById(R.id.password)
        signUpBtn=findViewById(R.id.signUpBtn)

        signUpBtn.setOnClickListener {
            val emailText=mail.text.toString()
            val passwordText=password.text.toString()

            if(emailText.isNotEmpty() && passwordText.isNotEmpty()){
                SignUser(emailText,passwordText)
            }
            else{
                Toast.makeText(this,"Fill details properly", Toast.LENGTH_LONG).show()
            }
        }
    }
    private fun SignUser(email:String, password:String){
        auth.createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener(this){task->
                if(task.isSuccessful){
                    Toast.makeText(this,"Registered",Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, LoginMain::class.java))
                }
                else{
                    Toast.makeText(this,"Registration Failed",Toast.LENGTH_SHORT).show()
                }
            }
    }
}