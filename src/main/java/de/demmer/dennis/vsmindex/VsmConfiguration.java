package de.demmer.dennis.vsmindex;

import java.io.Serializable;

public class VsmConfiguration implements Serializable{

    private boolean load;
    private boolean saveTextToTextData;
    private String textDir;
    private String indexPath;


    public String getIndexPath() {
        return indexPath;
    }

    public void setIndexPath(String indexPath) {
        this.indexPath = indexPath;
    }

    public boolean isSaveTextToTextData() {
        return saveTextToTextData;
    }

    public void setSaveTextToTextData(boolean saveTextToTextData) {
        this.saveTextToTextData = saveTextToTextData;
    }

    public boolean isLoad() {
        return load;
    }

    public void setLoad(boolean load) {
        this.load = load;
    }

    public String getTextDir() {
        return textDir;
    }

    public void setTextDir(String textDir) {
        this.textDir = textDir;
    }
}
