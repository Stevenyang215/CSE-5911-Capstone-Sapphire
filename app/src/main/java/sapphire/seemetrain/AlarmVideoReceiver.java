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
        startWakefulService(context, service);
    }
}
