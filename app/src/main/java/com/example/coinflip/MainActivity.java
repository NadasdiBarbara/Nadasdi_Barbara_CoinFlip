package com.example.coinflip;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private ImageView kep;
    private Button fejGomb, irasGomb;
    private TextView txtV_dobas,txtV_gyoz,txtV_vereseg, textViewCustom;
    private int dobasok, gyozelem, vereseg;
    private Random rnd;
    private boolean fejViras; //hamis->iras; igaz ->fej
    private Toast customToast;
    private AlertDialog.Builder alertBuilder;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        ujJatek();
        fejGomb.setOnClickListener(view -> {
            fejViras = rnd.nextBoolean();
            if (fejViras){
                dobasok++;
                gyozelem++;
                kep.setImageResource(R.drawable.heads);
                textViewCustom.setText("Dobás: Fej");
                customToast.show();
            }
            else{
                dobasok++;
                vereseg++;
                kep.setImageResource(R.drawable.tails);
                textViewCustom.setText("Dobás: Írás");
                customToast.show();
            }
            if (dobasok == 5){
                if (gyozelem>vereseg){
                    alertBuilder.setTitle("GYŐZELEM");
                    alertBuilder.create();
                    alertBuilder.show();
                }
                else{
                    alertBuilder.setTitle("VERESÉG").create().show();
                }
            }
            txtV_dobas.setText("Dobások: "+dobasok);
            txtV_gyoz.setText("Győzelem: "+gyozelem);
            txtV_vereseg.setText("Vereség: "+vereseg);
        });
        irasGomb.setOnClickListener(view -> {
            fejViras = rnd.nextBoolean();
            if (!fejViras){
                dobasok++;
                gyozelem++;
                kep.setImageResource(R.drawable.tails);
                textViewCustom.setText("Dobás: Írás");
                customToast.show();
            }
            else{
                dobasok++;
                vereseg++;
                kep.setImageResource(R.drawable.heads);
                textViewCustom.setText("Dobás: Fej");
                customToast.show();
            }
            if (dobasok == 5){
                if (gyozelem > vereseg){
                    alertBuilder.setTitle("GYŐZELEM");
                    alertBuilder.create();
                    alertBuilder.show();
                }
                else{
                    alertBuilder.setTitle("VERESÉG").create().show();
                }
            }
            txtV_dobas.setText("Dobások: "+dobasok);
            txtV_gyoz.setText("Győzelem: "+gyozelem);
            txtV_vereseg.setText("Vereség: "+vereseg);
        });

    }
    private void  init(){
        kep = findViewById(R.id.fej);
        fejGomb = findViewById(R.id.btn_fej);
        irasGomb = findViewById(R.id.btn_iras);
        txtV_dobas = findViewById(R.id.txtV_dobasok);
        txtV_gyoz = findViewById(R.id.txtV_gyozelem);
        txtV_vereseg = findViewById(R.id.txtV_vereseg);
        rnd = new Random();
        customToast = new Toast(getApplicationContext());
        CreatCustomToast();
        alertBuilder = new AlertDialog.Builder(this);
        CreatAlertDialog();
    }
    private void ujJatek(){
        dobasok = 0;
        gyozelem = 0;
        vereseg = 0;
        txtV_dobas.setText("Dobások: "+dobasok);
        txtV_gyoz.setText("Győzelem: "+gyozelem);
        txtV_vereseg.setText("Vereség: "+vereseg);
    }

    private void CreatAlertDialog() {
        alertBuilder.setMessage("Szeretnél e új játékot?");
        alertBuilder.setNegativeButton("Nem", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {finish();}
        });
        alertBuilder.setPositiveButton("Igen", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                ujJatek();
                closeContextMenu();
            }
        });
        //kötelező választani
        alertBuilder.setCancelable(false);
    }

    private void CreatCustomToast() {
        customToast.setDuration(Toast.LENGTH_SHORT);
        View view = getLayoutInflater().inflate(R.layout.custom_toast, findViewById(R.id.custom_toast));
        textViewCustom = view.findViewById(R.id.textViewCustom);
        customToast.setView(view);
        customToast.setGravity(Gravity.CENTER, 0, 0);
    }
}