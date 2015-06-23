package ti_028.weatherapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Shin on 2015/06/23.
 */
public class DrawRipple extends View {
    public DrawRipple(Context context){
        super(context);
    }
    public DrawRipple(Context context , AttributeSet attrs){
        super(context , attrs);
    }
    public DrawRipple(Context context , AttributeSet attrs , int defStyle){
        super(context,attrs,defStyle);
    }

    @Override
    protected void onDraw(Canvas canvas){
        Paint paint = new Paint();
        paint.setColor(Color.argb(255, 255, 255, 255));

        paint.setAntiAlias(false);
        canvas.drawCircle(70, 70, 20.0f, paint);

        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);
        canvas.drawCircle(500, 500, 50.0f, paint);
    }
}
