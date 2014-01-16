package com.anil.android.mindmap;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.PathShape;
import android.util.Log;
import android.view.View;

public class SampleCanvasActivity extends View {

    private Path mPath = null;
    private Paint mPaint = null;
    private PathShape mPathShape = null;
    private ShapeDrawable mShapeDrawable = null;

    public SampleCanvasActivity(Context context) {
        super(context);
    }

    private void init() {
        int width = this.getWidth() / 2;
        int height = this.getHeight() / 2;

        Log.d("init", String.format("width: %d; height: %d", width, height));

        this.mPath = new Path();
        this.mPath.moveTo(0, 0);
        this.mPath.lineTo(width, height);

        this.mPaint = new Paint();
        this.mPaint.setColor(Color.RED);
        this.mPaint.setStyle(Paint.Style.STROKE);
        this.mPathShape = new PathShape(this.mPath, this.getWidth(), this.getHeight());

        this.mShapeDrawable = new ShapeDrawable(this.mPathShape);
        this.mShapeDrawable.getPaint().set(this.mPaint);
        this.mShapeDrawable.setBounds(0, 0, this.getWidth(), this.getHeight());
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

        // Doing this here because in the constructor we don't have the width and height of the view, yet
        this.init();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Log.d("onDraw", "Drawing");

        // This works, but won't let me do what I'm really trying to do
        canvas.drawLine(0.0f, 0.0f, this.getWidth() / 2.0f, this.getHeight() / 2.0f, this.mPaint);

        // This should work, but does not
        //this.mPathShape.draw(canvas, this.mPaint);

        // This should work, but does not
        //this.mShapeDrawable.draw(canvas);
    }
}
