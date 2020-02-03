package fr.isen.ricciardi.androidtoolbox


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * A simple [Fragment] subclass.
 */
class LifespanFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        Log.d("TAG","Fragment1 onCreateView")
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_lifespan, container, false)
    }

    override fun onResume(){
        super.onResume()
        Log.d("TAG","Fragment1 onResume")

    }

    //Do the same for the rest of the states like in LifespanActivity


}
