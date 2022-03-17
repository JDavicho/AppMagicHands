package com.example.discapacidadauditiva

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.bumptech.glide.util.Util
import com.example.discapacidadauditiva.databinding.ActivityCamBinding
import com.example.discapacidadauditiva.databinding.ActivityCuentaBinding
import com.example.discapacidadauditiva.databinding.ActivityLoguin2Binding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.firestore.auth.User
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.squareup.okhttp.internal.DiskLruCache
import java.lang.ref.PhantomReference
import com.google.firebase.firestore.FirebaseFirestore as FirebaseFirestore1

class Cuenta : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    lateinit var binding3: ActivityCuentaBinding
    lateinit var databaseReference: DatabaseReference
    lateinit var storageReference: StorageReference
    lateinit var imageUri: Uri

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cuenta)
        binding3 = ActivityCuentaBinding.inflate(layoutInflater)

        setContentView(binding3.root)
        auth = FirebaseAuth.getInstance()

        databaseReference = FirebaseDatabase.getInstance().getReference("Usuarios")
//Toma datoos de la bd
        /*var userr = com.example.discapacidadauditiva.User()
        val uid = auth.currentUser?.uid
        if (uid != null) {
            if (uid!!.isNotEmpty()) {
                databaseReference.child(uid!!).addValueEventListener(object :ValueEventListener{
                    override fun onDataChange(snapshot: DataSnapshot) {
                        userr = snapshot.getValue(com.example.discapacidadauditiva.User::class.java)!!
                        binding3.nm.setText(userr.Nombre)


                    }

                    override fun onCancelled(error: DatabaseError) {

                    }


                })
            }
        }*/
        //databaseReference.child("Usuarios").child("nombre").get().addOnSuccessListener {
           /*Esto crea usuarios
val nomb: String=""
        databaseReference.child("Nombre").setValue(nomb)
        Toast.makeText(this,"Nombre: "+nomb,Toast.LENGTH_LONG).show()
*/


            binding3.save.setOnClickListener() {

                val Nombre = binding3.editTextText3.text.toString()
                val Edad = binding3.editTextText4.text.toString()
                val Numero_Telefonico = binding3.editTextText5.text.toString()

                val uid = auth.currentUser?.uid

                val user = User(Nombre, Edad, Numero_Telefonico)

                if (uid != null) {
                    databaseReference.child(uid!!).setValue(user).addOnCompleteListener {
                        if (it.isSuccessful) {
                            Toast.makeText(
                                this@Cuenta,
                                "Información agregegada corectamente " + Nombre,
                                Toast.LENGTH_SHORT
                            ).show()

                            //uploadprofilepic()

                        } else {
                            Toast.makeText(
                                this@Cuenta,
                                "Error, No se pudieron agregar los campos",
                                Toast.LENGTH_SHORT
                            ).show()

                        }
                    }

                }


                binding3.textView5.setOnClickListener() {
                    val lanzar = Intent(this, Cam::class.java)
                    startActivity(lanzar)
                }
                binding3.textView3.setOnClickListener() {
                    val lanzar2 = Intent(this, Texto::class.java)
                    startActivity(lanzar2)
                }


            }

        }

/*
    private fun uploadprofilepic() {

imageUri = Uri.parse("adroid.resource://$packageName/${R.drawable.userr}")
storageReference= FirebaseStorage.getInstance().getReference("Usuarios/"+auth.currentUser?.uid)
     storageReference.putFile(imageUri).addOnSuccessListener {
         Toast.makeText(this@Cuenta,"Error, No se pudieron agregar los campos",Toast.LENGTH_SHORT).show()

     }.addOnFailureListener {
         Toast.makeText(this@Cuenta,"Error, No se pudieron agregar los campos",Toast.LENGTH_SHORT).show()

     }

    }*/
    }




