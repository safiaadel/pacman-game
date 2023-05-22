package com.pacman;

import javax.swing.*;
import java.awt.*;

public class Board extends JPanel {

    Image startScreen = Toolkit.getDefaultToolkit().getImage("./src/pic/01-startScreen.jpg");
    Image winScreen = Toolkit.getDefaultToolkit().getImage("./src/pic/02-winScreen.jpg");
    Image overScreen = Toolkit.getDefaultToolkit().getImage("./src/pic/03-overScreen.jpg");

    // Ghosts images.
    Image redGhostRight = Toolkit.getDefaultToolkit().getImage("./src/pic/ghost10.jpg");
    Image redGhostLeft = Toolkit.getDefaultToolkit().getImage("./src/pic/ghost11.jpg");
    Image[] ghostsRed = {redGhostRight, redGhostLeft};

    Image yellowGhostRight = Toolkit.getDefaultToolkit().getImage("./src/pic/ghost20.jpg");
    Image yellowGhostLeft = Toolkit.getDefaultToolkit().getImage("./src/pic/ghost21.jpg");
    Image[] ghostsYellow = {yellowGhostRight, yellowGhostLeft};

    Image blueGhostRight = Toolkit.getDefaultToolkit().getImage("./src/pic/ghost30.jpg");
    Image blueGhostLeft = Toolkit.getDefaultToolkit().getImage("./src/pic/ghost31.jpg");
    Image[] ghostsBlue = {blueGhostRight, blueGhostLeft};

    Image pinkGhostRight = Toolkit.getDefaultToolkit().getImage("./src/pic/ghost40.jpg");
    Image pinkGhostLeft = Toolkit.getDefaultToolkit().getImage("./src/pic/ghost41.jpg");
    Image[] ghostsPink = {pinkGhostRight, pinkGhostLeft};


    // Pacman images.
    Image pacmanFull = Toolkit.getDefaultToolkit().getImage("./src/pic/pacman.jpg");
    Image pacmanLeft = Toolkit.getDefaultToolkit().getImage("./src/pic/pacmanleft.jpg");
    Image pacmanRight = Toolkit.getDefaultToolkit().getImage("./src/pic/pacmanright.jpg");
    Image pacmanUp = Toolkit.getDefaultToolkit().getImage("./src/pic/pacmanup.jpg");
    Image pacmanDown = Toolkit.getDefaultToolkit().getImage("./src/pic/pacmandown.jpg");
    Image[] pacmanImages = {pacmanLeft, pacmanRight, pacmanUp, pacmanDown};

    // Pacman.
    Pacman pacman = new Pacman(10 * Component.cellSize, 15 * Component.cellSize);

    // Ghosts.
    Ghost ghost1 = new Ghost(10 * Component.cellSize, 8 * Component.cellSize);
    Ghost ghost2 = new Ghost(10 * Component.cellSize, 9 * Component.cellSize);
    Ghost ghost3 = new Ghost(11 * Component.cellSize, 9 * Component.cellSize);
    Ghost ghost4 = new Ghost(9 * Component.cellSize, 9 * Component.cellSize);

    boolean start;
    boolean[][] balls;
    boolean[][] states;
    int lives = 9;
    int score;

    public void drawLives(Graphics g) {
        g.setColor(Color.yellow);
        for (int i = 0; i < lives; i++) {
            g.drawImage(pacmanLeft, (Component.cellSize + 5) * i + 15, Component.max + 20, null);
        }
    }

    public void init() {
        for (int i = 0; i < Component.cellSize; i++) {
            for (int j = 0; j < Component.cellSize; j++) {
                balls[i][j] = true;
                states[i][j] = true;
            }
        }
        balls[10][8] = false;
        balls[10][9] = false;
        balls[11][9] = false;
        balls[9][9] = false;
        balls[10][15] = false;
    }

    public Board() {
        start = true;
        balls = new boolean[Component.cellSize][Component.cellSize];
        states = new boolean[Component.cellSize][Component.cellSize];
        init();
    }

    public void update(Graphics g, int x, int y, int width, int height) {
        g.fillRect(x, y, width, height);
        for (int i = x / 20; i < x / 20 + width / 20; i++) {
            for (int j = y / 20; j < y / 20 + height / 20; j++) {
                balls[i][j] = false;
                states[i - 1][j - 1] = false;
            }
        }
    }

