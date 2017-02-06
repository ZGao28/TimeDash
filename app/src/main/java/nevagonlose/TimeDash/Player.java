package nevagonlose.TimeDash;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;


public class Player {
    //Animation stuff
    public animator runningAnimation;
    public animator jumpingAnimation;
    public animator fallingAnimation;
    public animator slidingAnimation;
    protected Context context;
    //--------------
    public float x;
    public float y;
    public float height;
    public int width;
    public float acceleration;
    public float gravity;
    public boolean jump = false;
    public boolean slide = false;
    public boolean fall = false;
    public boolean onPlatform = false;
    int slideTimer;
    public float oldHeight;
    public float oldY;
    public boolean flyingSection = false;
    public boolean swimmingSection = false;

    Player(int xx, int yy, int w, int h, Context current) {//Grants access to the context for image rendering
        x = xx;
        y = yy;
        width = w;
        height = h;

        fall = jump = false;
        context = current;
    }

    Player() {

    }


    //New methods for animation
    //Setup animator slide counts
    void createRunningAnimation(int slideCount) {
        if (slideCount > 0) {
            runningAnimation = new animator(slideCount, width, (int) height);
        }
    }

    void createJumpingAnimation(int slideCount) {
        if (slideCount > 0) {
            jumpingAnimation = new animator(slideCount, width, (int) height);
        }
    }

    void createFallingAnimation(int slideCount) {
        if (slideCount > 0) {
            fallingAnimation = new animator(slideCount, width, (int) height);
        }
    }

    void createSlidingAnimation(int slideCount) {
        if (slideCount > 0) {
            slidingAnimation = new animator(slideCount, width, (int) height/2);
        }
    }

    //add slides to the animators (this can also be done by directly accessing the animator ex. player.animator.addSlide())
    void setRunningSlide(int index, Bitmap image) {
        runningAnimation.setSlide(index, image);
    }

    void setJumpingSlide(int index, Bitmap image) {
        jumpingAnimation.setSlide(index, image);
    }

    void setFallingSlide(int index, Bitmap image) {
        fallingAnimation.setSlide(index, image);
    }

    //the rest of the animator functions can be accessed directly by the animator itself
    //displaying the animations depends on the state of the character (jumping, falling, running...)

    //change the animation slide of the animators
    public void update() {
        runningAnimation.changeSlide();
        jumpingAnimation.changeSlide();
        fallingAnimation.changeSlide();
        slidingAnimation.changeSlide();

        runningAnimation.setSize(width, height);
        jumpingAnimation.setSize(width, height);
        fallingAnimation.setSize(width, height);
        slidingAnimation.setSize(width, height);
    }

    void display(Canvas canvas, int xx, int yy, Paint characterPaint) {
        if (jump) {
            jumpingAnimation.viewCurrentSlide(canvas, characterPaint, xx, yy);
        } else if (fall) {
            fallingAnimation.viewCurrentSlide(canvas, characterPaint, xx, yy);
        } else if (slide) {
            slidingAnimation.viewCurrentSlide(canvas, characterPaint, xx, yy);
        }else if (jump == false && fall == false && slide == false) {
            runningAnimation.viewCurrentSlide(canvas, characterPaint, xx, yy);
        }
    }
    //-------------------------


    public void saveValues() {
        oldHeight = height;
    }

    public void updatePosition(float screenHeight) {

        if (flyingSection) {
            gravity = screenHeight / 2500;
            width = (int) screenHeight / 10;
            y = y - acceleration;
            acceleration = acceleration - gravity;
            height = screenHeight / 12;

            if (acceleration <= -11) {
                acceleration = -10.9f;
            }

            if (jump) {
                acceleration = screenHeight / 70;
                jump = false;
            }

            if (y <= 0) {
                y = screenHeight / 80;
                acceleration = 0;
            }

            if (y + height >= screenHeight) {
                y = screenHeight - height - 10;
                acceleration = 0;
            }

        } else if (swimmingSection) {
            gravity = - screenHeight / 2500;
            height = (int) screenHeight / 10;
            width = (int) screenHeight / 10;
            y = y - acceleration;
            acceleration = acceleration - gravity;

            if (acceleration >= 11) {
                acceleration = 10f;
            }

            if (jump) {
                acceleration = -screenHeight / 70;
                jump = false;
            }


            if (y + height >= screenHeight) {
                y = screenHeight - (height + 5);
                acceleration = 0;
            }
        } else {
            gravity = screenHeight / 530;
            if (jump && !slide) {
                y = y - acceleration;
                acceleration = acceleration - gravity;
                if (acceleration <= 0) {
                    fall = true;
                    jump = false;
                }
            }

            if (acceleration <= -14) {
                acceleration = -13.9f;
            }

            if (onPlatform || (y + height) >= screenHeight / 1.1) {
                fall = false;
                if ((y + height) > screenHeight / 1.09) {
                    y = screenHeight / 1.09f - height;
                }
            } else if (!onPlatform && (y + height) <= screenHeight / 1.1 && !jump) {
                fall = true;
            }

            if (fall) {
                y = y - acceleration;
                acceleration = acceleration - gravity;
            }
            if (slide) {
                slideTimer++;
                if (slideTimer > 50) {
                    slidingAnimation.stop();
                    runningAnimation.start();
                    slide = false;
                    y = y - height;
                    height = oldHeight;
                    slideTimer = 0;
                }
            }
        }
    }
}

//class for the main character contains main character specific settings
class mainCharacter extends Player {

