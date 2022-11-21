package mateusz.oleksik.smb_projekt_2

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.job.JobParameters
import android.app.job.JobService
import android.content.ComponentName
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import kotlin.concurrent.thread

class NotificationsJobService : JobService() {

    private var cancel = false

    override fun onStartJob(parameters: JobParameters?): Boolean {
        var extras = parameters!!.extras
        doWork(extras)
        return true
    }

    override fun onStopJob(parameters: JobParameters?): Boolean {
        cancel = true
        return true
    }

    private fun doWork(bundle: PersistableBundle){
        thread(start = true) {
            createNotificationChannel()
            sendNotification(bundle)
        }
    }

    private fun sendNotification(bundle: PersistableBundle){
        val componentName = ComponentName(
            Constants.ShoppingListProjectPackage,
            Constants.ShoppingListProjectEditActivityClass)

        val intent = Intent()
        intent.putExtra(Constants.ItemIDExtraID, bundle.getInt(Constants.ItemIDExtraID))
        intent.putExtra(Constants.ItemNameExtraID, bundle.getString(Constants.ItemNameExtraID))
        intent.putExtra(Constants.ItemAmountExtraID, bundle.getInt(Constants.ItemAmountExtraID))
        intent.putExtra(Constants.ItemPriceExtraID, bundle.getDouble(Constants.ItemPriceExtraID))
        intent.putExtra(Constants.ItemIsBoughtExtraID, bundle.getBoolean(Constants.ItemIsBoughtExtraID))

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