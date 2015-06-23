package ti_028.weatherapp;

import android.graphics.Color;

import java.util.Random;

/**
 * Created by Shin on 2015/06/23.
 */
public class Ball {
    int radius, posX, posY, velocity, dirX, dirY, color;

    public Ball(int r, int x, int y, int v, int _dirX, int _dirY) {
        Random random = new Random();
        radius   = r;
        posX     = x;
        posY     = y;
        velocity = v;
        dirX     = _dirX;
        dirY     = _dirY;
        color    = Color.argb(255, random.nextInt(255), random.nextInt(255), random.nextInt(255));
    }

    public void setPosX(int x) {
        posX += x;
    }

    public void setPosY(int y) {
        posY += y;
    }

    public void updateDirX() {
        dirX *= -1;
    }

    public void updateDirY() {
        dirY *= -1;
    }

    public void setColor() {
        Random random = new Random();
        color = Color.argb(255, random.nextInt(255), random.nextInt(255), random.nextInt(255));
    }
}
