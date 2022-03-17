package com.example.discapacidadauditiva

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.example.discapacidadauditiva.databinding.ActivityCamBinding
import com.example.discapacidadauditiva.databinding.ActivityCuentaBinding

class Cam : AppCompatActivity(), View.OnClickListener {
    lateinit var binding5: ActivityCamBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cam)
        binding5 = ActivityCamBinding.inflate(layoutInflater)
        setContentView(binding5.root)

//Funcion de los botones de la barra que envian a las demas ventanas
        /* irá tod0 lo funcional de la ventana cam: todos los eventos click de los botones y demas aquí*/














        //Funcion de los botones de la barra que envian a las demas ventanas
        binding5.textView6.setOnClickListener(){
            val lanzar = Intent(this, Cuenta::class.java)
            startActivity(lanzar)
        }
        binding5.textView3.setOnClickListener(){
            val lanzar2 = Intent(this, Texto::class.java)
            startActivity(lanzar2)
        }

    }

    override fun onClick(p0: View?) {

    }
}