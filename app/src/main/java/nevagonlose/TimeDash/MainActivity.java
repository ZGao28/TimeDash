package nevagonlose.TimeDash;

import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.os.Handler;


public class MainActivity extends AppCompatActivity {
    // Remember that you should never show the action bar if the
// status bar is hidden, so hide that too if necessary.
    private Handler handler;
    public boolean animate = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //handler for timing
        handler = new Handler();
        Runnable runnable = new Runnable(){
            @Override
            public void run() {
                //the animate variable controls the animation. the timer will only run if the animate
                //variable is true;
                if(animate) {
                    handler.postDelayed(this, 50);
                    dashView.player.update(); //change the slide of the main character's animation every interval (50ms)
                }
            }
        };

        handler.postDelayed(runnable, 1000);


    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        setContentView(R.layout.activity_main);
    }



}
