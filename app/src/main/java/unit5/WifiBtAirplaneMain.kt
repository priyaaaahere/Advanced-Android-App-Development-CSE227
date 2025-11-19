package unit5

import android.bluetooth.BluetoothAdapter
import android.content.Intent
import android.content.pm.PackageManager
import android.net.wifi.WifiManager
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import android.widget.ToggleButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.cse227etp.R
import android.Manifest
import androidx.core.app.ActivityCompat

class WifiBtAirplaneMain : AppCompatActivity() {

    private lateinit var wifiToggle: ToggleButton
    private lateinit var btToggle: ToggleButton
    private lateinit var airplaneToggle: ToggleButton

    private val REQUEST_BLUETOOTH_PERMISSION=1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_wifi_bt_airplane_main)

        wifiToggle=findViewById(R.id.wifiToggle)
        btToggle=findViewById(R.id.btToggle)
        airplaneToggle=findViewById(R.id.airplaneToggle)


        wifiToggle.setOnCheckedChangeListener{_, isChecked ->
            val wifiManager=applicationContext.getSystemService(WIFI_SERVICE) as WifiManager
            if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.Q){
                val panelIntent= Intent(Settings.Panel.ACTION_WIFI)
                startActivity(panelIntent)
                Toast.makeText(this@WifiBtAirplaneMain, "Wifi panel opened", Toast.LENGTH_LONG).show()
            }
            else{
                wifiManager.isWifiEnabled=isChecked
                if(isChecked){
                    Toast.makeText(this@WifiBtAirplaneMain, "Wifi Enabled", Toast.LENGTH_LONG).show()
                }
                else{
                    Toast.makeText(this@WifiBtAirplaneMain, "Wifi Disabled", Toast.LENGTH_LONG).show()
                }
            }
        }

        btToggle.setOnCheckedChangeListener { _, isChecked ->
            val bluetoothAdapter: BluetoothAdapter?= BluetoothAdapter.getDefaultAdapter()
            if(bluetoothAdapter==null){
                Toast.makeText(this@WifiBtAirplaneMain, "Bluetooth not available", Toast.LENGTH_LONG).show()
                return@setOnCheckedChangeListener
            }
            if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.S){
                if(ContextCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_CONNECT)
                    != PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(
                        this,
                        arrayOf(Manifest.permission.BLUETOOTH_CONNECT),
                        REQUEST_BLUETOOTH_PERMISSION
                    )
                    Toast.makeText(this@WifiBtAirplaneMain, "Bluetooth permission required", Toast.LENGTH_LONG).show()
                    btToggle.isChecked=bluetoothAdapter.isEnabled
                    return@setOnCheckedChangeListener
                }
            }
            if (isChecked) {
                bluetoothAdapter.enable()
                Toast.makeText(this, "Bluetooth Enabled", Toast.LENGTH_SHORT).show()
            } else {
                bluetoothAdapter.disable()
                Toast.makeText(this, "Bluetooth Disabled", Toast.LENGTH_SHORT).show()
            }
        }
        airplaneToggle.setOnClickListener {
            val intent=Intent(Settings.ACTION_AIRPLANE_MODE_SETTINGS)
            startActivity(intent)
        }

    }
}
