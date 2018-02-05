package com.company;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TextData implements Serializable{

    private String text;
    private String name;
    private int textLength;

    private Double similarity;

    private Double vectorLength;
    private List<Integer> tfVector;

    private List<Double> vector = new ArrayList<>();


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


    public void setVector(List<Double> generalIdfVector) {

        for (int i = 0; i  < generalIdfVector.size() ; i++) {
            Double current = generalIdfVector.get(i);

            double prod = current * tfVector.get(i);

            this.vector.add(prod);

        }

        setVectorLength(this.vector);


    }

    public List<Double> getVector() {
        return vector;
    }

    public void setQueryVector(List<Double> generalIdfVector){

        vector = new ArrayList<>();
        Integer maxTF = Collections.max(getTfVector());

        for (int j = 0; j < generalIdfVector.size() ; j++) {

            Double result = (tfVector.get(j)/maxTF.doubleValue()) * generalIdfVector.get(j);
//            System.out.println("(" + tfVector.get(j) + "/" + maxTF + ") * " + generalIdfVector.get(j) + " = " + result);

            this.vector.add(result);
        }



        setVectorLength(this.vector);

    }

    private void setVectorLength(List<Double> vector){

        double sum = 0;
        for (Double d : vector) {

            sum += Math.pow(d,2);

        }

        vectorLength = Math.sqrt(sum);

    }

    public Double getVectorLength(){
        return vectorLength;
    }

    public Double getSimilarity() {
        return similarity;
    }

    public void setSimilarity(Double similarity) {
        this.similarity = similarity;
    }
}
