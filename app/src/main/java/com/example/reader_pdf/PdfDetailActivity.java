package com.example.reader_pdf;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class PdfDetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_detail);

        TextView pdfTitle = findViewById(R.id.pdfTitle);
        String pdfName = getIntent().getStringExtra("pdf_name");

        pdfTitle.setText(pdfName);
    }
}
