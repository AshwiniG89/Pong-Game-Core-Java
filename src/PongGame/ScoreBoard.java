package PongGame;

import java.awt.*;

public class ScoreBoard extends Rectangle{

    static int GAME_WIDTH;
    static int GAME_HEIGHT;
    static int player1;
    static int player2;

    ScoreBoard(int GAME_WIDTH,int GAME_HEIGHT){
        ScoreBoard.GAME_WIDTH = GAME_WIDTH;
        ScoreBoard.GAME_HEIGHT = GAME_HEIGHT;
    }
    public static void draw(Graphics g){
        g.setColor(Color.CYAN);
        g.setFont(new Font(" Arial",Font.PLAIN,35));

        g.drawLine(GAME_WIDTH/2,0,GAME_WIDTH/2,GAME_HEIGHT);

        g.drawString(String.valueOf(player1/10)+String.valueOf(player1%10),(GAME_WIDTH/2)-85,50);
        g.drawString(String.valueOf(player2/10)+String.valueOf(player2%10),(GAME_WIDTH/2)+20,50);
    }
}
