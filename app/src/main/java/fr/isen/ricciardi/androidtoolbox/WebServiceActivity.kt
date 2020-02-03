package fr.isen.ricciardi.androidtoolbox

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import fr.isen.ricciardi.androidtoolbox.Classes.ContactAdapter
import fr.isen.ricciardi.androidtoolbox.Classes.RandomUserResult
import fr.isen.ricciardi.androidtoolbox.Classes.UserAdapter
import kotlinx.android.synthetic.main.activity_permissions.*
import kotlinx.android.synthetic.main.activity_web_service.*
import kotlinx.android.synthetic.main.recycler_view_contact_cell_webservice.*
import org.json.JSONObject

class WebServiceActivity : AppCompatActivity() {

    val url = "https://randomuser.me/api/?results=20"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_service)

        val queue = Volley.newRequestQueue(this)

        val request = StringRequest(Request.Method.GET, url, Response.Listener {
                response ->

            var gson = Gson()
            var result= gson.fromJson(response, RandomUserResult::class.java)

            result.results?.let{
                Log.d("volley", it[0].gender)

                RecyclerViewWS.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
                RecyclerViewWS.adapter = UserAdapter(it)
            }

        }, Response.ErrorListener {
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG)
        })

        queue.add(request)




    }
}
