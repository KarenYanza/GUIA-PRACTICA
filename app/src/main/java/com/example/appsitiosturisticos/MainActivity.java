package com.example.appsitiosturisticos;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;

import com.example.appsitiosturisticos.databinding.ActivityDetalleBinding;
import com.example.appsitiosturisticos.databinding.ActivityMainBinding;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    Bitmap bitmap;

    ActivityResultLauncher<Intent> activityResultLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.btnGuardar.setOnClickListener(v -> {
            String nombre = binding.txtNombre.getText().toString();
            String ubicacion = binding.txtUbicacion.getText().toString();
            String informacion = binding.txtInformacion.getText().toString();
            float valoracion = binding.rtbValoracion.getRating();
            abrirActivityDetalle(nombre,ubicacion,informacion,valoracion);
        });
        activityLaucher();
        binding.imgSitio.setOnClickListener(v -> {
            abrirCamara();

        });
    }
    public void abrirCamara(){
        Intent camaraIntent= new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //startActivity(camaraIntent);
        //startActivityForResult(camaraIntent,1000);
        activityResultLauncher.launch((camaraIntent));


    }

    public void activityLaucher(){
        activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode()==RESULT_OK){
                            if(result.getData() != null){
                                bitmap =result.getData().getExtras().getParcelable("data");
                                binding.imgSitio.setImageBitmap(bitmap);
                            }
                        }
                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode== Activity.RESULT_OK && requestCode==1000){
            if (data != null){
                bitmap =data.getExtras().getParcelable("data");
                binding.imgSitio.setImageBitmap(bitmap);

            }
        }
    }

    private void abrirActivityDetalle(String nom, String ubi, String inf, Float val){
        Intent intent =new Intent(this, ActivityDetalle.class);

        SitioTuristico sitio = new SitioTuristico(nom,ubi,inf,val);
        intent.putExtra(ActivityDetalle.SITIO_TURISTICO_KEY,sitio);
        intent.putExtra(ActivityDetalle.BITMAP_KEY,bitmap);


        startActivity(intent);
    }
}