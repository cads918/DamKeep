package com.example.damkeep

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import com.example.damkeep.api.response.CreateNotaDTO
import com.example.damkeep.di.MyApp
import com.example.damkeep.viewmodel.AddViewModel
import com.example.damkeep.viewmodel.DetalleViewModel
import javax.inject.Inject

class AddActivity : AppCompatActivity() {

    @Inject
    lateinit var addViewModel: AddViewModel

    lateinit var titulo : EditText
    lateinit var contenido : EditText
    lateinit var guardar : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)
        (applicationContext as MyApp).appComponent.inject(this)

        titulo = findViewById(R.id.editTextTituloAdd)
        contenido = findViewById(R.id.editTextContenidoAdd)
        guardar = findViewById(R.id.buttonGuardarAdd)

        guardar.setOnClickListener(View.OnClickListener {
            var n = CreateNotaDTO(titulo.text.toString(),contenido.text.toString())
            addViewModel.nuevaNota(n).observe(this, Observer {
                Toast.makeText(this, "Nota Creada correctamente", Toast.LENGTH_LONG).show()
                finish()
            })
        })
    }
}
