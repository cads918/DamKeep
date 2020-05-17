package com.example.damkeep.ui.Notas

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.damkeep.R
import com.example.damkeep.di.MyApp

import com.example.damkeep.ui.Notas.dummy.DummyContent
import com.example.damkeep.viewmodel.ListadoNotasViewModel
import javax.inject.Inject


class ListadoNotasFragment : Fragment() {

    private var columnCount = 1
    private lateinit var listadoAdapter : MyListadoNotasRecyclerViewAdapter
    @Inject lateinit var listadoNotasViewModel: ListadoNotasViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity?.applicationContext as MyApp).appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_listado_notas_list, container, false)

        listadoAdapter = MyListadoNotasRecyclerViewAdapter()

        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }
                adapter = listadoAdapter
            }
        }
        listadoNotasViewModel.listadoNotas().observe(viewLifecycleOwner, Observer {
            listadoAdapter.setData(it)
        })

        return view
    }


    override fun onResume() {
        super.onResume()
        listadoNotasViewModel.listadoNotas().observe(viewLifecycleOwner, Observer {
            listadoAdapter.setData(it)
        })
    }
}
