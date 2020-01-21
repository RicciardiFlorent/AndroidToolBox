package fr.isen.ricciardi.androidtoolbox

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

    fun sendMessage(view: View){
        val valueID = "${inputID.text}"
        val valuePW = "${inputPassword.text}"
        val duration = Toast.LENGTH_SHORT
        var text = ""

        if(valueID.equals("admin") && valuePW.equals("123")){
            text = "Identifiant correcte"
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }else{
            text = "Identifiant incorrecte"
        }

        val toast = Toast.makeText(this, text, duration)
        //toast.setGravity(Gravity.TOP or Gravity.LEFT, 500, 500)

        toast.show()
    }
}
