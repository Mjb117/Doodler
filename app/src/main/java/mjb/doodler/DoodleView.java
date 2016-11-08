package mjb.doodler;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Matt on 11/1/2016.
 */

public class DoodleView extends View {

    List<DrawPiece> drawList = new ArrayList<DrawPiece>();
    int currentRed;
    int currentGreen;
    int currentBlue;
    int currentSize;
    int currentOpacity;
    boolean split;

    private class DrawPiece {
        private Paint mpaintDoodle;
        private Path mpath;

        public DrawPiece(Paint paint, Path path) {
            mpaintDoodle = paint;
            mpath = path;
        }

        public Paint getPaint() {
            return mpaintDoodle;
        }

        public Path getPath() {
            return mpath;
        }
    }

    public DoodleView(Context context) {
        super(context);
        init(null, 0);
    }

    public DoodleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public DoodleView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {
        currentRed = 255;
        currentGreen = 0;
        currentBlue = 0;
        currentSize = 6;
        currentOpacity = 255;
        split = false;
        drawList.add(new DrawPiece(createPaint(currentRed, currentGreen, currentBlue, currentSize, currentOpacity), new Path()));
    }

    private Paint createPaint(int red, int green, int blue, int size, int opacity) {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setARGB(opacity, red, green, blue);
        paint.setStrokeWidth(size);

        return paint;
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        for (DrawPiece drawP : drawList) {
            canvas.drawPath(drawP.getPath(), drawP.getPaint());
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        float touchX = motionEvent.getX();
        float touchY = motionEvent.getY();
        DrawPiece drawp = new DrawPiece(createPaint(currentRed, currentGreen, currentBlue, currentSize, currentOpacity), new Path());
        DrawPiece drawp2 = new DrawPiece(createPaint(currentRed, currentGreen, currentBlue, currentSize, currentOpacity), new Path());
        DrawPiece drawp3 = new DrawPiece(createPaint(currentRed, currentGreen, currentBlue, currentSize, currentOpacity), new Path());
        DrawPiece drawp4 = new DrawPiece(createPaint(currentRed, currentGreen, currentBlue, currentSize, currentOpacity), new Path());

        switch(motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                drawp.getPath().moveTo(touchX, touchY);
                drawList.add(drawp);

                if (split) {
                    drawp2.getPath().moveTo(this.getWidth() - touchX, touchY);
                    drawList.add(drawp2);

                    drawp3.getPath().moveTo(touchX, this.getHeight() - touchY);
                    drawList.add(drawp3);

                    drawp4.getPath().moveTo(this.getWidth() - touchX, this.getHeight() - touchY);
                    drawList.add(drawp4);
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if (split) {
                    drawList.get(drawList.size() - 4).getPath().lineTo(touchX, touchY);
                    drawList.get(drawList.size() - 3).getPath().lineTo(this.getWidth() - touchX, touchY);
                    drawList.get(drawList.size() - 2).getPath().lineTo(touchX, this.getHeight() - touchY);
                    drawList.get(drawList.size() - 1).getPath().lineTo(this.getWidth() - touchX, this.getHeight() - touchY);
                }
                else {
                    drawList.get(drawList.size() - 1).getPath().lineTo(touchX, touchY);
                }
                break;
            case MotionEvent.ACTION_UP:
                break;
        }

        invalidate();
        return true;
    }

    public void clear(){
        drawList.clear();
        invalidate();
    }

    public void setRedValue(int red) {
        currentRed = red;
    }

    public void setGreenValue(int green) {
        currentGreen = green;
    }

    public void setBlueValue(int blue) {
        currentBlue = blue;
    }

    public void setSizeValue(int size) {
        currentSize = size;
    }

    public void setOpacityValue(int opacity) {
        currentOpacity = opacity;
    }

    public void setSplit(boolean splitbool) {
        split = splitbool;
    }
}
