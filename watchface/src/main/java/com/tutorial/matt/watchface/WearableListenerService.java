package com.tutorial.matt.watchface;

import android.content.Intent;

import com.google.android.gms.wearable.DataEvent;
import com.google.android.gms.wearable.DataEventBuffer;
import com.google.android.gms.wearable.DataMap;
import com.google.android.gms.wearable.DataMapItem;

/**
 * Created by matt on 1/4/16.
 */
public class WearableListenerService extends com.google.android.gms.wearable.WearableListenerService {
    @Override
    public void onDataChanged(DataEventBuffer dataEvents) {
        for (DataEvent dataEvent : dataEvents) {
            if (dataEvent.getType() == DataEvent.TYPE_CHANGED) {
                DataMap dataMap = DataMapItem.fromDataItem(dataEvent.getDataItem()).getDataMap();
                String path = dataEvent.getDataItem().getUri().getPath();
                if (path.equals("/today")) {
                    String minTemp = dataMap.getString("min-temp");
                    String maxTemp = dataMap.getString("max-temp");

                    Intent intent = new Intent();
                    intent.putExtra("min-temp", minTemp);
                    intent.putExtra("max-temp", maxTemp);
                    intent.setAction("com.tutorial.matt.TODAY_DATA_RECEIVED");
                    sendBroadcast(intent);
                }
            }
        }
    }
}
