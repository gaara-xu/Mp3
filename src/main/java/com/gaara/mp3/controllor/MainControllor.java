package com.gaara.mp3.controllor;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Controller
@Slf4j
public class MainControllor {

    @Value("${mypath}")
    private String path;

    @GetMapping("/index")
    public String admin(){
        return "index";
    }
    @GetMapping("/favicon.ico")
    public void go(){
        System.out.println("哈哈哈哈哈哈");
        return;
    }
    @GetMapping("/mp3")
    @ResponseBody
    public List<String> getmp3(){
        List<String> list = new ArrayList<>();
        try{
            File file = new File(path);
            File[] files = file.listFiles();
            Random rand = new Random();
            int random = rand.nextInt(files.length)+1;
            if (random+10>files.length) {
                random = files.length - 10;
            }
            System.out.println(random+"=======");
//            for (int i=0;i<files.length;i++){
            for (int i=random;i<random+10;i++){
//                log.error(files[i].getName());
                if (files[i].getName().length()>5&&files[i].getName().substring(files[i].getName().length()-4,files[i].getName().length()).equals(".mp3")){
                    list.add(files[i].getName());
                }
            }
        }catch (Exception e){
            log.error("【"+e.getMessage()+"】");
            e.printStackTrace();
        }

        return list;
    }
}
