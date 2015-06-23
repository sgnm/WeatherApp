package ti_028.weatherapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by Shin on 2015/06/23.
 */
public class DrawRipple extends View  {

    public float radius = 50;
    private ScheduledExecutorService ses = null;
    Canvas[] circle;
    int x[] = new int[5];
    int y[] = new int[5];

    public DrawRipple(Context context){
        super(context);
    }
    public DrawRipple(Context context , AttributeSet attrs){
        super(context , attrs);
    }
    public DrawRipple(Context context , AttributeSet attrs , int defStyle){
        super(context,attrs,defStyle);
    }

    Runnable task = new Runnable() {
        @Override
        public void run() {
            postInvalidate();
            radius += 0.5;
            Log.d("rad", String.valueOf(radius));
        }
    };

    public void onResume(){
        // タイマーの作成
        ses = Executors.newSingleThreadScheduledExecutor();

        // 一定時間ごとにRunnableの処理を実行
        //   => scheduleAtFixedRate(Runnableオブジェクト , 最初の実行時間 , 実行の周期 , 値の単位(列挙型TimeUnitの値) )
        ses.scheduleAtFixedRate(task, 0L, 5L, TimeUnit.MILLISECONDS);
    }

    public void onPause(){
        if (ses != null) {
            // タイマーを停止する
            ses.shutdown();
            ses = null;
        }
    }

    @Override
    protected void onDraw(Canvas canvas){
        Paint paint = new Paint();
        paint.setColor(Color.argb(255, 255, 255, 255));

        paint.setAntiAlias(false);

        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);
        circle = new Canvas[5];
        for (int i = 0; i < circle.length; i++){
            if (x[i] != 0 && y[i] != 0) {
                canvas.drawCircle(x[i], y[i], radius, paint);
            }
            Log.d("pos", "x= " + x[i] + " y= " + y[i]);
            Log.d("pos", "length " + circle.length);
        }
    }
}
