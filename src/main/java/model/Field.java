package model;

import java.awt.event.KeyEvent;
import java.util.Arrays;

public class Field {
    private model.Cell[][] cells = new model.Cell[4][4];
    private int score;
    private boolean isWin;
    private int emptyCells = 4 * 4;

    public void startGame() {
        score = 0;
        emptyCells = 4 * 4;

        for (int i = 0; i < 4; i++)
            for (int j = 0; j < 4; j++) {
                cells[i][j] = new Cell();
                cells[i][j].setValue(Values.EMPTY);
            }
            createRandomCell();
            createRandomCell();
    }

        public Cell getCell(int x, int y) {
            return cells[x][y];
    }

    private void createRandomCell() {
            Cell cell = cells[(int)(Math.random()*4)][(int)(Math.random()*4)];

            while (!cell.isEmpty()) {
                cell = cells[(int)(Math.random()*4)][(int)(Math.random()*4)];
            }
            if((int)(Math.random()*4) % 2 == 0){
                cell.setValue(Values.TWO);
            } else {
                cell.setValue(Values.FOUR);
            }
            emptyCells++;
    }

    public void moveCells(int key){
        Cell[][] ghost = getCellsGhost();

        switch (key) {
            case KeyEvent.VK_UP: {
                Up();
                break;
            }

            case KeyEvent.VK_DOWN: {
                Down();
                break;
            }

            case KeyEvent.VK_LEFT: {
                Left();
                break;
            }

            case KeyEvent.VK_RIGHT: {
                Right();
                break;
            }
        }
            if(isFail()) {
                endGame();
            } else{
                if(Arrays.deepEquals(cells, ghost))
                    createRandomCell();
            }
    }

    private void Up() {
        for (int i = 0; i < 4; i++) {
            for (int j = 1; j <= 3; j++) {
                Cell temp = cells[j][i];

                if (temp.getValue() != Values.EMPTY) {
                    int k = j - 1;
                    Cell ktemp = cells[k][i];

                    while ( k >= 0 && cells[k][i].getValue() == Values.EMPTY) {
                        k--;
                    }

                    if (ktemp.getValue() != Values.EMPTY) {
                        if (ktemp.getValue() == temp.getValue() && !ktemp.isMerged()) {
                            ktemp.setValue(Values.findByKey(temp.getValue().getNumberOnCell() * 2));
                            ktemp.setMerged(true);
                            temp.setValue(Values.EMPTY);
                            emptyCells--;
                            createRandomCell();
                            score += temp.getValue().getNumberOnCell();
                        } else {
                            createRandomCell();
                        }
                    } else {
                        ktemp.setValue(temp.getValue());
                        temp.setValue(Values.EMPTY);
                        createRandomCell();
                    }
                }
            }
        }
    }

    private void Down(){
        for(int i = 0; i < 4; i++) {
            for(int j = 2; j >= 0; j--){
                Cell temp = cells[j][i];

                if(temp.getValue() != Values.EMPTY){
                    int k = j + 1;
                    Cell ktemp = cells[k][i];

                    while (k <= 3 && cells[k][i].getValue() == Values.EMPTY) {
                        k++;
                    }
                        if(ktemp.getValue() != Values.EMPTY){
                            if(ktemp.getValue() == temp.getValue() && !ktemp.isMerged()) {
                                ktemp.setValue(Values.findByKey(temp.getValue().getNumberOnCell() * 2));
                                ktemp.setMerged(true);
                                temp.setValue(Values.EMPTY);
                                emptyCells--;
                                createRandomCell();
                                score += temp.getValue().getNumberOnCell();
                            } else {
                                createRandomCell();
                            }
                        } else{
                            ktemp.setValue(temp.getValue());
                            temp.setValue(Values.EMPTY);
                            createRandomCell();
                        }
                    }
                }
            }
        }

    private void Right(){
        for(int i = 0; i < 4; i++) {
            for(int j = 2; j >= 0; j--){
                Cell temp = cells[i][j];

                if(temp.getValue() != Values.EMPTY){
                    int k = j + 1;
                    Cell ktemp = cells[i][k];

                    while (cells[i][k].getValue() == Values.EMPTY && k <= 3) {
                        k++;
                    }

                    if(ktemp.getValue() != Values.EMPTY){
                        if(ktemp.getValue() == temp.getValue() && !ktemp.isMerged()) {
                            ktemp.setValue(Values.findByKey(temp.getValue().getNumberOnCell() * 2));
                            ktemp.setMerged(true);
                            temp.setValue(Values.EMPTY);
                            emptyCells--;
                            createRandomCell();
                            score += temp.getValue().getNumberOnCell();
                        } else {
                            createRandomCell();
                        }
                    } else{
                        ktemp.setValue(temp.getValue());
                        temp.setValue(Values.EMPTY);
                        createRandomCell();
                    }
                }
            }
        }
    }

    private void Left(){

        for(int i = 0; i < 4; i++) {
            for(int j = 1; j <= 3; j++){
                Cell temp = cells[i][j];

                if(temp.getValue() != Values.EMPTY){
                    int k = j - 1;
                    Cell ktemp = cells[i][k];

                    while (k >= 0 && cells[i][k].getValue() == Values.EMPTY) {
                        k--;
                    }
                    if(ktemp.getValue() != Values.EMPTY){
                        if(ktemp.getValue() == temp.getValue() && !ktemp.isMerged()) {
                            ktemp.setValue(Values.findByKey(temp.getValue().getNumberOnCell() * 2));
                            ktemp.setMerged(true);
                            temp.setValue(Values.EMPTY);
                            emptyCells--;
                            createRandomCell();
                            score += temp.getValue().getNumberOnCell();
                        } else {
                            createRandomCell();
                        }
                    } else{
                        ktemp.setValue(temp.getValue());
                        temp.setValue(Values.EMPTY);
                        createRandomCell();
                    }
                }
            }
        }
    }

    /*
    Up с if вместо while
    {
        for (int i = 0; i < 4; i++) {
            for (int j = 1; j <= 3; j++) {
                Cell temp = cells[j][i];

                if (temp.getValue() != Values.EMPTY) {
                    int k = j - 1;
                    Cell ktemp = cells[k][i];

                    for(; k >=0; k--){
                           if(cells[k][i].getValue() != Values.EMPTY){
                               break;
                           }
                    }

                    if (ktemp.getValue() != Values.EMPTY) {
                        if (ktemp.getValue() == temp.getValue() && !ktemp.isMerged()) {
                            ktemp.setValue(Values.findByKey(temp.getValue().getNumberOnCell() * 2));
                            ktemp.setMerged(true);
                            temp.setValue(Values.EMPTY);
                            emptyCells--;
                            createRandomCell();
                            score += temp.getValue().getNumberOnCell();
                        } else {
                            createRandomCell();
                        }
                    } else {
                        ktemp.setValue(temp.getValue());
                        temp.setValue(Values.EMPTY);
                        createRandomCell();
                    }
                }
            }
        }
    }
            */

    private Cell[][] getCellsGhost(){
        Cell[][] ghost = new Cell[4][4];
        for(int i = 0; i < 4; i++)
            System.arraycopy(this.cells[i], 0, ghost[i], 0, 4);
            return ghost;
    }

    private boolean isFail(){
            return emptyCells == 1;
    }

    private void endGame(){}

    public void printField(){
        for(int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (cells[i][j].getValue() == Values.EMPTY) {
                    System.out.print('\u25A1');
                } else {
                    System.out.print(cells[i][j].getValue().getNumberOnCell());
                }
            }
            System.out.println();
        }
    }
}


