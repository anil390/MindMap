package com.anil.android.mindmap;


import java.util.UUID;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

/**
 * This is demo code to accompany the Mobiletuts+ tutorial series:
 * - Android SDK: Create a Drawing App
 * 
 * Sue Smith
 * August 2013
 *
 */
public class MainActivity extends Activity implements OnClickListener {

	//custom drawing view
	private DrawingView drawView;
	//buttons
	private ImageButton currPaint, drawBtn, eraseBtn, newBtn, saveBtn, rectBtn;
	//sizes
	private float smallBrush, mediumBrush, largeBrush, smallRect, largeRect, mediumRect;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		//get drawing view
		drawView = (DrawingView)findViewById(R.id.drawing);
		

		//get the palette and first color button
		LinearLayout paintLayout = (LinearLayout)findViewById(R.id.paint_colors);
		currPaint = (ImageButton)paintLayout.getChildAt(0);
		currPaint.setImageDrawable(getResources().getDrawable(R.drawable.paint_pressed));

		//sizes from dimensions
		smallBrush = getResources().getInteger(R.integer.small_size);
		mediumBrush = getResources().getInteger(R.integer.medium_size);
		largeBrush = getResources().getInteger(R.integer.large_size);

		//draw button
		drawBtn = (ImageButton)findViewById(R.id.draw_btn);
		drawBtn.setOnClickListener(this);

		//set initial size
		drawView.setBrushSize(mediumBrush);

		//erase button
		eraseBtn = (ImageButton)findViewById(R.id.erase_btn);
		eraseBtn.setOnClickListener(this);

		//new button
		newBtn = (ImageButton)findViewById(R.id.new_btn);
		newBtn.setOnClickListener(this);

		//save button
		saveBtn = (ImageButton)findViewById(R.id.save_btn);
		saveBtn.setOnClickListener(this);
		
		rectBtn = (ImageButton)findViewById(R.id.rect_btn);
		rectBtn.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	//user clicked paint
	public void paintClicked(View view){
		//use chosen color

		//set erase false
		drawView.setErase(false);
		drawView.setBrushSize(drawView.getLastBrushSize());

		if(view!=currPaint){
			ImageButton imgView = (ImageButton)view;
			String color = view.getTag().toString();
			drawView.setColor(color);
			//update ui
			imgView.setImageDrawable(getResources().getDrawable(R.drawable.paint_pressed));
			currPaint.setImageDrawable(getResources().getDrawable(R.drawable.paint));
			currPaint=(ImageButton)view;
		}
	}

