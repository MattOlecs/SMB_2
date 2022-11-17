package mateusz.oleksik.smb_projekt_2

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class ShoppingItemCreationReceiver(val context: Context) : BroadcastReceiver() {

    override fun onReceive(p0: Context?, p1: Intent?) {
        Log.i(this::class.java.name, "Received broadcast")

        val serviceIntent = Intent(context, NotificationService::class.java)
        serviceIntent.putExtras(p1!!)
        context.startService(serviceIntent)
    }
}