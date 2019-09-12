package com.gaara.worm.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import static com.gaara.worm.utils.ShellUtils.execCmd;

/**
 * Created by Gaara_Xu
 * on 2019/5/4.
 */
@RestController
@RequestMapping("/page")
public class PageController {
    @GetMapping("/test")
    public String mmain(){
        String result="";
        try{
            result = execCmd("ssh root@39.97.186.148", null);
            System.out.println(result);
            result = execCmd("ifconfig",null);
            System.out.println(result);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    @RequestMapping(value = "/package", method = RequestMethod.GET)
    public void publish(){
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        response.setContentType("text/html; charset=UTF-8");
        Process process = null;
        String cmd = "sh /opt/docker/package-page.sh";
        try {
            Runtime  runtime = Runtime.getRuntime();
            process = runtime.exec(cmd);
            InputStream is = process.getInputStream();
            InputStreamReader isr = new InputStreamReader(is, "UTF-8");
            BufferedReader br = new BufferedReader(isr);
            PrintWriter write = response.getWriter();
            write.println("<script>");
            write.println("setTimeout(function(){document.body.style.background='#333';document.body.style.color='#fff';}, 100)");
            write.println("</script>");
            String line ;
            while((line = br.readLine()) != null){
                write.println(line + "<br>");
                write.flush();
            }
            write.close();
            is.close();
            isr.close();
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
