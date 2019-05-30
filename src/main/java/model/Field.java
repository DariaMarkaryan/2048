package model;

import java.awt.event.KeyEvent;

public class Field {
    private model.Cell[][] cells = new model.Cell[4][4];
    private model.Cell[][] obsoleteCells = getCellsGhost();
    private int score;
    private boolean isWin;
    private int emptyCells = 4 * 4;

    public Cell[][] getCells(){
        return cells;
    }

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
            if(Math.random() >= 0.0 && Math.random()<= 0.9){    //вероятность получить значение 2 в клетке = 0.9
                cell.setValue(Values.TWO);
            } else {
                cell.setValue(Values.FOUR);
            }
            emptyCells--;
    }

    private void unmixAll(){
        for(int i = 0; i < 3 ; i++)
            for(int j = 0; j < 3 ; j ++)
                cells[i][j].setMerged(false);
    }

    public void moveCells(int key){
        this.obsoleteCells = getCellsGhost(); // для отката хода

    this.unmixAll();
        switch (key) {
            case KeyEvent.VK_UP: {
                Up();
                createRandomCell();
                break;
            }

            case KeyEvent.VK_DOWN: {
                Down();
                createRandomCell();
                break;
            }

            case KeyEvent.VK_LEFT: {
                Left();
                createRandomCell();
                break;
            }

            case KeyEvent.VK_RIGHT: {
                Right();
                createRandomCell();
                break;
            }
        }
            if(isFail()) {
                endGame();
            }
    this.unmixAll();
    }

    private void Up() {
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

    private void Down(){
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

    private void Right(){
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

    private void Left(){
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

    private Cell[][] getCellsGhost(){
        Cell[][] ghost = new Cell[4][4];
        for(int i = 0; i < 4; i++)
            System.arraycopy(cells[i], 0, ghost[i], 0, 4);
            return ghost;
    }

    public void undo(){ cells = obsoleteCells; }

    private boolean isFail(){
            return emptyCells == 1;
    }

    private void endGame(){}

    public void printField(){
        for(int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
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