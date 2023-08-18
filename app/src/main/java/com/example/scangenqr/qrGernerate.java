package com.example.scangenqr;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.scangenqr.databinding.ActivityQrGernerateBinding;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class qrGernerate extends AppCompatActivity {
    ActivityQrGernerateBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityQrGernerateBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.genbut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data = binding.genedit.getText().toString();
                if(data.isEmpty()){
                    Toast.makeText(qrGernerate.this, "Please enter text to generate Qrcode", Toast.LENGTH_SHORT).show();
                }
                else{
                    generateQR();
                }
            }
        });
    }
    private void generateQR(){
        String data = binding.genedit.getText().toString();
        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        try {
            BitMatrix matrix = multiFormatWriter.encode(data, BarcodeFormat.QR_CODE,250,250);
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            Bitmap bitmap = barcodeEncoder.createBitmap(matrix);
            binding.genimg.setImageBitmap(bitmap);
        }
        catch (WriterException e) {
            throw new RuntimeException(e);
        }
    }
}