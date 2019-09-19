package com.gaara.worm.controller;

import com.gaara.worm.po.Worm;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import static com.gaara.worm.utils.CurlUtils.curl;
import static com.gaara.worm.utils.HttpRequest.sendGet;
import static com.gaara.worm.utils.ShellUtils.execCmd;


/********************************
 *    Author Gaara              *
 *    Version 1.0               *
 *    @ClassName WormController
 *    @Date 2019/9/12 下午3:52     
 *    @Description TODO         *
 ********************************/
@RestController
@RequestMapping("/worm")
public class WormController {

    @GetMapping("/qqq")
    public void qwe(){
        String url = "curl https://www.ec533.com/xiaoshuo/list-校园春色-3.html";
        System.out.println(curl(url).toString());
        String result="";
        System.out.println("the str ===="+url);
        try{
            result = execCmd(url, null);
            System.out.println(result);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @GetMapping("/www")
    public String qwee(){
        String url = "https://www.afw9.com/xiaoshuo/list-校园春色-3.html";
        return sendGet(url,null);
    }
}
