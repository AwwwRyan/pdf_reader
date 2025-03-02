package com.example.reader_pdf;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PdfAdapter extends RecyclerView.Adapter<PdfAdapter.ViewHolder> {
    private final List<PdfDocument> pdfList;
    private final Context context;

    public PdfAdapter(List<PdfDocument> pdfList, Context context) {
        this.pdfList = pdfList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pdf, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PdfDocument pdf = pdfList.get(position);
        holder.pdfName.setText(pdf.getName());

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, PdfDetailActivity.class);
            intent.putExtra("pdfUri", pdf.getUri());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return pdfList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView pdfName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            pdfName = itemView.findViewById(R.id.pdfName);
        }

    }

}
