package com.example.discapacidadauditiva

import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.speech.RecognizerIntent
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.*
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.discapacidadauditiva.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.storage.FirebaseStorage
import java.io.File
import java.util.*


class Texto : AppCompatActivity(), View.OnClickListener, TextWatcher {
    private lateinit var auth: FirebaseAuth
/*Variables*/
    lateinit var binding: ActivityMainBinding
    lateinit var activityResultLauncher: ActivityResultLauncher<Intent>
    private var tabla: TableLayout?=null
    private var traductor: Button? = null
    private var log: Button? = null
    private var ingre: EditText? = null
    private var view: ViewPager?= null
    private var img: ImageView? = null
    private var img2: ImageView? = null
    private var ln:LinearLayout?=null
    private var Inic: String = ""
    private var progressBar: ProgressBar?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
               /*Se inicializan variables*/
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)




        emoji()

            ingre = findViewById(R.id.editTextText) as EditText
        traductor = findViewById(R.id.tradu) as Button
        traductor!!.setOnClickListener(this)

        /*Botones que enlazan a las demas ventanas*/
        binding.textView5.setOnClickListener(){
            val lanzar = Intent(this, Cam::class.java)
            startActivity(lanzar)
        }
        binding.textView6.setOnClickListener(){
            val lanzar2 = Intent(this, Cuenta::class.java)
            startActivity(lanzar2)
        }


/*  Traduce y manda a llamar la imagen por medio del Audio*/
        binding.audi.setOnClickListener() {
            val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
            intent.putExtra(
                RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
            )
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
            intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Traduciendo..")
            try {
                activityResultLauncher.launch(intent)
            } catch (exp: ActivityNotFoundException) {
                Toast.makeText(applicationContext, "Device does supported", Toast.LENGTH_SHORT).show()

            }

        }
        /*Muestra la pantalla del microfono de Google*/
        activityResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {result: ActivityResult? ->
            if (result!!.resultCode == RESULT_OK && result!!.data != null){
                val spechtext= result!!.data!!.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)as ArrayList<Editable>
                binding.editTextText.text=spechtext[0]

                val imageName = binding.editTextText.text.toString()
                val storageRef =
                    FirebaseStorage.getInstance().reference.child("/Images/"+imageName+".gif")

                val localfile = File.createTempFile("tempGif", "gif")
                storageRef.getFile(localfile).addOnSuccessListener {

                    val bitmap = BitmapFactory.decodeFile(localfile.absolutePath)
                    binding.gifImageView2.setImageBitmap(bitmap)
                        traduccion()

                }.addOnFailureListener {
                    Toast.makeText(this, "Error, Traducción No Encontrada", Toast.LENGTH_SHORT)
                        .show()
                }
            }
            binding.tradu.setOnClickListener() {

                traduccion()
            }



        }
    }
/*Funcion del boton*/
    override fun onClick(p0: View?) {
        traduccion()

    }
/* Funcion que traduce y manda a llamar la imagen por medio de el boton*/
        private fun traduccion() {
                val imageName = binding.editTextText.text.toString()
                val storageRef =
                    FirebaseStorage.getInstance().reference.child("/Images/$imageName.gif")

                val localfile = File.createTempFile("tempImage", "anim")
                storageRef.getFile(localfile).addOnSuccessListener {

                    val bitmap = BitmapFactory.decodeFile(localfile.absolutePath)
                    binding.gifImageView2.setImageBitmap(bitmap)
               /*función que anima la imagen*/
                    Uri.parse(storageRef.toString())

                    Glide.with(this)
                        .load(localfile)
                        .centerCrop()
                        .fitCenter()
                        .override(600,400)
                        .into(binding.gifImageView2)
                        /*.dontAnimate()*/
                    binding.paused.setOnClickListener(){
                        Uri.parse(storageRef.toString())

                        Glide.with(this)
                            .load(localfile)
                            .centerCrop()
                            .dontAnimate()
                            .fitCenter()
                            .override(600,400)
                            .into(binding.gifImageView2)

                    }
                    binding.play.setOnClickListener(){
                        traduccion()

                    }


                }.addOnFailureListener {
                    Toast.makeText(this, "Error, Traducción No Encontrada", Toast.LENGTH_SHORT)
                        .show()
                }


        }
    private fun emoji() {
        val storageRef =
            FirebaseStorage.getInstance().reference.child("/Images/emoji.gif")

        val localfile = File.createTempFile("tempImage", "anim")
        storageRef.getFile(localfile).addOnSuccessListener {

            val bitmap = BitmapFactory.decodeFile(localfile.absolutePath)
            binding.gifImageView2.setImageBitmap(bitmap)
            /*función que anima la imagen*/
            Uri.parse(storageRef.toString())


            Glide.with(this)
                .load(localfile)
                .centerCrop()
                .fitCenter()
                .override(400,600)
                .into(binding.gifImageView2)

        }.addOnFailureListener {
            Toast.makeText(this, "Error, Traducción No Encontrada", Toast.LENGTH_SHORT)
                .show()


        }

    }
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

        }

        override fun afterTextChanged(p0: Editable?) {

        }
    }


