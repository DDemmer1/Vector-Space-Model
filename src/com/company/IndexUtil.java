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
            int j = Collections.binarySearch(wordList,current);


            if (wordList.contains(current)) {
                tfVector.set(j, tfVector.get(j) + 1);
            } else{
                throw new WordSetDesyncException("Das woerterbuch ist nicht mit den aktuellen Texten synchron!");
            }
        }


        return tfVector;
    }


    public List<Integer> getIdfVector(List<List<Integer>> tfVectorList, List<Integer> tfVector) {

        List<Integer> idfVector = new ArrayList<>();



        return idfVector;
    }
}
