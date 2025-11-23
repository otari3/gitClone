import constants.Constants;
import indexUtil.IndexUtil;
import initUtil.Init;
import myGitDataStructureFuncs.HashFolders;
import util.Util;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        String rootPath = System.getProperty("user.dir");
        File rootDir = new File(rootPath);
        String command = args[0];
        Constants.rootDir = rootPath;
        switch (command){
            case "init":
                Init.createGitFolder(rootPath);
                break;
            case "add":
                IndexUtil indexUtil = new IndexUtil();
                indexUtil.createIndex(rootDir);
                indexUtil.deleteFoldersThatWasDeleted();
                System.out.println(indexUtil.indexHashMap);
                System.out.println(indexUtil.indexHashMap.keySet().size());
                break;
            case "commit":
                break;
            default:
                break;
        }

    }
}
