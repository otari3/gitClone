package util;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FileUtils;

public class Util {
    public static void createFolder(String filePath){
        try {
            File myGitFolder = new File(filePath);
            FileUtils.forceMkdir(myGitFolder);
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }
    public static void createFile(File file){
        try {
            FileUtils.forceMkdir(file);
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    public static String hashFile(File file){
        try (FileInputStream fis = new FileInputStream(file)) {
            return DigestUtils.sha1Hex(DigestUtils.sha1Hex(fis)+file.getAbsoluteFile()) ;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static String hashFolder(List<String> filesHashes){
        String allHashes = filesHashes.stream().reduce("",(a,b)->a+b);
        return  DigestUtils.sha1Hex(allHashes);
    }
    public static String safeFileName(String filePath){
            return filePath
                .replace("/", "_").replace(".","_");
    }

}
