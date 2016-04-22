package sapphire.seemetrain;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.content.WakefulBroadcastReceiver;

/**
 * File Created by Joseph
 */
public class AlarmVideoReceiver extends WakefulBroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent){
        Intent service = new Intent(context, AlarmVideoService.class);
        service.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        service.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        context.startActivity(intent);
        //startWakefulService(context, service);
    }
}
