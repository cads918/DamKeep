package com.example.damkeep.di


import com.example.damkeep.*
import com.example.damkeep.api.NetworkModule
import com.example.damkeep.ui.Notas.ListadoNotasFragment
import com.example.damkeep.ui.dashboard.DashboardFragment
import com.example.damkeep.ui.home.HomeFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component( modules = [NetworkModule::class])
interface ApplicationComponent {
    fun inject(movieListFragment: HomeFragment)
    fun inject(dashboardFragment: DashboardFragment)
    fun inject(loginActivity: LoginActivity)
    fun inject(registerActivity: RegisterActivity)
    fun inject(listadoNotasFragment: ListadoNotasFragment)
    fun inject(detalleActivity: DetalleActivity)
    fun inject(editActivity: EditActivity)
    fun inject(mainActivity: MainActivity)
    fun inject(addActivity: AddActivity)
}