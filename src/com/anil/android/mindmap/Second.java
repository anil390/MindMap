package com.anil.android.mindmap;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;

public class Second extends Activity {

    SampleCanvasActivity drawView;

 

    @Override

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

 

        drawView = new SampleCanvasActivity(this);

        drawView.setBackgroundColor(Color.WHITE);

        setContentView(drawView);

 

    }

}
