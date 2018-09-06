package com.gaara.mp3.controllor;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j
public class MainControllor {

    @Value("${mypath}")
    private String path;

    @GetMapping("/index")
    public String admin(){
        return "index";
    }

    @GetMapping("/mp3")
    @ResponseBody
    public List<String> getmp3(){
        List<String> list = new ArrayList<>();
        try{
            File file = new File(path);
            File[] files = file.listFiles();
            for (int i=0;i<files.length;i++){
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
