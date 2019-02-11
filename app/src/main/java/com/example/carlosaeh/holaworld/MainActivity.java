package com.example.carlosaeh.holaworld;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {
    View[] cartas;
    ImageButton[] btns;
    Button restart;
    ArrayList<Integer> imgs;
    int cont=0;
    int carta1,carta2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imgs = new ArrayList<>();
        cartas = new View[16];
        btns = new ImageButton[16];
        restart = findViewById(R.id.restart);
        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Por favor Reinicia la aplicación ;)", Toast.LENGTH_SHORT).show();
            }
        });
        crearMazo();
        for(int i=0;i<16;i++){
            final int j = i;
            cartas[i] = findViewById(getResources().getIdentifier("c"+i, "id", getPackageName()));
            btns[i] = cartas[i].findViewById(R.id.carta);
            btns[j].setTag(0);
            cartas[j].setTag(0);
            btns[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (btns[j].getTag().equals(0)) {
                        int img = imgs.get(0);
                        btns[j].setImageResource(img);
                        btns[j].setTag(img);
                        imgs.remove(0);
                    }else{
                        btns[j].setImageResource((int)btns[j].getTag());
                    }
                    btns[j].setEnabled(false);
                    System.out.println(j);
                    cont++;
                    if (cont==1) {
                        carta1=j;
                    }
                    if (cont==2){
                        carta2=j;
                        System.out.println(btns[carta1].getDrawable());
                        System.out.println(btns[carta2].getDrawable().toString());
                        if (btns[carta1].getDrawable().getConstantState().equals(btns[carta2].getDrawable().getConstantState())){
                            cartas[carta1].setTag(1);
                            cartas[carta2].setTag(1);
                            //Toast.makeText(MainActivity.this, "Son Iguales", Toast.LENGTH_SHORT).show();
                        }else{
                            for(int x=0;x<16;x++){
                                btns[x].setEnabled(false);
                            }
                            (new Handler()).postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    btns[carta1].setImageResource(R.drawable.i0);
                                    btns[carta1].setEnabled(true);
                                    btns[carta2].setImageResource(R.drawable.i0);
                                    btns[carta2].setEnabled(true);
                                    for(int x=0;x<16;x++){
                                        if (!cartas[x].getTag().equals(1)) {
                                            btns[x].setEnabled(true);
                                        }
                                    }
                                }
                            },500);
                        }
                        cont=0;
                    }
                }
            });
        }
    }
    public void crearMazo(){
        imgs.add(R.drawable.i1);
        imgs.add(R.drawable.i2);
        imgs.add(R.drawable.i3);
        imgs.add(R.drawable.i4);
        imgs.add(R.drawable.i5);
        imgs.add(R.drawable.i6);
        imgs.add(R.drawable.i7);
        imgs.add(R.drawable.i8);
        imgs.addAll(imgs);
        System.out.println("Tamaño: "+imgs.size());
        Collections.shuffle(imgs);
    }
}
