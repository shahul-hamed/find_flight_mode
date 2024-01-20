package com.example.find_aeroplane_mode

import androidx.annotation.NonNull
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodChannel
import android.provider.Settings

class MainActivity: FlutterActivity() {
    private val CHANNEL = "flutter.native/helper"

    override fun configureFlutterEngine(@NonNull flutterEngine: FlutterEngine) {
        super.configureFlutterEngine(flutterEngine)
        MethodChannel(flutterEngine.dartExecutor.binaryMessenger, CHANNEL).setMethodCallHandler{
                call, result ->
            if(call.method == "getAeroPlaneMode") {
                this.getAeroPlaneMode(result)
            }
        }
    }
    private fun getAeroPlaneMode(result: MethodChannel.Result) {
        if(Settings.System.getInt(contentResolver, Settings.Global.AIRPLANE_MODE_ON, 0) == 1){
            result.success("on")
        } else {
            result.success("off")
        }
    }
}