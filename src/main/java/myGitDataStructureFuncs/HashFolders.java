package myGitDataStructureFuncs;
import constants.Constants;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.TrueFileFilter;
import util.Util;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class HashFolders {
    public String createFolderTree(File currRootFile){
        Collection<File> allFiles = FileUtils.listFilesAndDirs(currRootFile, TrueFileFilter.INSTANCE,null);
        ArrayList<String> hashes = new ArrayList<>();
        for (File fl:allFiles){
            String currName = fl.getName();
            if (currName.equals("mygit") || currName.equals(".DS_Store")){
                continue;
            }
            if (fl.isDirectory()){
                String currHash = createFolderTree(fl);
                hashes.add(currHash);
                String indexHashNameFilePath = Constants.rootDir+Constants.MY_GIT+Constants.INDEX_FOLDER_NAME+"/"+currHash+":"+Util.safeFileName(fl.getAbsolutePath())+".txt";
                Util.createFile(indexHashNameFilePath);
            }else if (fl.isFile()){
                String currHash = Util.hashFile(fl);
                String indexHashNameFilePath = Constants.rootDir+Constants.MY_GIT+Constants.INDEX_FOLDER_NAME+"/"+currHash+":"+Util.safeFileName(fl.getAbsolutePath())+".txt";
                hashes.add(currHash);
                Util.createFile(indexHashNameFilePath);
            }
        }
        return Util.hashFolder(hashes);
    }

}
