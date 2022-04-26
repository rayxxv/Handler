package com.example.handler

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.handler.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        onClick()
    }

    fun onClick() {
        val handler = object:  Handler(Looper.getMainLooper()) {
            override fun handleMessage(msg: Message) {
                val message = msg.obj as String
                tv_text.text = message
            }
        }
        Thread(Runnable {
            binding.btnProses.setOnClickListener{
                val tinggi = binding.etHeight.text.toString().toInt()
                var result = ""
                if (tinggi<155){
                    result = "Pendek"
                }else if (tinggi>154 && tinggi<175){
                    result = "Normal"
                }else if (tinggi>174){
                    result = "Tinggi"
                }

                val msg = Message.obtain()
                msg.obj = result
                msg.setTarget(handler)
                msg.sendToTarget()
            }
        }).start()
    }


}