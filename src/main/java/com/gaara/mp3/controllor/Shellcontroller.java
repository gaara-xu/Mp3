package com.gaara.mp3.controllor;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.databind.util.JSONPObject;
import netscape.javascript.JSObject;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static com.gaara.mp3.utils.ShellUtils.execCmd;
import static com.gaara.mp3.utils.ShellUtils.execdocker;

/********************************
 *    Author Gaara              *
 *    Version 1.0               *
 *    @ClassName Shellcontroller
 *    @Date 2019/1/30 下午3:10     
 *    @Description TODO         *
 ********************************/
@RestController
@RequestMapping("/shell")
public class Shellcontroller {

    @GetMapping("/test")
    public String mmain(){
        String result="";
        try{
            result = execCmd("java -version", null);
            System.out.println(result);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }



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
    @GetMapping("/docker")
    public JSONObject docker(){
        JSONObject json = new JSONObject();
        JSONObject dockerid = new JSONObject();
        JSONObject dockername = new JSONObject();
        try{
            List<ArrayList> lists = execdocker("docker ps", null);
            ArrayList<String> id = lists.get(0);
            ArrayList<String> name = lists.get(1);
            for (int i = 0;i<id.size();i++){
                dockerid.put(""+i,id.get(i));
                dockername.put(""+i,name.get(i));
            }
            json.put("dockerid",dockerid);
            json.put("dockername",dockername);
        }catch (Exception e){
            json.put("message","请求出错");
        }
        System.out.println(json.toString());
        return json;
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



}

