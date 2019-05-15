package model;

import java.awt.event.KeyEvent;
import java.util.Arrays;

public class Field {
    private model.Cell[][] cells = new model.Cell[4][4];
    private int score;
    private boolean isWin;

    public void startGame() {
        score = 0;
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
    }

    private boolean hasEmpty(){
        boolean empty = false;
        for(int i= 0; i < 4; i++){
            for(int j=0; j < 4; j++){
                if(cells[i][j].getValue().getNumberOnCell() == 0) empty = true;
            }
        }
        return empty;
    }

    public void moveCells(int key){
        Cell[][] ghost = getCellsGhost();

        switch (key){
            case KeyEvent.VK_UP : {
                Up();
                break;
            }

            case KeyEvent.VK_DOWN : {
                Down();
                break;
            }

            case KeyEvent.VK_LEFT : {
                Left();
                break;
            }

            case KeyEvent.VK_RIGHT : {
                Right();
                break;
            }

//            if(isFail()) {
//                endGame();
//            } else{
//                if(Arrays.deepEquals(cells, ghost))
//                    createRandomCell();
//            }
        }
    }

    private void Up(){
        for(int i = 0; i < 4; i++) {
            for(int j = 1; j < 3; j++){
               Cell temp = cells[j][i];

               if(temp.getValue() != Values.EMPTY){
                   for(int k = j - 1; k >= 0; k--){
                      Cell ktemp = cells[k][i];

                      if(ktemp.getValue() != Values.EMPTY){
                          if(ktemp.getValue() == temp.getValue() && !ktemp.isMerged()) {
                              ktemp.setValue(fromIntToValue(temp.getValue().getNumberOnCell())); //!?!
                              ktemp.setMerged(true);
                              temp.setValue(Values.EMPTY);
                            } else {
                                ktemp.setValue(temp.getValue());
                                temp.setValue(Values.EMPTY);
                                ktemp.setMerged(false);
                          }
                      } else{
                          ktemp.setValue(fromIntToValue(temp.getValue().getNumberOnCell()));
                          temp.setValue(Values.EMPTY);
                   }
               }
               }
            }
        }
    }

    private void Down(){}

    private void Right(){}

    private void Left(){}

    private Cell[][] getCellsGhost(){
        Cell[][] ghost = new Cell[4][4];
        for(int i = 0; i < 4; i++)
            for(int j = 0; j < 4; j++)
                ghost[i][j] = this.cells[i][j];
            return ghost;
    }

    private boolean isFail(){
        return false;
    }

    private void endGame(){}

    private Values fromIntToValue(int num) throws IllegalArgumentException{
        Values t;
        switch (num) {
            case 4:{
                t = Values.FOUR;
                break;}
            case 8: {
                t = Values.EIGHT;
                break;}
            case 16:{
                t = Values.SIXTEEN;
                break;}
            case 32:{
                t = Values.THIRTYTWO;
                break;}
            case 64:{
                t = Values.SIXTYFOUR;
                break;}
            case 128:{
                t = Values.HUNDREDTWENTYEIGHT;
                break;}
            case 256:{
                t = Values.TWOHUNDREDFIFTYSIX;
                break;}
            case 512:{
                t = Values.FIVEHUNDREDTWELVE;
                break;}
            case 1024:{
                t = Values.THOUSANDTWENTYFOUR;
                break;}
            case 2048:{
                t = Values.TWOTHOUSANDFORTYEIGHT;
                break;}
            default: throw new IllegalArgumentException();
        }
        return t;
    }


}


