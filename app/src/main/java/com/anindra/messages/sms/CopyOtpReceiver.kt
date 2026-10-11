package com.anindra.messages.sms

import android.content.BroadcastReceiver
import android.content.ClipData
import android.content.ClipDescription
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.PersistableBundle
import android.widget.Toast
import androidx.core.app.NotificationManagerCompat
import com.anindra.messages.MessagesApplication
import com.anindra.messages.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

/**
 * Handles the notification's "Copy code" action: puts the OTP on the clipboard,
 * removes the notification, and marks the thread read so the already-used code
 * does not come back inside the next notification from the same sender.
 */
class CopyOtpReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val code = intent.getStringExtra(EXTRA_CODE) ?: return
        val address = intent.getStringExtra(EXTRA_ADDRESS)
        val notifId = intent.getIntExtra(EXTRA_NOTIF_ID, Int.MIN_VALUE)

        val cm = context.getSystemService(Context.CLIPBOARD_SERVICE) as? ClipboardManager ?: return
        val clip = ClipData.newPlainText(context.getString(R.string.notif_code_copied), code)
        if (Build.VERSION.SDK_INT >= 33) {
            // Keeps the code out of the clipboard preview / suggestions.
            clip.description.extras = PersistableBundle().apply {
                putBoolean(ClipDescription.EXTRA_IS_SENSITIVE, true)
            }
        }
        cm.setPrimaryClip(clip)
        // Android 13+ shows its own "copied" confirmation.
        if (Build.VERSION.SDK_INT < 33) {
            Toast.makeText(context, R.string.notif_code_copied, Toast.LENGTH_SHORT).show()
        }

        // Copy done -> the notification goes away right away.
        if (notifId != Int.MIN_VALUE) NotificationManagerCompat.from(context).cancel(notifId)

        if (address != null) {
            val pending = goAsync()
            CoroutineScope(SupervisorJob() + Dispatchers.IO).launch {
                try {
                    val app = context.applicationContext as MessagesApplication
                    val repo = app.repository
                    repo.conversationIdForAddress(address)?.let { repo.markReadSuspend(it) }
                } catch (_: Exception) {
                } finally {
                    pending.finish()
                }
            }
        }
    }

    companion object {
        const val ACTION_COPY_OTP = "com.anindra.messages.COPY_OTP"
        const val EXTRA_CODE = "code"
        const val EXTRA_ADDRESS = "address"
        const val EXTRA_NOTIF_ID = "notif_id"
    }
}
