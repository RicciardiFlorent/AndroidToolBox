package fr.isen.ricciardi.androidtoolbox

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View.OnFocusChangeListener
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import fr.isen.ricciardi.androidtoolbox.Classes.User
import kotlinx.android.synthetic.main.activity_form.*
import java.io.BufferedReader
import java.io.File
import java.text.SimpleDateFormat
import java.util.*


class FormActivity : AppCompatActivity() {
    var currentDate = Date()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form)

        val filename = cacheDir.absolutePath+"/UserJson.json"

        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)
        val myFormat = "dd/MM/yyyy" // mention the format you need
        val sdf = SimpleDateFormat(myFormat, Locale.FRANCE)

        var user = readJSONFromFile(filename)
        inputFirstname.setText(user.firstName)
        inputLastName.setText(user.lastName)
        inputDate.setText(user.birthDate)

        inputDate.setOnFocusChangeListener(OnFocusChangeListener { view, hasFocus ->
            if (hasFocus) {
                val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                    c.set(Calendar.YEAR, year)
                    c.set(Calendar.MONTH, monthOfYear)
                    c.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                    // Display Selected date in TextView
                    inputDate.setText(sdf.format(c.time))

                }, year, month, day)
                dpd.show()
            }
        })

        saveButton.setOnClickListener {

            var firstName = "${inputFirstname.text}"
            var lastName = "${inputLastName.text}"
            var dateU = "${inputDate.text}"

            //check if all the fields are filled
            if(firstName.isEmpty() || lastName.isEmpty() || dateU.isEmpty()) {
                Toast.makeText(this, "Tous les champs doivent être remplis", Toast.LENGTH_SHORT).show()
            }else{ //if all the fields are filled , write the user informations in a file

                writeJSONToFile(filename,firstName,lastName,dateU)
                Toast.makeText(this, "${firstName} ${lastName} ${dateU}", Toast.LENGTH_SHORT).show()
            }

        }

        infosButton.setOnClickListener {
            var user = readJSONFromFile(filename)
            val alertDialogB : AlertDialog.Builder = AlertDialog.Builder(this)
            alertDialogB.setTitle("Informations").setMessage("Nom : " + user.lastName + "\nPrenom : " + user.firstName+ "\nDate de naissance : " + user.birthDate)
            var alertDialog: AlertDialog = alertDialogB.create()
            alertDialog.show()
        }
    }

    //this function allows to write a JSON objet into a file
    fun writeJSONToFile(s: String,firstName: String, lastName: String, date: String){
        var user = User(firstName, lastName, date)         //Create a Object of User
        var gson = Gson()//Create a Object of Gson
        var jsonString:String = gson.toJson(user)   //Convert the Json object to JsonString
        val file = File(s) //Initialize the File Writer and
        file.writeText(jsonString) //write into file
    }

    //This function allows to read a JSON object from a file
    fun readJSONFromFile(f:String): User{
        var gson = Gson() //Creating a new Gson object to read data
        val bufferedReader: BufferedReader = File(f).bufferedReader() // //Read the user.json file
        val inputString = bufferedReader.use{ it.readText()}  // Read the text from buffferReader and store in String variable
        var user = gson.fromJson(inputString, User::class.java) //Convert the Json File to Gson Object
        var birthDate = user.birthDate.toString().split("/")
        Toast.makeText(this, " Vous avez ${getAge(birthDate[2].toInt(),birthDate[1].toInt(),birthDate[0].toInt())} ans !", Toast.LENGTH_SHORT).show()

        return user
    }

    fun getAge(year: Int, month: Int, day: Int) : Int{
        val formatter = SimpleDateFormat("dd/MM/yyyy" )
        val dateString = formatter.format(currentDate)
        val currentDateSplit = dateString.split("/")
        val currentday = currentDateSplit[0].toInt()
        val currentmonth = currentDateSplit[1].toInt()
        val currentyear = currentDateSplit[2].toInt()

        var age = currentyear - year

        if((month > currentmonth)){
            age--
        }else if ( currentmonth == month && currentday < day){
            age--
        }


        return age

    }
}
