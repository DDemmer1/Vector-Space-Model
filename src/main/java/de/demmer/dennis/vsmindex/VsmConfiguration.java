package de.demmer.dennis.vsmindex;

public class VsmConfiguration {

    private boolean serialize;
    private boolean deserialize;


    public boolean isSerialize() {
        return serialize;
    }

    public void setSerialize(boolean serialize) {
        this.serialize = serialize;
    }

    public boolean isDeserialize() {
        return deserialize;
    }

    public void setDeserialize(boolean deserialize) {
        this.deserialize = deserialize;
    }
}
