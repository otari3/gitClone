package indexUtil;

import constants.Constants;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.TrueFileFilter;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.HashMap;

public class IndexMap {
    public HashMap<String,IndexModel> indexHashMap = new HashMap<>();
    public String pathToIndexFolder = Constants.rootDir+Constants.MY_GIT+Constants.INDEX_FOLDER_NAME;


    public void putValuesInMap(String hash,String path){
        IndexModel indexModel = new IndexModel(hash);
        indexModel.setTouched(true);
        indexHashMap.put(path,indexModel);
    }

    public void removeValue(String path){
        indexHashMap.remove(path);
    }
    public void changeIndex(String hash,String path){
        removeValue(path);
        putValuesInMap(hash,path);
    }
    public void toucheValue(String path){
        indexHashMap.get(path).touched = true;
    }


    public HashMap<String,IndexModel> createIndexMap(){
        File indexRootFile = new File(pathToIndexFolder);
        Collection<File> allFiles = FileUtils.listFilesAndDirs(indexRootFile, TrueFileFilter.INSTANCE,null);
        allFiles.remove(indexRootFile);
        HashMap<String,IndexModel> result = new HashMap<>();
        for(File fl:allFiles){
            String path = fl.getName();
            File file = FileUtils.listFilesAndDirs(fl,TrueFileFilter.INSTANCE,null)
                    .stream()
                    .filter((f)->f.getName().equals("hash.txt"))
                    .findFirst()
                    .orElseThrow();
            try {
                String hash = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
                IndexModel indexModel = new IndexModel(hash);
                indexModel.setPath(fl.getAbsolutePath());
                result.put(path,indexModel);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return result;
    }
}