	@Override
	public void onClick(View view){

		if(view.getId()==R.id.draw_btn){
			//draw button clicked
			final Dialog brushDialog = new Dialog(this);
			brushDialog.setTitle("Brush size:");
			brushDialog.setContentView(R.layout.brush_chooser);
			//listen for clicks on size buttons
			ImageButton smallBtn = (ImageButton)brushDialog.findViewById(R.id.small_brush);
			smallBtn.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View v) {
					drawView.setErase(false);
					drawView.setBrushSize(smallBrush);
					drawView.setLastBrushSize(smallBrush);
					brushDialog.dismiss();
				}
			});
			ImageButton mediumBtn = (ImageButton)brushDialog.findViewById(R.id.medium_brush);
			mediumBtn.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View v) {
					drawView.setErase(false);
					drawView.setBrushSize(mediumBrush);
					drawView.setLastBrushSize(mediumBrush);
					brushDialog.dismiss();
				}
			});
			ImageButton largeBtn = (ImageButton)brushDialog.findViewById(R.id.large_brush);
			largeBtn.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View v) {
					drawView.setErase(false);
					drawView.setBrushSize(largeBrush);
					drawView.setLastBrushSize(largeBrush);
					brushDialog.dismiss();
				}
			});
			//show and wait for user interaction
			brushDialog.show();
		}
		
		else if(view.getId()==R.id.rect_btn){
			//draw button clicked
			final Dialog rectDialog = new Dialog(this);
			rectDialog.setTitle("Rect size:");
			rectDialog.setContentView(R.layout.rect_chooser);
			//listen for clicks on size buttons
			ImageButton smallBtn = (ImageButton)rectDialog.findViewById(R.id.small_rect);
			smallBtn.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View v) {
					drawView.setErase(false);
					drawView.setRectSize(smallRect);
					drawView.setLastRectSize(smallRect);
					rectDialog.dismiss();
			/*		Canvas canvasBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
					drawCanvas = new Canvas(canvasBitmap);
					drawView.setColor(Color.BLACK);
					drawView.setStrokeWidth(3);
				        canvas.drawRect(30, 30, 80, 80,drawView);
				        drawView.setStrokeWidth(0);
				        drawView.setColor(Color.CYAN);
				        drawView.drawRect(33, 60, 77, 77, paint );
				        drawView.setColor(Color.YELLOW);
				        canvas.drawRect(33, 33, 77, 60, drawView );*/
					
					
					
				}
			});
			/*ImageButton mediumBtn = (ImageButton)rectDialog.findViewById(R.id.medium_rect);
			mediumBtn.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View v) {
					drawView.setErase(false);
					drawView.setrectSize(mediumrect);
					drawView.setLastrectSize(mediumrect);
					rectDialog.dismiss();
				}
			});
			ImageButton largeBtn = (ImageButton)rectDialog.findViewById(R.id.large_rect);
			largeBtn.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View v) {
					drawView.setErase(false);
					drawView.setrectSize(largerect);
					drawView.setLastrectSize(largerect);
					rectDialog.dismiss();
				}
			});*/
			//show and wait for user interaction
			rectDialog.show();
		}
		else if(view.getId()==R.id.erase_btn){
			//switch to erase - choose size
			final Dialog brushDialog = new Dialog(this);
			brushDialog.setTitle("Eraser size:");
			brushDialog.setContentView(R.layout.brush_chooser);
			//size buttons
			ImageButton smallBtn = (ImageButton)brushDialog.findViewById(R.id.small_brush);
			smallBtn.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View v) {
					drawView.setErase(true);
					drawView.setBrushSize(smallBrush);
					brushDialog.dismiss();
				}
			});
			ImageButton mediumBtn = (ImageButton)brushDialog.findViewById(R.id.medium_brush);
			mediumBtn.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View v) {
					drawView.setErase(true);
					drawView.setBrushSize(mediumBrush);
					brushDialog.dismiss();
				}
			});
			ImageButton largeBtn = (ImageButton)brushDialog.findViewById(R.id.large_brush);
			largeBtn.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View v) {
					drawView.setErase(true);
					drawView.setBrushSize(largeBrush);
					brushDialog.dismiss();
				}
			});
			brushDialog.show();
		}
		else if(view.getId()==R.id.new_btn){
			//new button
			AlertDialog.Builder newDialog = new AlertDialog.Builder(this);
			newDialog.setTitle("New drawing");
			newDialog.setMessage("Start new drawing (you will lose the current drawing)?");
			newDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener(){
				public void onClick(DialogInterface dialog, int which){
					drawView.startNew();
					dialog.dismiss();
				}
			});
			newDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener(){
				public void onClick(DialogInterface dialog, int which){
					dialog.cancel();
				}
			});
			newDialog.show();
		}
		else if(view.getId()==R.id.save_btn){
			//save drawing
			AlertDialog.Builder saveDialog = new AlertDialog.Builder(this);
			saveDialog.setTitle("Save drawing");
			saveDialog.setMessage("Save drawing to device Gallery?");
			saveDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener(){
				public void onClick(DialogInterface dialog, int which){
					//save drawing
					drawView.setDrawingCacheEnabled(true);
					//attempt to save
					String imgSaved = MediaStore.Images.Media.insertImage(
							getContentResolver(), drawView.getDrawingCache(),
							UUID.randomUUID().toString()+".png", "drawing");
					//feedback
					if(imgSaved!=null){
						Toast savedToast = Toast.makeText(getApplicationContext(), 
								"Drawing saved to Gallery!", Toast.LENGTH_SHORT);
						savedToast.show();
					}
					else{
						Toast unsavedToast = Toast.makeText(getApplicationContext(), 
								"Oops! Image could not be saved.", Toast.LENGTH_SHORT);
						unsavedToast.show();
					}
					drawView.destroyDrawingCache();
				}
			});
			saveDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener(){
				public void onClick(DialogInterface dialog, int which){
					dialog.cancel();
				}
			});
			saveDialog.show();
		}
	}

}
