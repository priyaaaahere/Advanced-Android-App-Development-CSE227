package unit1

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.cse227etp.R
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

data class Users(
    val id:Int?=null,
    val name:String?=null,
    val email:String?=null
)
class CrudOps: AppCompatActivity(){
    private lateinit var userId: EditText
    private lateinit var userName: EditText
    private lateinit var userEmail: EditText
    private lateinit var btnCreateUser: Button
    private lateinit var btnReadUser: Button
    private lateinit var btnUpdateUser: Button
    private lateinit var btnDeleteUser: Button

    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        userId=findViewById(R.id.etUserId)
        userName=findViewById(R.id.etUserName)
        userEmail=findViewById(R.id.etUserEmail)
        btnCreateUser=findViewById(R.id.btnCreateUser)
        btnReadUser=findViewById(R.id.btnReadUser)
        btnUpdateUser=findViewById(R.id.btnUpdateUser)
        btnDeleteUser=findViewById(R.id.btnDeleteUser)

        //firebase reference
        database= FirebaseDatabase.getInstance().getReference("Users")

        //Create User
        btnCreateUser.setOnClickListener {
            val id=userId.text.toString().toIntOrNull()
            val name=userName.text.toString()
            val mail=userEmail.text.toString()
            if(id!=null && name.isNotEmpty() && mail.isNotEmpty()){
                val user= Users(id,name,mail)
                database.child(id.toString()).setValue(user)
                    .addOnSuccessListener {
                        Toast.makeText(this, "CREATED", Toast.LENGTH_LONG).show()
                    }
                    .addOnFailureListener {
                        Toast.makeText(this, "FAILED", Toast.LENGTH_LONG).show()
                    }
            }
            else{
                Toast.makeText(this, "CHECK AGAIN SOME FIELDS ARE EMPTY", Toast.LENGTH_LONG).show()
            }
        }

        //Read user
        btnReadUser.setOnClickListener {
            val id=userId.text.toString()
            if(id.isNotEmpty()){
                database.child(id).get()
                    .addOnSuccessListener {
                        if(it.exists()){

                            Toast.makeText(this,"Button clicked", Toast.LENGTH_LONG).show()
                            val name = it.child("name").value
                            val mail=it.child("email").value
                            userName.setText(name.toString())
                            userEmail.setText(mail.toString())
                            Toast.makeText(this,"READ",Toast.LENGTH_LONG).show()
                        }
                        else{
                            Toast.makeText(this,"USER DOES NOT EXIST",Toast.LENGTH_LONG).show()
                        }
                    }
                    .addOnFailureListener {
                        Toast.makeText(this,"FAILED",Toast.LENGTH_LONG).show()
                    }
                }
            else{
                Toast.makeText(this,"CHECK AGAIN SOME FIELDS ARE EMPTY",Toast.LENGTH_LONG).show()
            }
        }
        //Update User
        btnUpdateUser.setOnClickListener {
            val id=userId.text.toString()
            val name=userName.text.toString()
            val mail=userEmail.text.toString()
            if(id.isNotEmpty() && name.isNotEmpty() && mail.isNotEmpty()){
                val user= Users(id.toInt(),name,mail)
                database.child(id).setValue(user)
                    .addOnSuccessListener {
                        Toast.makeText(this,"UPDATED",Toast.LENGTH_LONG).show()
                    }
                    .addOnFailureListener {
                        Toast.makeText(this,"FAILED",Toast.LENGTH_LONG).show()
                    }
            }
            else{
                Toast.makeText(this,"CHECK AGAIN SOME FIELDS ARE EMPTY",Toast.LENGTH_LONG).show()

            }
        }
        btnDeleteUser.setOnClickListener {
            val id=userId.text.toString()
            if(id.isNotEmpty()){
                database.child(id).removeValue()
                    .addOnSuccessListener {
                        Toast.makeText(this,"DELETED",Toast.LENGTH_LONG).show()
                        userName.setText("")
                        userEmail.setText("")
                    }
                    .addOnFailureListener {
                        Toast.makeText(this,"FAILED",Toast.LENGTH_LONG).show()
                    }
            }
            else{
                Toast.makeText(this,"CHECK AGAIN SOME FIELDS ARE EMPTY",Toast.LENGTH_LONG).show()
            }
        }
    }
}