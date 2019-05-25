package com.gaara.mp3.controller;


import com.alibaba.fastjson.JSONArray;
import com.gaara.mp3.po.Docker;
import com.gaara.mp3.utils.HttpRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.gaara.mp3.utils.ShellUtils.execdocker;

@Controller
@Slf4j
public class MainController {

    @Value("${mypath}")
    private String path;

    @GetMapping("/index")
    public String heartBeatCheck(ModelMap modelMap){
        HttpRequest httpRequest = new HttpRequest();
        boolean heart = false;
        List<Docker> list = new ArrayList<>();
        JSONArray json = new JSONArray();
        try{
            list = execdocker("docker ps  -a", null);
            for (int i = 0; i < 7; i++) {
                heart= httpRequest.checkoutHeart("http://127.0.0.1:808"+i+"/psi/services/utils/ping");
                list.get(i).setDocker_status(heart);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        modelMap.addAttribute("dockerList",list);
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
