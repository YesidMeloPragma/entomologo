package com.pragma.entomologo.sources.gpsLocation

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import androidx.core.content.ContextCompat
import com.google.android.gms.location.LocationRequest.PRIORITY_HIGH_ACCURACY
import com.google.android.gms.location.LocationServices
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

@Suppress("DEPRECATION")
class GPSLocationImpl @Inject constructor(private val context: Context) : GPSLocation {

    override suspend fun iHaveGPSPermissions(): Boolean {
        val permissions = arrayOf(ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION)
        for (permission in permissions) {
            val permissionStatus = ContextCompat.checkSelfPermission(context, permission)
            if (permissionStatus != PackageManager.PERMISSION_GRANTED) {
                return false
            }
        }
        return true
    }

    @SuppressLint("MissingPermission")
    override suspend fun getLocation(): Pair<Pair<Double, Double>, String> {
        val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
        return suspendCoroutine {
            continuation ->
            fusedLocationClient.getCurrentLocation(PRIORITY_HIGH_ACCURACY, null)
                .addOnSuccessListener {
                    location: Location? ->
                    if(location == null) {
                        continuation.resumeWithException(Exception("No se pudo obtener la ubicacion."))
                        return@addOnSuccessListener
                    }
                    val latitude = location.latitude
                    val longitude = location.longitude
                    val cityName = getCityName(latitude = latitude, longitude = longitude)
                    val locationInfo = Pair(Pair(latitude, longitude), cityName)
                    continuation.resume(value = locationInfo)
                }
        }
    }

    private fun getCityName(latitude: Double, longitude: Double): String {
        val geocoder = Geocoder(context)
        val addresses = geocoder.getFromLocation(latitude, longitude, 1) ?: return "Desconocida"

        return if (addresses.isNotEmpty()) {
            addresses[0].locality ?: addresses[0].subAdminArea ?: "Desconocida"
        } else {
            "Desconocida"
        }
    }
}