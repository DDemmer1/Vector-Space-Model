package de.demmer.dennis.vsmindex;

import de.demmer.dennis.vsmindex.TextData;
import de.demmer.dennis.vsmindex.VsmIndex;
import de.demmer.dennis.vsmindex.IndexUtil;

import java.util.ArrayList;

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

    public void initVector() {

        textData.setTfVector(new IndexUtil().getTfVector(new ArrayList<>(VsmIndex.getInstance().getDictionary()),term));
        textData.setQueryVector(VsmIndex.getInstance().getGeneralIDF());
    }



    public TextData getTextData() {
        return textData;
    }

}
