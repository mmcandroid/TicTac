package com.example.malek.tictac;


import java.util.Objects;

public class Casse {
    private String value;
    private int valueMatrix;
    private int row;
    private int col;

    public boolean isHasValue() {
        return !value.equals("");
    }

    private boolean hasValue  ;

    public Casse(String value, int valueMatrix, int row, int col) {
        this.value = value;
        this.valueMatrix = valueMatrix;
        this.row = row;
        this.col = col;
    }

    public Casse() {
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
        setValueMatrix();
    }

    public int getValueMatrix() {
        switch (value) {
            case "X":
                return 1;
            case "O":
                return -1;
            default:
                return 0;
        }
    }

    public void setValueMatrix() {
        switch (value) {
            case "X":
                this.valueMatrix = 1;
                break;
            case "O":
                this.valueMatrix = -1;
                break;
            default:
                this.valueMatrix = 0;
                break;
        }

    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }
}
