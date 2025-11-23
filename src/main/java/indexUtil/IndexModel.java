package indexUtil;

import java.util.Objects;

public class IndexModel {
    String hash;
    boolean touched;
    String path;

    public IndexModel(String hash){
        this.hash = hash;
        touched = false;
    }

    public String getHash() {
        return hash;
    }

   public boolean getTouched(){
        return touched;
   }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public void setTouched(boolean touched) {
        this.touched = touched;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return "IndexModel{" +
                "hash='" + hash + '\'' +
                ", touched=" + touched +
                '}';
    }
}
