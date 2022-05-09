package com.example.mydigimind

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_contrasena.*

class ContrasenaActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contrasena)
        auth = FirebaseAuth.getInstance()
        btn_restablecer.setOnClickListener {
            validateForm()
        }
    }

    fun validateForm(){
        var correo = et_correo_contr.text.toString()
        if (!correo.isNullOrBlank()){
        auth.sendPasswordResetEmail(correo)
            .addOnCompleteListener{ task ->
                if(task.isSuccessful){
                    Toast.makeText(this,"Se envió la recuperación al correo electrónico $correo", Toast.LENGTH_SHORT)
                        .show()
                }else{
                 Toast.makeText(this,"Ocurrió un problema al enviar el correo.", Toast.LENGTH_SHORT)
                     .show()
                }
            }
        }else{
            Toast.makeText(this,"Escriba el correo de recuperación",Toast.LENGTH_SHORT)
                .show()
        }

    }
}