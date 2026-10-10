package com.isetr.tp3ex3

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.isetr.tp3ex3.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.bt.setOnClickListener {
            val u = User(binding.et1.text.toString(), binding.et2.text.toString())
            if (u.email == "ADMIN" && u.password == "ADMIN") {
                val intent = Intent(this, ActivityProfil::class.java).apply {
                    putExtra("EXTRA_USER", u)
                }
                startActivity(intent)
                finish()
            } else {
                android.widget.Toast.makeText(this, "Email ou mot de passe incorrect", android.widget.Toast.LENGTH_SHORT).show()
            }
        }
    }
}