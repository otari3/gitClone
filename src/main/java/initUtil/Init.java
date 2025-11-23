package initUtil;
import constants.Constants;
import util.Util;

public class Init {
    public static void createGitFolder(String rootPath){
            String myGitFilePath = rootPath+Constants.MY_GIT;
            System.out.println(myGitFilePath);
            Util.createFolder(myGitFilePath);
            createIndexFolder(myGitFilePath);
            createObject(myGitFilePath);
    }
    public static void createIndexFolder(String myGitfilePath){
        String indexFilePath = myGitfilePath+ Constants.INDEX_FOLDER_NAME;
        Util.createFolder(indexFilePath);
    }
    public static void createObject(String myGitfilePath){
        String createObjectPath = myGitfilePath + Constants.OBJECT_FOLDER_NAME;
        Util.createFolder(createObjectPath);
    }
}
