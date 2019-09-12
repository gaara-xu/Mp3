package com.gaara.worm.controller;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

import static com.gaara.worm.utils.ShellUtils.execCmd;

/********************************
 *    Author Gaara              *
 *    Version 1.0               *
 *    @ClassName Shellcontroller
 *    @Date 2019/1/30 下午3:10     
 *    @Description TODO         *
 ********************************/
@RestController
@RequestMapping("/shell")
@Slf4j
public class Shellcontroller {




    @GetMapping("/pwd")
    public String pwd(){
        String result="";
        try{
            result = execCmd("pwd", null);
            System.out.println(result);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }



    @GetMapping("/restart")
    public String restart(@RequestParam String dockerid){
        String str = "docker restart " +dockerid;
        String result="";
        System.out.println("the str ===="+str);
        try{
            result = execCmd(str, null);
            System.out.println(result);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
    @GetMapping("/start")
    public String start(@RequestParam String dockerid){
        String str = "docker start " +dockerid;
        String result="";
        System.out.println("the str ===="+str);
        try{
            result = execCmd(str, null);
            System.out.println(result);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
    @GetMapping("/stop")
    public String stop(@RequestParam String dockerid){
        String str = "docker stop " +dockerid;
        String result="";
        System.out.println("the str ===="+str);
        try{
            result = execCmd(str, null);
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
        String cmd = "sh /opt/docker/package-psi.sh";
        try {
            Runtime  runtime = Runtime.getRuntime();
            process = runtime.exec(cmd);

            InputStream is = process.getInputStream();
            InputStreamReader isr = new InputStreamReader(is, "utf-8");
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

    @RequestMapping(value = "/log/{dockerId}", method = RequestMethod.GET)
    public void server0log(@PathVariable("dockerId")String dockerId){
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        response.setContentType("text/html; charset=UTF-8");
        Process process = null;
        String cmd = "docker logs -f "+dockerId +"";
        try {
            Runtime  runtime = Runtime.getRuntime();
            log.info(cmd);
            process = runtime.exec(cmd);
            System.out.println("log start ");
            InputStream is = process.getInputStream();
            InputStreamReader isr = new InputStreamReader(is, "utf-8");
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

