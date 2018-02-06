package de.demmer.dennis.vsmindex;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.*;
import java.util.*;

public class VsmIndex extends ArrayList<TextData> implements Serializable {

    private  List<String> dictionary;
    private  List<Double> generalIDF;
    private static VsmConfiguration config;
    private static VsmIndex instance;

    private VsmIndex() {

    }

    public static VsmIndex getInstance() {
        if (VsmIndex.instance == null) {
            System.out.println("Index == null");
            try {
                instance.config = loadConfig();
                System.out.println("Config geladen");

                if (!instance.config.isLoad()) {

                    System.out.println("Neuer Index wird erstellt");
                    instance = new VsmIndex();
                    File dir = new File(instance.config.getTextDir());
                    List<String> fileURLs = new ArrayList<>();

                    for (File f : dir.listFiles()) {
                        fileURLs.add(f.getPath());
                    }
                    initIndex(fileURLs);

                    serialize();
                    System.out.println("Index wurde gespeichert in "+ instance.config.getIndexPath()+ "/index.ser");


                } else {
                    System.out.println("Index wird deserialisiert");
                    deserialize();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return instance;
    }

    private static void serialize() {
        try {
            FileOutputStream fileOut = new FileOutputStream(instance.config.getIndexPath()+"/index.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(instance);
            out.writeObject(instance.dictionary);
            out.writeObject(instance.generalIDF);
            out.close();
            fileOut.close();
        } catch (IOException i) {
            i.printStackTrace();
        }


    }


    private static void deserialize() {
        try {
            FileInputStream fileIn = new FileInputStream(config.getIndexPath()+"/index.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            instance = (VsmIndex) in.readObject();
            in.close();
            fileIn.close();
            if (instance!=null){
                System.out.println("Index erfolgreich deserialisiert");

            }
        } catch (IOException i) {
            i.printStackTrace();
            return;
        } catch (ClassNotFoundException c) {
            System.out.println("VsmIndex class not found");
            c.printStackTrace();
            return;
        }


    }


    protected static List<Double> getGeneralIDF() {
        return instance.generalIDF;
    }

    protected static List<String> getDictionary() {
        return instance.dictionary;
    }


    private static void initIndex(List<String> fileURLs) throws IOException {

        System.out.println("Building VSM Index");
        IndexUtil indexUtil = new IndexUtil();
        Tokenizer tokenizer = new Tokenizer();

        //Dictionary aufbauen
        List<String> dictionary = initDictionary(fileURLs);

        //Term Frequenz Vektoren bestimmen und TextData initialieren
        for (String url : fileURLs) {
            TextData textData = new TextData();

            String text = tokenizer.getText(url, "UTF-8");

            if(config.isSaveTextToTextData()){
            textData.setText(text);
            } else{
                textData.setText("");
            }
            textData.setName(url);
            textData.setTextLength(text.length());
            textData.setTfVector(indexUtil.getTfVector(dictionary, text));

            instance.add(textData);

        }

        List<List<Integer>> tfVectorList = new ArrayList<>();

        for (TextData td : instance) {
            tfVectorList.add(td.getTfVector());
        }

        instance.generalIDF = indexUtil.getIdfVector(tfVectorList);

        for (TextData td : instance) {
            td.setVector(instance.generalIDF);
        }


        System.out.println("Index complete, with " + instance.size() + " entrys");

    }

    private static List<String> initDictionary(List<String> urls) {

        System.out.println("Building dictionary from " + urls.size() + " files");

        Tokenizer tokenizer = new Tokenizer();


        SortedSet<String> wordSet = new TreeSet<>();

        for (String s : urls) {
            try {
                String text = tokenizer.getText(s, "UTF-8");
                tokenizer.getWordSet(text, wordSet);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

//        List<String>  dictionary = new ArrayList<>(wordSet);
        instance.dictionary = new ArrayList<>(wordSet);

        System.out.println("Dictionary complete. Size: " + instance.dictionary.size());

        return instance.dictionary;
    }

    private static VsmConfiguration loadConfig() throws IOException {

        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        VsmConfiguration config = mapper.readValue(new File("conf/index_conf.yaml"), VsmConfiguration.class);
//      System.out.println(ReflectionToStringBuilder.toString(configuration, ToStringStyle.MULTI_LINE_STYLE));


        return config;
    }

}
