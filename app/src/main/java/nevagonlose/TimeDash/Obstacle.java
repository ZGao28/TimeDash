package nevagonlose.TimeDash;

public class Obstacle {
    public float x;
    public float y;
    public float height;
    public float width;
    public boolean hitDetected = false;
    public float oldX;
    public float oldY;



    public void collision(Player player){
        //colliding with object
        if (player.x + player.width/1.25 >= (x)  && player.x + player.width/15<= x && player.y + player.height/1.25 >=  y && player.y +player.height/5 <= y ){
            hitDetected = true;
        }
    }

    public void collision2(Player player){
        if (player.x + player.width/1.25 >= (x + width/5)  && player.x + player.width/5<= x - width/3 && player.y + player.height/1.25 >=  y&& player.y+player.height/5  <= y){
            hitDetected = true;
        }
    }

    public void collision3(Player player){
        if (player.x + player.width/1.25 >= (x + width/2.8)  && player.x+ player.width/15 <= x - width/3 && player.y + player.height/1.25 >=  y  && player.y +player.height/5 <= y){
            hitDetected = true;
        }
    }

    public void collision4(Player player){
        //colliding with object
        if (player.x + player.width/1.25 >= x  && player.x + player.width/15<= x && player.y + player.height/1.25 >=  (y) && player.y +player.height/5 <= y){
            hitDetected = true;
        }
    }

    public void updatePosition(float screenWidth){
            x = x - screenWidth/200;
    }

}
