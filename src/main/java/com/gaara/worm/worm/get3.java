package com.gaara.worm.worm;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import static com.gaara.worm.utils.FileUtils.contentToTxt;

public class get3 {

	public static void main(String[] args) {

		readTxtFile("/gaara/sex/images/1024images/url.txt");
	}

	public static void readTxtFile(String filePath) {
		try {
			String encoding = "UTF-8";
			File file = new File(filePath);
			if (file.isFile() && file.exists()) { // 判断文件是否存在
				InputStreamReader read = new InputStreamReader(
						new FileInputStream(file), encoding);// 考虑到编码格式
				BufferedReader bufferedReader = new BufferedReader(read);
				String lineTxt = null;
				while ((lineTxt = bufferedReader.readLine()) != null) {
					readurlandwrite(lineTxt);
				}
				read.close();
			} else {
				System.out.println("找不到指定的文件");
			}
		} catch (Exception e) {
			System.out.println("读取文件内容出错");
			e.printStackTrace();
		}

	}

	public static void readurlandwrite(String url) { 
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
				geturl(line);
			}
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// deng 3miao
		try {
			Thread.sleep(1000);
		} catch (Exception e) {
			// TODO: handle exception
		}

	}
	/**
	 * 处理字符串
	 * @param txt
	 */
	public static void geturl(String txt){
		 
			String[] strs = txt.split("window.open");
			if (strs.length>2) {
				for (int i = 0; i < strs.length; i++) {
					try {
						System.out.println("downjpg==="+strs[i].toString().split("'")[1]);
						if(strs[i].toString().split("'")[1].length()>10){
							contentToTxt("/gaara/sex/images/1024images/wdownload.sh", "wget "+strs[i].toString().split("'")[1]+";");
						}
					} catch (ArrayIndexOutOfBoundsException e) {
						 
					}
					
				}
			}
		 
	}
	
}

