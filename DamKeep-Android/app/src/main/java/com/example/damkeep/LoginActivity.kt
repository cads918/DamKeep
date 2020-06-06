package com.example.damkeep

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.SyncStateContract
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.Observer
import com.example.damkeep.api.TokenInterceptor
import com.example.damkeep.api.response.LoginDTO
import com.example.damkeep.common.Constants
import com.example.damkeep.di.MyApp
import com.example.damkeep.viewmodel.LoginViewModel
import javax.inject.Inject

class LoginActivity : AppCompatActivity() {

    @Inject
    lateinit var loginViewModel: LoginViewModel

    @Inject
    lateinit var sharedPref: SharedPreferences

    @Inject
    lateinit var tokenInterceptor: TokenInterceptor
    lateinit var username :  EditText
    lateinit var password :  EditText
    lateinit var login : Button
    lateinit var register : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        (applicationContext as MyApp).appComponent.inject(this)

        username = findViewById(R.id.editTextEmail)
        password = findViewById(R.id.editTextPassword)
        login = findViewById(R.id.buttonLogin)
        register = findViewById(R.id.buttonRegister)

        var u = username.text
        var p = password.text
        login.setOnClickListener(View.OnClickListener {
            loginViewModel.login(LoginDTO(u.toString(),p.toString())).observe(this,
                Observer {
                    tokenInterceptor.token = it.token
                    var i= Intent(this, MainActivity::class.java).apply {
                    }
                    startActivity(i)
                    finish()
                    Log.i("respuesta",""+it)
                })
        })

        register.setOnClickListener(View.OnClickListener {
            var i= Intent(this, RegisterActivity::class.java).apply {
            }
            startActivity(i)
        })
    }
}
