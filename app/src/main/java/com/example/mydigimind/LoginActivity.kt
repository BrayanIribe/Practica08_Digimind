package com.example.mydigimind

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        auth = FirebaseAuth.getInstance()
        btn_registrar.setOnClickListener {
            val intent: Intent = Intent(this, RegistroActivity::class.java)
            startActivity(intent)
        }

        btn_ingresar.setOnClickListener {
            validateForm()
         }

        btn_contrasena.setOnClickListener{
            val intent: Intent = Intent(this, ContrasenaActivity::class.java)
            startActivity(intent)
        }


    }
    fun validateForm(){
        var email = et_correo.text.toString().trim()
        var passwd = et_contraseña.text.toString()
        if(!email.isNullOrBlank() && !passwd.isNullOrBlank()){
            login(email, passwd)
        }else{
            Toast.makeText(this,"Formulario invalido.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun login(email:String, password:String){
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    val intent: Intent = Intent(this, MainActivity::class.java)
                    intent.putExtra("user",user)
                    startActivity(intent)
                } else {
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                }
            }

    }

}