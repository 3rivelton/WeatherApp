package com.weatherapp.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import com.weatherapp.R
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.weatherapp.MainViewModel
import com.weatherapp.model.Forecast
import java.text.DecimalFormat

@Composable
fun HomePage(
    viewModel: MainViewModel,
    modifier: Modifier
) {
    Column {
        Row {
            AsyncImage(
                model = viewModel.city?.weather?.imgUrl,
                modifier = Modifier.size(100.dp),
                error = painterResource(id = R.drawable.loading),
                contentDescription = "Imagem"
            )
            Column {
                Spacer(modifier = Modifier.size(20.dp))
                Text(text = viewModel.city?.name ?: "Selecione uma cidade...", fontSize = 24.sp)
                Spacer(modifier = Modifier.size(10.dp))
                Text(text = viewModel.city?.weather?.desc ?: "...", fontSize = 20.sp)
                Spacer(modifier = Modifier.size(10.dp))
                Text(text = "Temp: " + viewModel.city?.weather?.temp + "℃", fontSize = 20.sp)
            }
        }

//        if (viewModel.city == null || viewModel.city!!.forecast == null) return

        viewModel.city?.forecast?.let { forecasts ->
            LazyColumn {
                items(forecasts) { forecast ->
                    ForecastItem(
                        forecast = forecast,
                        onClick = {},
                        modifier = modifier
                    )
                }
            }
        }
    }
}

@Composable
fun ForecastItem(
    forecast: Forecast,
    onClick: (Forecast) -> Unit,
    modifier: Modifier = Modifier
) {
    val format = DecimalFormat("#.0")
    val tempMin = format.format(forecast.tempMin)
    val tempMax = format.format(forecast.tempMax)

    Row(
        modifier =
        modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable(onClick = { onClick(forecast) }),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = forecast.imgUrl,
            modifier = Modifier.size(40.dp),
            error = painterResource(id = R.drawable.loading),
            contentDescription = "Imagem"
        )
        Spacer(modifier = Modifier.size(12.dp))
        Column {
            Text(
                modifier = Modifier,
                text = forecast.weather,
                fontSize = 20.sp
            )
            Row {
                Text(modifier = Modifier, text = forecast.date, fontSize = 16.sp)
                Spacer(modifier = Modifier.size(12.dp))
                Text(modifier = Modifier, text = "Min: $tempMin℃", fontSize = 14.sp)
                Spacer(modifier = Modifier.size(12.dp))
                Text(modifier = Modifier, text = "Max: $tempMax℃", fontSize = 14.sp)
            }
        }
    }
}