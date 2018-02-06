package de.demmer.dennis.searchengine;

import de.demmer.dennis.vsmindex.TextData;
import de.demmer.dennis.vsmindex.VsmIndex;
import de.demmer.dennis.vsmindex.Query;

import java.util.Collections;
import java.util.List;

/**
 * Created by Dennis on 04.02.2018.
 */
public class SearchEngine {

    private VsmIndex vsmIndex;

    public SearchEngine() {
        this.vsmIndex = VsmIndex.getInstance();
    }



    public List<TextData> search(Query query) {

        query.initVector();

        calculateSimilarity(query,vsmIndex);

        Collections.sort(vsmIndex, new ResultComparator());

        return vsmIndex;

    }


    private List<TextData> calculateSimilarity(Query query, VsmIndex vsmIndex){


        for (int i = 0; i < vsmIndex.size() ; i++) {

            TextData currentTextData = vsmIndex.get(i);

            Double similarity = 0d;

            for (int j = 0; j < currentTextData.getVector().size(); j++) {

                Double currentVal = currentTextData.getVector().get(j);

                Double currentQueryVal = query.getTextData().getVector().get(j);

                similarity += currentVal*currentQueryVal;



            }

            similarity = similarity/ (currentTextData.getVectorLength()*query.getTextData().getVectorLength());

            currentTextData.setSimilarity(similarity);

        }


        return vsmIndex;
    }
}
