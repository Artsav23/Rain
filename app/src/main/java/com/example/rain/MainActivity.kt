package com.example.rain

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar
import com.example.rain.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.startStop.setOnClickListener {
           startStop()
        }
        figureDecorated()
        changeSizeFigure()
        changeFigure()
        direction()
    }

    private fun direction() {
        binding.seekBarDirection.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                binding.rainObject.direction = progress - 50
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })
    }

    private fun figureDecorated() {
        binding.checkBoxColor.setOnCheckedChangeListener { _, isChecked ->
            binding.rainObject.mixColor = isChecked
        }
    }

    private fun changeSizeFigure() {
        binding.seekBarSize.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                binding.rainObject.sizeFigure = progress.toString().toFloat()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}

        })
    }

    private fun changeFigure() {
        binding.checkBoxSquare.setOnClickListener {
            isChecked(circle = false, line = false, square = true)
            binding.rainObject.kindFigure = KindFigure.Square
        }
        binding.checkBoxCircle.setOnClickListener {
           isChecked(circle = true, square = false, line = false)
            binding.rainObject.kindFigure = KindFigure.Circle
        }
        binding.checkBoxLine.setOnClickListener {
           isChecked (line = true, square = false, circle = false)
            binding.rainObject.kindFigure = KindFigure.Line
        }
    }

    private fun isChecked(circle: Boolean, line: Boolean, square: Boolean) {
        binding.checkBoxCircle.isChecked = circle
        binding.checkBoxLine.isChecked = line
        binding.checkBoxSquare.isChecked = square
    }

    private fun startStop() {
        if (binding.startStop.text == "Stop") {
            binding.rainObject.stop()
            binding.startStop.text = "Start"
        }
        else {
            binding.rainObject.start()
            binding.startStop.text = "Stop"
        }
    }
}