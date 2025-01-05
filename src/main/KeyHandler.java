package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;
import java.util.Set;

public class KeyHandler implements KeyListener {

    public boolean upPressed, downPressed, leftPressed, rightPressed, shiftPressed;
    public Set<Integer> pressedKeys = new HashSet<>();
    boolean keyPressed = false;


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        pressedKeys.add(e.getKeyCode());

        int code = e.getKeyCode(); // Returns the integer keyCode associated with the key in this event

        // Check if keys are pressed
        if(code == KeyEvent.VK_W) {
            upPressed = true;
        }

        if(code == KeyEvent.VK_S) {
            downPressed = true;
        }

        if(code == KeyEvent.VK_A) {
            leftPressed = true;
        }

        if(code == KeyEvent.VK_D) {
            rightPressed = true;
        }

        if(code == KeyEvent.VK_SHIFT) {
            shiftPressed = true;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

        pressedKeys.remove(e.getKeyCode());

        int code = e.getKeyCode();

        // Check if keys are released
        if(code == KeyEvent.VK_W) {
            upPressed = false;
        }

        if(code == KeyEvent.VK_S) {
            downPressed = false;
        }

        if(code == KeyEvent.VK_A) {
            leftPressed = false;
        }

        if(code == KeyEvent.VK_D) {
            rightPressed = false;
        }

        if(code == KeyEvent.VK_SHIFT) {
            shiftPressed = false;
        }

    }

    public boolean checkIfKeyPressed() {

        if (pressedKeys.isEmpty()) {
            return keyPressed = false;
        }
        else {
            return keyPressed = true;
        }

    }

}
