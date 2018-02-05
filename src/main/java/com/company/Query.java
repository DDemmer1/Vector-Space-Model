package com.company;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dennis on 04.02.2018.
 */
public class Query {

    private String term;



    private TextData textData;

    public Query (String term){
        this.term = term;
        initTextData();
    }


    private void initTextData(){

        textData = new TextData();

        textData.setTextLength(term.length());
        textData.setText(term);
        textData.setName("Query");


    }

    public void initVector(VsmIndex vsmIndex) {
        try {
            textData.setTfVector(new IndexUtil().getTfVector(new ArrayList<>(vsmIndex.getDictionary()),term));

            textData.setQueryVector(vsmIndex.getGeneralIDF());


        } catch (WordSetDesyncException e) {
            e.printStackTrace();
        }

    }



    public TextData getTextData() {
        return textData;
    }

}
