package indexUtil;

import constants.Constants;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.TrueFileFilter;
import util.Util;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class IndexUtil {
    public IndexMap indexMap = new IndexMap();
    public HashMap<String,IndexModel> indexHashMap;

    public IndexUtil(){
        indexMap.indexHashMap = indexMap.createIndexMap();
        indexHashMap = indexMap.indexHashMap;
    }
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
                String currentFileRelativePath = Util.getRelativePath(fl.getAbsolutePath());
                String safeName = Util.safeFileName(currentFileRelativePath);
                String folderPath = Constants.rootDir+Constants.MY_GIT+Constants.INDEX_FOLDER_NAME+"/"+safeName;
                if (!indexHashMap.containsKey(safeName)){
                    addFileToIndex(hash,folderPath);
                    indexMap.putValuesInMap(hash,safeName);
                }else {
                    if (!indexHashMap.get(safeName).hash.equals(hash)){
                        changeIndexHash(hash,folderPath);
                        indexMap.changeIndex(hash,safeName);
                    }
                    indexMap.toucheValue(safeName);
                }
            }
        }
    }

    public static void addFileToIndex(String hash,String pathToFile){
        String newFilePath = pathToFile+"/"+"hash.txt";
        Util.createFolder(pathToFile);
        File file = new File(newFilePath);
        try {
            FileUtils.writeStringToFile(file, hash, StandardCharsets.UTF_8);
        } catch (IOException e) {
           throw new RuntimeException(e);
        }
    }
    public static void deleteFolderFromIndex(String pathToFile){
        try {
            File file = new File(pathToFile);
            FileUtils.deleteDirectory(file);
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }
    public void deleteFoldersThatWasDeleted(){
        ArrayList<String> removingKeys = new ArrayList<>();
        for (String key:indexHashMap.keySet()){
            IndexModel indexModel = indexHashMap.get(key);
            if (!indexModel.touched){
                deleteFolderFromIndex(indexModel.path);
                removingKeys.add(key);
            }
        }
        for(String key:removingKeys){
            indexHashMap.remove(key);
        }
    }

    public static void changeIndexHash(String hash,String pathToFile){
        deleteFolderFromIndex(pathToFile);
        addFileToIndex(hash,pathToFile);
    }

}
