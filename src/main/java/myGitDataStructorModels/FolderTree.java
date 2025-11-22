package myGitDataStructorModels;

import java.util.ArrayList;
import java.util.Objects;

public class FolderTree {
    String name;
    ArrayList<Objects> neighbours = new ArrayList<>();

    public void setName(String name) {
        this.name = name;
    }
}
