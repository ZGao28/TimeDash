package nevagonlose.TimeDash;

/**
 * Created by Zi on 2016-12-27.
 */

public class pet {
    public float x;
    public float y;
    public float width;
    public float height;
    public boolean mounted = false;
    public  float oldX;

    public void updatePosition(Player player, float screenWidth){


        if (mounted){
            x = player.x - player.width/1.2f;
            y = player.y + height/4;
        } else {
            x = x - screenWidth/200;
            y = screenWidth/4;
        }

        if (player.x + player.width >= (x) && player.x <= x + width && player.y + player.height >=  y && player.y <= y + height) {
            mounted = true;
        } else {
            mounted = false;
        }

    }


    public void updatePosition2(Player player, float screenWidth){

        if (mounted){
            x = player.x;
            y = player.y + height/1.2f;
        } else {
            x = x - screenWidth/200;
        }

        if (player.x + player.width >= (x) && player.x <= x + width && player.y + player.height >=  y && player.y <= y + height) {
            mounted = true;
        } else {
            mounted = false;
        }

    }


}
