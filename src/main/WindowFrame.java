package main;

import javax.swing.*;

public class WindowFrame extends JFrame {

    public WindowFrame() {

        GamePanel gamePanel = new GamePanel();

        // Update the screen with new information
        gamePanel.startGameThread();

        // Frame
        this.setTitle("The Game");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.add(gamePanel);
        this.pack(); // Causes this window to be sized to fit the preferred size and layout of its subcomponent (=GamePanel)
        this.setLocationRelativeTo(null); // Display window at the center of the screen

        // Visibility
        this.setVisible(true);

    }

}
