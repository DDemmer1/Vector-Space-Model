package com.company;

import java.util.List;

public class TextData{

    private String text;

    private int textLength;

    private String name;

    private List<Integer> tfVector;

    private List<Integer> idfVector;

    private List<Integer> vector;


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getTextLength() {
        return textLength;
    }

    public void setTextLength(int textLength) {
        this.textLength = textLength;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Integer> getTfVector() {
        return tfVector;
    }

    public void setTfVector(List<Integer> tfVector) {
        this.tfVector = tfVector;
    }

    public List<Integer> getIdfVector() {
        return idfVector;
    }

    public void setIdfVector(List<Integer> idfVector) {
        this.idfVector = idfVector;
    }

    public List<Integer> getVector() {
        return vector;
    }

    public void setVector(List<Integer> vector) {
        this.vector = vector;
    }
}
