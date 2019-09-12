package com.gaara.worm.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/********************************
 *    Author Gaara              *
 *    Version 1.0               *
 *    @ClassName CurlUtils
 *    @Date 2019/9/12 下午4:16     
 *    @Description TODO         *
 ********************************/
public class CurlUtils {

    public static StringBuffer curl(String url) {
        StringBuffer stringBuffer = new StringBuffer();
        String[] cmds = {
                "curl",
                "-i",
                "-w",
                "状态%{http_code}；DNS时间%{time_namelookup}；"
                        + "等待时间%{time_pretransfer}TCP 连接%{time_connect}；发出请求%{time_starttransfer}；"
                        + "总时间%{time_total}", url };
        ProcessBuilder pb = new ProcessBuilder(cmds);
        pb.redirectErrorStream(true);
        Process p;
        try {
            p = pb.start();
            BufferedReader br = null;
            String line = null;

            br = new BufferedReader(new InputStreamReader(p.getInputStream()));

            while ((line = br.readLine()) != null) {
                stringBuffer.append(line);
            }
            br.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return stringBuffer;
    }
}
