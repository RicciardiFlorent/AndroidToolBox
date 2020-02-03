package fr.isen.ricciardi.androidtoolbox

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.location.Location
import android.location.LocationManager
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.provider.ContactsContract
import android.provider.MediaStore
import android.provider.Settings
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.location.*
import fr.isen.ricciardi.androidtoolbox.Classes.Contact
import fr.isen.ricciardi.androidtoolbox.Classes.ContactAdapter
import fr.isen.ricciardi.androidtoolbox.Classes.ContactModel
import kotlinx.android.synthetic.main.activity_permissions.*

class PermissionsActivity : AppCompatActivity(), LocationListener {


    companion object {
        val contactPermissionRequestCode = 2
        val gpsPermissionRequestCode = 3
    }

    lateinit var locationManager: LocationManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_permissions)
        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        permissions_picture.setOnClickListener{
            val galeryIntent = Intent(Intent.ACTION_PICK)// Gallery
            galeryIntent.type = "image/*"

            val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

            val intentChoose = Intent.createChooser(galeryIntent, "Gallery")
            intentChoose.putExtra(Intent.EXTRA_INITIAL_INTENTS, arrayOf(cameraIntent))
            startActivityForResult(intentChoose,11)
        }

        requestPermission(android.Manifest.permission.READ_CONTACTS, contactPermissionRequestCode) {
            readContact()
        }

        requestPermission(android.Manifest.permission.ACCESS_COARSE_LOCATION, gpsPermissionRequestCode) {
            startGPS()
        }
    }

    fun readContact(){
        var contactList = ArrayList<ContactModel>()
        val contacts = contentResolver.query(ContactsContract.Contacts.CONTENT_URI,null,null,null,null)
        while(contacts?.moveToNext() ?: false){
            val displayName = contacts?.getString(contacts.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME))
            val contactModel = ContactModel()
            contactModel.displayName = displayName.toString()
            contactList.add(contactModel)
            Log.d("Contact", contactModel.displayName)
        }
        contactRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
        contactRecyclerView.adapter = ContactAdapter(contactList)
    }


    @SuppressLint("MissingPermission")
    fun startGPS() {
        locationManager.requestSingleUpdate(LocationManager.NETWORK_PROVIDER, this, null)
        val location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
        location?.let {
            refreshPositionUI(it)
        }
    }

    fun refreshPositionUI(location: Location){
        textLatitude.text = "Latitude: ${location.latitude}"
        textLongitude.text = "Longitude: ${location.longitude}"
    }


    fun requestPermission(permissionToRequest: String, requestCode: Int, handler: ()-> Unit) {
        if(ContextCompat.checkSelfPermission(this, permissionToRequest) != PackageManager.PERMISSION_GRANTED) {
            if(ActivityCompat.shouldShowRequestPermissionRationale(this, permissionToRequest)) {
                //display toast
            } else {
                ActivityCompat.requestPermissions(this, arrayOf(permissionToRequest), requestCode)
            }
        } else {
            handler()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK ){
            if(data?.data == null){
                val bitmap = data?.extras?.get("data") as? Bitmap
                bitmap?.let{
                    permissions_picture.setImageBitmap(it)
                }
            }else{
                permissions_picture.setImageURI(data?.data)

            }

        }
    }

    override fun onLocationChanged(location: Location?) {
        location?.let{
            refreshPositionUI(it)
        }
    }


}

private fun LocationManager.requestSingleUpdate(networkProvider: String, permissionsActivity: PermissionsActivity, nothing: Nothing?) {

}


