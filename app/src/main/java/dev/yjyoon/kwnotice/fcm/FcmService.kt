package dev.yjyoon.kwnotice.fcm

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import dev.yjyoon.kwnotice.R
import dev.yjyoon.kwnotice.presentation.ui.webview.WebViewActivity

class FcmService : FirebaseMessagingService() {

    override fun onMessageReceived(message: RemoteMessage) {
        if (message.notification != null) {
            sendNotification(message)
        }
    }

    private fun sendNotification(message: RemoteMessage) {
        val notification: RemoteMessage.Notification = message.notification!!
        val data = message.data

        val intent = Intent(this, WebViewActivity::class.java).apply {
            this.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            this.putExtra("url", data["url"])
        }

        val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)

        val notificationBuilder = NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle(notification.title)
            .setContentText(notification.body)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)

        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                NOTIFICATION_CHANNEL_ID,
                NOTIFICATION_CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT
            )
            notificationManager.createNotificationChannel(channel)
        }

        notificationManager.notify(0, notificationBuilder.build())
    }

    companion object {

        const val NOTIFICATION_CHANNEL_ID = "kw_notice_channel_id"
        const val NOTIFICATION_CHANNEL_NAME = "kw_notice_channel_name"
    }
}
