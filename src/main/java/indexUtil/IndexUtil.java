package indexUtil;

import constants.Constants;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.TrueFileFilter;
import util.Util;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collection;

public class IndexUtil {
    public void createIndex(File root){
        Collection<File> currAllFiles = FileUtils.listFilesAndDirs(root, TrueFileFilter.INSTANCE,null);
        for(File fl:currAllFiles){
            String currName = fl.getName();
            if (currName.equals("mygit") || currName.equals(".DS_Store") || currName.equals(".git") || fl.equals(root) || currName.equals(".idea")){
                continue;
            }
            if (fl.isDirectory()){
                createIndex(fl);
            }else if (fl.isFile()){
                String hash = Util.hashFile(fl);
                String folderPath = Constants.rootDir+Constants.MY_GIT+Constants.INDEX_FOLDER_NAME+"/"+hash;
                addFileToIndex(fl,folderPath);
            }
        }
    }

    private void addFileToIndex(File currFile,String pathToFile){
        String newFilePath = pathToFile+"/"+"path.txt";
        System.out.println(newFilePath);
        Util.createFolder(pathToFile);
        File file = new File(newFilePath);
        try {
            FileUtils.writeStringToFile(file, currFile.getAbsolutePath(), StandardCharsets.UTF_8);
        } catch (IOException e) {
           throw new RuntimeException(e);
        }
    }

}
