package nevagonlose.TimeDash;

public class Platform {
    public float x;
    public float y;
    public float height;
    public float width;
    public boolean hitDetected = false;
    public float oldX;
    public float oldY;


    public void collision(Player player){
        //for landing on top
        if (player.x + player.width/1.5 >= x && player.x + player.width/4 <= x + width && player.y + player.height >=  y && player.y+player.height/4  <= y + height) {
            if (player.y + player.height <= y + height / 3) {
                player.y = y - player.height;
                player.onPlatform = true;
                player.fall = false;
                player.acceleration = 0;
            } else {
                hitDetected = true;
            }
        } else {
            player.onPlatform = false;
        }
    }

    public void updatePosition(float screenWidth){
        x = x - screenWidth/200;
    }


}
