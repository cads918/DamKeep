package com.example.damkeep.ui.Notas

import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.damkeep.DetalleActivity
import com.example.damkeep.R
import com.example.damkeep.api.response.Nota
import com.example.damkeep.di.MyApp


import kotlinx.android.synthetic.main.fragment_listado_notas.view.*


class MyListadoNotasRecyclerViewAdapter(

) : RecyclerView.Adapter<MyListadoNotasRecyclerViewAdapter.ViewHolder>() {

    private var listado : List<Nota> = ArrayList()
    private val mOnClickListener: View.OnClickListener

    init {
        mOnClickListener = View.OnClickListener { v ->
            val item = v.tag as Nota
            var i= Intent(MyApp.instance, DetalleActivity::class.java).apply {
                putExtra("id", item.id.toString())
            }
            MyApp.instance.startActivity(i)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_listado_notas, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = listado[position]

        holder.titulo.text = item.titulo

        with(holder.mView) {
            tag = item
            setOnClickListener(mOnClickListener)
        }
    }

    override fun getItemCount(): Int = listado.size

    fun setData(listadoNotas: List<Nota>) {
        listado = listadoNotas
        notifyDataSetChanged()
    }

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val titulo: TextView = mView.textViewTituloDetalle

    }
}
