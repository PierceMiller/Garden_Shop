package com.plantatree.plantatree;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.stream53.plantatree.plantatree.R;

public class Image_Drag extends AppCompatActivity {

    ImageView image_view_drag;
    RelativeLayout.LayoutParams PARAMETERS;

    int INITIAL_WIDTH;
    int INITIAL_HEIGHT;
    float scalediff, angle;
    float xCord2, yCord2, xCord, yCord;
    private static final int NONE = 0;
    private static final int DRAG = 1;
    private static final int ZOOM = 2;
    private int mode = NONE;
    private float oldDist = 1f;
    private float d = 0f;
    private float newRot = 0f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_drag);

        image_view_drag = (ImageView) findViewById(R.id.image_view_drag);

        /*Specifies how a view is positioned within a RelativeLayout,
        *with the x and y positioned at 500 each*/
        RelativeLayout.LayoutParams layoutPosition = new RelativeLayout.LayoutParams(500, 500);
        layoutPosition.leftMargin = 50;
        layoutPosition.topMargin = 50;
        layoutPosition.bottomMargin = -150;
        layoutPosition.rightMargin = -150;

        //applies the position to the image
        image_view_drag.setLayoutParams(layoutPosition);

        image_view_drag.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final ImageView view = (ImageView) v;

                //Retrieves a bitmap that is attached to an imageview
                ((BitmapDrawable) view.getDrawable()).setAntiAlias(true);
                switch (event.getAction() & MotionEvent.ACTION_MASK) {
                    case MotionEvent.ACTION_DOWN:

                        PARAMETERS = (RelativeLayout.LayoutParams) view.getLayoutParams();

                        INITIAL_WIDTH = PARAMETERS.width;
                        INITIAL_HEIGHT = PARAMETERS.height;

                        xCord2 = event.getRawX() - PARAMETERS.leftMargin;
                        yCord2 = event.getRawY() - PARAMETERS.topMargin;
                        mode = DRAG;
                        break;

                    case MotionEvent.ACTION_POINTER_DOWN:

                        oldDist = AREA(event);
                        if (oldDist > 10f) {
                            mode = ZOOM;
                        }

                        d = MOVE(event);

                        break;
                    case MotionEvent.ACTION_UP:

                        break;

                    case MotionEvent.ACTION_POINTER_UP:
                        mode = NONE;

                        break;
                    case MotionEvent.ACTION_MOVE:
                        if (mode == DRAG) {

                            xCord = event.getRawX();
                            yCord = event.getRawY();

                            PARAMETER_SETTING(xCord, yCord);

                            view.setLayoutParams(PARAMETERS);

                        } else if (mode == ZOOM) {

                            if (event.getPointerCount() == 2) {

                                newRot = MOVE(event);
                                float r = newRot - d;
                                angle = r;

                                xCord = event.getRawX();
                                yCord = event.getRawY();

                                float newDist = AREA(event);
                                if (newDist > 10f) {
                                    float scale = newDist / oldDist * view.getScaleX();
                                    if (scale > 0.6) {
                                        scalediff = scale;
                                        view.setScaleX(scale);
                                        view.setScaleY(scale);

                                    }
                                }

                                view.animate().rotationBy(angle).setDuration(0).setInterpolator(new LinearInterpolator()).start();

                                xCord = event.getRawX();
                                yCord = event.getRawY();

                                PARAMETERS.leftMargin = (int) ((xCord - xCord2) + scalediff);
                                PARAMETERS.topMargin = (int) ((yCord - yCord2) + scalediff);

                                PARAMETERS.rightMargin = 0;
                                PARAMETERS.bottomMargin = 0;
                                PARAMETERS.rightMargin = PARAMETERS.leftMargin + (5 * PARAMETERS.width);
                                PARAMETERS.bottomMargin = PARAMETERS.topMargin + (10 * PARAMETERS.height);

                                view.setLayoutParams(PARAMETERS);

                            }
                        }
                        break;
                }

                return true;
            }
        });
    }

    public void PARAMETER_SETTING(float x, float y){

        PARAMETERS.leftMargin = (int) (xCord - xCord2);
        PARAMETERS.topMargin = (int) (yCord - yCord2);

        PARAMETERS.rightMargin = 0;
        PARAMETERS.bottomMargin = 0;
        PARAMETERS.rightMargin = PARAMETERS.leftMargin + (5 * PARAMETERS.width);
        PARAMETERS.bottomMargin = PARAMETERS.topMargin + (10 * PARAMETERS.height);

    }

    private float AREA(MotionEvent event) {

        //
        float x = event.getX(0) - event.getX(1);
        float y = event.getY(0) - event.getY(1);

        return (float) Math.sqrt(x * x + y * y);
    }

    private float MOVE(MotionEvent event) {


        double delta_x = (event.getX(0) - event.getX(1));
        double delta_y = (event.getY(0) - event.getY(1));
        double radians = Math.atan2(delta_y, delta_x);

        return (float) Math.toDegrees(radians);
    }

    //Inflate menu button on the desired activity
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if(id == R.id.menu_Catalogue){

            Intent startTopic1 = new Intent (this, Catalogue_Activity.class);
            startActivity(startTopic1);

        }
        if(id == R.id.menu_Cart){

            Intent startTopic1 = new Intent (this, Shopping_Cart.class);
            startActivity(startTopic1);

        }
        if(id == R.id.menu_Quiz){

            Intent startTopic1 = new Intent (this, Quiz_Start.class);
            startActivity(startTopic1);

        }
        if(id == R.id.menu_Compare){

            Intent startTopic1 = new Intent (this, Image_Drag.class);
            startActivity(startTopic1);

        }

        return super.onOptionsItemSelected(item);
    }
}


