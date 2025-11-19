package unit4

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.cse227etp.R

class EnvironmentSensorMain : AppCompatActivity(), SensorEventListener {

    private lateinit var sensorManager: SensorManager

    private var temperatureSensor: Sensor?=null
    private var lightSensor: Sensor?=null
    private var pressureSensor: Sensor?=null
    private var humiditySensor: Sensor?=null

    private lateinit var temp: TextView
    private lateinit var light: TextView
    private lateinit var pressure: TextView
    private lateinit var humidity: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_environment_sensor)

        sensorManager=getSystemService(SENSOR_SERVICE) as SensorManager

        temp=findViewById(R.id.tvTemperature)
        light=findViewById(R.id.tvLight)
        pressure=findViewById(R.id.tvPressure)
        humidity=findViewById(R.id.tvHumidity)

        //sensors
        temperatureSensor=sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE)
        lightSensor=sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)
        pressureSensor=sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE)
        humiditySensor=sensorManager.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY)
    }
    override fun onResume(){
        super.onResume()
        temperatureSensor?.also{
            sensorManager.registerListener(this,it,SensorManager.SENSOR_DELAY_NORMAL)
        }
        lightSensor?.also{
            sensorManager.registerListener(this,it,SensorManager.SENSOR_DELAY_NORMAL)
        }
        pressureSensor?.also{
            sensorManager.registerListener(this,it,SensorManager.SENSOR_DELAY_NORMAL)
        }
        humiditySensor?.also{
            sensorManager.registerListener(this,it,SensorManager.SENSOR_DELAY_NORMAL)
        }

    }

    override fun onPause(){
        super.onPause()
        sensorManager.unregisterListener(this)
    }

    override fun onSensorChanged(event: SensorEvent?) {
        event?.let{
            when(event.sensor.type){
                Sensor.TYPE_AMBIENT_TEMPERATURE->{
                    temp.text="Temperature: ${event.values[0]} C"
                }
                Sensor.TYPE_LIGHT->{
                    light.text="Light: ${event.values[0]} lx"
                }
                Sensor.TYPE_PRESSURE->{
                    pressure.text="Pressure: ${event.values[0]} hPa"
                }
                Sensor.TYPE_RELATIVE_HUMIDITY->{
                    humidity.text="Humidity: ${event.values[0]} %"
                }
            }
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy:Int){
        //not used
    }
}