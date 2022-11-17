package mateusz.oleksik.smb_projekt_2

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.*
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import androidx.core.app.NotificationManagerCompat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        registerReceiver()
        createNotificationChannel()
    }

    private fun registerReceiver(){
        val receiver = ShoppingItemCreationReceiver(applicationContext)
        val intent = "mateusz.oleksik.SHOPPING_ITEM_INTENT"
        val permission = "mateusz.oleksik.SHOPPING_ITEMS_PERMISSIONS"

        val filter = IntentFilter(intent)
        registerReceiver(receiver, filter, permission, null)
    }

    private fun createNotificationChannel(){
        val notificationChannel = NotificationChannel(
            Constants.NotificationChannelID,
            Constants.NotificationChannelName,
            NotificationManager.IMPORTANCE_DEFAULT
        )
        notificationChannel.description = Constants.NotificationChannelDescription
        val notificationManager = NotificationManagerCompat.from(this)
        notificationManager.createNotificationChannel(notificationChannel)
    }
}