    public void drawBalls(Graphics g) {
        g.setColor(Color.yellow);
        for (int i = 1; i < Component.cellSize; i++) {
            for (int j = 1; j < Component.cellSize; j++) {
                if (balls[i][j])
                    g.fillOval(i * 20 + 8, j * 20 + 8, 4, 4);
            }
        }
    }

    public void reset() {
        if (lives > 0)
            lives--;
        ghost1.x = 10 * Component.cellSize;
        ghost1.y = 8 * Component.cellSize;
        ghost2.x = 10 * Component.cellSize;
        ghost2.y = 9 * Component.cellSize;
        ghost3.x = 11 * Component.cellSize;
        ghost3.y = 9 * Component.cellSize;
        ghost4.x = 9 * Component.cellSize;
        ghost4.y = 9 * Component.cellSize;
        pacman.x = 10 * Component.cellSize;
        pacman.y = 15 * Component.cellSize;
        Game.flag = true;
    }

    public boolean check() {
        for (int i = 1; i < Component.cellSize; i++) {
            for (int j = 1; j < Component.cellSize; j++) {
                if (balls[i][j])
                    return false;
            }
        }
        return true;
    }

    public void drawBoard(Graphics g) {
        g.setColor(Color.white);
        g.drawRect(19, 19, 382, 382);
        g.setColor(Color.blue);
        update(g, 40, 40, 60, 20);
        update(g, 120, 40, 60, 20);
        update(g, 200, 20, 20, 40);
        update(g, 240, 40, 60, 20);
        update(g, 320, 40, 60, 20);
        update(g, 40, 80, 60, 20);
        update(g, 160, 80, 100, 20);
        update(g, 200, 80, 20, 60);
        update(g, 320, 80, 60, 20);
        update(g, 20, 120, 80, 60);
        update(g, 320, 120, 80, 60);
        update(g, 20, 200, 80, 60);
        update(g, 320, 200, 80, 60);
        update(g, 160, 160, 40, 20);
        update(g, 220, 160, 40, 20);
        update(g, 160, 180, 20, 20);
        update(g, 160, 200, 100, 20);
        update(g, 240, 180, 20, 20);
        update(g, 120, 120, 60, 20);
        update(g, 120, 80, 20, 100);
        update(g, 280, 80, 20, 100);
        update(g, 240, 120, 60, 20);
        update(g, 280, 200, 20, 60);
        update(g, 120, 200, 20, 60);
        update(g, 160, 240, 100, 20);
        update(g, 200, 260, 20, 40);
        update(g, 120, 280, 60, 20);
        update(g, 240, 280, 60, 20);
        update(g, 40, 280, 60, 20);
        update(g, 80, 280, 20, 60);
        update(g, 320, 280, 60, 20);
        update(g, 320, 280, 20, 60);
        update(g, 20, 320, 40, 20);
        update(g, 360, 320, 40, 20);
        update(g, 160, 320, 100, 20);
        update(g, 200, 320, 20, 60);
        update(g, 40, 360, 140, 20);
        update(g, 240, 360, 140, 20);
        update(g, 280, 320, 20, 60);
        update(g, 120, 320, 20, 60);

        repaint();
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(0, 0, 420, 500);
        drawBoard(g);
        drawBalls(g);
        drawLives(g);
        Font f = new Font("Arial", Font.BOLD, 20);
        g.setFont(f);
        g.drawString("Score: " + score, Component.max / 2 + 100, Component.max + 35);
        g.drawImage(ghostsRed[ghost1.index], ghost1.x, ghost1.y, null);
        g.drawImage(ghostsYellow[ghost2.index], ghost2.x, ghost2.y, null);
        g.drawImage(ghostsPink[ghost3.index], ghost3.x, ghost3.y, null);
        g.drawImage(ghostsBlue[ghost4.index], ghost4.x, ghost4.y, null);
        g.drawImage(pacmanImages[pacman.index], pacman.x, pacman.y, null);
        if (start)
            g.drawImage(startScreen, 0, 0, null);
        if (lives == 0)
            g.drawImage(overScreen, 0, 0, null);
        if (check())
            g.drawImage(winScreen, 0, 0, null);
    }

}
