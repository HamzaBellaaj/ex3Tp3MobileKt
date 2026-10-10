package com.isetr.tp3ex3

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.isetr.tp3ex3.databinding.ActivityProfilBinding

class ActivityProfil : AppCompatActivity() {
    private lateinit var binding: ActivityProfilBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityProfilBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val user: User? = if (Build.VERSION.SDK_INT >=
            Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra("EXTRA_USER", User::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra("EXTRA_USER")
        }
        binding.resul.text= user?.email+"@isetr.tn"
        binding.fb.setOnClickListener {
            try {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com"))
                startActivity(intent)
            } catch (_: Exception) {
                android.widget.Toast.makeText(this, "Impossible d'ouvrir le lien", android.widget.Toast.LENGTH_SHORT).show()
            }
        }
        fun deco(){
            var intent= Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
        binding.deco.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("Confirmation de déconnexion")
                .setMessage("Êtes-vous sûr de vouloir vous déconnecter et quitter votre session ?")
                .setPositiveButton("Oui") { _, _ ->deco()}
                .setNegativeButton("Non") { dialog, _ ->
                    dialog.dismiss()
                }
                .setIcon(R.drawable.ic_deco)
                .show()
        }
        binding.eme.setOnClickListener {
            fun sendEmail() {
                val desti = "directeur@isetr.tn"
                val subject = "Informations de mon compte (ADMIN)"
                val body = "Bonjour M.Le directeur ,\n\nMon email est Admin@isetr.tn ."
                val emailIntent = Intent(Intent.ACTION_SEND).apply {
                    type = "text/plain"
                    putExtra(Intent.EXTRA_EMAIL, arrayOf(desti))
                    putExtra(Intent.EXTRA_SUBJECT, subject)
                    putExtra(Intent.EXTRA_TEXT, body)
                }
                if (emailIntent.resolveActivity(packageManager) != null) {
                    val chooserTitle = "Choisir une application de messagerie"
                    startActivity(Intent.createChooser(emailIntent, chooserTitle))
                } else {
                    Toast.makeText(this, "Aucun client mail n'est installé sur cet appareil.",
                        Toast.LENGTH_LONG).show()
                }
            }
            sendEmail()
        }
    }
}