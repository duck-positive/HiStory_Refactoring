package com.umc.history

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.umc.history.databinding.ActivitySettingLogoutBinding

class SettingLogoutActivity : AppCompatActivity() {
    private var _binding: ActivitySettingLogoutBinding?=null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivitySettingLogoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }


}