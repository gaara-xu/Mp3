package com.gaara.mp3.po;

import lombok.Data;

/**
 * Created by Gaara_Xu
 * on 2019/5/25.
 */
@Data
public class Docker {

    private String docker_id;
    private String docker_name;

    /**
     * 容器状态 true 可用  false 不可用
     */
    private boolean docker_status;
}
