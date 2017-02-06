package nevagonlose.TimeDash;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceView;
import android.view.SurfaceHolder;
import android.view.MotionEvent;

import static nevagonlose.TimeDash.mainCharacter.calculateInSampleSize;

public class dashView extends SurfaceView implements SurfaceHolder.Callback {
    private DashThread dashThread;
    private Activity activity;
    private int screenWidth;
    private int screenHeight;
    private int selectLevel;

    private Paint backgroundPaint;
    private Paint playerPaint;
    private Paint obstaclePaint;
    private Paint platformPaint;
    private Paint textPaint;
    private Paint groundPaint;
    private Paint fadePaint;

    private Paint menuBackGroundPaint;
    private Paint menuTitlePaint;
    private Paint[] buttonPaint = new Paint[3];
    private Paint[] levelPaint = new Paint[5];
    private Paint buttonPaint2;

    private boolean clicked = false;

    private boolean loadLevel1Values = false;
    private boolean loadMenuValues = false;
    private boolean loadLevel2Values = false;
    private boolean loadLevelSelectValues  =false;
    private boolean loadGameOverValues = false;

    private boolean caveEntered = false;
    private boolean positionReached = false;

    private int w;
    private int h;

    private int oldGameState = 0;

    private int n = 12;
    private int n2 = 12;


    int gameState = 0;

    //initialize levels
    levels[] lev = new levels[5];

    //initialize buttons
    button[] b = new button[10];

    //initialize snowflakes

    //initialize player
    static public mainCharacter player; //the main character must be static so that it can be referenced in other class. This also insures only one main character is made.

    private Obstacle portal1 = new Obstacle();
    private Obstacle portal2 = new Obstacle();
    private pet pterodactyl = new pet();
    private pet seaTurtle = new pet();

    //initialize obstacles
    Obstacle[] o1 = new Obstacle[200];
    Obstacle[] o2 = new Obstacle[200];

    //initialize platforms
    Platform[] p1 = new Platform[50];
    Platform[] p2 = new Platform[50];

    //menu images
    Bitmap bBack;
    Bitmap backWord;
    Bitmap bHighscores;
    Bitmap highscoresIcon;
    Bitmap bLevel2;
    Bitmap levelModeIMG2;
    Bitmap bEndless2;
    Bitmap endlessModeIMG2;
    Bitmap bMenubackground;
    Bitmap menuBackground;
    Bitmap bLevelselectb;
    Bitmap levelSelectBackground;
    Bitmap bGameOver;
    Bitmap gameOverImage;
    Bitmap bLevelComplete;
    Bitmap levelCompleteImage;
    Bitmap bReplayImage;
    Bitmap bReturtoMenuImage;
    Bitmap replayImage;
    Bitmap returntoMenuImage;
    Bitmap bCredits;
    Bitmap creditScreen;
    Bitmap bPrehistoricImage;
    Bitmap bIceAgeImage;
    Bitmap bComingSoon;
    Bitmap comingSoon;
    Bitmap prehistoricImage;
    Bitmap iceAgeImage;


    //level 1 images
    Bitmap bRock;
    Bitmap rockImage4;
    Bitmap rockImage5;
    Bitmap rockImage7;
    Bitmap rockImage8;

    Bitmap bCloud;
    Bitmap cloudImage7;

    Bitmap bStalactite;
    Bitmap stalactite;
    Bitmap bStalactite2;
    Bitmap stalactite2;
    Bitmap bMagma;
    Bitmap magma;

    Bitmap bBirdy;
    Bitmap bird;

    Bitmap bBackground1;
    Bitmap background1;
    Bitmap bBackground1Cave;
    Bitmap background1cave;

    Bitmap bPlatform;
    Bitmap platform;
    Bitmap platform2;
    Bitmap platform3;
    Bitmap platform4;
    Bitmap platform5;
    Bitmap platform6;
    Bitmap platform7;
    Bitmap platformLong;
    Bitmap platformLong2;
    Bitmap bPortal;
    Bitmap portal;
    Bitmap bLava;
    Bitmap lava;


    //level 2 images
    Bitmap bSnowrock;
    Bitmap snowrock;
    Bitmap snowrock2;
    Bitmap snowrock3;
    Bitmap snowrock4;

    Bitmap bIcicle;
    Bitmap icicle;
    Bitmap icicle2;
    Bitmap icicle4;

    Bitmap bPillar;
    Bitmap bPillar2;
    Bitmap pillar;
    Bitmap pillar2;
    Bitmap bFish;
    Bitmap fish;
    Bitmap bBackgroundSnow;
    Bitmap backgroundSnow;
    Bitmap bBackgroundWater;
    Bitmap backgroundWater;

    Bitmap platformSnow;
    Bitmap platformSnow2;
    Bitmap platformSnow3;
    Bitmap platformSnow4;
    Bitmap platformSnow5;
    Bitmap platformSnow6;
    Bitmap platformSnow7;
    Bitmap platformSnow8;
    Bitmap bPlatformSnow;

    Bitmap bTurtle;
    Bitmap turtle;

    public dashView(Context context, AttributeSet attrs) {
        super(context, attrs);
        activity = (Activity) context;
        getHolder().addCallback(this);


        for (int i = 0; i <= 4; i++) {
            lev[i] = new levels();
        }

        for (int i = 0; i <= 9; i++) {
            b[i] = new button();
        }

        for (int i = 0; i < 200; i++) {
            o1[i] = new Obstacle();
            o2[i] = new Obstacle();
        }



        for (int i = 0; i < 50; i++) {
            p1[i] = new Platform();
            p2[i] = new Platform();
        }



        backgroundPaint = new Paint();
        playerPaint = new Paint();
        obstaclePaint = new Paint();
        platformPaint = new Paint();
        textPaint = new Paint();
        groundPaint = new Paint();
        fadePaint = new Paint();

        menuBackGroundPaint = new Paint();
        menuTitlePaint = new Paint();
        buttonPaint2 = new Paint();
        buttonPaint[0] = new Paint();
        buttonPaint[1] = new Paint();
        buttonPaint[2] = new Paint();

        for (int i = 0; i <= 4; i++) {
            levelPaint[i] = new Paint();
        }

        backgroundPaint.setColor(Color.rgb(150, 150, 150));
        textPaint.setTextSize(50);
        textPaint.setColor(Color.rgb(20, 200, 200));
        groundPaint.setColor(Color.rgb(139, 69, 19));
        buttonPaint[0].setColor(Color.rgb(51, 101, 138));
        buttonPaint[1].setColor(Color.rgb(51, 101, 138));
        buttonPaint[2].setColor(Color.BLACK);
        buttonPaint2.setColor(Color.rgb(243, 233, 97));
        fadePaint.setColor(Color.rgb(180, 180, 180));

        levelPaint[0].setColor(Color.RED);
        levelPaint[1].setColor(Color.rgb(243, 233, 97));
        levelPaint[2].setColor(Color.BLUE);
        levelPaint[3].setColor(Color.GREEN);
        levelPaint[4].setColor(Color.BLACK);
    }

    public void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        screenWidth = w;
        screenHeight = h;

