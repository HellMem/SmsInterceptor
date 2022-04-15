package coronadosolutions.smsinterceptor.services;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

public class TransactionService extends IntentService {

    public TransactionService() {
        super("TransactionService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        String action = intent.getAction();

        if (action == null) return;

        Bundle params = intent.getExtras();
        String amount = params.getString("amount");


        addTransaction(amount, action);
    }

    private void addTransaction(String amount, String type) {

        if (type.toUpperCase().contains("RETIRO")) {
            Toast.makeText(getApplicationContext(), "DEJA DE GASTAR PRRO", Toast.LENGTH_LONG).show();
        } else if (type.toUpperCase().contains("DEPOSITO")) {
            Toast.makeText(getApplicationContext(), "TE PAGARON PRRO", Toast.LENGTH_LONG).show();
        }

    }


}
