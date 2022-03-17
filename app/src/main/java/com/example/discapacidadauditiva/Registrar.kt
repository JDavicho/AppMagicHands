package com.example.discapacidadauditiva

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.discapacidadauditiva.databinding.ActivityCuentaBinding
import com.example.discapacidadauditiva.databinding.ActivityRegistrarBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

class Registrar : AppCompatActivity(), View.OnClickListener {
    private var reg:Button?=null
    private var email:EditText?=null
    private var pass:EditText?=null
    lateinit var binding6: ActivityRegistrarBinding
    val ref = FirebaseAuth.getInstance()
    lateinit var auth: FirebaseAuth

    lateinit var databaseReference: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrar)
        binding6 = ActivityRegistrarBinding.inflate(layoutInflater)
        setContentView(binding6.root)


        auth = Firebase.auth
        auth = FirebaseAuth.getInstance()

        databaseReference = FirebaseDatabase.getInstance().getReference("Usuarios")
        binding6.regi.setOnClickListener {

                val Nombre = binding6.editTextText2.text.toString()

                val Edad = binding6.editTextText6.text.toString()
            val Numero_Telefonico = binding6.editTextText7.text.toString()
            val user = User(Nombre, Edad, Numero_Telefonico)

            val em = binding6.email.text.toString()
            val password = binding6.pas.text.toString()
            databaseReference.child("Usuarios").child(Nombre).get().addOnSuccessListener {

                databaseReference.child(Nombre).setValue(Nombre)
                crearcuenta(em, password)
            Toast.makeText(
                baseContext, "Creado Coreectamente",
                Toast.LENGTH_SHORT).show()
            val uid = auth.currentUser?.uid

            if (uid != null) {
                databaseReference.child(uid!!).setValue(user).addOnCompleteListener {
                    if (it.isSuccessful) {
                        Toast.makeText(
                            this@Registrar,
                            "Información agregegada corectamente " + Nombre,
                            Toast.LENGTH_SHORT
                        ).show()

                        //uploadprofilepic()

                    } else {
                        Toast.makeText(
                            this@Registrar,
                            "Error, No se pudieron agregar los campos",
                            Toast.LENGTH_SHORT
                        ).show()
                        }
                    }
                }

            }

            val lanzar = Intent(this, Loguin2::class.java)
            startActivity(lanzar)
        }

    }
    private fun crearcuenta(em:String,password:String) {
        auth.createUserWithEmailAndPassword(em, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(
                        baseContext, "Creado Coreectamente",
                        Toast.LENGTH_SHORT).show()
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("TAG", "createUserWithEmail:failure", task.exception)
                    Toast.makeText(
                        baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT
                    )
                }
            }
    }

    override fun onClick(p0: View?) {

    }
}