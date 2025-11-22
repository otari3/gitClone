import constants.Constants;
import initUtil.Init;
import myGitDataStructureFuncs.HashFolders;
import util.Util;

import java.io.File;
import java.nio.file.Path;

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
            case "commit":
                HashFolders hashFolders = new HashFolders();
                String currHash = hashFolders.createFolderTree(rootDir);
                String indexHashNameFilePath = Constants.rootDir+Constants.MY_GIT+Constants.INDEX_FOLDER_NAME+"/"+currHash+":"+Util.safeFileName(rootPath)+".txt";
                Util.createFile(indexHashNameFilePath);
                break;
            default:
                break;
        }

    }
}
