package com.example.catchthekenny;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
TextView timeTV, scoreTV;
ImageView imageView, imageView1, imageView2, imageView3, imageView4, imageView5,imageView6, imageView7, imageView8;
ImageView[] imageArray;
Handler handler;
Runnable runnable;
int score;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (ImageView) findViewById(R.id.imageView);
        imageView1 = (ImageView) findViewById(R.id.imageView1);
        imageView2 = (ImageView) findViewById(R.id.imageView2);
        imageView3 = (ImageView) findViewById(R.id.imageView3);
        imageView4 = (ImageView) findViewById(R.id.imageView4);
        imageView5 = (ImageView) findViewById(R.id.imageView5);
        imageView6 = (ImageView) findViewById(R.id.imageView6);
        imageView7 = (ImageView) findViewById(R.id.imageView7);
        imageView8 = (ImageView) findViewById(R.id.imageView8);
        timeTV = (TextView)  findViewById(R.id.textViewTime);
        scoreTV = (TextView) findViewById(R.id.textViewScore);
        imageArray = new ImageView[] {imageView,imageView1,imageView2,imageView3,imageView4,imageView5,imageView6,imageView7,imageView8};
        hideImages();
        score = 0;
        new CountDownTimer(10000, 1000){

            @Override
            public void onTick(long millisUntilFinished) {
                timeTV.setText("Time: "+millisUntilFinished/1000);
            }


            @Override
            public void onFinish() {

               timeTV.setText("Time Off");
                handler.removeCallbacks(runnable);
                for (ImageView image : imageArray) {
                    image.setVisibility(View.INVISIBLE);
                }


                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);

                alert.setTitle("Restart?");
                alert.setMessage("Are you sure to restart game?");
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        //restart

                        Intent intent = getIntent();
                        finish();
                        startActivity(intent);

                    }
                });

                alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "Game Over!", Toast.LENGTH_SHORT).show();
                    }
                });

                alert.show();

            }
        }.start();

    }
        public void hideImages() {

        handler = new Handler();

        runnable = new Runnable() {
            @Override
            public void run() {

                for (ImageView image : imageArray) {
                    image.setVisibility(View.INVISIBLE);
                }

                Random random = new Random();
                int i = random.nextInt(9);
                imageArray[i].setVisibility(View.VISIBLE);

                handler.postDelayed(this,500);

            }
        };


        handler.post(runnable);


    }

    public void increaseScore(View view){
    score++;
    scoreTV.setText("Score: "+score);
    }
}