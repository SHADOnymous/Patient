package com.example.mudrikahindi.patient;



import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

import android.support.annotation.Nullable;
import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;



public class Receive extends BroadcastReceiver {
    private final String MSG_BODY="GPS COORDINATES";

    String send = null;
    private LocationManager locationManager;
    private String provider;

    private static SmsListener mListener;

    final SmsManager sms = SmsManager.getDefault();


    @Override
    public void onReceive(Context context, Intent intent) {



        // Retrieves a map of extended data from the intent.
        final Bundle bundle = intent.getExtras();



        if (bundle != null) {

            final Object[] pdusObj = (Object[]) bundle.get("pdus");

            for (int i = 0; i < pdusObj.length; i++) {

                SmsMessage currentMessage = SmsMessage.createFromPdu((byte[]) pdusObj[i]);
                String phoneNumber = currentMessage.getDisplayOriginatingAddress();

                String senderNum = phoneNumber;
                String message = currentMessage.getDisplayMessageBody();

                Log.i("SmsReceiver", "senderNum: " + senderNum + "; message: " + message);




               // MainActivity.setSmsDetails(senderNum, message);
               // mListener.messageReceived(message);
                // Show Alert
                   /*int duration = Toast.LENGTH_LONG;
                    Toast toast = Toast.makeText(context,
                            "senderNum: "+ senderNum + ", message: " + message, duration);
                    toast.show();
                            */
                    /*Intent in = new Intent("SmsMessage.intent.MAIN").
                            putExtra("get_msg", senderNum+":"+message);

                    //You can place your check conditions here(on the SMS or the sender)
                    //and then send another broadcast
                    context.sendBroadcast(in);
                         */

                /*Intent ii = new Intent(context, MainActivity.class);
                ii.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                ii.putExtra("message",senderNum.toString());
                context.startActivity(ii);*/

                sendSMS(senderNum,MSG_BODY);
            }
        }

    }
    public void sendSMS(String phoneNumber, String message)
    {
        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(phoneNumber, null, message, null, null);
    }

/*  public static void bindListener(SmsListener listener) {
        mListener = listener;
    }

*/
}



