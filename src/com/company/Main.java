package com.company;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException, WordSetDesyncException {

        VsmIndex vsmIndex = new VsmIndex("texts");

        for (TextData td : vsmIndex) {

            System.out.print("Name: " + td.getName()+" | ");
            System.out.print("VectorLength: " + td.getVectorLength()+" | ");


            for (Double i : td.getVector()) {
                System.out.print(Math.floor(i * 1000)/1000+" ");
            }
            System.out.println();
        }

//        Query query = new Query("new new times");
        Query query = new Query("Romeop");

        SearchEngine searchEngine = new SearchEngine(vsmIndex);
        List<TextData> results = searchEngine.search(query);
        System.out.println();
        for (int i = 0; i < results.size(); i++) {
            System.out.println(i + ". " + results.get(i).getName() + " | " + results.get(i).getSimilarity());
        }


    }
}
