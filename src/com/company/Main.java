package com.company;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException, WordSetDesyncException {

        Tokenizer tokenizer = new Tokenizer();
        SortedSet<String> wordSet = new TreeSet<>();

        for (File f: new File("texts").listFiles()) {
            String text = tokenizer.getText(f, "UTF-8");
            tokenizer.getWordSet(text, wordSet);
        }


        List<String> wordList = new ArrayList<>(wordSet);

//        for (String s :
//                wordList) {
//            System.out.println(s);
//        }

        List<TextData> index = new ArrayList<>();

        List<Integer> tfVector = new IndexUtil().getTfVector(wordList,Tokenizer.getText(new File("texts/Hamlett.txt"), "UTF-8"));

//        for (Integer i: tfVector) {
//            System.out.print(i + " ");
//        }

        List<Integer> idfVector = new IndexUtil().getIdfVector();





    }
}
