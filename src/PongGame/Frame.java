package PongGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Frame extends JFrame {

    Panel panel;

    Frame(){
        panel = new Panel();
        this.add(panel);
        this.setTitle("PONG GAME");
        this.setResizable(false);
        this.setBackground(Color.BLACK);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();//fit the game panel size automatically
        this.setVisible(true);
        this.setLocationRelativeTo(null);

    }
}