    mainCharacter(int xx, int yy, int w, int h, Context current) {
        super(xx, yy, w, h, current);

        //Testing of image loading
        //image dimensions
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(context.getResources(), R.drawable.run__000, options); //create options to find the dimensions of the image
        options.inSampleSize = calculateInSampleSize(options, 100, 100); //set the sample size of the options so that the image can be scaled down
        options.inJustDecodeBounds = false;

        //------------------------------------------------------------

        //hard code running animation
        createRunningAnimation(10);
        Bitmap b = BitmapFactory.decodeResource(context.getResources(), R.drawable.run__000, options);
        setRunningSlide(0, b);
        b = BitmapFactory.decodeResource(context.getResources(), R.drawable.run__001, options);
        setRunningSlide(1, b);
        b = BitmapFactory.decodeResource(context.getResources(), R.drawable.run__002, options);
        setRunningSlide(2, b);
        b = BitmapFactory.decodeResource(context.getResources(), R.drawable.run__003, options);
        setRunningSlide(3, b);
        b = BitmapFactory.decodeResource(context.getResources(), R.drawable.run__004, options);
        setRunningSlide(4, b);
        b = BitmapFactory.decodeResource(context.getResources(), R.drawable.run__005, options);
        setRunningSlide(5, b);
        b = BitmapFactory.decodeResource(context.getResources(), R.drawable.run__006, options);
        setRunningSlide(6, b);
        b = BitmapFactory.decodeResource(context.getResources(), R.drawable.run__007, options);
        setRunningSlide(7, b);
        b = BitmapFactory.decodeResource(context.getResources(), R.drawable.run__008, options);
        setRunningSlide(8, b);
        b = BitmapFactory.decodeResource(context.getResources(), R.drawable.run__009, options);
        setRunningSlide(9, b);

        //hard code jumping animation
        createJumpingAnimation(10);
        jumpingAnimation.setStallIndex(9); //stall the animation on the 10th slide
        b = BitmapFactory.decodeResource(context.getResources(), R.drawable.jump__000, options);
        jumpingAnimation.setSlide(0, b);
        b = BitmapFactory.decodeResource(context.getResources(), R.drawable.jump__001, options);
        jumpingAnimation.setSlide(1, b);
        b = BitmapFactory.decodeResource(context.getResources(), R.drawable.jump__002, options);
        jumpingAnimation.setSlide(2, b);
        b = BitmapFactory.decodeResource(context.getResources(), R.drawable.jump__003, options);
        jumpingAnimation.setSlide(3, b);
        b = BitmapFactory.decodeResource(context.getResources(), R.drawable.jump__004, options);
        jumpingAnimation.setSlide(4, b);
        b = BitmapFactory.decodeResource(context.getResources(), R.drawable.jump__005, options);
        jumpingAnimation.setSlide(5, b);
        b = BitmapFactory.decodeResource(context.getResources(), R.drawable.jump__006, options);
        jumpingAnimation.setSlide(6, b);
        b = BitmapFactory.decodeResource(context.getResources(), R.drawable.jump__007, options);
        jumpingAnimation.setSlide(7, b);
        b = BitmapFactory.decodeResource(context.getResources(), R.drawable.jump__008, options);
        jumpingAnimation.setSlide(8, b);
        b = BitmapFactory.decodeResource(context.getResources(), R.drawable.jump__009, options);
        jumpingAnimation.setSlide(9, b);

        //hard code falling animation
        createFallingAnimation(4);
        fallingAnimation.setStallIndex(3); //changes the animation type to reverse
        b = BitmapFactory.decodeResource(context.getResources(), R.drawable.jump__009, options);
        fallingAnimation.setSlide(0, b);
        b = BitmapFactory.decodeResource(context.getResources(), R.drawable.jump__008, options);
        fallingAnimation.setSlide(1, b);
        b = BitmapFactory.decodeResource(context.getResources(), R.drawable.jump__007, options);
        fallingAnimation.setSlide(2, b);
        b = BitmapFactory.decodeResource(context.getResources(), R.drawable.jump__006, options);
        fallingAnimation.setSlide(3, b);

        //hard code sliding animation
        createSlidingAnimation(10);
        slidingAnimation.setStallIndex(9);
        b = BitmapFactory.decodeResource(context.getResources(), R.drawable.slide__000, options);
        slidingAnimation.setSlide(0, b);
        b = BitmapFactory.decodeResource(context.getResources(), R.drawable.slide__001, options);
        slidingAnimation.setSlide(1, b);
        b = BitmapFactory.decodeResource(context.getResources(), R.drawable.slide__002, options);
        slidingAnimation.setSlide(2, b);
        b = BitmapFactory.decodeResource(context.getResources(), R.drawable.slide__003, options);
        slidingAnimation.setSlide(3, b);
        b = BitmapFactory.decodeResource(context.getResources(), R.drawable.slide__004, options);
        slidingAnimation.setSlide(4, b);
        b = BitmapFactory.decodeResource(context.getResources(), R.drawable.slide__005, options);
        slidingAnimation.setSlide(5, b);
        b = BitmapFactory.decodeResource(context.getResources(), R.drawable.slide__006, options);
        slidingAnimation.setSlide(6, b);
        b = BitmapFactory.decodeResource(context.getResources(), R.drawable.slide__007, options);
        slidingAnimation.setSlide(7, b);
        b = BitmapFactory.decodeResource(context.getResources(), R.drawable.slide__008, options);
        slidingAnimation.setSlide(8, b);
        b = BitmapFactory.decodeResource(context.getResources(), R.drawable.slide__009, options);
        slidingAnimation.setSlide(9, b);
    }


    //This Method is for testing purposes to determine how much we should scale down the images
    public static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) >= reqHeight
                    && (halfWidth / inSampleSize) >= reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }
}

