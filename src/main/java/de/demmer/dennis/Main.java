package de.demmer.dennis;

import de.demmer.dennis.vsmindex.Query;
import de.demmer.dennis.searchengine.SearchEngine;
import de.demmer.dennis.vsmindex.TextData;
import de.demmer.dennis.vsmindex.VsmIndex;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException{

        SearchEngine searchEngine = new SearchEngine();

        Query query = new Query("love hate family");

        searchEngine.search(query);

        System.out.println("Query: " + query.getTextData().getText());


//        int i = 1;
//        for (TextData td : VsmIndex.getInstance()) {
//            System.out.println(i++ + ". " + td.getName() + "  " + td.getSimilarity());
//        }

        System.out.println(ReflectionToStringBuilder.toString(VsmIndex.getInstance().get(0), ToStringStyle.MULTI_LINE_STYLE));





    }
}
