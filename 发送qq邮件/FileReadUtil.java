package com.iask.starpires.common.util;

import java.io.*;

/**
 * 读取文件的类
 * @author yuanp
 * @date 2019/11/11
 */
public class FileReadUtil {
    public static String readFileContent(String filePath) throws IOException {
        StringBuffer stringBuffer=new StringBuffer();
        File file=new File(filePath);
            BufferedReader br=new BufferedReader(new FileReader(file));
            String str=null;
            while ((str=br.readLine())!=null){
                stringBuffer.append(str);
            }
            br.close();
        return stringBuffer.toString();
    }
}
