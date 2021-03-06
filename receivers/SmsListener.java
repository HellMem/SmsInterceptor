package coronadosolutions.smsinterceptor.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

import coronadosolutions.smsinterceptor.services.TransactionService;

public class SmsListener extends BroadcastReceiver {

    private SharedPreferences preferences;

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO Auto-generated method stub


        if (intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED")) {
            Bundle bundle = intent.getExtras();           //---get the SMS message passed in---
            SmsMessage[] msgs = null;
            String msg_from;
            if (bundle != null) {
                //---retrieve the SMS message received---
                try {
                    Object[] pdus = (Object[]) bundle.get("pdus");
                    msgs = new SmsMessage[pdus.length];
                    for (int i = 0; i < msgs.length; i++) {
                        msgs[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                        msg_from = msgs[i].getOriginatingAddress();


                        String msgBody = msgs[i].getMessageBody();


                        if (msg_from.equals("Citibanamex")) {
                            Intent transactionServiceIntent = new Intent(context, TransactionService.class);
                            transactionServiceIntent.setAction(msgBody.toUpperCase().contains("RETIRO") ? "RETIRO" : msgBody.toUpperCase().contains("DEPOSITO") ? "DEPOSITO" : "");

                            context.startService(intent);
                        }
                    }
                } catch (Exception e) {
                }
            }
        }
    }


}