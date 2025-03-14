package com.example.esercitazione;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import android.os.Bundle;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private TextView numView, numView1, numView2;
    private ScrollView scrollView; // Dichiarazione della variabile, inizializzazione rimossa
    private TextView infoTextView;
    private CardView infoCardView;
    private List<String> history = new ArrayList<>(); // Lista per la cronologia

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        numView = findViewById(R.id.numView);
        numView1 = findViewById(R.id.numView1);
        numView2 = findViewById(R.id.numView2);
        infoTextView = findViewById(R.id.infoTextView);
        infoCardView = findViewById(R.id.infoCardView);

        // Inizializza scrollView dopo setContentView
        scrollView = findViewById(R.id.scrollView);
    }

    public void addBTN(View view) {
        updateCounter(view, 1);
    }

    public void menusBTN(View view) {
        updateCounter(view, -1);
    }

    private void updateCounter(View view, int increment) {
        TextView currentTextView = null;
        String hobby = "";

        if (view.getId() == R.id.addBTN || view.getId() == R.id.menusBTN) {
            currentTextView = numView;
            hobby = "Lavoro";
        } else if (view.getId() == R.id.addBTN1 || view.getId() == R.id.menusBTN1) {
            currentTextView = numView1;
            hobby = "Sport";
        } else if (view.getId() == R.id.addBTN2 || view.getId() == R.id.menusBTN2) {
            currentTextView = numView2;
            hobby = "Acqua";
        }

        if (currentTextView != null) {
            int currentValue = Integer.parseInt(currentTextView.getText().toString());
            currentValue += increment;

            // Impedisci valori negativi
            if (currentValue < 0) {
                currentValue = 0;
            }

            currentTextView.setText(String.valueOf(currentValue));

            updateInfoCard(hobby, currentValue);
        }
    }

    private void updateInfoCard(String hobby, int value) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        String currentDateAndTime = sdf.format(new Date());

        String infoText = hobby + ": " + value + "\nData e Ora: " + currentDateAndTime;
        history.add(infoText); // Aggiungi alla cronologia

        // Aggiorna la CardView con tutta la cronologia
        StringBuilder historyBuilder = new StringBuilder();
        for (String item : history) {
            historyBuilder.append(item).append("\n\n");
        }
        infoTextView.setText(historyBuilder.toString());
        // Scorri automaticamente alla fine
        scrollView.post(() -> scrollView.fullScroll(View.FOCUS_DOWN)); // Lambda expression
    }
}