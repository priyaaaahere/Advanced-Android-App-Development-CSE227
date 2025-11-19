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
import kotlin.math.abs

class PositionSensorMain : AppCompatActivity(), SensorEventListener {

    private lateinit var sensorManager: SensorManager
    private var rotationVector: Sensor? = null

    private lateinit var tvPosition: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_position_sensor_main)

        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        tvPosition=findViewById(R.id.tvPosition)
        rotationVector=sensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR)
    }

    override fun onResume(){
        super.onResume()
        rotationVector?.also{
            sensorManager.registerListener(this,it,SensorManager.SENSOR_DELAY_NORMAL)
        }
    }

    override fun onPause(){
        super.onPause()
        sensorManager.unregisterListener(this)
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if(event?.sensor?.type==Sensor.TYPE_ROTATION_VECTOR){
            val rotationMatrix=FloatArray(9)
            val orientationAngles=FloatArray(3)

            SensorManager.getRotationMatrixFromVector(rotationMatrix, event.values)
            SensorManager.getOrientation(rotationMatrix,orientationAngles)

            val azimuth=orientationAngles[0]
            val pitch=orientationAngles[1]
            val roll=orientationAngles[2]

            val predicted=classifyPosition(azimuth, pitch, roll)
            tvPosition.text="Position: $predicted \nPitch: $pitch\n Roll: $roll\n Azimuth: $azimuth"
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        }
    private fun classifyPosition(azimuth: Float, pitch: Float, roll: Float): String{
        return when{
            abs(pitch)<0.3 && abs(roll)<0.3-> "Flat"
            pitch<-1 ->"Upright"
            pitch>1-> "Upside Down"
            roll>1 ->"Tilted Right"
            roll<-1->"Tilted Left"
            else->"Unknown"
        }
    }
}