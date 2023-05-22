package com.pacman;

import java.util.HashSet;
import java.util.Set;

public class Ghost extends Component {
    public Ghost(int x, int y) {
        this.x = x;
        this.y = y;
        direction = 'L';
    }

    public boolean choice() {
        return x % cellSize == 0 && y % cellSize == 0;
    }

    public char selectDirection() {
        int randome;
        int newX = x;
        int newY = y;
        Set<Character> mySet = new HashSet<Character>();
        char backwards = 'R';
        switch (direction) {
            case 'L':
                backwards = 'R';
                break;
            case 'R':
                backwards = 'L';
                break;
            case 'U':
                backwards = 'D';
                break;
            case 'D':
                backwards = 'U';
                break;
        }
        char newDirection = backwards;
        while (newDirection == backwards || !isValid(newX, newY)) {
            if (mySet.size() == 3) {
                mySet.clear();
                newDirection = backwards;
                break;
            }
            randome = (int) (Math.random() * 4) + 1;
            if (randome == 1) {
                newDirection = 'L';
                newX -= speed;
            } else if (randome == 2) {
                newDirection = 'R';
                newX += cellSize;
            } else if (randome == 3) {
                newDirection = 'U';
                newY -= speed;
            } else if (randome == 4) {
                newDirection = 'D';
                newY += cellSize;
            }
            if (newDirection != backwards) {
                mySet.add(newDirection);
            }
            index = randome % 2;
        }
        return newDirection;
    }

    public void move() {
        if (choice()) {
            direction = selectDirection();
        }
        switch (direction) {
            case 'L':
                if (isValid(x - speed, y))
                    x -= speed;
                break;
            case 'R':
                if (isValid(x + cellSize, y))
                    x += speed;
                break;
            case 'U':
                if (isValid(x, y - speed))
                    y -= speed;
                break;
            case 'D':
                if (isValid(x, y + cellSize))
                    y += speed;
                break;
        }
    }
}
