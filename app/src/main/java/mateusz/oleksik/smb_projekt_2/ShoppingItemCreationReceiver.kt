package mateusz.oleksik.smb_projekt_2

import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.BroadcastReceiver
import android.content.ComponentName
import android.content.Context
import android.content.Context.JOB_SCHEDULER_SERVICE
import android.content.Intent
import android.os.PersistableBundle
import android.widget.Toast


class ShoppingItemCreationReceiver : BroadcastReceiver() {

    private var _jobId = 1

    override fun onReceive(p0: Context?, p1: Intent?) {
        Toast.makeText(p0, "Received broadcast", Toast.LENGTH_LONG).show()

        var bundle = PersistableBundle()
        bundle.putInt(Constants.ItemIDExtraID, p1!!.getIntExtra(Constants.ItemIDExtraID, 0))
        bundle.putString(Constants.ItemNameExtraID, p1.getStringExtra((Constants.ItemNameExtraID)))
        bundle.putInt(Constants.ItemAmountExtraID, p1.getIntExtra(Constants.ItemAmountExtraID, 0))
        bundle.putDouble(Constants.ItemPriceExtraID, p1.getDoubleExtra(Constants.ItemPriceExtraID, 0.0))
        bundle.putBoolean(Constants.ItemIsBoughtExtraID, p1.getBooleanExtra(Constants.ItemIsBoughtExtraID, false))
        scheduleNotificationJob(p0!!, bundle)
    }

    private fun scheduleNotificationJob(context: Context, bundle: PersistableBundle){
        val cn = ComponentName(context, NotificationsJobService::class.java)
        val info = JobInfo.Builder(_jobId++, cn)
            .setRequiresBatteryNotLow(true)
            .setPersisted(true)
            .setPeriodic(15*60*1000)
            .setExtras(bundle)
            .build()

        val scheduler = context.getSystemService(JOB_SCHEDULER_SERVICE) as JobScheduler
        if(scheduler.schedule(info) == JobScheduler.RESULT_SUCCESS){
            Toast.makeText(context, "Job scheduled successfully!", Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(context, "Job scheduling failed.", Toast.LENGTH_SHORT).show()
        }
    }
}