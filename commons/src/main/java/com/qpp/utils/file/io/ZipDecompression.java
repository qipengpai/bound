package com.qpp.utils.file.io;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class ZipDecompression {

    /**
     * @param args
     * @throws IOException
     */
    /*public static void main(String[] args) throws IOException {
        decompression("E:/usr/loanManager/imageAndDocument/temp/uploadify.zip","E:/usr/loanManager/imageAndDocument/temp");
    }*/

    /**
     * @Author qipengpai
     * @Description //TODO 解压ZIP文件
     * @param zipFile  要解压的ZIP文件路径
     * @param destination  解压到哪里
     * @Date 14:43 2018/10/12
     * @Param [zipFile, destination]
     * @Throws IOException
     * @return java.util.List<java.lang.String>
     **/
    public static List<String> decompression(String zipFile, String destination) throws IOException {
    	List<String> directorys = new ArrayList<String>();
    	
        ZipFile zip = new ZipFile(zipFile);
        Enumeration en = zip.entries();
        ZipEntry entry=null;
        byte[] buffer=new byte[8192];
        int length=-1;
        InputStream input=null;
        BufferedOutputStream bos=null;
        File file = null;
        
        while(en.hasMoreElements()) {
        	try {
        		entry=(ZipEntry)en.nextElement();
			} catch (Exception e) {
				try {
				 zip = new ZipFile(zipFile,Charset.forName("GBK"));
				 en = zip.entries();
				 entry=(ZipEntry)en.nextElement();
                  } catch (IOException e2) {
                	  throw new IOException(e2);
				}
			}
            
            if(entry.isDirectory()) {
                //System.out.println(entry.getName());
                directorys.add(entry.getName());
                continue;
            }
            
            input=zip.getInputStream(entry);
            file=new File(destination,entry.getName());
            if(!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            bos=new BufferedOutputStream(new FileOutputStream(file));
            
            while(true) {
                length=input.read(buffer);
                if(length==-1) break;
                bos.write(buffer,0,length);
            }
            bos.close();
            input.close();
        }
        zip.close();
        
        return directorys;
    }
}
