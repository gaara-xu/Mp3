package com.gaara.worm.utils;

import java.io.*;

/********************************
 *    Author Gaara              *
 *    Version 1.0               *
 *    @ClassName FileUtils
 *    @Date 2019/9/12 下午3:18     
 *    @Description 操作文件         *
 ********************************/
public class FileUtils {


    /**
     * 写文件
     * @param filePath
     * @param content
     */
    public static void contentToTxt(String filePath, String content) {
        String str = new String(); //原有txt内容
        String s1 = new String();//内容更新
        try {
            File f = new File(filePath);
            if (f.exists()) {
                System.out.print("..");
            } else {
                System.out.print("文件不存在.....正在创建");
                f.createNewFile();// 不存在则创建
                System.out.println("...文件创建已完成！");
            }
            BufferedReader input = new BufferedReader(new FileReader(f));

            while ((str = input.readLine()) != null) {
                s1 += str + "\n";
            }
            // System.out.println(s1);
            input.close();
            s1 += content;

            BufferedWriter output = new BufferedWriter(new FileWriter(f));
            output.write(s1);
            output.close();
        } catch (Exception e) {
            e.printStackTrace();

        }
    }






}
