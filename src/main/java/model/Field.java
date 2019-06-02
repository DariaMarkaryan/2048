package model;

import java.util.Arrays;
import java.awt.event.KeyEvent;

public class Field {
    public final static int SIZE = 4;
    private Cell[][] cells = new Cell[SIZE][SIZE];
    public int score = 0;
    public int emptyCells = SIZE * SIZE;
    public boolean isFail = false;

    public void startGame() {
        score = 0;
        emptyCells = SIZE * SIZE;

        for (int i = 0; i < SIZE; i++)
            for (int j = 0; j < SIZE; j++) {
                cells[i][j] = new Cell(Values.EMPTY);
            }
            createRandomCell();
            createRandomCell();
    }

        public Cell getCell(int x, int y) {
            return cells[x][y];
    }

    private void createRandomCell() {
            Cell cell = cells[(int)(Math.random() * SIZE )][(int)(Math.random() * SIZE)];

            while (!cell.isEmpty()) {
                cell = cells[(int)(Math.random() * SIZE)][(int)(Math.random() * SIZE)];
            }
            if(Math.random() >= 0.0 && Math.random() <= 0.9){    //вероятность получить значение 2 в клетке = 0.9
                cell.setValue(Values.TWO);
            } else {
                cell.setValue(Values.FOUR);
            }
    }

    private void unmergeAll(){
        for(int i = 0; i < SIZE ; i++)
            for(int j = 0; j < SIZE ; j ++)
                cells[i][j].setMerged(false);
    }

    public void moveCells(int key){
        switch (key) {
            case KeyEvent.VK_UP: {
                up();
                break;
            }

            case KeyEvent.VK_DOWN: {
                down();
                break;
            }

            case KeyEvent.VK_LEFT: {
                left();
                break;
            }

            case KeyEvent.VK_RIGHT: {
                right();
                break;
            }
        }
            if(emptyCells == 0)
                        if(endGame())
                            isFail = true;

        if(emptyCells != 0)
            createRandomCell();

        this.unmergeAll();
    }

    private void down() {
        for(int i = 0; i < SIZE; i++){
            for(int j = SIZE - 2; j >= 0; j--) {
                if(cells[j][i].getValue() != Values.EMPTY){
                    for (int x = j + 1; x < SIZE; x++) {
                        if(cells[x][i].getValue() != Values.EMPTY){
                            if(cells[x][i].getValue() == cells[j][i].getValue() && !cells[x][i].isMerged()){
                                cells[x][i].setValue(Values.findByKey(cells[j][i].getValue().getNumberOnCell() * 2));
                                cells[x][i].setMerged(true);
                                cells[j][i].setValue(Values.EMPTY);
                                score += cells[x][i].getValue().numberOnCell;
                            }
                            if((cells[x][i].getValue() != cells[j][i].getValue() || cells[x][i].isMerged()) && x - 1 != j){
                                cells[x - 1][i].setValue(cells[j][i].getValue());
                                cells[j][i].setValue(Values.EMPTY);
                            }
                            break;
                        }
                        if(x == 3 && cells[x][i].getValue() == Values.EMPTY) {
                            cells[x][i].setValue(cells[j][i].getValue());
                            cells[j][i].setValue(Values.EMPTY);
                            break;
                        }
                    }
                }
            }
        }
    }

    private void right(){
        for(int i = 0; i < SIZE; i++){
            for(int j = SIZE - 2; j >= 0; j--) {
                if(cells[i][j].getValue() != Values.EMPTY){
                    for (int x = j + 1; x < SIZE; x++) {
                        if(cells[i][x].getValue() != Values.EMPTY){
                            if(cells[i][x].getValue() == cells[i][j].getValue() && !cells[i][x].isMerged()){
                                cells[i][x].setValue(Values.findByKey(cells[i][j].getValue().getNumberOnCell() * 2));
                                cells[i][x].setMerged(true);
                                cells[i][j].setValue(Values.EMPTY);
                                score += cells[i][x].getValue().getNumberOnCell();
                            }
                            if((cells[i][x].getValue() != cells[i][j].getValue() || cells[i][x].isMerged()) && x - 1 != j){
                                cells[i][x - 1].setValue(cells[i][j].getValue());
                                cells[i][j].setValue(Values.EMPTY);
                            }
                            break;
                        }
                        if(x == 3 && cells[i][x].getValue() == Values.EMPTY) {
                            cells[i][x].setValue(cells[i][j].getValue());
                            cells[i][j].setValue(Values.EMPTY);
                            break;
                        }
                    }
                }
            }
        }
    }

