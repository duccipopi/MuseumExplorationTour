package br.duccipopi.met.viewmodel

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Application
import android.content.Intent
import android.location.Location
import android.net.Uri
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.duccipopi.met.MyApp
import com.google.android.gms.common.ConnectionResult.SUCCESS
import com.google.android.gms.common.GoogleApiAvailability
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices


class DirectionsViewModel(app: Application) : AndroidViewModel(app) {

    companion object {
        val metLocation = Location("The MeT").apply {
            latitude = 40.7803465031835
            longitude = -73.96332983068702
        }
    }

    val googlePlayServiceAvailable: Boolean

    private val _lastDistance = MutableLiveData<Float>()
    val lastDistance: LiveData<Float>
        get() = _lastDistance


    private var fusedClient: FusedLocationProviderClient? = null


    init {
        googlePlayServiceAvailable = GoogleApiAvailability.getInstance()
            .isGooglePlayServicesAvailable(app.applicationContext) == SUCCESS
    }

    @SuppressLint("MissingPermission")
    fun updateLocation(activity: Activity) {
        if (fusedClient == null) {
            fusedClient = LocationServices.getFusedLocationProviderClient(activity)
        }

        val location = fusedClient?.lastLocation
        location?.addOnCompleteListener {
            if (it.isSuccessful) it.result?.let { loc ->
                // Distance in meters
                _lastDistance.value = loc.distanceTo(metLocation)
            }
        }
    }

    fun getIntentMapsForDirections(): Intent {
        val gmmIntentUri: Uri = Uri.parse("google.navigation:q=${metLocation.latitude},${metLocation.longitude}")
        val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
        mapIntent.setPackage("com.google.android.apps.maps")

        return mapIntent

    }


}