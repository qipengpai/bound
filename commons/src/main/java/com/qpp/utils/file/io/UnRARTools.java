package com.qpp.utils.file.io;

import com.github.junrar.Archive;
import com.github.junrar.rarfile.FileHeader;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

public class UnRARTools {  
  
    public static List<String> unrar(File sourceRar, File destDir) throws Exception {
    	List<String> directorys = new ArrayList<String>();
        Archive archive = null;  
        FileOutputStream fos = null;
        //System.out.println("Starting...");  
        try {  
            archive = new Archive(sourceRar);  
            FileHeader fh = archive.nextFileHeader();  
            File destFileName = null;
            while (fh != null) {  
                String compressFileName = fh.getFileNameW().isEmpty()?fh.getFileNameString().replaceAll("\\\\", "/").trim():fh.getFileNameW().replaceAll("\\\\", "/").trim();
                destFileName = new File(destDir.getAbsolutePath() + "/" + compressFileName);
                if (fh.isDirectory()) {  
                    if (!destFileName.exists()) {  
                        destFileName.mkdirs();  
                    }  
                    System.out.println(compressFileName);
                    directorys.add(compressFileName);
                    fh = archive.nextFileHeader();  
                    continue;  
                }   
                if (!destFileName.getParentFile().exists()) {  
                    destFileName.getParentFile().mkdirs();  
                }  
                fos = new FileOutputStream(destFileName);
                archive.extractFile(fh, fos);  
                fos.close();  
                fos = null;  
                fh = archive.nextFileHeader();  
            }  
  
            archive.close();  
            archive = null;  
            //System.out.println("Finished !");  
            
            return directorys;
        } catch (Exception e) {
            throw e;  
        } finally {  
            if (fos != null) {  
                try {  
                    fos.close();  
                    fos = null;  
                } catch (Exception e) {
                    e.printStackTrace(); 
                }  
            }  
            if (archive != null) {  
                try {  
                    archive.close();  
                    archive = null;  
                } catch (Exception e) {
                    e.printStackTrace(); 
                }  
            }  
        }  
    } 
    
    
    /*public static void main(String[] args) {
    	try {
			unrar(new File("C:/Users/lenovo/Downloads/新建文件夹/新建文件夹.rar"), new File("C:/Users/lenovo/Downloads/新建文件夹/"));
		} catch (Exception e) {
			e.printStackTrace();
		}
    }*/
}  
