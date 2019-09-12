package com.gaara.worm.po;

import lombok.Data;

/********************************
 *    Author Gaara              *
 *    Version 1.0               *
 *    @ClassName Worm
 *    @Date 2019/9/12 下午3:51     
 *    @Description TODO         *
 ********************************/
@Data
public class Worm {
    private String type;
    private String titleStart;
    private String titleEnd;
    private String listStart;
    private String listEnd;
    private String pageStart;
    private String pageEnd;
    private String url;
}
