package com.example.discapacidadauditiva

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.discapacidadauditiva.databinding.ActivityLoguin2Binding
import com.example.discapacidadauditiva.databinding.ActivityMainBinding
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class Loguin2 : AppCompatActivity(), View.OnClickListener {
    private var ini: Button? = null
    private var reg: Button? = null
    private var email:EditText?=null
    private var pass:EditText?=null
    private lateinit var auth: FirebaseAuth
    lateinit var binding2: ActivityLoguin2Binding

    val ref = FirebaseAuth.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loguin2)
        binding2 = ActivityLoguin2Binding.inflate(layoutInflater)
        setContentView(binding2.root)
        auth = Firebase.auth
        ini = findViewById<Button>(R.id.ini)
        ini!!.setOnClickListener(this)
        reg = findViewById<Button>(R.id.Reg)
        reg!!.setOnClickListener(clickListener2)

        email = findViewById<EditText>(R.id.email1)
        pass = findViewById<EditText>(R.id.editTextTextPassword)






    }

    val clickListener2 = View.OnClickListener {
        val lanzar = Intent(this, Registrar::class.java)
        startActivity(lanzar)
    }

    private fun SingIn(email1:String,pass1:String){
        auth.signInWithEmailAndPassword(email1, pass1)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {

                    Log.d("TAG", "signInWithEmail:success")
                    reaload()
                } else {
                    Log.w("TAG", "signInWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Correo o contraseña incorrecta",
                        Toast.LENGTH_SHORT).show()

                }
            }

    }

    override fun onClick(p0: View?) {

        val email1 = binding2.email1.text.toString()
        val pass1 = binding2.editTextTextPassword.text.toString()
        when{
            email1.isEmpty()|| pass1.isEmpty() ->{
                Toast.makeText(baseContext, "Correo o contraseña incorrecta",
                    Toast.LENGTH_SHORT).show()

            }      }


        SingIn(email1,pass1)

    }
    private fun reaload(){
        val lanzar = Intent(this, Texto::class.java)
        startActivity(lanzar)
    }
    }
