package fr.isen.ricciardi.androidtoolbox

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_lifespan.*

class LifespanActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lifespan)

        textStateActivity.text = "onCreate"
    }

    override fun onStart(){
        super.onStart()
        textStateActivity.text = "onStart"
        Log.d("TAG", "onStart")


    }

    override fun onResume(){
        super.onResume()
        textStateActivity.text = "onResume"

    }

    override fun onRestart(){
        super.onRestart()
        textStateActivity.text = "onRestart"

    }

    override fun onPause(){
        super.onPause()
        Log.d("TAG", "onPause")
    }

    override fun onStop(){
        super.onStop()
        Log.d("TAG", "onStop")

    }

    override fun onDestroy() {
        super.onDestroy()
        Toast.makeText(this, "onDestroy", Toast.LENGTH_SHORT)
    }
}
