package com.company;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException, WordSetDesyncException {

//        VsmIndex vsmIndex = new VsmIndex("texts");

//        try {
//            FileOutputStream fileOut =
//                    new FileOutputStream("ser/index.ser");
//            ObjectOutputStream out = new ObjectOutputStream(fileOut);
//            out.writeObject(vsmIndex);
//            out.close();
//            fileOut.close();
//            System.out.printf("Index ist gespeichert in ser/index.ser");
//        } catch (IOException i) {
//            i.printStackTrace();
//        }



        VsmIndex vsmIndex = null;
        try {
            FileInputStream fileIn = new FileInputStream("ser/index.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            vsmIndex = (VsmIndex) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException i) {
            i.printStackTrace();
            return;
        } catch (ClassNotFoundException c) {
            System.out.println("VsmIndex class not found");
            c.printStackTrace();
            return;
        }

//        for (TextData td : vsmIndex) {
//
//            System.out.print("Name: " + td.getName()+" | ");
//            System.out.print("VectorLength: " + td.getVectorLength()+" | ");
//
//
//            for (Double i : td.getVector()) {
//                System.out.print(Math.floor(i * 1000)/1000+" ");
//            }
//            System.out.println();
//        }

//        Query query = new Query("new new times");



        Query query = new Query("poison");

        SearchEngine searchEngine = new SearchEngine(vsmIndex);
        List<TextData> results = searchEngine.search(query);
//        System.out.println();

        Collections.sort(results, new Comparator<TextData>() {
            @Override
            public int compare(TextData o1, TextData o2) {

                if(o2.getSimilarity()-o1.getSimilarity()<0){
                    return -1;
                }

                if(o2.getSimilarity()-o1.getSimilarity()>0){
                    return 1;
                }

                return 0;

            }
        });



        for (int i = 0; i < results.size(); i++) {
            System.out.println(i+1 + ". " + results.get(i).getName() + " | " + results.get(i).getSimilarity());
        }


    }
}
