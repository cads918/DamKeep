package com.example.damkeep

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import com.example.damkeep.api.response.Nota
import com.example.damkeep.di.MyApp
import com.example.damkeep.viewmodel.DetalleViewModel
import com.example.damkeep.viewmodel.EditViewModel
import javax.inject.Inject

class EditActivity : AppCompatActivity() {

    @Inject
    lateinit var detalleViewModel: DetalleViewModel

    @Inject
    lateinit var editarViewModel: EditViewModel

    lateinit var titulo : EditText
    lateinit var contenido : EditText
    lateinit var guardar : Button
    lateinit var idNota : String
    lateinit var nota: Nota

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)
        (applicationContext as MyApp).appComponent.inject(this)

        titulo = findViewById(R.id.editTextEditarTitulo)
        contenido = findViewById(R.id.editTextEditarContenido)
        guardar = findViewById(R.id.buttonGuardarEditado)

        idNota = intent.getStringExtra("id")

        detalleViewModel.notaById(idNota).observe(this, Observer {
            titulo.text.append(it.titulo)
            contenido.text.append(it.contenido)
            nota = it
        })

        guardar.setOnClickListener(View.OnClickListener {
            nota.titulo = titulo.text.toString()
            nota.contenido = contenido.text.toString()
            editarViewModel.editarNota(nota.id.toString(),nota).observe(this, Observer {
                Toast.makeText(this, "Nota editada correctamente", Toast.LENGTH_LONG).show()
                finish()
            })
        })

    }
}