    private void left() {
        for(int i = 0; i < SIZE; i++){
            for(int j = 1; j < SIZE; j++) {
                if(cells[i][j].getValue() != Values.EMPTY){
                    for (int x = j - 1; x >= 0 ; x--) {
                        if(cells[i][x].getValue() != Values.EMPTY){
                            if(cells[i][x].getValue() == cells[i][j].getValue() && !cells[i][x].isMerged()){
                                cells[i][x].setValue(Values.findByKey(cells[i][j].getValue().getNumberOnCell() * 2));
                                cells[i][x].setMerged(true);
                                cells[i][j].setValue(Values.EMPTY);
                                score += cells[i][x].getValue().getNumberOnCell();
                            }

                            if((cells[i][x].getValue() != cells[i][j].getValue() || cells[i][x].isMerged()) && x + 1 != j){
                                cells[i][x + 1].setValue(cells[i][j].getValue());
                                cells[i][j].setValue(Values.EMPTY);
                            }
                            break;
                        }
                        if(x == 0 && cells[i][x].getValue() == Values.EMPTY) {
                            cells[i][x].setValue(cells[i][j].getValue());
                            cells[i][j].setValue(Values.EMPTY);
                            break;
                        }
                    }
                }
            }
        }
    }

    private void up() {
        for(int i = 0; i < SIZE; i++){
            for(int j = 1; j < SIZE; j++) {

                if(cells[j][i].getValue() != Values.EMPTY){
                    for (int x = j - 1; x >= 0 ; x--) {
                        if(cells[x][i].getValue() != Values.EMPTY){
                            if(cells[x][i].getValue() == cells[j][i].getValue() && !cells[x][i].isMerged()){
                                cells[x][i].setValue(Values.findByKey(cells[j][i].getValue().getNumberOnCell() * 2));
                                cells[x][i].setMerged(true);
                                cells[j][i].setValue(Values.EMPTY);
                                score += cells[x][i].getValue().numberOnCell;
                            }
                            if((cells[x][i].getValue() != cells[j][i].getValue() || cells[x][i].isMerged()) && x + 1 != j){
                                cells[x + 1][i].setValue(cells[j][i].getValue());
                                cells[j][i].setValue(Values.EMPTY);
                            }
                            break;
                        }
                        if(x == 0 && cells[x][i].getValue() == Values.EMPTY) {
                            cells[x][i].setValue(cells[j][i].getValue());
                            cells[j][i].setValue(Values.EMPTY);
                            break;
                        }
                    }
                }
            }
        }
    }

    private boolean endGame(){
        boolean hasEqualsCells = false;
        for(int i = 0; i < SIZE - 1; i++)
            for(int j = 0; j < SIZE - 1; j++){
                if(cells[i][j].getValue() == cells[i][j + 1].getValue() || cells[j][i].getValue() == cells[i + 1][j].getValue())
                    hasEqualsCells = true;
            }

        return !hasEqualsCells;
    }

    public Cell[][] getCells(){
        return cells;
    }

    public void printField(){
        for(int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (cells[i][j].getValue() == Values.EMPTY) {
                    System.out.print('*');
                } else {
                    System.out.print(cells[i][j].getValue().getNumberOnCell());
                }
            }
            System.out.println();
        }
    }

}

