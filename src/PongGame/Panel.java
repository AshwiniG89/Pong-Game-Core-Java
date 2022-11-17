package PongGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class Panel extends JPanel implements Runnable{

    static final int GAME_WIDTH=1000;
    static final int GAME_HEIGHT=(int)(GAME_WIDTH*(0.5555));//double value(5/9)--actual width by height ratio of table
    static final Dimension SCREEN_SIZE=new Dimension(GAME_WIDTH,GAME_HEIGHT);
    static final int BALL_DIAMETER =20;
    static final int PADDLE_WIDTH=25;
    static final int PADDLE_HEIGHT=100;
    static final int MAX_SCORE = 5;
    static boolean gameRun = true;
    Thread gameThread;
    Image image;
    Graphics graphics;
    Random random;
    Paddle paddle1;
    Paddle paddle2;
    Ball ball;
    ScoreBoard score;

    Panel(){
        newPaddles();
        newBall();
        score=new ScoreBoard(GAME_WIDTH,GAME_HEIGHT);
        this.setFocusable(true);
        this.addKeyListener(new AL());
        this.setPreferredSize(SCREEN_SIZE);

        gameThread=new Thread(this);
        gameThread.start();
    }

    public void newBall(){
        random =new Random();
        ball=new Ball((GAME_WIDTH/2)-(BALL_DIAMETER/2),random.nextInt(GAME_HEIGHT-BALL_DIAMETER),BALL_DIAMETER,BALL_DIAMETER);
    }
    public void newPaddles(){

        paddle1 = new Paddle(0,(GAME_HEIGHT/2)-(PADDLE_HEIGHT/2),PADDLE_WIDTH,PADDLE_HEIGHT,1);
        paddle2 = new Paddle((GAME_WIDTH-PADDLE_WIDTH),(GAME_HEIGHT/2)-(PADDLE_HEIGHT/2),PADDLE_WIDTH,PADDLE_HEIGHT,2);

    }
    public void paint(Graphics g){
        image= createImage(getWidth(),getHeight());
        graphics = image.getGraphics();
        draw(graphics);
        g.drawImage(image,0,0,this);
    }
    public void draw(Graphics g){

        paddle1.draw(g);
        paddle2.draw(g);
        ball.draw(g);
        ScoreBoard.draw(g);
    }
    public void move(){
        paddle1.move();
        paddle2.move();
        ball.move();
    }
    public void checkCollision(){
        // stops paddles to go out of boundaries

        if(paddle1.y <= 0)
            paddle1.y = 0;
        if(paddle1.y >= (GAME_HEIGHT - PADDLE_HEIGHT))
            paddle1.y=GAME_HEIGHT - PADDLE_HEIGHT;

        if(paddle2.y <= 0)
            paddle2.y = 0;
        if(paddle2.y >= (GAME_HEIGHT - PADDLE_HEIGHT))
            paddle2.y=GAME_HEIGHT - PADDLE_HEIGHT;
        if(ball.y <= 0){
            ball.setYDirection(-ball.yVelocity);
        }
        if(ball.y >= GAME_HEIGHT-BALL_DIAMETER){
            ball.setYDirection(-ball.yVelocity);
        }

        //bounce ball off paddles
        if(ball.intersects(paddle1)){
            ball.xVelocity = Math.abs(ball.xVelocity);
            if(ball.xVelocity >0){
                ball.yVelocity++;
            }else
                ball.yVelocity--;
            ball.setXDirection(ball.xVelocity);
            ball.setYDirection(ball.yVelocity);
        }
        if(ball.intersects(paddle2)){
            ball.xVelocity = Math.abs(ball.xVelocity);
            if(ball.xVelocity >0){
                ball.yVelocity++;
            }else
                ball.yVelocity--;
            ball.setXDirection(-ball.xVelocity);
            ball.setYDirection(ball.yVelocity);
        }
        // gives a player 1 point and creates new paddle and ball
        if(ball.x <= 0){
            score.player2++;
               if (score.player2 < MAX_SCORE) {
                   newPaddles();
                   newBall();
               }else{
                   gameRun =false;
                   System.out.println("GAME OVER");
                   System.out.println("WINNER IS PLAYER-2");
               }
        }
        if(ball.x >= GAME_WIDTH-BALL_DIAMETER){
            score.player1++;
            if(score.player1 < MAX_SCORE) {
                newPaddles();
                newBall();
            }else{
                gameRun = false;
                System.out.println("GAME OVER");
                System.out.println("WINNER IS PLAYER-1");
            }
        }
    }
    public void run(){
        //loop
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000/amountOfTicks;
        double delta = 0;
        while(gameRun){
            long now = System.nanoTime();
            delta+=((now-lastTime)/ns);
            lastTime =now;
            if(delta >= 1) {
                move();
                checkCollision();
                repaint();
                delta--;
            }
        }
    }

    //action Listener
    public  class AL extends KeyAdapter {
        public void keyPressed(KeyEvent e) {
            paddle1.keyPressed(e);
            paddle2.keyPressed(e);

        }

        public void keyReleased(KeyEvent e) {
            paddle1.keyReleased(e);
            paddle2.keyReleased(e);
        }
    }
}
