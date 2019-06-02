package model;

public class Cell {
    private Values value;
    private boolean isMerged = false;

    Cell(Values value){
        this.value = value;
    }

    void setMerged(boolean status) {

        this.isMerged = status;
    }

    boolean isMerged() {

        return isMerged;
    }

    public Values getValue() {

        return value;
    }

    void setValue(Values value) throws IllegalArgumentException {
        this.value = value;
    }

    public boolean isEmpty() {
        return this.value == Values.EMPTY;
    }
}