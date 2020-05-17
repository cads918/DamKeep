package com.example.damkeep

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.damkeep.di.MyApp
import com.example.damkeep.viewmodel.DetalleViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import javax.inject.Inject

class DetalleActivity : AppCompatActivity() {

    @Inject
    lateinit var detalleViewModel: DetalleViewModel

    lateinit var idNota : String
    lateinit var titulo : TextView
    lateinit var contenido : TextView
    lateinit var creacion : TextView
    lateinit var edicion : TextView
    lateinit var delete : FloatingActionButton
    lateinit var editar : FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle)
        (applicationContext as MyApp).appComponent.inject(this)

        idNota = intent.getStringExtra("id")

        titulo = findViewById(R.id.textViewTituloDetalle)
        contenido = findViewById(R.id.textViewContenidoDetalle)
        creacion = findViewById(R.id.textViewFechaCreancionDetalle)
        edicion = findViewById(R.id.textViewFechaEdicionCreacion)
        delete = findViewById(R.id.floatingActionButtonDelete)
        editar = findViewById(R.id.floatingActionButtonEditar)

        detalleViewModel.notaById(idNota).observe(this, Observer {
            titulo.text = it.titulo
            contenido.text = it.contenido
            creacion.text = it.fecha_creacion
            edicion.text = it.fecha_modi
        })

        delete.setOnClickListener(View.OnClickListener {
            val alertDialogBuilder =
                AlertDialog.Builder(
                    this
                )
            alertDialogBuilder.setTitle("Borrado")
            alertDialogBuilder
                .setMessage("¿Estas seguro que deseas borrar esta nota?")
                .setCancelable(false)
                .setPositiveButton("Si",
                    DialogInterface.OnClickListener { dialog, id ->
                        detalleViewModel.borrarNota(idNota)
                        Toast.makeText(this, "Nota Borrada correctamente", Toast.LENGTH_LONG).show()
                        finish()
                    })
                .setNegativeButton("No",
                    DialogInterface.OnClickListener { dialog, id -> dialog.cancel() })
            val alertDialog = alertDialogBuilder.create()
            alertDialog.show()
        })

        editar.setOnClickListener(View.OnClickListener {
            var i= Intent(this, EditActivity::class.java).apply {
                putExtra("id", idNota)
            }
            MyApp.instance.startActivity(i)
        })

    }

    override fun onResume() {
        super.onResume()
        detalleViewModel.notaById(idNota).observe(this, Observer {
            titulo.text = it.titulo
            contenido.text = it.contenido
            creacion.text = it.fecha_creacion
            edicion.text = it.fecha_modi
        })
    }
}
