package model;

import java.util.*;

public enum Values {
    EMPTY(0),
    TWO(2),
    FOUR(4),
    EIGHT(8),
    SIXTEEN(16),
    THIRTYTWO(32),
    SIXTYFOUR(64),
    HUNDREDTWENTYEIGHT(128),
    TWOHUNDREDFIFTYSIX(256),
    FIVEHUNDREDTWELVE(512),
    THOUSANDTWENTYFOUR(1024),
    TWOTHOUSANDFORTYEIGHT(2048);
    int numberOnCell;

    Values(int numberOnCell){
        this.numberOnCell = numberOnCell;
    }

    int getNumberOnCell(){
        return numberOnCell;
    }

    private static final Map<Integer,Values> map;
    static {
        map = new HashMap<>();
        for (Values v : Values.values()) {
            map.put(v.numberOnCell, v);
        }
    }

    public static Values findByKey(int i) {
        return map.get(i);
    }
}
