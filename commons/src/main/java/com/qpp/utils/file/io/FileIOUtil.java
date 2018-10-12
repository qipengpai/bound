package com.qpp.utils.file.io;

import java.io.*;

public class FileIOUtil {
	
	private static int cache = 2048;
	
	public static void writeBytesToFile(byte[] content, String path) {
		FileOutputStream os = null;
        
        try {
			File validation = new File(path);
			
			if(! validation.exists()) {
				validation.createNewFile();
			}
			os = new FileOutputStream(validation);
			
			int s = 0;
			int e = cache;
			while(true) {
				if(e > content.length) {
					os.write(content, s, content.length - s);
					break;
				}
				os.write(content, s, cache);
				s += cache;
				e += cache;
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(os != null) {
					os.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	
	/*public static String readBytesFromFile(String path) {
		File validation = new File(path);
		if(validation.exists()) {
			FileInputStream is = null;
			try {
				is = new FileInputStream(path);
				byte[] temp = new byte[cache];
				int b = 0;
				StringBuilder content = new StringBuilder();
				while(true) {
					b = is.read(temp);
					if(b == -1) {
						break;
					}
					content.append(new String(temp, 0, b));
				}
				return content.toString();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				return null;
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			} finally {
				try {
					if(is != null) {
						is.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} else {
			return null;
		}
	}*/
	
	public static byte[] readBytesFromFile(String path) {
		File validation = new File(path);
		if(validation.exists()) {
			FileInputStream is = null;
			try {
				is = new FileInputStream(path);
				byte[] data = new byte[is.available()];  
				is.read(data);
				return data;
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				return null;
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			} finally {
				try {
					if(is != null) {
						is.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} else {
			return null;
		}
	}
}
