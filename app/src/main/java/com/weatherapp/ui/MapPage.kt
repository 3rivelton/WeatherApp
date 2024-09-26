package com.weatherapp.ui

import android.content.Context
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.weatherapp.MainViewModel

@Composable
fun MapPage(
    viewModel : MainViewModel,
    context: Context
) {
    val recife = LatLng(-8.05, -34.9)
    val caruaru = LatLng(-8.27, -35.98)
    val joaopessoa = LatLng(-7.12, -34.84)
    val camPosState = rememberCameraPositionState ()

    GoogleMap (
        modifier = Modifier.fillMaxSize(),
        onMapClick = { viewModel.add("Nova cidade", location = it) },
        cameraPositionState = camPosState
    ) {
        viewModel.cities.forEach {
            if (it.location != null) {
                Marker( state = MarkerState(position = it.location!!),
                    title = it.name, snippet = "${it.location}")
            }
        }
        Marker(
            state = MarkerState(position = recife),
            title = "Recife",
            snippet = "Marcador em Recife",
            icon = BitmapDescriptorFactory.defaultMarker( BitmapDescriptorFactory.HUE_BLUE)
        )
        Marker(
            state = MarkerState(position = caruaru),
            title = "Caruaru",
            snippet = "Marcador em Caruaru",
            icon = BitmapDescriptorFactory.defaultMarker( BitmapDescriptorFactory.HUE_BLUE)
        )
        Marker(
            state = MarkerState(position = joaopessoa),
            title = "Joao Pessoa",
            snippet = "Marcador em Joao Pessoa",
            icon = BitmapDescriptorFactory.defaultMarker( BitmapDescriptorFactory.HUE_BLUE)
        )
    }
}