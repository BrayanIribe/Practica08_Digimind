package com.example.mydigimind

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_registro.*

class RegistroActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
// ...
// Initialize Firebase Auth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)
        auth = FirebaseAuth.getInstance()

        btn_registrar.setOnClickListener {
            validateForm()
        }
    }

    private fun register(email:String, passwd:String){
        auth.createUserWithEmailAndPassword(email, passwd)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    Toast.makeText(baseContext, "${user?.email} creado exitosamente.", Toast.LENGTH_SHORT).show()
                } else {
                    Log.w(TAG, "createUserWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Error de registro",
                        Toast.LENGTH_SHORT).show()
                }
            }
    }


    fun validateForm(){
        var email = et_correo.text.toString().trim()
        var password = et_contra_reg.text.toString()
        var password_confirm = et_contra_reg_conf.text.toString()
        println(email)
        if (!email.isNullOrBlank() && !password.isNullOrBlank() && !password_confirm.isNullOrBlank()){
            if (password == password_confirm){
                register(email,password);
            }else{
                Toast.makeText(this,"Credenciales invalidas.",Toast.LENGTH_SHORT)
                    .show()
            }
        }else{
            Toast.makeText(this,"Error en el formulario.",Toast.LENGTH_SHORT)
                .show()
        }
    }
}