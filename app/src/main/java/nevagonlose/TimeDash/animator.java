package nevagonlose.TimeDash;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by Solomon on 2016-08-19.
 */
public class animator {
    private Bitmap[] slideImages;
    private Bitmap[] slideViews;
    private boolean isRunning; //the animation will only show if isRunning is true
    private int interval; //slides changed per interval
    private int width;
    private int height;
    private int stallIndex; //if this int equals and index number, the animation will pause on that slide
    private int index; //which slide should be shown
    private char type;// type of animation can be standard 's' or reversal 'r'
    Runnable run;

    //initialize the amount of slides in the animation where numberOfSlides = the amount of images
    animator(int numberOfSlides, int w, int h){
        slideImages = new Bitmap[numberOfSlides];
        slideViews = new Bitmap[numberOfSlides];
        isRunning = true;
        stallIndex = -1;
        interval = 1;
        type = 's';
        width = w;
        height  = h;
    }

    public void setSize(float w, float h){
        width = (int)w;
        height = (int)h;
    }

    public void setSize(int w, int h){
        width = w;
        height = h;
    }

    //set an image to become one of the slides
    public boolean setSlide(int index, Bitmap image){
        slideImages[index] = image;
        slideViews[index] = Bitmap.createScaledBitmap(slideImages[index], width, height, true);
        index = 0;
        return true;
    }

    public void reverseTypeAnimation(){
        type = 'r';
    }

    //sets the stall index
    public void setStallIndex(int i){
        stallIndex = i;
    }

    //increase the index by 1 so that the next slide is shown
    //if the index is greater than the amount of slides, bring the index to zero
    public void changeSlide(){
        if(isRunning) {
            if(index != stallIndex) {
                index += interval;
            }
            if (index >= slideViews.length) {
                if(type == 'r'){
                    interval = -1;
                    index -= 1;
                }else{
                    index = 0;
                }
            }
            if(index <= 0){
                if(type == 'r' && interval == -1){
                    interval = 1;
                    index = 0;
                }
            }
        }
    }

    public boolean isRunning(){
        return isRunning;
    }

    public void stop(){
        isRunning = false;
    }

    public void start(){
        isRunning = true;
        index = 0;
    }


    public void pause(int pauseIndex){
        stallIndex = pauseIndex;
        index = stallIndex;
    }

    public void unpause(){
        stallIndex = -1;
        index = 0;
    }

    public void viewSlide(Canvas canvas, int index, Paint paint){
        if(isRunning) {
            canvas.drawBitmap(slideViews[index], 0, 0, paint);
        }
    }

    public void viewCurrentSlide(Canvas canvas, Paint paint){
        if(isRunning) {
            canvas.drawBitmap(slideViews[index], 0, 0, paint);
        }
    }

    public void viewCurrentSlide(Canvas canvas, Paint paint, float x, float y){
        if(isRunning) {
            canvas.drawBitmap(slideViews[index], x, y, paint);
        }
    }

}