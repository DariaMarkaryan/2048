package model;
import java.util.stream.Collectors;
import java.util.function.Function;
import java.util.Map;
import java.util.Arrays;

public class Cell {
    private Values value;
    private boolean isMerged = false;

    public void setMerged(boolean status) {
        this.isMerged = status;
    }

    public boolean isMerged() {
        return isMerged;
    }

    public Values getValue() {
        return value;
    }

    public void setValue(Values value) throws IllegalArgumentException {
        this.value = value;
    }

    public boolean isEmpty() {
        return this.value == Values.EMPTY;
    }
}
