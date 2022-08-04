package com.example.appsitiosturisticos;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;

import com.example.appsitiosturisticos.databinding.ActivityDetalleBinding;

public class ActivityDetalle extends AppCompatActivity {

    public static final String SITIO_TURISTICO_KEY= "sitioTuristico";
    public static final String BITMAP_KEY="bitmap";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityDetalleBinding binding = ActivityDetalleBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Bundle extras=getIntent().getExtras();
        SitioTuristico sitio=extras.getParcelable(SITIO_TURISTICO_KEY);
        binding.setSitioTuristico(sitio);

        Bitmap bitmap=extras.getParcelable(BITMAP_KEY);
        if (bitmap != null){
            binding.imgSitio.setImageBitmap(bitmap);
        }
    }
}