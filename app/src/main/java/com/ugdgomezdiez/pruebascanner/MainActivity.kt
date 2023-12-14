package com.ugdgomezdiez.pruebascanner

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import com.ugdgomezdiez.pruebascanner.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var requestCamera: ActivityResultLauncher<String>? = null
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        requestCamera = registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                startBarcodeScanActivity()
            } else {
                Toast.makeText(this, "Permission Not Granted", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnBc.setOnClickListener {
            requestCamera?.launch(android.Manifest.permission.CAMERA)
        }
    }

    private fun startBarcodeScanActivity() {
        val intent = Intent(this, BarcodeScan::class.java)
        startActivity(intent)
    }
}