package com.example.damkeep

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import com.example.damkeep.api.response.CreateUserDTO
import com.example.damkeep.di.MyApp
import com.example.damkeep.viewmodel.LoginViewModel
import javax.inject.Inject

class RegisterActivity : AppCompatActivity() {

    @Inject
    lateinit var loginViewModel: LoginViewModel

    lateinit var username : EditText
    lateinit var fullName: EditText
    lateinit var password : EditText
    lateinit var password2 : EditText
    lateinit var register : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        (applicationContext as MyApp).appComponent.inject(this)

        username = findViewById(R.id.editTextUsernameRegister)
        fullName = findViewById(R.id.editTextNombreRegister)
        password = findViewById(R.id.editTextPasswordRegister)
        password2 = findViewById(R.id.editTextPassword2Register)
        register = findViewById(R.id.buttonRegisterRegister)

        var u = username.text
        var f = fullName.text
        var p = password.text
        var p2 = password2.text
        register.setOnClickListener(View.OnClickListener {
            if (p.toString().equals(p2.toString())){
                var creado = CreateUserDTO(u.toString(),f.toString(),p.toString(),p2.toString())
                loginViewModel.register(creado).observe(this, Observer {
                    Toast.makeText(this, "Usuario registrado correctamente", Toast.LENGTH_LONG).show()
                    finish()
                })
            }else{
                Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_LONG).show()
            }
        })
    }
}
