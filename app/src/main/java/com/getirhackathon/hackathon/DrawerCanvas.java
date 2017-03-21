package com.getirhackathon.hackathon;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.View;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class DrawerCanvas extends View {
    ArrayList<Rect> rectangle = new ArrayList<>();
    ArrayList<customCircle> circle = new ArrayList<>();
    ArrayList<Paint> rectanglePaints = new ArrayList<>();
    ArrayList<Paint> circlePaints = new ArrayList<>();
    public DrawerCanvas(Context context, JSONArray cizilecekJSON) {
        super(context);
         for (int i = 0; i < cizilecekJSON.length(); i++) {
                    try {
                        JSONObject object = cizilecekJSON.getJSONObject(i);
                        if(object.getString("type").equalsIgnoreCase("circle")){
                            circleDraw(object.getString("xPosition"),object.getString("yPosition"),object.getString("r"),object.getString("color"));
                        }
                        else{
                            circleRectangle(object.getString("xPosition"),object.getString("yPosition"),object.getString("width"),object.getString("height"),object.getString("color"));
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
    }

    private void circleDraw(String xPosition, String yPosition, String r, String color) {
        Log.d("DrawerCanvas","X , Y : " +xPosition + " , " + yPosition + "\nRadius : " + r + "\nColor : " + color);
        customCircle customCircle = new customCircle(Integer.parseInt(xPosition), Integer.parseInt(yPosition),Integer.parseInt(r));
        Paint paint = new Paint();
        paint.setColor(Color.parseColor("#"+color));
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        circle.add(customCircle);
        circlePaints.add(paint);


    }
    private void circleRectangle(String xPosition, String yPosition, String width, String height, String color){
        Rect rect = new Rect(Integer.parseInt(xPosition), Integer.parseInt(yPosition),(Integer.parseInt(width) + Integer.parseInt(xPosition))   , (Integer.parseInt(height) + Integer.parseInt(yPosition)));
        Paint paint = new Paint();
        paint.setColor(Color.parseColor("#"+color));
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        rectangle.add(rect);
        rectanglePaints.add(paint);
        Log.d("DrawerCanvas","X , Y : " +Integer.parseInt(xPosition) + " , " + Integer.parseInt(yPosition) + "\nWidth : " + (Integer.parseInt(width) + Integer.parseInt(xPosition)) + "\nHeight : " + (Integer.parseInt(height) + Integer.parseInt(yPosition))+ "\nColor : " + color);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < rectangle.size(); i++) {
            canvas.drawRect(rectangle.get(i), rectanglePaints.get(i));

        }
        for (int i = 0; i < circle.size(); i++) {
            canvas.drawCircle(circle.get(i).getX(),circle.get(i).getY(),circle.get(i).getRadius(),circlePaints.get(i));
        }
    }
    class customCircle{
        int x,y,radius;
        customCircle(int x, int y, int radius){
            this.x = x;
            this.y = y;
            this.radius = radius;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public int getRadius() {
            return radius;
        }
    }
}
