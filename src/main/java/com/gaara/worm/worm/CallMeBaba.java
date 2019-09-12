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

public class CallMeBaba {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		getTheFirstUrl();
	}

	/**
	 * 
	 * @author gaara
	 * @param url
	 * @return txt
	 */
	public static void getTheFirstUrl(){
		for (int i = 1; i < 79; i++) {
			contentToTxt("/gaara/sex/log.txt", "现在正在处理第"+i+"个页面。。。\r\n");
			String []cmds = {"curl", "-i", "-w", "状态%{http_code}；DNS时间%{time_namelookup}；"
					+ "等待时间%{time_pretransfer}TCP 连接%{time_connect}；发出请求%{time_starttransfer}；"
					+ "总时间%{time_total}","https://www.bbb670.com/htm/downlist5/"+i+".htm"};
			ProcessBuilder pb=new ProcessBuilder(cmds);
			pb.redirectErrorStream(true);
			Process p;
			try {
				p = pb.start();
				BufferedReader br=null;
				String line=null;
				
				br=new BufferedReader(new InputStreamReader(p.getInputStream()));
				 
				while((line=br.readLine())!=null){ 
					
					String string = handlestr(line);
					getDownloadUrl(string);
				}
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//deng 3miao
			try {
				Thread.sleep(5000);
			} catch (Exception e) {
				// TODO: handle exception
			}
			
		}
		
	}
	/**
	 *    第一阶段的处理，取出文章页
	 * @param str
	 * @return
	 */
	public static String handlestr(String str) {  
		String reString = "";
		String[] strs = str.split("href=\"/htm/down5");
		if (strs.length>=1) { 
				try {
					String url ="https://www.bbb670.com/htm/down5" 
							+strs[1].toString().split("\"")[0].toString(); 
					contentToTxt("/gaara/sex/urls.txt", url);
					reString=url;
				} catch (Exception e) {
				} 
		}
		return reString;
	}
	/**
	 * 获取下载链接
	 */
	public static void getDownloadUrl(String url) {  
		String []cmds = {"curl", "-i", "-w", "状态%{http_code}；DNS时间%{time_namelookup}；"
				+ "等待时间%{time_pretransfer}TCP 连接%{time_connect}；发出请求%{time_starttransfer}；"
				+ "总时间%{time_total}",url};
		ProcessBuilder pb=new ProcessBuilder(cmds);
		pb.redirectErrorStream(true);
		Process p;
		try {
			p = pb.start();
			BufferedReader br=null;
			String line=null; 
			br=new BufferedReader(new InputStreamReader(p.getInputStream()));
			while((line=br.readLine())!=null){ 
				try {
					String strs = line.split("generate_down")[1].toString().split("\"")[1].toString();  
					String downloadurl ="http://down.maomixia.com:888"+strs;
					contentToTxt("/gaara/sex/downloadurl.txt", downloadurl);
				} catch (Exception e) {
					 
				}
			}
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 
		
		
	}

	
	
	
	
	
	
}

