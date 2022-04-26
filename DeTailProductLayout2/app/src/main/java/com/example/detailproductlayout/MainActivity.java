package com.example.detailproductlayout;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

public class MainActivity extends AppCompatActivity {
    private ImageSwitcher imageSwitcher;
    private Button buttonPrevious;
    private Button buttonNext;
    final  String[] imageNames = {"anhdautay","anhdautay1","anhdautay2"};
    private int currentIndex;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonPrevious = (Button) findViewById(R.id.previous);
        buttonNext = (Button) findViewById(R.id.next);
        imageSwitcher = (ImageSwitcher) findViewById(R.id.photo);
        imageSwitcher.setFactory(new ViewSwitcher.ViewFactory() {

            // Returns the view to show Image
            // (Usually should use ImageView)
            @Override
            public View makeView() {
                ImageView imageView = new ImageView(getApplicationContext());

                imageView.setBackgroundColor(Color.LTGRAY);

                ImageSwitcher.LayoutParams params= new ImageSwitcher.LayoutParams(
                        ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
                imageView.setLayoutParams(params);
                return imageView;
            }
        });

        this.currentIndex=0;
        this.showImage(this.currentIndex);

        buttonPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                previousImage();
            }
        });

        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextImage();
            }
        });

    }
    private void previousImage()  {
        if(currentIndex > 0) {
            currentIndex--;
        }else  {
            Toast.makeText(getApplicationContext(), "No Previous Image", Toast.LENGTH_SHORT).show();
            return;
        }
        this.showImage(currentIndex);
    }
    private void nextImage()  {
        if(currentIndex < this.imageNames.length-1) {
            currentIndex++;
        }else  {
            Toast.makeText(getApplicationContext(), "No Next Image", Toast.LENGTH_SHORT).show();
            return;
        }
        this.showImage(currentIndex);
    }

    private void showImage(int imgIndex) {
        String imageName= this.imageNames[imgIndex];

        int resId= getDrawableResIdByName(imageName);
        if(resId!=  0) {
            this.imageSwitcher.setImageResource(resId);
        }
    }

    private int getDrawableResIdByName(String resName) {
        String pkgName = this.getPackageName();
        // Return 0 if not found.
        int resID = this.getResources().getIdentifier(resName , "drawable", pkgName);
        Log.i("MyLog", "Res Name: " + resName + "==> Res ID = " + resID);
        return resID;
    }

}