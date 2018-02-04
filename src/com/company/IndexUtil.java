package com.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class IndexUtil {


    public List<Integer> getTfVector(List<String> wordList, String text) throws WordSetDesyncException {


        Tokenizer tokenizer = new Tokenizer();
        //tfvektor Liste initialisieren
        List<Integer> tfVector = new ArrayList<>();
        for (int i = 0; i < wordList.size(); i++) {
            tfVector.add(i, 0);
        }

        //Textliste des zu ueberpruefenden Textes erstellen
        List<String> textList = tokenizer.getWordList(text);


        //zaehlen der Woerter im Text
        for (int i = 0; i < textList.size(); i++) {
            String current = textList.get(i);

            //Indexposition im woerterbuch feststellen
            int j = Collections.binarySearch(wordList, current);


            if (wordList.contains(current)) {
                tfVector.set(j, tfVector.get(j) + 1);
            } else {
                throw new WordSetDesyncException("Das woerterbuch ist nicht mit den aktuellen Texten synchron!");
            }
        }


        return tfVector;
    }


    public List<Double> getIdfVector(List<List<Integer>> tfVectorList) {

        List<Double> idfVector = new ArrayList<>();

        Integer length = tfVectorList.size();

        for (int j = 0; j < tfVectorList.get(0).size();  j++) {

            Integer sum = 0;

            for (int i = 0; i < tfVectorList.size(); i++) {

                List<Integer> currentList = tfVectorList.get(i);

                Integer currentInt = currentList.get(j);

                sum += currentInt;




            }

            //idf Berechnung
            Double idf = Math.log(length.doubleValue()/sum.doubleValue())/Math.log(2d);

            idfVector.add(idf);

        }

        return idfVector;
    }
}
