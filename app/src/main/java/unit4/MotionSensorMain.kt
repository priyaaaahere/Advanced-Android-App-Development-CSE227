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

class MotionSensorMain : AppCompatActivity(), SensorEventListener {

    private lateinit var sensorManager: SensorManager

    private lateinit var tvAccelerometer: TextView
    private lateinit var tvGyroscope: TextView

    private var accelerometerSensor: Sensor? = null
    private var gyroscopeSensor:Sensor? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_motion_sensor_main)

        tvAccelerometer=findViewById(R.id.tvAccelerometer)
        tvGyroscope=findViewById(R.id.tvGyroscope)

        sensorManager=getSystemService(SENSOR_SERVICE) as SensorManager

        accelerometerSensor=sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        gyroscopeSensor=sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE)

    }
    override fun onResume(){
        super.onResume()
        accelerometerSensor?.also{
            sensorManager.registerListener(this,it,SensorManager.SENSOR_DELAY_NORMAL)
        }
        gyroscopeSensor?.also{
            sensorManager.registerListener(this,it,SensorManager.SENSOR_DELAY_NORMAL)
        }
    }
    override fun onPause(){
        super.onPause()
        sensorManager.unregisterListener(this)
    }
    override fun onSensorChanged(event: SensorEvent?){
        event?.let{
            when(event.sensor.type){
                Sensor.TYPE_ACCELEROMETER->{
                    val x=event.values[0]
                    val y=event.values[1]
                    val z=event.values[2]
                    tvAccelerometer.text="Accelerometer reading: x=$x, y=$y, z=$z"
                }
                Sensor.TYPE_GYROSCOPE->{
                    val x=event.values[0]
                    val y=event.values[1]
                    val z=event.values[2]
                    tvGyroscope.text="Gyroscope reading: x=$x, y=$y, z=$z"
                }
            }
        }
    }
    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int){
        //no usage
    }
}