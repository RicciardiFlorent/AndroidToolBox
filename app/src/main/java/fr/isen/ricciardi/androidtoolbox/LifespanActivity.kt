package fr.isen.ricciardi.androidtoolbox

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import android.widget.Toast
import androidx.core.view.GestureDetectorCompat
import androidx.core.view.MotionEventCompat
import androidx.lifecycle.Lifecycle
import kotlinx.android.synthetic.main.activity_lifespan.*

class LifespanActivity : AppCompatActivity() {
    val newFragment = LifespanFragment()
    val newFragment2 = Lifespan2Fragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lifespan)

        textStateActivity.text = "onCreate"


        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.lifeSpanActivityLayout, newFragment)
        transaction.commit()

        buttonFragment.setOnClickListener{
            changeFragment(newFragment, newFragment2 )
        }



    }

    fun changeFragment( fragment: LifespanFragment, fragment2: Lifespan2Fragment){
        if(fragment.isResumed){
            Log.d("TAG","Fragment 1 is resumed")
            supportFragmentManager.beginTransaction().replace(R.id.lifeSpanActivityLayout, fragment2).commit()
        }else{
            Log.d("TAG","Fragment 2 is resumed")
            supportFragmentManager.beginTransaction().replace(R.id.lifeSpanActivityLayout, fragment).commit()

        }
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