//                 ПОПЫТКА ОБЪЕДИНИТЬ up,down,left,right В ОДНУ ФУНКЦИЮ. ДЛИННО, ЗАПУТАЛАСЬ
//    private void moveCells2(int key) {
//        for (int i = 0; i < SIZE; i++) {
//
//            if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_UP) {
//                for (int j = SIZE - 3; j < SIZE; j++) {
//                    int x, y, t;
//                    t = j - 1;
//
//                    if (key == KeyEvent.VK_LEFT) {
//                        x = i;
//                        y = j;
//                    } else {
//                        x = j;
//                        y = i;
//                    }
//
//                    if (cells[x][y].getValue() != Values.EMPTY) {
//                        for (; t >= 0; t--) {
//                            if (cells[x][t].getValue() != Values.EMPTY) {
//                                if (cells[x][t].getValue() == cells[x][y].getValue() && !cells[x][t].isMerged()) {
//                                    cells[x][t].setValue(Values.findByKey(cells[x][y].getValue().getNumberOnCell() * 2));
//                                    cells[x][t].setMerged(true);
//                                    cells[x][y].setValue(Values.EMPTY);
//                                }
//                                if ((cells[x][t].getValue() != cells[x][y].getValue() || cells[x][y].isMerged()) && t + 1 != y) {
//                                    cells[x][t + 1].setValue(cells[x][y].getValue());
//                                    cells[x][y].setValue(Values.EMPTY);
//                                }
//                                break;
//                            }
//                            if (t == 0 && cells[x][t].getValue() == Values.EMPTY) {
//                                cells[x][t].setValue(cells[x][y].getValue());
//                                cells[x][y].setValue(Values.EMPTY);
//                                break;
//                            }
//                        }
//                    }
//                }
//            }
//
//            if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_DOWN) {
//                for (int j = SIZE - 3; j < SIZE; j++) {
//                    int x, y, t;
//                    t = j + 1;
//
//                    if (key == KeyEvent.VK_RIGHT) {
//                        x = i;
//                        y = j;
//                    } else {
//                        x = j;
//                        y = i;
//                    }
//                    if (cells[x][y].getValue() != Values.EMPTY) {
//                        for (; t < SIZE ; t++) {
//                            if (key == KeyEvent.VK_DOWN){
//                            if (cells[t][y].getValue() != Values.EMPTY) {
//                                if (cells[t][y].getValue() == cells[x][y].getValue() && !cells[t][y].isMerged()) {
//                                    cells[t][y].setValue(Values.findByKey(cells[x][y].getValue().getNumberOnCell() * 2));
//                                    cells[t][y].setMerged(true);
//                                    cells[x][y].setValue(Values.EMPTY);
//                                }
//                                if ((cells[t][y].getValue() != cells[x][y].getValue() || cells[t][y].isMerged()) && t - 1 != x) {
//                                    cells[t - 1][y].setValue(cells[x][y].getValue());
//                                    cells[x][y].setValue(Values.EMPTY);
//                                }
//                                break;
//                            }
//                                if (t == 3 && cells[t][y].getValue() == Values.EMPTY) {
//                                    cells[t][y].setValue(cells[x][y].getValue());
//                                    cells[x][y].setValue(Values.EMPTY);
//                                    break;
//                                }
//                            } else {
//                                if (cells[x][t].getValue() != Values.EMPTY) {
//                                    if (cells[x][t].getValue() == cells[x][y].getValue() && !cells[x][t].isMerged()) {
//                                        cells[x][t].setValue(Values.findByKey(cells[x][y].getValue().getNumberOnCell() * 2));
//                                        cells[x][t].setMerged(true);
//                                        cells[x][y].setValue(Values.EMPTY);
//                                    }
//                                    if ((cells[x][t].getValue() != cells[x][y].getValue() || cells[x][t].isMerged()) && t - 1 != y) {
//                                        cells[x][t - 1].setValue(cells[x][y].getValue());
//                                        cells[x][y].setValue(Values.EMPTY);
//                                    }
//                                    break;
//                                }
//                                if (t == 3 && cells[x][t].getValue() == Values.EMPTY) {
//                                    cells[x][t].setValue(cells[x][y].getValue());
//                                    cells[x][y].setValue(Values.EMPTY);
//                                    break;
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//        }
//    }