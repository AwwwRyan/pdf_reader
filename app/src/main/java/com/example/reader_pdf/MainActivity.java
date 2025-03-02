package com.example.reader_pdf;

import android.net.Uri;
import android.os.Bundle;

import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<String> pdfList = new ArrayList<>();
    private PdfAdapter pdfAdapter;

    // File picker launcher
    private final ActivityResultLauncher<String> filePickerLauncher =
            registerForActivityResult(new ActivityResultContracts.GetContent(), uri -> {
                if (uri != null) {
                    String fileName = getFileName(uri);
                    pdfList.add(fileName);
                    pdfAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(this, "No file selected", Toast.LENGTH_SHORT).show();
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        FloatingActionButton fab = findViewById(R.id.fab);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        pdfAdapter = new PdfAdapter(pdfList, this);
        recyclerView.setAdapter(pdfAdapter);

        fab.setOnClickListener(v -> filePickerLauncher.launch("application/pdf"));
    }

    // Extract file name from URI
    private String getFileName(Uri uri) {
        String path = uri.getLastPathSegment();
        if (path != null) {
            return path.substring(path.lastIndexOf("/") + 1);
        }
        return "Unknown File";
    }
}
