package com.example.unitconverter

import android.graphics.drawable.Icon
import android.os.Bundle
import android.text.style.BackgroundColorSpan
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.unitconverter.ui.theme.UnitConverterTheme
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment
import java.time.format.TextStyle
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnitConverterTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    unitConverter()
                }
            }
        }
    }
}

@Composable
fun unitConverter(){

    var inputValue by remember {mutableStateOf("") }
    var outputValue by remember {mutableStateOf("") }
    var inputUnit by remember {mutableStateOf("Meters") }
    var outputUnit by remember {mutableStateOf("Meters") }
    var iExpand by remember {mutableStateOf(false) }
    var oExpand by remember {mutableStateOf(false) }
    var conversionFactor = remember {mutableStateOf(1.00) }
    var oConversionFactor = remember {mutableStateOf(1.00) }

 fun convertUnit(){
     // ?: elvis operator .. work like if else logic
     val inputValueDouble = inputValue.toDoubleOrNull()?:0.0
     val result = (inputValueDouble*conversionFactor.value*100.0/oConversionFactor.value).roundToInt()/100.0
     outputValue = result.toString()
 }


    Column (modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center ,
        horizontalAlignment = Alignment.CenterHorizontally)

      {
       //here the ui element stacked bellow each other
       Text(text = "Unit Converter"  , style = MaterialTheme.typography.headlineLarge)


        Spacer(modifier = Modifier.height(20.dp))
        OutlinedTextField(value = inputValue, onValueChange ={
            inputUnit = it
        }, label = { Text(text = "Enter Value")} )
          Spacer(modifier = Modifier.height(10.dp))
        Row {
            // here the ui element align side by side
           //val context = LocalContext.current

//            Button(onClick = {
//                //Toast.makeText(context,"Thanks For Clicking",Toast.LENGTH_LONG).show()
//            }) {
//                Text(text = "Click me!")
//            }
              // input Box
              Box{
                  //input Button
                  Button(onClick = { iExpand = true }) {
                      Text(text = inputUnit)
                      Icon(Icons.Default.ArrowDropDown, contentDescription = "DropDownIcon")
                  }
                  DropdownMenu(expanded = iExpand, onDismissRequest = { iExpand = false }) {
                      DropdownMenuItem(
                          text = { Text(text = "Centimeters") },
                          onClick = {
                              iExpand = false
                              inputUnit= "Centimeters"
                              conversionFactor.value = 0.01
                              convertUnit()
                          })
                      DropdownMenuItem(
                          text = { Text(text = "Meters") },
                          onClick = {
                              iExpand = false
                              inputUnit= "Meters"
                              conversionFactor.value = 1.0
                              convertUnit()
                          })
                      DropdownMenuItem(
                          text = { Text(text = "Milimeters") },
                          onClick = {
                              iExpand = false
                              inputUnit= "Milimeters"
                              conversionFactor.value = 0.001
                              convertUnit()
                          })
                      DropdownMenuItem(
                          text = { Text(text = "Feet") },
                          onClick = {
                              iExpand = false
                              inputUnit= "Feet"
                              conversionFactor.value = 0.3048
                              convertUnit()
                          })

                  }
              }
            Spacer(modifier = Modifier.width(45.dp))
           //output Box
            Box{
                //output Button
                  Button(onClick = { oExpand = true }) {
                      Text(text = outputUnit)
                      Icon(Icons.Default.ArrowDropDown, contentDescription = "DropDownIcon")
                  }
                      DropdownMenu(expanded = oExpand, onDismissRequest = { oExpand = false }) {
                          DropdownMenuItem(
                              text = { Text(text = "Centimeters") },
                              onClick = {
                                  oExpand = false
                                  outputUnit= "Centimeters"
                                  oConversionFactor.value = 0.01
                                  convertUnit()
                              })
                          DropdownMenuItem(
                              text = { Text(text = "Meters") },
                              onClick = {
                                  oExpand = false
                                  outputUnit= "Meters"
                                  oConversionFactor.value = 1.0
                                  convertUnit()
                              })
                          DropdownMenuItem(
                              text = { Text(text = "Milimeters") },
                              onClick = {
                                  oExpand = false
                                  outputUnit= "Milimeters"
                                  oConversionFactor.value = 0.001
                                  convertUnit()
                              })
                          DropdownMenuItem(
                              text = { Text(text = "Feet") },
                              onClick = {
                                  oExpand = false
                                  outputUnit= "Feet"
                                  oConversionFactor.value = 0.3048
                                  convertUnit()
                              })

                      }


              }


        }
          Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Result" , style = MaterialTheme.typography.headlineMedium)
    }
}
@Preview(showBackground = true)
@Composable
fun unitConverterPreview(){
    unitConverter()
}