package PongGame;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Paddle extends Rectangle{

    int id;
    int yVelocity;//paddle movement
    int speed = 10;

    Paddle(int x,int y,int PADDLE_WIDTH,int PADDLE_HEIGHT,int id ){
        super(x,y,PADDLE_WIDTH,PADDLE_HEIGHT);
        this.id = id;

    }
    public void keyPressed(KeyEvent e){
        switch(id){
            case 1:
                if(e.getKeyCode() == KeyEvent.VK_W){//W to move up
                    setYDirection(-speed);
                    move();
                }
                if(e.getKeyCode() == KeyEvent.VK_S){//s to move down
                    setYDirection(speed);
                    move();
                }
                break;
            case 2:
                if(e.getKeyCode() == KeyEvent.VK_UP){//W to move up
                    setYDirection(-speed);
                    move();
                }
                if(e.getKeyCode() == KeyEvent.VK_DOWN){//s to move down
                    setYDirection(speed);
                    move();
                }
                break;
        }
    }
    public void keyReleased(KeyEvent e){
        switch(id){
            case 1:
                if(e.getKeyCode() == KeyEvent.VK_W){//W to move up
                    setYDirection(0);
                    move();
                }
                if(e.getKeyCode() == KeyEvent.VK_S){//s to move down
                    setYDirection(0);
                    move();
                }
                break;
            case 2:
                if(e.getKeyCode() == KeyEvent.VK_UP){//W to move up
                    setYDirection(0);
                    move();
                }
                if(e.getKeyCode() == KeyEvent.VK_DOWN){//s to move down
                    setYDirection(0);
                    move();
                }
                break;
        }
    }
    public void setYDirection(int yDirection){
        yVelocity =yDirection;
    }
    public void move(){
        y=y+yVelocity;
    }
    public void draw(Graphics g){
        if(id == 1)
            g.setColor(Color.BLUE);
        else
            g.setColor(Color.GREEN);
        g.fillRect(x,y,width,height);
    }

}
