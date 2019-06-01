package model;

import java.util.Arrays;
import java.awt.event.KeyEvent;

public class Field {
    public final static int SIZE = 4;
    private Cell[][] cells = new Cell[SIZE][SIZE];
    private int score;
    private int emptyCells = SIZE * SIZE;

    public Field(){
    }

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
            emptyCells--;
    }

    private void unmixAll(){
        for(int i = 0; i < SIZE - 1 ; i++)
            for(int j = 0; j < SIZE - 1 ; j ++)
                cells[i][j].setMerged(false);
    }

    public void moveCells(int key){
    this.unmixAll();
        switch (key) {
            case KeyEvent.VK_UP: {
                up();
                createRandomCell();
                break;
            }

            case KeyEvent.VK_DOWN: {
                down();
                createRandomCell();
                break;
            }

            case KeyEvent.VK_LEFT: {
                left();
                createRandomCell();
                break;
            }

            case KeyEvent.VK_RIGHT: {
                right();
                createRandomCell();
                break;
            }
        }
            if(isFail()) {
                endGame();
            }
    this.unmixAll();
    }

    private void up() {
        for(int i = 0; i < 4; i++) {
            for(int j = 1; j < 4; j++){
                Cell temp = cells[j][i];

                if(temp.getValue() != Values.EMPTY){
                    Cell ktemp;
                    int whereIsK = j - 1;

                    for(int k = j - 1; k >= 0; k--){
                        if(cells[k][i].getValue() != Values.EMPTY) {
                            whereIsK = k;
                            break;
                        }
                    whereIsK = k;
                }
                    ktemp = cells[whereIsK][i];

                if(ktemp.getValue() !=  Values.EMPTY) {
                    if (ktemp.getValue() == temp.getValue() && !ktemp.isMerged()) {
                        ktemp.setValue(Values.findByKey(temp.getValue().getNumberOnCell() * 2));
                        ktemp.setMerged(true);
                        temp.setValue(Values.EMPTY);
                        emptyCells--;
                        score += temp.getValue().getNumberOnCell();
                    } else {
                        cells[whereIsK + 1][i].setValue(temp.getValue());
                        temp.setValue(Values.EMPTY);
                    }
                } else {
                    ktemp.setValue(temp.getValue());
                    temp.setValue(Values.EMPTY);
                }
            }
        }
        }
    }

    private void down(){
        for(int i = 0; i < 4; i++) {
            for(int j = 2; j >= 0; j--){
                Cell temp = cells[j][i];

                if(temp.getValue() != Values.EMPTY){
                    Cell ktemp;
                    int whereIsK = j + 1;

                    for(int k = j + 1; k < 4; k++){
                       if(cells[k][i].getValue() != Values.EMPTY) {
                           whereIsK = k;
                           break;
                       }
                       whereIsK = k;
                   }
                    ktemp = cells[whereIsK][i];

                       if(ktemp.getValue() !=  Values.EMPTY) {
                           if (ktemp.getValue() == temp.getValue() && !ktemp.isMerged()) {
                               ktemp.setValue(Values.findByKey(temp.getValue().getNumberOnCell() * 2));
                               ktemp.setMerged(true);
                               temp.setValue(Values.EMPTY);
                               emptyCells--;
                               score += temp.getValue().getNumberOnCell();
                           } else {
                               cells[whereIsK - 1][i].setValue(temp.getValue());
                               temp.setValue(Values.EMPTY);
                           }
                       } else {
                            ktemp.setValue(temp.getValue());
                            temp.setValue(Values.EMPTY);
                        }
                    }
                }
            }
        }

        /*
        *
        *
        *  private void down() {
        for(int i = 0; i < SIZE; i++){

             for(int j = SIZE - 2; j >= 0; j--) {
                 if(cells[j][i].getValue() != Values.EMPTY){

                    for (int x = j + 1; x < SIZE; x++) {
                        if(cells[x][i].getValue() != Values.EMPTY){
                            //находим первую непустую и делаем что то с cells[j][i]
                            if(cells[x][i].getValue() == cells[j][i].getValue() && !cells[x][i].isMerged()){
                                cells[x][i].setValue(Values.findByKey(cells[j][i].getValue().getNumberOnCell() * 2));
                                cells[x][i].setMerged(true);
                                cells[j][i].setValue(Values.EMPTY);
                            }
                            if((cells[x][i].getValue() != cells[j][i].getValue() || cells[x][i].isMerged()) && x - 1 != j){
                                cells[x - 1][i].setValue(cells[j][i].getValue());
                                cells[j][i].setValue(Values.EMPTY);
                            }
                            break;
                        }
                        if(x == 3 && cells[x][i].getValue() == Values.EMPTY) {
                            // если дошли до низа и не встретили непустую, то туда будем пихать cells[j][i]
                        cells[x][i].setValue(cells[j][i].getValue());
                        cells[j][i].setValue(Values.EMPTY);
                        break;
                       }
                    }
                  }
                }
            }
        for(int t = 0; t < SIZE; t++)
            for(int j = 0; j < SIZE; j++){
                cells[j][t].setMerged(false);
                }
        }
        *
        * */


    private void right(){
        for(int i = 0; i < 4; i++) {
            for(int j = 2; j >= 0; j--){
                Cell temp = cells[i][j];

                if(temp.getValue() != Values.EMPTY){
                    Cell ktemp;
                    int whereIsK = j + 1;

                    for(int k = j + 1; k < 4; k++){
                        if(cells[i][k].getValue() != Values.EMPTY) {
                            whereIsK = k;
                            break;
                        }
                        whereIsK = k;
                    }
                    ktemp = cells[i][whereIsK];

                    if(ktemp.getValue() !=  Values.EMPTY) {
                        if (ktemp.getValue() == temp.getValue() && !ktemp.isMerged()) {
                            ktemp.setValue(Values.findByKey(temp.getValue().getNumberOnCell() * 2));
                            ktemp.setMerged(true);
                            temp.setValue(Values.EMPTY);
                            emptyCells--;
                            score += temp.getValue().getNumberOnCell();
                        } else {
                            cells[i][whereIsK - 1].setValue(temp.getValue());
                            temp.setValue(Values.EMPTY);
                        }
                    } else {
                        ktemp.setValue(temp.getValue());
                        temp.setValue(Values.EMPTY);
                    }
                }
            }
        }
    }

    private void left(){
        for(int i = 0; i < 4; i++) {
            for(int j = 1; j < 4; j++){
                Cell temp = cells[i][j];

                if(temp.getValue() != Values.EMPTY){
                    Cell ktemp;
                    int whereIsK = j - 1;

                    for(int k = j - 1; k >= 0; k--){
                        if(cells[i][k].getValue() != Values.EMPTY) {
                            whereIsK = k;
                            break;
                        }
                        whereIsK = k;
                    }
                    ktemp = cells[i][whereIsK];

                    if(ktemp.getValue() !=  Values.EMPTY) {
                        if (ktemp.getValue() == temp.getValue() && !ktemp.isMerged()) {
                            ktemp.setValue(Values.findByKey(temp.getValue().getNumberOnCell() * 2));
                            ktemp.setMerged(true);
                            temp.setValue(Values.EMPTY);
                            emptyCells--;
                            score += temp.getValue().getNumberOnCell();
                        } else {
                            cells[i][whereIsK + 1].setValue(temp.getValue());
                            temp.setValue(Values.EMPTY);
                        }
                    } else {
                        ktemp.setValue(temp.getValue());
                        temp.setValue(Values.EMPTY);
                    }
                }
            }
        }
    }

    private boolean isFail(){
            final  Cell[][] oldCells = cells;
            boolean isEquals = false;

            moveCells(KeyEvent.VK_UP);
            moveCells(KeyEvent.VK_DOWN);
            moveCells(KeyEvent.VK_LEFT);
            moveCells(KeyEvent.VK_RIGHT);

            if(Arrays.deepEquals(oldCells, cells))
                isEquals = true;
            else
                cells = oldCells;
            return isEquals;
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
    public void endGame(){}
}