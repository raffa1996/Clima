package com.example.android.clima.app.sync;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class ClimateSyncService extends Service {
    private static final Object sSyncAdapterLock = new Object();
    private static ClimateSyncAdapter sClimateSyncAdapter = null;

    @Override
    public void onCreate() {
        Log.d("ClimateSyncService", "onCreate - ClimateSyncService");
        synchronized (sSyncAdapterLock) {
            if (sClimateSyncAdapter == null) {
                sClimateSyncAdapter = new ClimateSyncAdapter(getApplicationContext(), true);
            }
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return sClimateSyncAdapter.getSyncAdapterBinder();
    }
}