package com.example.reader_pdf;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.OpenableColumns;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<PdfDocument> pdfList;
    private PdfAdapter adapter;
    private TextView emptyMessage;

    private final ActivityResultLauncher<String> filePicker =
            registerForActivityResult(new ActivityResultContracts.GetContent(), uri -> {
                if (uri != null) {
                    addPdfToList(uri);
                } else {
                    Toast.makeText(this, "No PDF selected", Toast.LENGTH_SHORT).show();
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pdfList = new ArrayList<>();
        emptyMessage = findViewById(R.id.emptyMessage);
        setupRecyclerView();

        checkEmptyState();

        findViewById(R.id.fab).setOnClickListener(v -> filePicker.launch("application/pdf"));
    }

    private void setupRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new PdfAdapter(pdfList, this);
        recyclerView.setAdapter(adapter);

        checkEmptyState();
    }

    private void checkEmptyState() {
        if (pdfList.isEmpty()) {
            emptyMessage.setVisibility(View.VISIBLE);
        } else {
            emptyMessage.setVisibility(View.GONE);
        }
    }

    private void addPdfToList(Uri uri) {
        String fileName = getFileName(uri);
        PdfDocument pdfDocument = new PdfDocument(fileName, uri.toString());
        pdfList.add(pdfDocument);
        adapter.notifyDataSetChanged();

        checkEmptyState();
    }

    private String getFileName(Uri uri) {
        String name = "Unknown.pdf";
        ContentResolver contentResolver = getContentResolver();
        Cursor cursor = contentResolver.query(uri, null, null, null, null);
        if (cursor != null) {
            int nameIndex = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME);
            if (nameIndex != -1 && cursor.moveToFirst()) {
                name = cursor.getString(nameIndex);
            }
            cursor.close();
        }
        return name;
    }
}
