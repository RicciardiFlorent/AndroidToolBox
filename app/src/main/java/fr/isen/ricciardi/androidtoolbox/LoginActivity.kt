package fr.isen.ricciardi.androidtoolbox


import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {


    var PREF_FILE = "pref_file"
    var login = "loginKey"
    var password = "passwordKey"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val sharedPreferences = getSharedPreferences(PREF_FILE, Context.MODE_PRIVATE)

        buttonValidate.setOnClickListener {
            val valueID = "${inputID.text}"
            val valuePW = "${inputPassword.text}"

            if(valueID.equals("admin") && valuePW.equals("123")){
                val editor = sharedPreferences.edit()
                editor.putString(login, valueID)
                editor.putString(password, valuePW)
                editor.commit()


                val intent = Intent(this, HomeActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            }else{
                Toast.makeText(this, "Identifiant ou Mot de passe incorrecte", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onResume(){
        super.onResume()
        val sharedPreferences = getSharedPreferences(PREF_FILE, Context.MODE_PRIVATE)
        if(sharedPreferences.getString(login,"")=="admin" && sharedPreferences.getString(password,"")== "123"){
            val intent = Intent(this, HomeActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }
    }



}
