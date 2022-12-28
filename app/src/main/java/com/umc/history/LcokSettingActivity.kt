//package com.umc.history
//
//import android.content.Intent
//import android.os.Build
//import android.os.Bundle
//import android.widget.CompoundButton
//import android.widget.Switch
//import androidx.appcompat.app.AppCompatActivity
//import com.umc.history.databinding.ActivityLockSettingBinding
//import com.umc.history.databinding.ActivitySettingLogoutBinding
//
//class LcokSettingActivity : AppCompatActivity() {
//    private var mBinding: ActivityLockSettingBinding?=null
//    private val binding get() = mBinding!!
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//        mBinding = ActivityLockSettingBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        val switch= findViewById<Switch>(R.id.lock_setting_sb)
//        val intent = Intent(this, LockScreenService::class.java)
//
//
//        //  스위치를 클릭했을때
//        switch.setOnCheckedChangeListener{CompoundButton, onSwitch ->
//
//            //  스위치가 켜지면
//            if (onSwitch){
//                this.startForegroundService(Intent(this,LockScreenService::class.java))
//            }
//
//            //  스위치가 꺼지면
//            else{
//                this.stopService(Intent(this,LockScreenService::class.java))
//            }
//        }
//
//
//
//
//
//    }
//
//
//}