package de.demmer.dennis.searchengine;

import de.demmer.dennis.vsmindex.TextData;

import java.util.Comparator;

public class ResultComparator implements Comparator<TextData>{


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
}
