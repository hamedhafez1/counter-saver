package ir.roela.countersaver.ui.pedometer

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import ir.roela.countersaver.R

class PedometerActivityOld : AppCompatActivity(), SensorEventListener {

    private var sensorManager: SensorManager? = null
    private var txtStepTaken: TextView? = null
    private var running = false
    private var totalSteps = 0f
    private var previousTotalSteps = 0f
    private var stepSensor: Sensor? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pedometer)
        resetSteps()
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        txtStepTaken = findViewById(R.id.txtStepTaken)


    }

    override fun onResume() {
        super.onResume()
        try {
            stepSensor = sensorManager?.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)
            if (stepSensor == null) {
                Toast.makeText(this, "No sensor detected on this device", Toast.LENGTH_SHORT).show()
            } else {
                running = true
                sensorManager!!.registerListener(
                    this,
                    stepSensor,
                    SensorManager.SENSOR_DELAY_UI,
                    0
                )
            }
        } catch (e: Exception) {
            Log.e("pedometer", e.message.toString())
        }
    }


    override fun onPause() {
        super.onPause()
        running = false
        sensorManager?.unregisterListener(this)
    }

    override fun onSensorChanged(event: SensorEvent?) {
        Toast.makeText(this, "onSensorChanged", Toast.LENGTH_SHORT).show()
        if (running) {
            Toast.makeText(this, "running", Toast.LENGTH_SHORT).show()
            totalSteps = event!!.values[0]
            val currentSteps = totalSteps.toInt() - previousTotalSteps.toInt()
            txtStepTaken?.text = ("5484654")
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

    }

    private fun resetSteps() {
        txtStepTaken?.setOnClickListener {
            Toast.makeText(this, "Long tap to reset steps", Toast.LENGTH_SHORT).show()
        }

        txtStepTaken?.setOnLongClickListener {
            previousTotalSteps = totalSteps
            txtStepTaken?.text = 0.toString()
//            saveData()
            true
        }
    }
}