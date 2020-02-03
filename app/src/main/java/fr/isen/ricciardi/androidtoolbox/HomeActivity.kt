package fr.isen.ricciardi.androidtoolbox

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {
    var PREF_FILE = "pref_file"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val sharedPreferences = getSharedPreferences(PREF_FILE, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        imageLifeSpan.setOnClickListener {
            val intent = Intent(this, LifespanActivity::class.java)
            startActivity(intent)
        }

        btnDisconnection.setOnClickListener({
            editor.clear().commit()
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)

        })

        imageSave.setOnClickListener {
            val intent = Intent(this, FormActivity::class.java)
            startActivity(intent)
        }

        imagePermissions.setOnClickListener {
            val intent = Intent(this, PermissionsActivity::class.java)
            startActivity(intent)
        }

        imageWebServices.setOnClickListener{
            val intent = Intent(this, WebServiceActivity::class.java)
            startActivity(intent)
        }
    }
}
