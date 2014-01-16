package com.anil.android.mindmap;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

public class BallActivity extends Activity {
	
	private DrawingView drawView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_ball);
		 FrameLayout main = (FrameLayout) findViewById(R.id.fm);
		 main.addView(new Ball(this,50,50,25));
		 main.setOnTouchListener(new View.OnTouchListener() {
			    public boolean onTouch(View v, MotionEvent e) {
			        float x = e.getX();
				float y = e.getY();
			        FrameLayout flView = (FrameLayout) v;
				flView.addView(new Ball(getParent(), x,y,25));
				return true;
			    }
			});
	}	

}
