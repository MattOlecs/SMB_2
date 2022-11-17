package mateusz.oleksik.smb_projekt_2

import android.app.PendingIntent
import android.app.Service
import android.content.ComponentName
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class NotificationService : Service() {

    private lateinit var customBinder: CustomBinder

    init {
        customBinder = CustomBinder()
    }

    override fun onBind(p0: Intent?): IBinder? {
        return customBinder
    }

    inner class CustomBinder : Binder(){
        fun getService(): NotificationService = this@NotificationService
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if (intent != null) {
            sendNotification(intent)
        }
        return START_NOT_STICKY
    }

    private fun sendNotification(intent: Intent){
        val componentName = ComponentName(
            Constants.ShoppingListProjectPackage,
            Constants.ShoppingListProjectEditActivityClass)

        intent.component = componentName
        val pendingIntent = PendingIntent.getActivity(
            applicationContext,
            1,
            intent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )
        val notification = NotificationCompat.Builder(applicationContext, Constants.NotificationChannelID)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle(Constants.ItemCreationNotificationTitle)
            .setContentText(Constants.ItemCreationNotificationText)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .build()
        val notificationManager = NotificationManagerCompat.from(applicationContext)
        notificationManager.notify(1, notification)
    }
}