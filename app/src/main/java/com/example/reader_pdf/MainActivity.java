package com.example.reader_pdf;

import android.net.Uri;
import android.os.Bundle;

import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {

    private final ActivityResultLauncher<String> filePicker =
            registerForActivityResult(new ActivityResultContracts.GetContent(), uri -> {
                if (uri != null) {
                    openPdfViewer(uri);
                } else {
                    Toast.makeText(this, "No PDF selected", Toast.LENGTH_SHORT).show();
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.fab).setOnClickListener(v -> filePicker.launch("application/pdf"));
    }

    private void openPdfViewer(Uri uri) {
        Intent intent = new Intent(this, PdfDetailActivity.class);
        intent.putExtra("pdfUri", uri.toString());
        startActivity(intent);
    }
}
