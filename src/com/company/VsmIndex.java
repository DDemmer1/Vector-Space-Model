package com.company;


import java.io.File;
import java.io.IOException;
import java.util.*;

public class VsmIndex extends ArrayList<TextData> {

    private SortedSet<String> dictionary;

    public VsmIndex (String textDir){
        File dir = new File(textDir);
        List<String> fileURLs = new ArrayList<>();
        dictionary = new TreeSet<>();

        for (File f: dir.listFiles()) {
            fileURLs.add(f.getPath());
        }


        try {
            initIndex(fileURLs);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (WordSetDesyncException e) {
            e.printStackTrace();
        }


    }

    private void initIndex(List<String> fileURLs) throws IOException, WordSetDesyncException {

        System.out.println("Building VSM Index");
        IndexUtil indexUtil = new IndexUtil();
        Tokenizer tokenizer = new Tokenizer();

        //Dictionary aufbauen
        List<String> dictionary = getDictionary(fileURLs);

        //Term Frequenz Vektoren bestimmen und TextData initialieren
        for (String url : fileURLs) {
            TextData textData = new TextData();

            String text = tokenizer.getText(url ,"UTF-8");

            textData.setText("text");
            textData.setName(url);
            textData.setTextLength(text.length());
            textData.setTfVector(indexUtil.getTfVector(dictionary, text));

            this.add(textData);

        }

        List<List<Integer>> tfVectorList = new ArrayList<>();
        for (TextData td : this) {
            tfVectorList.add(td.getTfVector());
        }




        System.out.println("Index complete, with " + this.size() + " entrys");

    }

    private List<String> getDictionary(List<String> urls) {

        System.out.println("Building dictionary from " + urls.size()+ " files");

        Tokenizer tokenizer = new Tokenizer();


        SortedSet<String> wordSet = new TreeSet<>();

        for (String s : urls) {
            try {
                String text = tokenizer.getText(s,"UTF-8");
                tokenizer.getWordSet(text, wordSet);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        List<String>  dictionary = new ArrayList<>(wordSet);

        System.out.println("Dictionary complete. Size: " + dictionary.size());

        return dictionary;
    }


}