        setMenuValues();
        setGameValues();
        player.saveValues();

    }

    public void prepareMenu() {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(getResources(), R.drawable.background2, options); //create options to find the dimensions of the image

        options.inSampleSize = calculateInSampleSize(options, w, h); //set the sample size of the options so that the image can be scaled down
        options.inJustDecodeBounds = false;


        BitmapFactory.Options options2 = new BitmapFactory.Options();
        options2.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(getResources(), R.drawable.locked, options2); //create options to find the dimensions of the image

        options2.inSampleSize = calculateInSampleSize(options2, (int)lev[1].width, (int) lev[1].height); //set the sample size of the options so that the image can be scaled down
        options2.inJustDecodeBounds = false;


        bBack = BitmapFactory.decodeResource(getResources(), R.drawable.back);
        bHighscores = BitmapFactory.decodeResource(getResources(), R.drawable.info);
        bLevel2 = BitmapFactory.decodeResource(getResources(), R.drawable.levelmode2);
        bEndless2 = BitmapFactory.decodeResource(getResources(), R.drawable.endlessmode2);
        bMenubackground = BitmapFactory.decodeResource(getResources(), R.drawable.menuscreen, options);
        bCredits = BitmapFactory.decodeResource(getResources(), R.drawable.credits, options);


        highscoresIcon = Bitmap.createScaledBitmap(bHighscores, (w / 15), (w / 15), true);
        levelModeIMG2 = Bitmap.createScaledBitmap(bLevel2, (int) (w / 4.1), (w / 18), true);
        endlessModeIMG2 = Bitmap.createScaledBitmap(bEndless2, (int) (w / 4.1), (w / 18), true);
        backWord = Bitmap.createScaledBitmap(bBack, (w / 11), (h / 12), true);
        menuBackground = Bitmap.createScaledBitmap(bMenubackground, w, h, true);
        creditScreen = Bitmap.createScaledBitmap(bCredits,  w, h, true);

    }

    public void loadLevelSelect(){
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(getResources(), R.drawable.background2, options); //create options to find the dimensions of the image

        options.inSampleSize = calculateInSampleSize(options, w, h); //set the sample size of the options so that the image can be scaled down
        options.inJustDecodeBounds = false;


        BitmapFactory.Options options2 = new BitmapFactory.Options();
        options2.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(getResources(), R.drawable.locked, options2); //create options to find the dimensions of the image

        options2.inSampleSize = calculateInSampleSize(options2, (int)lev[1].width, (int) lev[1].height); //set the sample size of the options so that the image can be scaled down
        options2.inJustDecodeBounds = false;

        bBack = BitmapFactory.decodeResource(getResources(), R.drawable.back);
        bLevelselectb = BitmapFactory.decodeResource(getResources(), R.drawable.level_select, options);
        bIceAgeImage = BitmapFactory.decodeResource(getResources(), R.drawable.select_ice, options2);
        bPrehistoricImage = BitmapFactory.decodeResource(getResources(), R.drawable.select_pre, options2);
        bComingSoon = BitmapFactory.decodeResource(getResources(), R.drawable.locked, options2);


        levelSelectBackground = Bitmap.createScaledBitmap(bLevelselectb, w, h, true);
        prehistoricImage = Bitmap.createScaledBitmap(bPrehistoricImage,  (int)lev[1].width, (int) lev[1].height, true);
        iceAgeImage = Bitmap.createScaledBitmap(bIceAgeImage,  (int)lev[1].width, (int) lev[1].height, true);
        comingSoon = Bitmap.createScaledBitmap(bComingSoon,  (int)lev[1].width, (int) lev[1].height, true);
        backWord = Bitmap.createScaledBitmap(bBack, (w / 11), (h / 12), true);
    }

    public void cleanMenu() {
        bBack = null;
        bHighscores = null;
        bLevel2 = null;
        bEndless2 = null;
        bMenubackground = null;
        highscoresIcon = null;
        levelModeIMG2 = null;
        endlessModeIMG2 = null;
        backWord = null;
        menuBackground = null;
        bCredits = null;
        creditScreen = null;
    }


    public void cleanLevelSelect(){
        levelSelectBackground = null;
        bLevelselectb = null;
        iceAgeImage = null;
        bIceAgeImage = null;
        comingSoon = null;
        bComingSoon = null;
        bPrehistoricImage = null;
        prehistoricImage = null;
        backWord = null;
        bBack = null;

    }

    public void cleanGameOver(){
        returntoMenuImage = null;
        bReturtoMenuImage = null;
        replayImage = null;
        bReplayImage = null;
        bLevelComplete = null;
        bGameOver = null;
        levelCompleteImage = null;
        gameOverImage = null;
    }

    public void loadGameOver(){
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(getResources(), R.drawable.background2, options); //create options to find the dimensions of the image

        options.inSampleSize = calculateInSampleSize(options, w, h); //set the sample size of the options so that the image can be scaled down
        options.inJustDecodeBounds = false;


        bGameOver = BitmapFactory.decodeResource(getResources(), R.drawable.game_over_screen, options);
        bLevelComplete = BitmapFactory.decodeResource(getResources(), R.drawable.level_complete, options);
        bReplayImage = BitmapFactory.decodeResource(getResources(), R.drawable.replay);
        bReturtoMenuImage = BitmapFactory.decodeResource(getResources(), R.drawable.menu);
        gameOverImage = Bitmap.createScaledBitmap(bGameOver, w, h, true);
        levelCompleteImage = Bitmap.createScaledBitmap(bLevelComplete, w, h, true);
        replayImage = Bitmap.createScaledBitmap(bReplayImage, h/10, h/10, true);
        returntoMenuImage = Bitmap.createScaledBitmap(bReturtoMenuImage, h/10, h/10, true);

    }

    public void setMenuValues() {
        w = screenWidth;
        h = screenHeight;

        b[0].width = w / 3.7f;
        b[0].height = h / 5.7f;
        b[0].x = w / 2 - b[0].width / 2;
        b[0].y = h / 3;

        b[1].width = w / 3.7f;
        b[1].height = h / 5.7f;
        b[1].x = w / 2 - b[1].width / 2;
        b[1].y = h / 1.8f;

        b[2].width = w / 9;
        b[2].height = h / 10;
        b[2].x = w / 30;
        b[2].y = h / 20;

        b[3].width = w / 8;
        b[3].height = h / 8;
        b[3].x = w / 3;
        b[3].y = h / 1.23f;

        b[4].width = w / 8;
        b[4].height = h / 8;
        b[4].x = w / 1.666f;
        b[4].y = h / 1.23f;

        //level select setup
        lev[0].height = h / 1.8f;
        lev[0].width = 3 * w / 5;
        lev[0].x = w / 5;
        lev[0].y = h / 4;

        lev[1].height = h / 1.8f;
        lev[1].width = 3 * w / 5;
        lev[1].x = w / 5;
        lev[1].y = h / 4;

        lev[2].height = h / 1.8f;
        lev[2].width = 3 * w / 5;
        lev[2].x = w / 5;
        lev[2].y = h / 4;

        lev[3].height = h / 1.8f;
        lev[3].width = 3 * w / 5;
        lev[3].x = w / 5;
        lev[3].y = h / 4;

        lev[4].height = h / 1.8f;
        lev[4].width = 3 * w / 5;
        lev[4].x = w / 5;
        lev[4].y = h / 4;
    }


    //Start up setup
    public void setGameValues() {
        //Width = 1810, Height = 1017
        player = new mainCharacter((int) (w / 2.6f), (int) (h / 1.15f), h / 8, h / 8, this.getContext());
        player.x = w / 2.6f;
        player.y = h / 1.15f;
        player.gravity = h / 500;


        //LEVEL 1 OBSTACLE/PLATFORM SETUP
        //--------------------------------


        for (int i = 0; i <= 16; i++) {
            p1[i].height = h / 8;
        }


        p1[0].height = h / 12;
        p1[0].width = 0.2f * w;
        p1[0].y = 0.91075f * h - p1[0].height;
        p1[0].x = 1.5f * w;

        p1[1].height = h / 12;
        p1[1].width = 0.25f * w;
        p1[1].y = 0.8f * h - p1[0].height;
        p1[1].x = 1.78f * w;

        p1[2].height = h / 12;
        p1[2].width = 0.25f * w;
        p1[2].y = 0.7f * h - p1[0].height;
        p1[2].x = 2.15f * w;

        p1[3].height = h / 12;
        p1[3].width = 0.2f * w;
        p1[3].y = 0.91075f * h - p1[0].height - h / 15;
        p1[3].x = 3.35f * w;

        p1[4].height = h / 12;
        p1[4].width = 0.2f * w;
        p1[4].y = 0.87f * h - (p1[4].height * 1.5f);
        p1[4].x = 4.5f * w;

        p1[5].height = h / 12;
        p1[5].width = 0.45f * w;
        p1[5].y = 0.73f * h - (p1[4].height * 1.5f);
        p1[5].x = 4.7f * w;

        p1[6].height = h / 12;
        p1[6].width = 0.8f * w;
        p1[6].y = 0.59f * h - (p1[4].height * 1.5f);
        p1[6].x = 5.2f * w;

        p1[7].height = h / 12;
        p1[7].width = 0.06f * w;
        p1[7].y = 0.59f * h - (p1[4].height * 1.5f);
        p1[7].x = 6.13f * w;

        p1[8].height = h / 12;
        p1[8].width = 0.32f * w;
        p1[8].y = 0.59f * h - (p1[4].height * 1.5f);
        p1[8].x = 6.3f * w;

        p1[9].height = h / 12;
        p1[9].width = 0.06f * w;
        p1[9].y = 0.59f * h - (p1[4].height * 1.5f);
        p1[9].x = 6.73f * w;

        p1[10].height = h / 12;
        p1[10].width = 0.32f * w;
        p1[10].y = 0.59f * h - (p1[4].height * 1.5f);
        p1[10].x = 6.9f * w;

        p1[11].height = h / 12;
        p1[11].width = 0.1f * w;
        p1[11].y = 0.7f * h - h / 12;
        p1[11].x = 7.29f * w;

        p1[12].height = h / 12;
        p1[12].width = 0.06f * w;
        p1[12].y = 0.55f * h - h / 12;
        p1[12].x = 7.45f * w;

        p1[13].height = h / 12;
        p1[13].width = 0.06f * w;
        p1[13].y = 0.7f * h - h / 12;
        p1[13].x = 7.59f * w;

        p1[14].height = h / 12;
        p1[14].width = 0.32f * w;
        p1[14].y = 0.7f * h - h / 12;
        p1[14].x = 7.8f * w;

        p1[15].height = h / 12;
        p1[15].width = 0.1f * w;
        p1[15].y = 0.91075f * h - p1[15].height;
        p1[15].x = 9.11f * w;

        o1[127].height = screenHeight - screenHeight / 1.15f;
        o1[127].width = w+w/2;
        o1[127].y = h - o1[127].height;
        o1[127].x = p1[15].x + p1[15].width;

        p1[16].height = h / 12;
        p1[16].width = 0.06f * w;
        p1[16].y = 0.794f * h - p1[16].height;
        p1[16].x = 9.33f * w;


        p1[17].height = h / 12;
        p1[17].width = 0.06f * w;
        p1[17].y = 0.644f * h - p1[17].height;
        p1[17].x = 9.51f * w;

        p1[18].height = h / 12;
        p1[18].width = 0.06f * w;
        p1[18].y = 0.494f * h - p1[18].height;
        p1[18].x = 9.69f * w;

        p1[19].height = h / 12;
        p1[19].width = 0.06f * w;
        p1[19].y = 0.594f * h - p1[19].height;
        p1[19].x = 9.83f * w;

        p1[20].height = h / 12;
        p1[20].width = 0.06f * w;
        p1[20].y = 0.444f * h - p1[20].height;
        p1[20].x = 10.01f * w;

        p1[21].height = h / 12;
        p1[21].width = 0.06f * w;
        p1[21].y = 0.294f * h - p1[21].height;
        p1[21].x = 10.19f * w;

        p1[22].height = h / 12;
        p1[22].width = 0.06f * w;
        p1[22].y = 0.394f * h - p1[22].height;
        p1[22].x = 10.33f * w;

        p1[23].height = h / 12;
        p1[23].width = 0.06f * w;
        p1[23].y = 0.594f * h - p1[23].height;
        p1[23].x = 10.55f * w;

        p1[24].height = h / 8;
        p1[24].width = 0.58f * w;
        p1[24].y = 0.935f * h - p1[24].height;
        p1[24].x = 10.81f * w;

        p1[25].height = h / 8;
        p1[25].width = 0.04f * w;
        p1[25].y = 0.935f * h - p1[25].height;
        p1[25].x = 11.48f * w;

        p1[26].height = h / 8;
        p1[26].width = 0.04f * w;
        p1[26].y = 0.935f * h - p1[25].height;
        p1[26].x = 11.68f * w;

        p1[27].height = h / 12;
        p1[27].width = 0.04f * w;
        p1[27].y = 0.724f * h - p1[27].height;
        p1[27].x = 11.85f * w;

        p1[28].height = h / 12;
        p1[28].width = 0.04f * w;
        p1[28].y = 0.574f * h - p1[28].height;
        p1[28].x = 12.02f * w;

        p1[29].height = h / 12;
        p1[29].width = 0.04f * w;
        p1[29].y = 0.424f * h - p1[29].height;
        p1[29].x = 12.19f * w;

        p1[30].height = h / 12;
        p1[30].width = 0.04f * w;
        p1[30].y = 0.274f * h - p1[30].height;
        p1[30].x = 12.36f * w;

        pterodactyl.x = w * 12.48f;
        pterodactyl.width = w / 7;
        pterodactyl.height = h / 8;
        pterodactyl.oldX = pterodactyl.x;


        for (int i = 0; i <= 30; i++) {
            p1[i].oldX = p1[i].x;
        }


        o1[0].height = h / 15;
        o1[0].width = w / 16;
        o1[0].y = 0.91075f * h - o1[0].height;
        o1[0].x = 1.0f * w;

        o1[1].height = h / 15;
        o1[1].width = w / 16;
        o1[1].y = 0.91075f * h - o1[0].height;
        o1[1].x = 1.2f * w;

        o1[2].height = h / 15;
        o1[2].width = w / 16;
        o1[2].y = 0.8f * h - o1[0].height - h / 12;
        o1[2].x = 1.88f * w;

        o1[3].height = h / 15;
        o1[3].width = w / 13;
        o1[3].y = 0.7f * h - o1[0].height - h / 12;
        o1[3].x = 2.27f * w;

        o1[4].height = h / 15;
        o1[4].width = w / 13;
        o1[4].y = 0.91075f * h - o1[0].height;
        o1[4].x = 2.33f * w;

        o1[5].height = h / 15;
        o1[5].width = w / 13;
        o1[5].y = 0.91075f * h - o1[0].height;
        o1[5].x = 2.41f * w;

        o1[6].height = h / 15;
        o1[6].width = w / 14;
        o1[6].y = 0.91075f * h - o1[0].height;
        o1[6].x = 2.7f * w;

        o1[7].height = h / 15;
        o1[7].width = w / 14;
        o1[7].y = 0.91075f * h - o1[0].height;
        o1[7].x = 2.92f * w;

        o1[9].height = h / 15;
        o1[9].width = w / 11;
        o1[9].y = 0.91075f * h - o1[0].height;
        o1[9].x = 3.35f * w;

        o1[10].height = h / 15;
        o1[10].width = w / 11;
        o1[10].y = 0.91075f * h - o1[0].height;
        o1[10].x = 3.46f * w;

        o1[11].height = h / 7;
        o1[11].width = w / 6;
        o1[11].y = 0.83f * h - o1[11].height;
        o1[11].x = 3.8f * w;

        o1[13].height = h / 15;
        o1[13].width = w / 11;
        o1[13].y = 0.91075f * h - o1[0].height;
        o1[13].x = 4.2f * w;

        o1[14].height = h / 15;
        o1[14].width = w / 14;
        o1[14].y = 0.73f * h - (p1[4].height * 1.5f) - h / 15;
        o1[14].x = 4.77f * w;


        o1[16].height = h / 15;
        o1[16].width = w / 14;
        o1[16].y = 0.59f * h - (p1[4].height * 1.5f) - h / 15;
        o1[16].x = 5.3f * w;

        o1[17].height = h / 7;
        o1[17].width = w / 6;
        o1[17].y = 0.52f * h - o1[17].height - (p1[4].height * 1.5f);
        o1[17].x = 5.5f * w;

        o1[18].height = h / 15;
        o1[18].width = w / 13;
        o1[18].y = 0.59f * h - (p1[4].height * 1.5f) - h / 15;
        o1[18].x = 5.8f * w;

        o1[19].height = h / 15;
        o1[19].width = w / 13;
        o1[19].y = 0.59f * h - (p1[4].height * 1.5f) - h / 15;
        o1[19].x = 6.42f * w;

        o1[20].height = h / 15;
        o1[20].width = w / 16;
        o1[20].y = 0.59f * h - h / 12 - h / 15 - h / 24;
        o1[20].x = 7.14f * w;

        o1[21].height = h / 15;
        o1[21].width = w / 13;
        o1[21].y = 0.7f * h - h / 12 - h / 15;
        o1[21].x = 7.92f * w;

        o1[23].height = h / 15;
        o1[23].width = w / 13;
        o1[23].y = 0.91075f * h - h / 15;
        o1[23].x = 4.58f * w;

        o1[24].height = h / 15;
        o1[24].width = w / 13;
        o1[24].y = 0.91075f * h - h / 15;
        o1[24].x = 4.68f * w;

        o1[25].height = h / 15;
        o1[25].width = w / 13;
        o1[25].y = 0.91075f * h - h / 15;
        o1[25].x = 4.78f * w;

        o1[26].height = h / 15;
        o1[26].width = w / 13;
        o1[26].y = 0.91075f * h - h / 15;
        o1[26].x = 4.88f * w;

        o1[27].height = h / 15;
        o1[27].width = w / 13;
        o1[27].y = 0.91075f * h - h / 15;
        o1[27].x = 4.98f * w;

        o1[28].height = h / 15;
        o1[28].width = w / 13;
        o1[28].y = 0.91075f * h - h / 15;
        o1[28].x = 5.08f * w;

        o1[29].height = h / 15;
        o1[29].width = w / 13;
        o1[29].y = 0.91075f * h - h / 15;
        o1[29].x = 5.18f * w;

        o1[30].height = h / 15;
        o1[30].width = w / 13;
        o1[30].y = 0.91075f * h - h / 15;
        o1[30].x = 5.28f * w;

        o1[31].height = h / 15;
        o1[31].width = w / 13;
        o1[31].y = 0.91075f * h - h / 15;
        o1[31].x = 5.38f * w;

        o1[32].height = h / 15;
        o1[32].width = w / 13;
        o1[32].y = 0.91075f * h - h / 15;
        o1[32].x = 5.48f * w;

        o1[33].height = h / 15;
        o1[33].width = w / 13;
        o1[33].y = 0.91075f * h - h / 15;
        o1[33].x = 5.58f * w;

        o1[34].height = h / 15;
        o1[34].width = w / 13;
        o1[34].y = 0.91075f * h - h / 15;
        o1[34].x = 5.68f * w;

        o1[35].height = h / 15;
        o1[35].width = w / 13;
        o1[35].y = 0.91075f * h - h / 15;
        o1[35].x = 5.78f * w;

        o1[36].height = h / 15;
        o1[36].width = w / 13;
        o1[36].y = 0.91075f * h - h / 15;
        o1[36].x = 5.88f * w;

        o1[37].height = h / 15;
        o1[37].width = w / 13;
        o1[37].y = 0.91075f * h - h / 15;
        o1[37].x = 5.98f * w;

        o1[38].height = h / 15;
        o1[38].width = w / 13;
        o1[38].y = 0.91075f * h - h / 15;
        o1[38].x = 6.08f * w;

        o1[39].height = h / 15;
        o1[39].width = w / 13;
        o1[39].y = 0.91075f * h - h / 15;
        o1[39].x = 6.18f * w;

        o1[40].height = h / 15;
        o1[40].width = w / 13;
        o1[40].y = 0.91075f * h - h / 15;
        o1[40].x = 6.28f * w;

        o1[41].height = h / 15;
        o1[41].width = w / 13;
        o1[41].y = 0.91075f * h - h / 15;
        o1[41].x = 6.38f * w;

        o1[42].height = h / 15;
        o1[42].width = w / 13;
        o1[42].y = 0.91075f * h - h / 15;
        o1[42].x = 6.48f * w;

        o1[43].height = h / 15;
        o1[43].width = w / 13;
        o1[43].y = 0.91075f * h - h / 15;
        o1[43].x = 6.58f * w;

        o1[44].height = h / 15;
        o1[44].width = w / 13;
        o1[44].y = 0.91075f * h - h / 15;
        o1[44].x = 6.68f * w;

        o1[45].height = h / 15;
        o1[45].width = w / 13;
        o1[45].y = 0.91075f * h - h / 15;
        o1[45].x = 6.78f * w;

        o1[46].height = h / 15;
        o1[46].width = w / 13;
        o1[46].y = 0.91075f * h - h / 15;
        o1[46].x = 6.88f * w;

        o1[47].height = h / 15;
        o1[47].width = w / 13;
        o1[47].y = 0.91075f * h - h / 15;
        o1[47].x = 6.98f * w;

        o1[48].height = h / 15;
        o1[48].width = w / 13;
        o1[48].y = 0.91075f * h - h / 15;
        o1[48].x = 7.08f * w;

        o1[49].height = h / 15;
        o1[49].width = w / 13;
        o1[49].y = 0.91075f * h - h / 15;
        o1[49].x = 7.18f * w;

        o1[50].height = h / 15;
        o1[50].width = w / 13;
        o1[50].y = 0.91075f * h - h / 15;
        o1[50].x = 7.28f * w;

        o1[51].height = h / 15;
        o1[51].width = w / 13;
        o1[51].y = 0.91075f * h - h / 15;
        o1[51].x = 7.38f * w;

        o1[52].height = h / 15;
        o1[52].width = w / 13;
        o1[52].y = 0.91075f * h - h / 15;
        o1[52].x = 7.48f * w;

        o1[53].height = h / 15;
        o1[53].width = w / 13;
        o1[53].y = 0.91075f * h - h / 15;
        o1[53].x = 7.58f * w;

        o1[54].height = h / 15;
        o1[54].width = w / 13;
        o1[54].y = 0.91075f * h - h / 15;
        o1[54].x = 7.68f * w;

        o1[55].height = h / 15;
        o1[55].width = w / 13;
        o1[55].y = 0.91075f * h - h / 15;
        o1[55].x = 7.78f * w;

        o1[56].height = h / 15;
        o1[56].width = w / 13;
        o1[56].y = 0.91075f * h - h / 15;
        o1[56].x = 7.88f * w;

        o1[57].height = h / 15;
        o1[57].width = w / 13;
        o1[57].y = 0.91075f * h - h / 15;
        o1[57].x = 7.98f * w;

        o1[58].height = h / 15;
        o1[58].width = w / 13;
        o1[58].y = 0.91075f * h - h / 15;
        o1[58].x = 8.08f * w;

        o1[59].height = h / 15;
        o1[59].width = w / 13;
        o1[59].y = 0.91075f * h - h / 15;
        o1[59].x = 8.18f * w;


        o1[61].height = h / 15;
        o1[61].width = w / 11;
        o1[61].y = 0.91075f * h - h / 15;
        o1[61].x = 8.6f * w;

        o1[62].height = h / 15;
        o1[62].width = w / 13;
        o1[62].y = 0.91075f * h - h / 15;
        o1[62].x = 9.0f * w;

        o1[63].height = h / 15;
        o1[63].width = w / 11;
        o1[63].y = 0.935f * h - h / 15 - h / 8;
        o1[63].x = 10.98f * w;


        for (int i = 67; i <= 83; i++) {
            o1[i].width = w / 15;
            o1[i].height = w / 15;
        }
        //magma balls
        o1[67].x = w * 2f + w * n;
        o1[67].y = h / 2;
        o1[68].x = w * 2.7f + w * n;
        o1[68].y = h / 4;
        o1[70].x = w * 2.9f + w * n;
        o1[70].y = h / 1.5f;
        o1[71].x = w * 3.3f + w * n;
        o1[71].y = h / 2.5f;
        o1[72].x = w * 4.2f + w * n;
        o1[72].y = h / 1.7f;
        o1[73].x = w * 4.4f + w * n;
        o1[73].y = h / 4;
        o1[74].x = w * 5.4f + w * n;
        o1[74].y = h / 3f;
        o1[75].x = w * 5.8f + w * n;
        o1[75].y = h / 2;
        o1[76].x = w * 6.0f + w * n;
        o1[76].y = h / 9;
        o1[77].x = w * 7 + w * n;
        o1[77].y = h / 1.4f;
        o1[78].x = w * 7.3f + w * n;
        o1[78].y = h / 1.8f;
        o1[79].x = w * 8f + w * n;
        o1[79].y = h / 3.7f;
        o1[80].x = w * +w * n;
        o1[80].y = h / 1.4f;
        o1[81].x = w * 8.5f + w * n;
        o1[81].y = h / 5.5f;
        o1[82].x = w * 7.6f + w * n;
        o1[82].y = h / 15f;
        o1[83].x = w * 8.3f + w * n;
        o1[83].y = h / 2;
        o1[83].x = w * 7.5f + w * n;
        o1[83].y = h / 3.2f;

        for (int i = 83; i <= 126; i++) {
            o1[i].height = h / 1.5f;
            o1[i].width = w / 3.5f;
        }


        //top stalactites
        o1[84].x = w * 10.1f + w * n;
        o1[84].y = h / -3;
        o1[85].x = w * 10.5f + w * n;
        o1[85].y = h / -2f;
        o1[86].x = w * 10.8f + w * n;
        o1[86].y = h / -3f;
        o1[87].x = w * 11.7f + w * n;
        o1[87].y = -h / 3;
        o1[88].x = w * 12.2f + w * n;
        o1[88].y = h / -2;
        o1[89].x = w * 12.8f + w * n;
        o1[89].y = -h / 3;
        o1[90].x = w * 13.4f + w * n;
        o1[90].y = -h / 3;
        o1[91].x = w * 14f + w * n;
        o1[91].y = -h / 3;
        o1[92].x = w * 15f + w * n;
        o1[92].y = h / -3;
        o1[93].x = w * 15.6f + w * n;
        o1[93].y = h / -4;
        o1[94].x = w * 16.2f + w * n;
        o1[94].y = h / -3;
        o1[95].x = w * 16.5f + w * n;
        o1[95].y = h / -3;
        o1[96].x = w * 17.3f + w * n;
        o1[96].y = h / -3;
        o1[97].x = w * 18.1f + w * n;
        o1[97].y = h / -3;
        o1[98].x = w * 18.9f + w * n;
        o1[98].y = h / -3;
        o1[99].x = w * 20f + w * n;
        o1[99].y = h / -3;
        o1[100].x = w * 20.5f + w * n;
        o1[100].y = h / -2.5f;
        o1[101].x = w * 21.0f + w * n;
        o1[101].y = h / -2.5f;
        o1[102].x = w * 21.4f + w * n;
        o1[102].y = h / -2;
        o1[103].x = w * 21.8f + w * n;
        o1[103].y = h / -3;
        o1[104].x = w * 22.1f + w * n;
        o1[104].y = h / -1.8f;
        o1[105].x = w * 22.4f + w * n;
        o1[105].y = h / -4;
        o1[106].x = w * 22.5f + w * n;
        o1[106].y = h / -1.8f;

        //bottom stalactites
        o1[107].x = w * 10.4f + w * n;
        o1[107].y = h / 1.8f;
        o1[108].x = w * 11.2f + w * n;
        o1[108].y = h / 2f;
        o1[109].x = w * 12f + w * n;
        o1[109].y = h / 2;
        o1[110].x = w * 12.2f + w * n;
        o1[110].y = h / 2;
        o1[111].x = w * 13.1f + w * n;
        o1[111].y = h / 1.6f;
        o1[112].x = w * 13.7f + w * n;
        o1[112].y = h / 1.9f;
        o1[113].x = w * 14.3f + w * n;
        o1[113].y = h / 1.9f;
        o1[114].x = w * 14.7f + w * n;
        o1[114].y = h / 2.2f;
        o1[115].x = w * 15.3f + w * n;
        o1[115].y = h / 2.2f;
        o1[116].x = w * 15.9f + w * n;
        o1[116].y = h / 2.2f;
        o1[117].x = w * 16.9f + w * n;
        o1[117].y = h / 2.2f;
        o1[118].x = w * 17.7f + w * n;
        o1[118].y = h / 2.2f;
        o1[119].x = w * 18.5f + w * n;
        o1[119].y = h / 2.2f;
        o1[120].x = w * 19.3f + w * n;
        o1[120].y = h / 2.2f;
        o1[121].x = w * 20f + w * n;
        o1[121].y = h / 1.3f;
        o1[122].x = w * 20.5f + w * n;
        o1[122].y = h / 1.4f;
        o1[123].x = w * 21.0f + w * n;
        o1[123].y = h / 1.2f;
        o1[124].x = w * 21.4f + w * n;
        o1[124].y = h / 1.8f;
        o1[125].x = w * 21.8f + w * n;
        o1[125].y = h / 1.34f;
        o1[126].x = w * 22.1f + w * n;
        o1[126].y = h / 1.8f;

        portal1.x = w * 22.6f + w * n;
        portal1.y = h / 4;
        portal1.height = h / 2;
        portal1.width = w / 5;

        portal1.oldX = portal1.x;


        for (int i = 0; i <= 127; i++) {
            o1[i].oldX = o1[i].x;
        }
        //Level 2 -----------------------------------------------------------------------------------------------

        p2[0].height = h / 12;
        p2[0].width = h / 2.5f;
        p2[0].y = h * 0.92f - p2[0].height;
        p2[0].x = 2.2f * w;

        p2[1].height = h / 12;
        p2[1].width = h / 2.5f;
        p2[1].y = h * 0.92f - p2[1].height;
        p2[1].x = 5.25f * w;

        p2[2].height = h / 12;
        p2[2].width = h / 5;
        p2[2].y = h / 1.2f - p2[1].height;
        p2[2].x = 5.55f * w;

        p2[3].height = h / 12;
        p2[3].width = h / 2.5f;
        p2[3].y = h / 1.3f - p2[1].height;
        p2[3].x = 5.75f * w;

        p2[4].height = h / 12;
        p2[4].width = h / 10f;
        p2[4].y = h / 1.2f - p2[1].height;
        p2[4].x = 6.02f * w;

        p2[5].height = h / 12;
        p2[5].width = h / 2.5f;
        p2[5].y = h / 1.3f - p2[1].height;
        p2[5].x = 6.15f * w;

        p2[6].height = h / 12;
        p2[6].width = h / 5f;
        p2[6].y = h / 1.2f - p2[1].height;
        p2[6].x = 6.55f * w;


        p2[7].height = h / 12;
        p2[7].width = h / 10f;
        p2[7].y = h / 1.34f - p2[1].height;
        p2[7].x = 6.79f * w;

        p2[8].height = h / 12;
        p2[8].width = h / 1.5f;
        p2[8].y = h * 0.92f - p2[8].height;
        p2[8].x = 7.98f * w;

        p2[9].height = h / 12;
        p2[9].width = 1.5f * h;
        p2[9].y = h * 0.92f - p2[8].height;
        p2[9].x = 8.85f * w;

        p2[10].height = h / 15;
        p2[10].width = h / 10;
        p2[10].y = h / 1.35f - p2[10].height;
        p2[10].x = 9.7f * w;

        p2[11].height = h / 15;
        p2[11].width = h / 10;
        p2[11].y = h / 1.6f - p2[10].height;
        p2[11].x = 9.88f * w;

        p2[12].height = h / 15;
        p2[12].width = h / 10;
        p2[12].y = h / 1.85f - p2[10].height;
        p2[12].x = 10.05f * w;

        p2[13].height = h / 15;
        p2[13].width = h / 10;
        p2[13].y = h / 1.35f - p2[10].height;
        p2[13].x = 10.2f * w;

        p2[14].height = h / 15;
        p2[14].width = h / 10;
        p2[14].y = h / 1.62f - p2[10].height;
        p2[14].x = 10.37f * w;

        p2[15].height = h / 15;
        p2[15].width = h / 10;
        p2[15].y = h / 1.8f - p2[10].height;
        p2[15].x = 10.57f * w;


        p2[17].height = h / 15;
        p2[17].width = h / 10;
        p2[17].y = h / 1.5f - p2[10].height;
        p2[17].x = 10.82f * w;

        p2[18].height = h / 15;
        p2[18].width = h / 10;
        p2[18].y = h / 1.85f - p2[10].height;
        p2[18].x = 10.97f * w;

        p2[19].height = h / 15;
        p2[19].width = h / 10;
        p2[19].y = h / 2.1f - p2[10].height;
        p2[19].x = 11.16f * w;

        p2[20].height = h / 15;
        p2[20].width = h / 1.8f;
        p2[20].y = h / 2.1f - p2[10].height;
        p2[20].x = 11.32f * w;

        p2[21].height = h / 15;
        p2[21].width = 1.1f * h;
        p2[21].y = h / 1.5f - p2[20].height;
        p2[21].x = 11.7f * w;

        o2[0].height = h / 12;
        o2[0].width = h / 12;
        o2[0].y = h * 0.92f - o2[0].height;
        o2[0].x = w;

        o2[1].height = h / 12;
        o2[1].width = h / 12;
        o2[1].y = h * 0.92f - o2[0].height;
        o2[1].x = w + w / 4;

        o2[2].height = h / 12;
        o2[2].width = h / 12;
        o2[2].y = h * 0.92f - o2[0].height;
        o2[2].x = w + w / 2;

        o2[3].height = h / 10;
        o2[3].width = h / 10;
        o2[3].y = h / 1.21f - o2[3].height;
        o2[3].x = 7 * w / 4;

        o2[4].height = h / 10;
        o2[4].width = h / 10;
        o2[4].y = h / 1.34f - o2[3].height;
        o2[4].x = 2.35f * w;

        o2[5].height = h / 12;
        o2[5].width = h / 12;
        o2[5].y = h * 0.92f - o2[0].height;
        o2[5].x = 2.7f * w;

        o2[6].height = h / 10;
        o2[6].width = h / 10;
        o2[6].y = h / 1.21f - o2[6].height;
        o2[6].x = 3 * w;

        o2[7].height = h / 12;
        o2[7].width = h / 12;
        o2[7].y = h * 0.92f - o2[7].height;
        o2[7].x = 3.25f * w;


        o2[9].height = h / 10;
        o2[9].width = h / 10;
        o2[9].y = h / 1.21f - o2[6].height;
        o2[9].x = 3.6f * w;

        o2[10].height = h / 12;
        o2[10].width = h / 12;
        o2[10].y = h * 0.92f - o2[7].height;
        o2[10].x = 3.9f * w;

        o2[11].height = h / 12;
        o2[11].width = h / 12;
        o2[11].y = h * 0.92f - o2[7].height;
        o2[11].x = 4.15f * w;

        o2[12].height = h / 12;
        o2[12].width = h / 12;
        o2[12].y = h * 0.92f - o2[7].height;
        o2[12].x = 4.4f * w;

        o2[13].height = h / 12;
        o2[13].width = h / 12;
        o2[13].y = h * 0.92f - o2[7].height;
        o2[13].x = 4.65f * w;

        o2[18].height = h / 10;
        o2[18].width = h / 10;
        o2[18].y = h / 1.2f - o2[3].height;
        o2[18].x = 4.9f * w;

        o2[20].height = h / 12;
        o2[20].width = h / 12;
        o2[20].y = h / 1.3f - p2[1].height - o2[20].height;
        o2[20].x = 6.25f * w;

        o2[21].height = h / 20;
        o2[21].width = h / 20;
        o2[21].y = h * 0.92f - o2[21].height;
        o2[21].x = 6.45f * w;

        o2[22].height = h / 10;
        o2[22].width = h / 10;
        o2[22].y = h / 1.34f;
        o2[22].x = 6.79f * w;

        o2[23].height = h / 20;
        o2[23].width = h / 15;
        o2[23].y = h * 0.92f - o2[23].height;
        o2[23].x = 7.12f * w;

        o2[25].height = h / 10;
        o2[25].width = h / 6;
        o2[25].y = h / 1.21f - o2[25].height;
        o2[25].x = 7.65f * w;


        o2[26].height = h / 10;
        o2[26].width = h / 6;
        o2[26].y = h / 1.34f - o2[26].height;
        o2[26].x = 8.2f * w;


        o2[29].height = h / 10;
        o2[29].width = h / 6;
        o2[29].y = h / 1.42f - o2[26].height;
        o2[29].x = 8.5f * w;


        o2[32].height = h / 20;
        o2[32].width = h / 15;
        o2[32].y = h * 0.92f - h / 12 - o2[32].height;
        o2[32].x = 8.95f * w;

        o2[33].height = h / 20;
        o2[33].width = h / 15;
        o2[33].y = h * 0.92f - h / 12 - o2[32].height;
        o2[33].x = 8.98f * w;

        o2[34].height = h / 20;
        o2[34].width = h / 15;
        o2[34].y = h * 0.92f - h / 12 - o2[32].height;
        o2[34].x = 9.2f * w;

        o2[35].height = h / 20;
        o2[35].width = h / 15;
        o2[35].y = h * 0.92f - h / 12 - o2[32].height;
        o2[35].x = 9.4f * w;

        o2[36].height = h / 20;
        o2[36].width = h / 15;
        o2[36].y = h * 0.92f - h / 12 - o2[32].height;
        o2[36].x = 9.43f * w;

        o2[37].height = h / 10;
        o2[37].width = h / 6;
        o2[37].y = h / 3.2f - o2[37].height;
        o2[37].x = 11.5f * w;

        o2[38].height = h / 10;
        o2[38].width = h / 6;
        o2[38].y = h / 1.9f - o2[37].height;
        o2[38].x = 11.9f * w;

        o2[39].height = h / 20;
        o2[39].width = h / 15;
        o2[39].y = h / 1.5f - p2[20].height - o2[39].height;
        o2[39].x = 12.18f * w;

        o2[40].height = h / 20;
        o2[40].width = h / 15;
        o2[40].y = h / 1.5f - p2[20].height - o2[39].height;
        o2[40].x = 12.2f * w;


        o2[162].height = h / 20;
        o2[162].width = w / 10;
        o2[162].y = h * 0.92f - o2[162].height;
        o2[162].x = 9.7f * w;

        o2[163].height = h / 20;
        o2[163].width = w / 10;
        o2[163].y = h * 0.92f - o2[162].height;
        o2[163].x = 9.8f * w;

        o2[164].height = h / 20;
        o2[164].width = w / 10;
        o2[164].y = h * 0.92f - o2[162].height;
        o2[164].x = 9.9f * w;

        o2[165].height = h / 20;
        o2[165].width = w / 10;
        o2[165].y = h * 0.92f - o2[162].height;
        o2[165].x = 10.0f * w;

        o2[166].height = h / 20;
        o2[166].width = w / 10;
        o2[166].y = h * 0.92f - o2[162].height;
        o2[166].x = 10.1f * w;

        o2[167].height = h / 20;
        o2[167].width = w / 10;
        o2[167].y = h * 0.92f - o2[162].height;
        o2[167].x = 10.2f * w;

        o2[168].height = h / 20;
        o2[168].width = w / 10;
        o2[168].y = h * 0.92f - o2[162].height;
        o2[168].x = 10.3f * w;

        o2[169].height = h / 20;
        o2[169].width = w / 10;
        o2[169].y = h * 0.92f - o2[162].height;
        o2[169].x = 10.4f * w;

        o2[170].height = h / 20;
        o2[170].width = w / 10;
        o2[170].y = h * 0.92f - o2[162].height;
        o2[170].x = 10.5f * w;

        o2[171].height = h / 20;
        o2[171].width = w / 10;
        o2[171].y = h * 0.92f - o2[162].height;
        o2[171].x = 10.6f * w;

        o2[172].height = h / 20;
        o2[172].width = w / 10;
        o2[172].y = h * 0.92f - o2[162].height;
        o2[172].x = 10.7f * w;

        o2[173].height = h / 20;
        o2[173].width = w / 10;
        o2[173].y = h * 0.92f - o2[162].height;
        o2[173].x = 10.8f * w;

        o2[174].height = h / 20;
        o2[174].width = w / 10;
        o2[174].y = h * 0.92f - o2[162].height;
        o2[174].x = 10.9f * w;

        o2[175].height = h / 20;
        o2[175].width = w / 10;
        o2[175].y = h * 0.92f - o2[162].height;
        o2[175].x = 11.0f * w;

        o2[176].height = h / 20;
        o2[176].width = w / 10;
        o2[176].y = h * 0.92f - o2[162].height;
        o2[176].x = 11.1f * w;

        o2[177].height = h / 20;
        o2[177].width = w / 10;
        o2[177].y = h * 0.92f - o2[162].height;
        o2[177].x = 11.2f * w;

        o2[178].height = h / 20;
        o2[178].width = w / 10;
        o2[178].y = h * 0.92f - o2[162].height;
        o2[178].x = 11.3f * w;

        o2[179].height = h / 20;
        o2[179].width = w / 10;
        o2[179].y = h * 0.92f - o2[162].height;
        o2[179].x = 11.4f * w;

        o2[180].height = h / 20;
        o2[180].width = w / 10;
        o2[180].y = h * 0.92f - o2[162].height;
        o2[180].x = 11.5f * w;

        o2[181].height = h / 20;
        o2[181].width = w / 10;
        o2[181].y = h * 0.92f - o2[162].height;
        o2[181].x = 11.6f * w;

        o2[182].height = h / 20;
        o2[182].width = w/10;
        o2[182].y = h * 0.92f - o2[162].height;
        o2[182].x = 12.85f * w;

        o2[183].height = h / 20;
        o2[183].width = w/10;
        o2[183].y = h * 0.92f - o2[162].height;
        o2[183].x = 12.95f * w;

        o2[184].height = h / 20;
        o2[184].width = w/10;
        o2[184].y = h * 0.92f - o2[162].height;
        o2[184].x = 13.05f * w;

        o2[185].height = h / 20;
        o2[185].width = w/10;
        o2[185].y = h * 0.92f - o2[162].height;
        o2[185].x = 13.15f * w;

        seaTurtle.x = 12.7f * w;
        seaTurtle.height = h / 12;
        seaTurtle.width = w / 11;
        seaTurtle.y = h/1.17f;

        seaTurtle.oldX = seaTurtle.x;

        for (int i = 44; i <= 125; i++) {
            o2[i].height = h;
            o2[i].width = w / 2.5f;
        }

        o2[44].x = w * 2 + w * n2;
        o2[44].y = h / -1.2f;
        o2[45].x = w * 2.4f + w * n2;
        o2[45].y = h / -1.5f;
        o2[46].x = w * 2.8f + w * n2;
        o2[46].y = h / -2.4f;
        o2[47].x = w * 3.2f + w * n2;
        o2[47].y = h / -1.5f;
        o2[48].x = w * 3.6f + w * n2;
        o2[48].y = h / -1.2f;
        o2[49].x = w * 4 + w * n2;
        o2[49].y = h / -1.34f;
        o2[50].x = w * 4.4f + w * n2;
        o2[50].y = h / -1.52f;
        o2[51].x = w * 4.8f + w * n2;
        o2[51].y = h / -1.6f;
        o2[52].x = w * 5.2f + w * n2;
        o2[52].y = h / -2.1f;
        o2[53].x = w * 5.6f + w * n2;
        o2[53].y = h / -2.4f;
        o2[54].x = w * 6 + w * n2;
        o2[54].y = h / -1.72f;
        o2[55].x = w * 6.4f + w * n2;
        o2[55].y = h / -1.42f;
        o2[56].x = w * 6.8f + w * n2;
        o2[56].y = h / -1.28f;
        o2[57].x = w * 7.2f + w * n2;
        o2[57].y = h / -1.15f;
        o2[58].x = w * 7.6f + w * n2;
        o2[58].y = h / -1.1f;
        o2[59].x = w * 8 + w * n2;
        o2[59].y = h / -1.6f;
        o2[60].x = w * 8.4f + w * n2;
        o2[60].y = h / -1.5f;
        o2[61].x = w * 8.8f + w * n2;
        o2[61].y = h / -1.4f;
        o2[62].x = w * 9.2f + w * n2;
        o2[62].y = h / -1.4f;
        o2[63].x = w * 9.6f + w * n2;
        o2[63].y = h / -1.2f;
        o2[64].x = w * 10 + w * n2;
        o2[64].y = h / -1.3f;
        o2[65].x = w * 10.4f + w * n2;
        o2[65].y = h / -1.35f;
        o2[66].x = w * 10.8f + w * n2;
        o2[66].y = h / -1.4f;
        o2[67].x = w * 11.2f + w * n2;
        o2[67].y = h / -1.45f;
        o2[68].x = w * 11.6f + w * n2;
        o2[68].y = h / -1.5f;
        o2[69].x = w * 12 + w * n2;
        o2[69].y = h / -1.55f;
        o2[70].x = w * 12.4f + w * n2;
        o2[70].y = h / -1.9f;
        o2[71].x = w * 12.8f + w * n2;
        o2[71].y = h / -1.15f;
        o2[72].x = w * 13.2f + w * n2;
        o2[72].y = h / -1.15f;
        o2[73].x = w * 13.6f + w * n2;
        o2[73].y = h / -1.15f;
        o2[74].x = w * 14 + w * n2;
        o2[74].y = h / -1.15f;
        o2[75].x = w * 14.4f + w * n2;
        o2[75].y = h / -1.2f;
        o2[76].x = w * 14.8f + w * n2;
        o2[76].y = h / -1.3f;
        o2[77].x = w * 15.2f + w * n2;
        o2[77].y = h / -1.38f;
        o2[78].x = w * 15.6f + w * n2;
        o2[78].y = h / -1.4f;
        o2[79].x = w * 16 + w * n2;
        o2[79].y = h / -1.65f;
        o2[80].x = w * 16.4f + w * n2;
        o2[80].y = h / -1.6f;
        o2[81].x = w * 16.8f + w * n2;
        o2[81].y = h / -1.4f;
        o2[82].x = w * 17.2f + w * n2;
        o2[82].y = h / -1.3f;
        o2[83].x = w * 17.6f + w * n2;
        o2[83].y = h / -1.2f;
        o2[84].x = w * 18f;
        o2[84].y = h / -1.05f;

        //bottom
        o2[85].x = w * 2 + w * n2;
        o2[85].y = h / 1.05f;
        o2[86].x = w * 2.4f + w * n2;
        o2[86].y = h / 1.05f;
        o2[87].x = w * 2.8f + w * n2;
        o2[87].y = h / 1.05f;
        o2[88].x = w * 3.2f + w * n2;
        o2[88].y = h / 1.05f;
        o2[89].x = w * 3.6f + w * n2;
        o2[89].y = h / 1.35f;
        o2[90].x = w * 4 + w * n2;
        o2[90].y = h / 1.3f;
        o2[91].x = w * 4.4f + w * n2;
        o2[91].y = h / 1.18f;
        o2[92].x = w * 4.8f + w * n2;
        o2[92].y = h / 1.2f;
        o2[93].x = w * 5.2f + w * n2;
        o2[93].y = h / 1.1f;
        o2[94].x = w * 5.6f + w * n2;
        o2[94].y = h / 1.03f;
        o2[95].x = w * 6 + w * n2;
        o2[95].y = h / 1.13f;
        o2[96].x = w * 6.4f + w * n2;
        o2[96].y = h / 1.3f;
        o2[97].x = w * 6.8f + w * n2;
        o2[97].y = h / 1.35f;
        o2[98].x = w * 7.2f + w * n2;
        o2[98].y = h / 1.55f;
        o2[99].x = w * 7.6f + w * n2;
        o2[99].y = h / 1.3f;
        o2[100].x = w * 8 + w * n2;
        o2[100].y = h / 1.3f;
        o2[101].x = w * 8.4f + w * n2;
        o2[101].y = h / 1.34f;
        o2[102].x = w * 8.8f + w * n2;
        o2[102].y = h / 1.38f;
        o2[103].x = w * 9.2f + w * n2;
        o2[103].y = h / 1.42f;
        o2[104].x = w * 9.6f + w * n2;
        o2[104].y = h / 1.15f;
        o2[105].x = w * 10 + w * n2;
        o2[105].y = h / 1.2f;
        o2[106].x = w * 10.4f + w * n2;
        o2[106].y = h / 1.28f;
        o2[107].x = w * 10.8f + w * n2;
        o2[107].y = h / 1.36f;
        o2[108].x = w * 11.2f + w * n2;
        o2[108].y = h / 1.4f;
        o2[109].x = w * 11.6f + w * n2;
        o2[109].y = h / 1.4f;
        o2[110].x = w * 12 + w * n2;
        o2[110].y = h / 1.33f;
        o2[111].x = w * 12.4f + w * n2;
        o2[111].y = h / 1.2f;
        o2[112].x = w * 12.8f + w * n2;
        o2[112].y = h / 1.1f;
        o2[113].x = w * 13.2f + w * n2;
        o2[113].y = h / 1.1f;
        o2[114].x = w * 13.6f + w * n2;
        o2[114].y = h / 1.1f;
        o2[115].x = w * 14 + w * n2;
        o2[115].y = h / 1.1f;
        o2[116].x = w * 14.4f + w * n2;
        o2[116].y = h / 1.3f;
        o2[117].x = w * 14.8f + w * n2;
        o2[117].y = h / 1.27f;
        o2[118].x = w * 15.2f + w * n2;
        o2[118].y = h / 1.23f;
        o2[119].x = w * 15.6f + w * n2;
        o2[119].y = h / 1.2f;
        o2[120].x = w * 16 + w * n2;
        o2[120].y = h / 1.1f;
        o2[121].x = w * 16.4f + w * n2;
        o2[121].y = h / 1.05f;
        o2[122].x = w * 16.8f + w * n2;
        o2[122].y = h / 1.05f;
        o2[123].x = w * 17.2f + w * n2;
        o2[123].y = h / 1.05f;
        o2[124].x = w * 17.6f + w * n2;
        o2[124].y = h / 1.05f;
        o2[125].x = w * 18f;
        o2[125].y = h / 1.05f;


        //fishes
        for (int i = 126; i <= 161; i++) {
            o2[i].height = h / 8.5f;
            o2[i].width = h / 8.5f;
        }


        o2[126].x = w * 12.9f + w * n2;
        o2[126].y = h / 3f;
        o2[127].x = w * 13.1f + w * n2;
        o2[127].y = h / 1.3f;
        o2[128].x = w * 13.3f + w * n2;
        o2[128].y = h / 4f;
        o2[129].x = w * 13.7f + w * n2;
        o2[129].y = h / 2f;
        o2[130].x = w * 14f + w * n2;
        o2[130].y = h / 1.6f;
        o2[131].x = w * 14.1f + w * n2;
        o2[131].y = h / 4f;
        o2[132].x = w * 14.5f + w * n2;
        o2[132].y = h / 4.4f;
        o2[133].x = w * 14.75f + w * n2;
        o2[133].y = h / 1.9f;
        o2[134].x = w * 15.1f + w * n2;
        o2[134].y = h / 2.5f;
        o2[135].x = w * 15.8f + w * n2;
        o2[135].y = h / 1.8f;
        o2[136].x = w * 16.05f + w * n2;
        o2[136].y = h / 1.35f;
        o2[137].x = w * 16.25f + w * n2;
        o2[137].y = h / 2f;
        o2[138].x = w * 16.4f + w * n2;
        o2[138].y = h / 2f;
        o2[139].x = w * 16.6f + w * n2;
        o2[139].y = h / 1.3f;
        o2[140].x = w * 16.8f + w * n2;
        o2[140].y = h / 2.4f;
        o2[141].x = w * 17 + w * n2;
        o2[141].y = h / 1.65f;
        o2[142].x = w * 17.1f + w * n2;
        o2[142].y = h / 2.3f;
        o2[143].x = w * 17.3f + w * n2;
        o2[143].y = h / 1.8f;
        o2[144].x = w * 17.5f + w * n2;
        o2[144].y = h / 3f;
        o2[145].x = w * 17.6f + w * n2;
        o2[145].y = h / 1.5f;
        o2[146].x = w * 17.9f + w * n2;
        o2[146].y = h / 1.4f;
        o2[147].x = w * 18.1f + w * n2;
        o2[147].y = h / 2f;
        o2[148].x = w * 18.2f + w * n2;
        o2[148].y = h / 2.6f;
        o2[149].x = w * 18.35f + w * n2;
        o2[149].y = h / 3.8f;
        o2[150].x = w * 18.4f + w * n2;
        o2[150].y = h / 9f;
        o2[151].x = w * 18.5f + w * n2;
        o2[151].y = h / 8.5f;
        o2[152].x = w * 18.65f + w * n2;
        o2[152].y = h / 10.7f;
        o2[153].x = w * 18.8f + w * n2;
        o2[153].y = h / 10.3f;
        o2[158].x = w * 18.3f + w * n2;
        o2[158].y = h / 1.45f;
        o2[159].x = w * 18.5f + w * n2;
        o2[159].y = h / 2.4f;
        o2[160].x = w * 18.625f + w * n2;
        o2[160].y = h / 1.3f;
        o2[161].x = w * 18.75f + w * n2;
        o2[161].y = h / 2.4f;


        portal2.x = w * 19.1f + w * n;
        portal2.y = h / 4;
        portal2.height = h / 2;
        portal2.width = w / 5;

        portal2.oldX = portal2.x;


        for (int i = 0; i < 31; i++) {
            p1[i].oldX = p1[i].x;
        }
        for (int i = 0; i < 128; i++) {
            o1[i].oldX = o1[i].x;
        }
        for (int i = 0; i < 187; i++) {
            o2[i].oldX = o2[i].x;
        }

        for (int i = 0; i <= 30; i++) {
            p2[i].oldX = p2[i].x;
        }
        for (int i = 0; i <= 30; i++) {
            p2[i].oldY = p2[i].y;
        }

        for (int i = 0; i < 44; i++) {
            o2[i].oldY = o2[i].y;
        }

        for (int i = 162; i < 186; i++) {
            o2[i].oldY = o2[i].y;
        }
    }


    public void prepareLevel1() {

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(getResources(), R.drawable.background, options); //create options to find the dimensions of the image
        options.inSampleSize = calculateInSampleSize(options, w, h); //set the sample size of the options so that the image can be scaled down
        options.inJustDecodeBounds = false;


        bRock = BitmapFactory.decodeResource(getResources(), R.drawable.rock);
        bCloud = BitmapFactory.decodeResource(getResources(), R.drawable.thundercloud);
        rockImage4 = Bitmap.createScaledBitmap(bRock, w / 16, h / 15, true);
        rockImage5 = Bitmap.createScaledBitmap(bRock, w / 13, h / 15, true);
        rockImage7 = Bitmap.createScaledBitmap(bRock, w / 14, h / 15, true);
        rockImage8 = Bitmap.createScaledBitmap(bRock, w / 11, h / 15, true);
        cloudImage7 = Bitmap.createScaledBitmap(bCloud, w / 6, h / 7, true);
        bBackground1 = BitmapFactory.decodeResource(getResources(), R.drawable.background, options);
        background1 = Bitmap.createScaledBitmap(bBackground1, w, h + h / 50, true);
        bBackground1Cave = BitmapFactory.decodeResource(getResources(), R.drawable.cave, options);
        background1cave = Bitmap.createScaledBitmap(bBackground1Cave, w, h, true);

        bBirdy = BitmapFactory.decodeResource(getResources(), R.drawable.pterodactyl);
        bird = Bitmap.createScaledBitmap(bBirdy, (int) pterodactyl.width, (int) pterodactyl.height, true);

        bStalactite = BitmapFactory.decodeResource(getResources(), R.drawable.stalactite);
        stalactite = Bitmap.createScaledBitmap(bStalactite, (int) o1[90].width, (int) o1[90].height, true);
        bStalactite2 = BitmapFactory.decodeResource(getResources(), R.drawable.stalactite2);
        stalactite2 = Bitmap.createScaledBitmap(bStalactite2, (int) o1[90].width, (int) o1[90].height, true);
        bMagma = BitmapFactory.decodeResource(getResources(), R.drawable.magma_ball);
        magma = Bitmap.createScaledBitmap(bMagma, w / 15, w / 15, true);


        bPlatform = BitmapFactory.decodeResource(getResources(), R.drawable.platform);
        platform = Bitmap.createScaledBitmap(bPlatform, (int) p1[0].width, (int) p1[0].height, true);
        platform2 = Bitmap.createScaledBitmap(bPlatform, (int) p1[1].width, (int) p1[1].height, true);
        platform3 = Bitmap.createScaledBitmap(bPlatform, (int) p1[5].width, (int) p1[5].height, true);
        platform4 = Bitmap.createScaledBitmap(bPlatform, (int) p1[6].width, (int) p1[6].height, true);
        platform5 = Bitmap.createScaledBitmap(bPlatform, (int) p1[7].width, (int) p1[7].height, true);
        platform6 = Bitmap.createScaledBitmap(bPlatform, (int) p1[14].width, (int) p1[14].height, true);
        platform7 = Bitmap.createScaledBitmap(bPlatform, (int) p1[15].width, (int) p1[15].height, true);
        platformLong = Bitmap.createScaledBitmap(bPlatform, (int) p1[16].width, (int) p1[16].height, true);
        platformLong2 = Bitmap.createScaledBitmap(bPlatform, (int) p1[27].width, (int) p1[27].height, true);

        bPortal = BitmapFactory.decodeResource(getResources(), R.drawable.portalx);
        portal = Bitmap.createScaledBitmap(bPortal, (int) portal1.width, (int) portal1.height, true);

        bLava = BitmapFactory.decodeResource(getResources(), R.drawable.lava);
        lava = Bitmap.createScaledBitmap(bLava, (int) o1[127].width, (int) o1[127].height, true);
    }

    public void cleanLevel1() {
        for (int i = 0; i < 50; i++) {
            p1[i].x = p1[i].oldX;
            p1[i].hitDetected = false;
        }
        for (int i = 0; i < 200; i++) {
            o1[i].x = o1[i].oldX;
            o1[i].hitDetected = false;
        }

        pterodactyl.x = pterodactyl.oldX;
        pterodactyl.mounted = false;
        player.flyingSection = false;

        player.y = h / 1.3f;

        player.height = h / 8;

        caveEntered = false;

        portal1.x = portal1.oldX;
        portal1.hitDetected = false;

        player.height = screenHeight / 8;
        player.flyingSection = false;

        positionReached = false;

        bRock = null;
        bCloud = null;
        rockImage4 = null;
        rockImage5 = null;
        rockImage7 = null;
        rockImage8 = null;
        cloudImage7 = null;

        bStalactite = null;
        stalactite = null;
        bStalactite2 = null;
        stalactite2 = null;
        bMagma = null;
        magma = null;
        bBackground1 = null;
        background1 = null;
        bBackground1Cave = null;
        background1cave = null;

        bPlatform = null;
        platform = null;
        platform2 = null;
        platform3 = null;
        platform4 = null;
        platform5 = null;
        platform6 = null;
        platform7 = null;
        platformLong = null;
        platformLong2 = null;
        bBirdy = null;
        bird = null;

        bPortal = null;
        portal = null;
    }

    public void prepareLevel2() {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(getResources(), R.drawable.background, options); //create options to find the dimensions of the image
        options.inSampleSize = calculateInSampleSize(options, w, h); //set the sample size of the options so that the image can be scaled down
        options.inJustDecodeBounds = false;


        bSnowrock = BitmapFactory.decodeResource(getResources(), R.drawable.rock_snowed);
        snowrock = Bitmap.createScaledBitmap(bSnowrock, h / 12, h / 12, true);
        snowrock2 = Bitmap.createScaledBitmap(bSnowrock, h / 15, h / 20, true);
        snowrock3 = Bitmap.createScaledBitmap(bSnowrock, w / 10, h / 20, true);
        snowrock4 = Bitmap.createScaledBitmap(bSnowrock, h / 25, h / 25, true);
        bIcicle = BitmapFactory.decodeResource(getResources(), R.drawable.icicle_platformx);
        icicle = Bitmap.createScaledBitmap(bIcicle, h / 10, h / 10, true);
        icicle2 = Bitmap.createScaledBitmap(bIcicle, h / 10, h / 10, true);
        icicle4 = Bitmap.createScaledBitmap(bIcicle, h / 6, h / 10, true);
        bPillar = BitmapFactory.decodeResource(getResources(), R.drawable.under_rocks);
        bPillar2 = BitmapFactory.decodeResource(getResources(), R.drawable.under_rocks2);
        bFish = BitmapFactory.decodeResource(getResources(), R.drawable.evil_fish);
        fish = Bitmap.createScaledBitmap(bFish, (int) o2[150].width, (int) o2[150].height, true);
        bTurtle = BitmapFactory.decodeResource(getResources(), R.drawable.sea_turtle);
        turtle = Bitmap.createScaledBitmap(bTurtle, (int) seaTurtle.width, (int) seaTurtle.height, true);
        bBackgroundWater = BitmapFactory.decodeResource(getResources(), R.drawable.underwater, options);
        backgroundWater = Bitmap.createScaledBitmap(bBackgroundWater, w, h, true);
        pillar = Bitmap.createScaledBitmap(bPillar, (int) o2[90].width, h, true);
        pillar2 = Bitmap.createScaledBitmap(bPillar2, (int) o2[90].width, h, true);
        bBackgroundSnow = BitmapFactory.decodeResource(getResources(), R.drawable.background2, options);
        backgroundSnow = Bitmap.createScaledBitmap(bBackgroundSnow, w, h, true);
        bPlatformSnow = BitmapFactory.decodeResource(getResources(), R.drawable.snow_rock);
        platformSnow = Bitmap.createScaledBitmap(bPlatformSnow, (int) p2[0].width, (int) p2[0].height, true);
        platformSnow2 = Bitmap.createScaledBitmap(bPlatformSnow, (int) p2[2].width, (int) p2[2].height, true);
        platformSnow3 = Bitmap.createScaledBitmap(bPlatformSnow, (int) p2[4].width, (int) p2[4].height, true);
        platformSnow4 = Bitmap.createScaledBitmap(bPlatformSnow, (int) p2[8].width, (int) p2[8].height, true);
        platformSnow5 = Bitmap.createScaledBitmap(bPlatformSnow, (int) p2[10].width, (int) p2[10].height, true);
        platformSnow6 = Bitmap.createScaledBitmap(bPlatformSnow, (int) p2[20].width, (int) p2[20].height, true);
        platformSnow7 = Bitmap.createScaledBitmap(bPlatformSnow, (int) p2[21].width, (int) p2[21].height, true);
        platformSnow8 = Bitmap.createScaledBitmap(bPlatformSnow, (int) p2[9].width, (int) p2[9].height, true);

        bPortal = BitmapFactory.decodeResource(getResources(), R.drawable.portalx);
        portal = Bitmap.createScaledBitmap(bPortal, (int) portal1.width, (int) portal1.height, true);
    }


    public void cleanLevel2() {

        for (int i = 0; i <= 30; i++) {
            p2[i].y = p2[i].oldY;
        }

        for (int i = 0; i < 44; i++) {
            o2[i].y = o2[i].oldY;
        }

        for (int i = 162; i < 186; i++) {
            o2[i].y = o2[i].oldY;
        }

        for (int i = 0; i < 30; i++) {
            p2[i].x = p2[i].oldX;
            p2[i].hitDetected = false;
        }
        for (int i = 0; i < 187; i++) {
            o2[i].x = o2[i].oldX;
            o2[i].hitDetected = false;
        }

        seaTurtle.x = seaTurtle.oldX;
        seaTurtle.mounted = false;
        player.swimmingSection = false;

        player.y = h / 1.3f;

        player.height = h / 8;

        portal2.x = portal2.oldX;
        portal2.hitDetected = false;

        bSnowrock = null;
        snowrock = null;
        snowrock2 = null;
        snowrock3 = null;
        snowrock4 = null;

        bIcicle = null;
        icicle = null;
        icicle2 = null;
        icicle4 = null;

        bPillar2 = null;
        pillar = null;
        pillar2 = null;
        bPillar = null;

        fish = null;
        bFish = null;
        turtle = null;
        bTurtle = null;
        backgroundWater = null;
        bBackgroundWater = null;
        bBackgroundSnow = null;
        backgroundSnow = null;

        bPlatformSnow = null;

        platformSnow = null;
        platformSnow2 = null;
        platformSnow3 = null;
        platformSnow4 = null;
        platformSnow5 = null;
        platformSnow6 = null;
        platformSnow7 = null;

        bPortal  = null;
        portal = null;
    }


    //Main Menu Screen/Method
    public void mainMenu(Canvas canvas) {
        canvas.drawBitmap(menuBackground, 0, 0, menuBackGroundPaint);
        canvas.drawBitmap(highscoresIcon, 5 * (screenWidth / 11) + screenWidth / 100, screenHeight / 1.26f, menuTitlePaint);

        for (int i = 0; i <= 1; i++) {
            canvas.drawRect(b[i].x - screenWidth / 120, b[i].y - screenWidth / 120, b[i].x + b[i].width + screenWidth / 120, b[i].y + b[i].height + screenWidth / 120, buttonPaint2);
            canvas.drawRect(b[i].x, b[i].y, b[i].x + b[i].width, b[i].y + b[i].height, buttonPaint[i]);
        }

        canvas.drawBitmap(levelModeIMG2, b[0].x + w / 70, b[0].y + h / 27, menuTitlePaint);
        canvas.drawBitmap(endlessModeIMG2, b[1].x + w / 70, b[1].y + h / 27, menuTitlePaint);

    }


    //Draw game elements onto screen
    public void drawGameElements(Canvas canvas) {
        clicked = false;

        if (player.jump == false && player.fall == false && !player.runningAnimation.isRunning() && player.slide == false) {
            player.runningAnimation.start();
        }

        if (gameState == 2) {
            if(pterodactyl.mounted){
                player.runningAnimation.pause(6);
            }

            if (pterodactyl.mounted && !caveEntered) {
                player.flyingSection = true;
                player.jump = true;
                player.fall = false;
                player.slide = false;
                player.x = player.x + screenWidth / 190;
                if (player.x >= screenWidth) {
                    caveEntered = true;
                    player.x = 0;
                }
            }

            if (o1[126].x <= 0) {
                player.x = player.x + screenWidth / 190;
            }

            if (caveEntered) {
                if (!positionReached) {
                    player.x = player.x + screenWidth / 190;
                } else if (o1[126].x > 0) {
                    player.x = w / 3.5f;
                }

                if (player.x >= w / 3.5) {
                    positionReached = true;
                }
                canvas.drawBitmap(background1cave, 0, 0, obstaclePaint);
            } else {
                canvas.drawBitmap(background1, 0, 0, obstaclePaint);
            }

            player.display(canvas, (int) player.x, (int) player.y, playerPaint);


            canvas.drawBitmap(platform, p1[0].x, p1[0].y, obstaclePaint);
            canvas.drawBitmap(platform2, p1[1].x, p1[1].y, obstaclePaint);
            canvas.drawBitmap(platform2, p1[2].x, p1[2].y, obstaclePaint);
            canvas.drawBitmap(platform, p1[3].x, p1[3].y, obstaclePaint);
            canvas.drawBitmap(platform, p1[4].x, p1[4].y, obstaclePaint);
            canvas.drawBitmap(platform3, p1[5].x, p1[5].y, obstaclePaint);
            canvas.drawBitmap(platform4, p1[6].x, p1[6].y, obstaclePaint);
            canvas.drawBitmap(platform5, p1[7].x, p1[7].y, obstaclePaint);
            canvas.drawBitmap(platform6, p1[8].x, p1[8].y, obstaclePaint);
            canvas.drawBitmap(platform5, p1[9].x, p1[9].y, obstaclePaint);
            canvas.drawBitmap(platform6, p1[10].x, p1[10].y, obstaclePaint);
            canvas.drawBitmap(platform7, p1[11].x, p1[11].y, obstaclePaint);
            canvas.drawBitmap(platform5, p1[12].x, p1[12].y, obstaclePaint);
            canvas.drawBitmap(platform5, p1[13].x, p1[13].y, obstaclePaint);
            canvas.drawBitmap(platform6, p1[14].x, p1[14].y, obstaclePaint);
            canvas.drawBitmap(platform7, p1[15].x, p1[15].y, obstaclePaint);
            canvas.drawBitmap(platformLong, p1[16].x, p1[16].y, obstaclePaint);
            canvas.drawBitmap(platformLong, p1[17].x, p1[17].y, obstaclePaint);
            canvas.drawBitmap(platformLong, p1[18].x, p1[18].y, obstaclePaint);
            canvas.drawBitmap(platformLong, p1[19].x, p1[19].y, obstaclePaint);
            canvas.drawBitmap(platformLong, p1[20].x, p1[20].y, obstaclePaint);
            canvas.drawBitmap(platformLong, p1[21].x, p1[21].y, obstaclePaint);
            canvas.drawBitmap(platformLong, p1[22].x, p1[22].y, obstaclePaint);
            canvas.drawBitmap(platformLong, p1[23].x, p1[23].y, obstaclePaint);
            canvas.drawBitmap(platform3, p1[24].x, p1[24].y, obstaclePaint);
            canvas.drawBitmap(platformLong2, p1[25].x, p1[25].y, obstaclePaint);
            canvas.drawBitmap(platformLong2, p1[26].x, p1[26].y, obstaclePaint);
            canvas.drawBitmap(platformLong2, p1[27].x, p1[27].y, obstaclePaint);
            canvas.drawBitmap(platformLong2, p1[28].x, p1[28].y, obstaclePaint);
            canvas.drawBitmap(platformLong2, p1[29].x, p1[29].y, obstaclePaint);
            canvas.drawBitmap(platformLong2, p1[30].x, p1[30].y, obstaclePaint);
            canvas.drawBitmap(bird, pterodactyl.x, pterodactyl.y, obstaclePaint);

            canvas.drawBitmap(portal, portal1.x, portal1.y, obstaclePaint);

            for (int i = 0; i <= 31; i++) {
                if (p1[i].hitDetected) {
                    p1[i].hitDetected = false;
                    gameState = 10;
                }
            }


            canvas.drawBitmap(rockImage4, o1[0].x, o1[0].y, obstaclePaint);
            canvas.drawBitmap(rockImage4, o1[1].x, o1[1].y, obstaclePaint);
            canvas.drawBitmap(rockImage4, o1[2].x, o1[2].y, obstaclePaint);
            canvas.drawBitmap(rockImage5, o1[3].x, o1[3].y, obstaclePaint);
            canvas.drawBitmap(rockImage5, o1[4].x, o1[4].y, obstaclePaint);
            canvas.drawBitmap(rockImage5, o1[5].x, o1[5].y, obstaclePaint);
            for (int i = 6; i <= 7; i++) {
                canvas.drawBitmap(rockImage7, o1[i].x, o1[i].y, obstaclePaint);
            }
            for (int i = 9; i <= 10; i++) {
                canvas.drawBitmap(rockImage8, o1[i].x, o1[i].y, obstaclePaint);
            }
            canvas.drawBitmap(cloudImage7, o1[11].x, o1[11].y, obstaclePaint);
            canvas.drawBitmap(rockImage8, o1[13].x, o1[13].y, obstaclePaint);
            canvas.drawBitmap(rockImage7, o1[14].x, o1[14].y, obstaclePaint);
            canvas.drawBitmap(rockImage7, o1[16].x, o1[16].y, obstaclePaint);
            canvas.drawBitmap(cloudImage7, o1[17].x, o1[17].y, obstaclePaint);
            canvas.drawBitmap(rockImage5, o1[18].x, o1[18].y, obstaclePaint);
            canvas.drawBitmap(rockImage5, o1[19].x, o1[19].y, obstaclePaint);
            canvas.drawBitmap(rockImage4, o1[20].x, o1[20].y, obstaclePaint);
            canvas.drawBitmap(rockImage5, o1[21].x, o1[21].y, obstaclePaint);

            for (int i = 23; i <= 59; i++) {
                canvas.drawBitmap(rockImage5, o1[i].x, o1[i].y, obstaclePaint);
            }
            canvas.drawBitmap(rockImage8, o1[61].x, o1[61].y, obstaclePaint);
            canvas.drawBitmap(rockImage5, o1[62].x, o1[62].y, obstaclePaint);
            canvas.drawBitmap(rockImage8, o1[63].x, o1[63].y, obstaclePaint);


            if (portal1.hitDetected) {
                gameState = 9;
            }

            for (int i = 0; i <= 127; i++) {
                if (o1[i].hitDetected) {
                    o1[i].hitDetected = false;
                    gameState = 10;
                }
            }


            for (int i = 67; i <= 68; i++) {
                canvas.drawBitmap(magma, o1[i].x, o1[i].y, obstaclePaint);
            }

            for (int i = 70; i <= 83; i++) {
                canvas.drawBitmap(magma, o1[i].x, o1[i].y, obstaclePaint);
            }

            for (int i = 84; i <= 106; i++) {
                canvas.drawBitmap(stalactite2, o1[i].x, o1[i].y, obstaclePaint);
            }

            for (int i = 107; i <= 126; i++) {
                canvas.drawBitmap(stalactite, o1[i].x, o1[i].y, obstaclePaint);
            }

            canvas.drawBitmap(lava, o1[127].x, o1[127].y, obstaclePaint);

        } else if (gameState == 3) {

            if(seaTurtle.mounted){
                player.runningAnimation.pause(6);
            }

            if (!seaTurtle.mounted) {
                canvas.drawBitmap(backgroundSnow, 0, 0, obstaclePaint);
            } else {
                player.swimmingSection = true;
                for (int i = 0; i <= 43; i++) {
                    if (o2[i].y > -h) {
                        o2[i].y = o2[i].y - h / 60;
                        player.jump = false;
                        player.slide = false;
                        player.fall = false;
                    }
                }

                for (int i = 162; i <= 186; i++) {
                    if (o2[i].y > -h) {
                        o2[i].y = o2[i].y - h / 60;
                    }
                }

                for (int i = 0; i <= 30; i++) {
                    if (p2[i].y > -h) {
                        p2[i].y = p2[i].y - h / 60;
                    }
                }

                canvas.drawBitmap(backgroundWater, 0, 0, obstaclePaint);
            }

            for (int i = 0; i <= 22; i++) {
                if (p2[i].hitDetected) {
                    p2[i].hitDetected = false;
                    gameState = 10;
                }
            }

            canvas.drawBitmap(platformSnow, p2[0].x, p2[0].y, obstaclePaint);
            canvas.drawBitmap(platformSnow, p2[1].x, p2[1].y, obstaclePaint);
            canvas.drawBitmap(platformSnow2, p2[2].x, p2[2].y, obstaclePaint);
            canvas.drawBitmap(platformSnow, p2[3].x, p2[3].y, obstaclePaint);
            canvas.drawBitmap(platformSnow3, p2[4].x, p2[4].y, obstaclePaint);
            canvas.drawBitmap(platformSnow, p2[5].x, p2[5].y, obstaclePaint);
            canvas.drawBitmap(platformSnow2, p2[6].x, p2[6].y, obstaclePaint);
            canvas.drawBitmap(platformSnow3, p2[7].x, p2[7].y, obstaclePaint);
            canvas.drawBitmap(platformSnow4, p2[8].x, p2[8].y, obstaclePaint);
            canvas.drawBitmap(platformSnow8, p2[9].x, p2[9].y, obstaclePaint);
            canvas.drawBitmap(platformSnow5, p2[10].x, p2[10].y, obstaclePaint);
            canvas.drawBitmap(platformSnow5, p2[11].x, p2[11].y, obstaclePaint);
            canvas.drawBitmap(platformSnow5, p2[12].x, p2[12].y, obstaclePaint);
            canvas.drawBitmap(platformSnow5, p2[13].x, p2[13].y, obstaclePaint);
            canvas.drawBitmap(platformSnow5, p2[14].x, p2[14].y, obstaclePaint);
            canvas.drawBitmap(platformSnow5, p2[15].x, p2[15].y, obstaclePaint);
            canvas.drawBitmap(platformSnow5, p2[17].x, p2[17].y, obstaclePaint);
            canvas.drawBitmap(platformSnow5, p2[18].x, p2[18].y, obstaclePaint);
            canvas.drawBitmap(platformSnow5, p2[19].x, p2[19].y, obstaclePaint);
            canvas.drawBitmap(platformSnow6, p2[20].x, p2[20].y, obstaclePaint);
            canvas.drawBitmap(platformSnow7, p2[21].x, p2[21].y, obstaclePaint);


            for (int i = 0; i <= 1; i++) {
                canvas.drawBitmap(snowrock, o2[i].x, o2[i].y, obstaclePaint);
            }
            canvas.drawBitmap(snowrock, o2[2].x, o2[2].y, obstaclePaint);
            canvas.drawBitmap(icicle, o2[3].x, o2[3].y, obstaclePaint);
            canvas.drawBitmap(icicle, o2[4].x, o2[4].y, obstaclePaint);
            canvas.drawBitmap(snowrock, o2[5].x, o2[5].y, obstaclePaint);
            canvas.drawBitmap(icicle2, o2[6].x, o2[6].y, obstaclePaint);
            canvas.drawBitmap(snowrock, o2[7].x, o2[7].y, obstaclePaint);
            canvas.drawBitmap(icicle2, o2[9].x, o2[9].y, obstaclePaint);
            canvas.drawBitmap(snowrock, o2[10].x, o2[10].y, obstaclePaint);
            canvas.drawBitmap(snowrock, o2[11].x, o2[11].y, obstaclePaint);
            canvas.drawBitmap(snowrock, o2[12].x, o2[12].y, obstaclePaint);
            canvas.drawBitmap(snowrock, o2[13].x, o2[13].y, obstaclePaint);
            canvas.drawBitmap(icicle, o2[18].x, o2[18].y, obstaclePaint);
            canvas.drawBitmap(snowrock, o2[20].x, o2[20].y, obstaclePaint);
            canvas.drawBitmap(snowrock, o2[21].x, o2[21].y, obstaclePaint);
            canvas.drawBitmap(icicle, o2[22].x, o2[22].y, obstaclePaint);
            canvas.drawBitmap(snowrock, o2[23].x, o2[23].y, obstaclePaint);
            canvas.drawBitmap(icicle4, o2[25].x, o2[25].y, obstaclePaint);
            canvas.drawBitmap(icicle4, o2[26].x, o2[26].y, obstaclePaint);
            canvas.drawBitmap(icicle4, o2[29].x, o2[29].y, obstaclePaint);
            canvas.drawBitmap(snowrock2, o2[32].x, o2[32].y, obstaclePaint);
            canvas.drawBitmap(snowrock2, o2[33].x, o2[33].y, obstaclePaint);
            canvas.drawBitmap(snowrock2, o2[34].x, o2[34].y, obstaclePaint);
            canvas.drawBitmap(snowrock2, o2[35].x, o2[35].y, obstaclePaint);
            canvas.drawBitmap(snowrock2, o2[36].x, o2[36].y, obstaclePaint);
            canvas.drawBitmap(icicle4, o2[37].x, o2[37].y, obstaclePaint);
            canvas.drawBitmap(icicle4, o2[38].x, o2[38].y, obstaclePaint);
            canvas.drawBitmap(snowrock2, o2[39].x, o2[39].y, obstaclePaint);
            canvas.drawBitmap(snowrock2, o2[40].x, o2[40].y, obstaclePaint);

            for (int i = 162; i <= 181; i++) {
                canvas.drawBitmap(snowrock3, o2[i].x, o2[i].y, obstaclePaint);
            }

            for (int i = 182; i <= 185; i++) {
                canvas.drawBitmap(snowrock3, o2[i].x, o2[i].y, obstaclePaint);
            }



            player.display(canvas, (int) player.x, (int) player.y, playerPaint);
            canvas.drawBitmap(turtle, seaTurtle.x, seaTurtle.y, platformPaint);

           canvas.drawBitmap(portal, portal2.x, portal2.y, obstaclePaint);

            if (portal2.hitDetected) {
                gameState = 9;
                portal2.hitDetected = false;
            }


            for (int i  = 0; i <= 186; i++){
                if (o2[i].hitDetected) {
                    o2[i].hitDetected = false;
                    gameState = 10;
                }
            }


            for (int i = 42; i <= 43; i++) {
                canvas.drawBitmap(snowrock3, o2[i].x, o2[i].y, obstaclePaint);
            }

            for (int i = 44; i <= 84; i++) {
                canvas.drawBitmap(pillar, o2[i].x, o2[i].y, obstaclePaint);

            }

            for (int i = 85; i <= 125; i++) {
                canvas.drawBitmap(pillar2, o2[i].x, o2[i].y, obstaclePaint);
            }

            for (int i = 126; i <= 153; i++) {
                canvas.drawBitmap(fish, o2[i].x, o2[i].y, obstaclePaint);
            }

            for (int i = 158; i <= 161; i++) {
                canvas.drawBitmap(fish, o2[i].x, o2[i].y, obstaclePaint);
            }

        }
    }


    //Game Over Screen/Method
    public void gameOver(Canvas canvas) {
        canvas.drawBitmap(gameOverImage, 0, 0, obstaclePaint);

        for (int i = 3; i <= 4; i++) {
            canvas.drawRect(b[i].x - screenWidth / 120, b[i].y - screenWidth / 120, b[i].x + b[i].width + screenWidth / 120, b[i].y + b[i].height + screenWidth / 120, buttonPaint2);
            canvas.drawRect(b[i].x, b[i].y, b[i].x + b[i].width, b[i].y + b[i].height, buttonPaint[1]);
        }
        canvas.drawBitmap(returntoMenuImage, b[4].x + h/18, b[4].y + h/85, obstaclePaint);
        canvas.drawBitmap(replayImage, b[3].x + h/18, b[3].y + h/85, obstaclePaint);

    }

    public void levelComplete(Canvas canvas) {
        canvas.drawBitmap(levelCompleteImage, 0, 0, obstaclePaint);


        for (int i = 3; i <= 4; i++) {
            canvas.drawRect(b[i].x - screenWidth / 120, b[i].y - screenWidth / 120, b[i].x + b[i].width + screenWidth / 120, b[i].y + b[i].height + screenWidth / 120, buttonPaint2);
            canvas.drawRect(b[i].x, b[i].y, b[i].x + b[i].width, b[i].y + b[i].height, buttonPaint[1]);
        }
        canvas.drawBitmap(returntoMenuImage, b[4].x + h/18, b[4].y + h/85, obstaclePaint);
        canvas.drawBitmap(replayImage, b[3].x + h/18, b[3].y + h/85, obstaclePaint);
    }

    public void creditsScreen(Canvas canvas){
        canvas.drawBitmap(creditScreen, 0, 0, obstaclePaint);
        canvas.drawRect(b[2].x - screenWidth / 120, b[2].y - screenWidth / 120, b[2].x + b[2].width + screenWidth / 120, b[2].y + b[2].height + screenWidth / 120, buttonPaint[2]);
        canvas.drawRect(b[2].x, b[2].y, b[2].x + b[2].width, b[2].y + b[2].height, backgroundPaint);
        canvas.drawBitmap(backWord, b[2].x + screenWidth / 120, b[2].y + screenWidth / 120, menuTitlePaint);
    }


    public void levelSelect(Canvas canvas) {
        canvas.drawBitmap(levelSelectBackground, 0, 0, backgroundPaint);
        canvas.drawRect(b[2].x - screenWidth / 120, b[2].y - screenWidth / 120, b[2].x + b[2].width + screenWidth / 120, b[2].y + b[2].height + screenWidth / 120, buttonPaint[2]);
        canvas.drawRect(b[2].x, b[2].y, b[2].x + b[2].width, b[2].y + b[2].height, backgroundPaint);
        canvas.drawBitmap(backWord, b[2].x + screenWidth / 120, b[2].y + screenWidth / 120, menuTitlePaint);

        canvas.drawRect(lev[0].x - w/80, lev[0].y - w/80, lev[0].x + lev[0].width + w/80, lev[0].y + lev[0].height + w/80, levelPaint[1]);
        if (selectLevel == 0) {
            canvas.drawBitmap(prehistoricImage, lev[0].x, lev[0].y, obstaclePaint);
        } else if (selectLevel == 1) {
            canvas.drawBitmap(iceAgeImage, lev[0].x, lev[0].y, obstaclePaint);
        } else if (selectLevel == 2) {
            canvas.drawBitmap(comingSoon, lev[0].x, lev[0].y, obstaclePaint);
        } else if (selectLevel == 3) {
            canvas.drawBitmap(comingSoon, lev[0].x, lev[0].y, obstaclePaint);
        } else if (selectLevel == 4) {
            canvas.drawBitmap(comingSoon, lev[0].x, lev[0].y, obstaclePaint);
        }
        canvas.drawRect(w/2.82f, h/1.16f, w/1.555f, h/1.04f, levelPaint[4]);
        canvas.drawRect(w/2.78f, h/1.15f, w/1.57f, h/1.05f, fadePaint);
        for (int i = 0; i < 5; i++) {
            canvas.drawCircle(w / 2.58f + i * h / 10, h / 1.1f, h / 80, levelPaint[4]);
        }

        canvas.drawCircle(w / 2.58f + selectLevel * h / 10, h / 1.1f, h / 80, levelPaint[0]);
        clicked = false;

    }


    //User Gesture Detectors
    private float down_touch_x;
    private float up_touch_x;
    private float down_touch_y;
    private float up_touch_y;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                //Get value of first contact
                down_touch_x = event.getX();
                down_touch_y = event.getY();

                for (int i = 0; i < 2; i++) {
                    if (gameState == 0 && down_touch_x > b[i].x && down_touch_x < b[i].x + b[i].width
                            && down_touch_y > b[i].y && down_touch_y < b[i].y + b[i].height) {
                        buttonPaint[i].setColor(Color.rgb(100, 180, 230));
                    }
                }
                break;

            case MotionEvent.ACTION_MOVE:
                break;

            case MotionEvent.ACTION_UP:
                //Get value of when user removes finger from screen
                up_touch_x = event.getX();
                up_touch_y = event.getY();

                if (gameState == 0) {
                    for (int i = 0; i < 2; i++) {
                        if (up_touch_x > b[i].x && up_touch_x < b[i].x + b[i].width
                                && up_touch_y > b[i].y && up_touch_y < b[i].y + b[i].height) {
                            gameState = i + 1;
                            buttonPaint[i].setColor(Color.rgb(51, 101, 138));
                            clicked = true;
                        } else {
                            buttonPaint[i].setColor(Color.rgb(51, 101, 138));
                        }
                    }

                    //settings wheel
                    if (up_touch_x > 5 * (screenWidth / 11) && up_touch_x < 5 * (screenWidth / 11) + (w / 15)
                            && up_touch_y > screenHeight / 1.26f && up_touch_y < screenHeight / 1.25f + (w / 15)) {
                       gameState = 8;
                    }

                }

                if (gameState == 1 && !clicked) {
                    if (up_touch_x > b[2].x && up_touch_x < b[2].x + b[2].width && up_touch_y > b[2].y && up_touch_y < b[2].y + b[2].height) {
                        gameState = 0;
                    }

                    //swipe right
                    if (up_touch_x - down_touch_x < -100 && Math.abs(up_touch_y - down_touch_y) < 600 && selectLevel < 4) {
                        selectLevel = selectLevel + 1;
                    } else if (up_touch_x - down_touch_x > 100 && Math.abs(up_touch_y - down_touch_y) < 600 && selectLevel > 0) {
                        selectLevel = selectLevel - 1;
                    } else if (up_touch_x >= lev[0].x && up_touch_x <= lev[0].x + lev[0].width && up_touch_y >= lev[0].y
                            && up_touch_y <= lev[0].y + lev[0].height) {
                        if(selectLevel < 2) {
                            gameState = selectLevel + 2;
                            clicked = true;
                        }
                    }
                }


                if (gameState == 2 || gameState == 3 && !clicked) {
                    if (up_touch_y - down_touch_y > 100 && Math.abs(up_touch_x - down_touch_x) < 800 && !player.fall && !player.jump && !player.slide) {
                        player.oldY = player.y;
                        player.height = player.oldHeight / 2;
                        player.y = player.oldY + player.height;
                        player.slide = true;
                        player.runningAnimation.stop();
                        player.slidingAnimation.start();
                        player.updatePosition(screenHeight);
                    } else {
                        if (!player.fall && !player.slide && !player.jump) {
                            player.acceleration = 17 * (screenHeight / 450);
                            player.jump = true;
                            player.updatePosition(screenHeight);
                            if (!player.flyingSection && !player.swimmingSection) {
                                player.runningAnimation.stop();
                                player.jumpingAnimation.start();
                            }
                        }
                    }
                }

                if (gameState == 8){
                    if (up_touch_x > b[2].x && up_touch_x < b[2].x + b[2].width && up_touch_y > b[2].y && up_touch_y < b[2].y + b[2].height) {
                        gameState = 0;
                    }
                }

                if (gameState == 10 || gameState == 9) {
                    if (up_touch_x > b[3].x && up_touch_x < b[3].x + b[3].width
                            && up_touch_y > b[3].y && up_touch_y < b[3].y + b[3].height) {
                        gameState = oldGameState;
                        buttonPaint[1].setColor(Color.rgb(51, 101, 138));
                        clicked = true;
                    } else {
                        buttonPaint[1].setColor(Color.rgb(51, 101, 138));
                    }

                    if (up_touch_x > b[4].x && up_touch_x < b[4].x + b[4].width
                            && up_touch_y > b[4].y && up_touch_y < b[4].y + b[4].height) {
                        gameState = 1;
                        buttonPaint[1].setColor(Color.rgb(51, 101, 138));
                        clicked = true;
                    } else {
                        buttonPaint[1].setColor(Color.rgb(51, 101, 138));
                    }
                }


                break;

            case MotionEvent.ACTION_CANCEL:
                break;

            case MotionEvent.ACTION_OUTSIDE:
                break;
        }
        return true;
    }


    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        dashThread = new DashThread(holder);
        dashThread.setRunning(true);
        dashThread.start();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        dashThread.setRunning(false);
        while (retry) {
            try {
                dashThread.join();
                retry = false;
            } catch (InterruptedException e) {
            }
        }

    }


    //Loops Application

    private class DashThread extends Thread {
        private final SurfaceHolder surfaceHolder;
        private boolean threadIsRunning = true;

        public DashThread(SurfaceHolder holder) {
            surfaceHolder = holder;
            setName("DashThread");
        }

        public void setRunning(boolean running) {
            threadIsRunning = running;
        }

        @Override
        public void run() {
            Canvas canvas = null;
            while (threadIsRunning) {
                try {
                    canvas = surfaceHolder.lockCanvas(null);
                    synchronized (surfaceHolder) {

                        if (gameState == 0) {
                            if (!loadMenuValues) {
                                cleanLevelSelect();
                                prepareMenu();
                                loadLevelSelectValues = false;
                                loadMenuValues = true;
                            }
                            mainMenu(canvas);
                        } else if (gameState == 1) {
                            if (!loadLevelSelectValues) {
                                cleanLevel1();
                                cleanLevel2();
                                cleanMenu();
                                cleanGameOver();
                                loadLevelSelect();
                                loadMenuValues = false;
                                loadLevel1Values = false;
                                loadLevel2Values = false;
                                loadLevelSelectValues = true;
                                loadGameOverValues = false;
                            }
                            levelSelect(canvas);
                        } else if (gameState == 2) {
                            oldGameState = 2;
                            if (!loadLevel1Values) {
                                cleanLevelSelect();
                                cleanGameOver();
                                loadLevelSelectValues = false;
                                loadGameOverValues = false;
                                prepareLevel1();
                                loadLevel1Values = true;
                            }
                            drawGameElements(canvas);

                            if (o1[127].x > -w/2) {
                                o1[127].updatePosition(screenWidth);
                            }
                            player.updatePosition(screenHeight);
                            pterodactyl.updatePosition(player, screenWidth);

                            for (int i = 0; i <= 30; i++) {
                                p1[i].updatePosition(screenWidth);
                                p1[i].collision(player);
                            }
                            for (int i = 0; i <= 83; i++) {
                                o1[i].updatePosition(screenWidth);
                                o1[i].collision(player);
                            }

                            for (int i = 84; i <= 106; i++) {
                                o1[i].updatePosition(screenWidth);
                                o1[i].collision2(player);
                            }

                            for (int i = 107; i <= 126; i++) {
                                o1[i].updatePosition(screenWidth);
                                o1[i].collision3(player);
                            }

                            o1[127].collision(player);

                            if (o1[126].x > 0) {
                                portal1.updatePosition(screenWidth);
                            }
                            portal1.collision(player);

                        } else if (gameState == 3) {
                            oldGameState = 3;
                            if (!loadLevel2Values) {
                                cleanLevelSelect();
                                cleanGameOver();
                                loadLevelSelectValues = false;
                                loadGameOverValues = false;
                                prepareLevel2();
                                loadLevel2Values = true;
                            }
                            drawGameElements(canvas);
                            player.updatePosition(screenHeight);
                            for (int i = 0; i <= 43; i++) {
                                o2[i].updatePosition(screenWidth);
                                o2[i].collision(player);
                            }

                            for (int i = 44; i <= 186; i++) {
                                o2[i].updatePosition(screenWidth);
                                o2[i].collision4(player);
                            }

                            for (int i = 0; i <= 21; i++) {
                                p2[i].updatePosition(screenWidth);
                                p2[i].collision(player);
                            }

                            portal2.updatePosition(screenWidth);

                            portal2.collision(player);

                            seaTurtle.updatePosition2(player, screenWidth);

                        } else if (gameState == 8) {
                            creditsScreen(canvas);
                        } else if (gameState == 9) {
                            if (!loadGameOverValues) {
                                cleanLevel1();
                                cleanLevel2();
                                cleanMenu();
                                cleanLevelSelect();
                                loadGameOver();
                                loadMenuValues = false;
                                loadLevel1Values = false;
                                loadLevel2Values = false;
                                loadLevelSelectValues = false;
                                loadGameOverValues = true;
                            }
                            levelComplete(canvas);

                        } else if (gameState == 10) {
                            if (!loadGameOverValues) {
                                cleanLevel1();
                                cleanLevel2();
                                cleanMenu();
                                cleanLevelSelect();
                                loadGameOver();
                                loadMenuValues = false;
                                loadLevel1Values = false;
                                loadLevel2Values = false;
                                loadLevelSelectValues = false;
                                loadGameOverValues = true;
                            }
                            gameOver(canvas);
                        }
                    }
                } finally {
                    if (canvas != null)
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    else {
                        System.exit(0);
                    }
                }
            }
        }
    }
}



