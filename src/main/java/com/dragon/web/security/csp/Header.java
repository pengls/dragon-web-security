package com.dragon.web.security.csp;

import lombok.Data;

/**
 * @ClassName: Header
 * @Description: Http Header
 * @Author: pengl
 * @Date: 2020/5/16 20:36
 * @Version V1.0
 */
@Data
public class Header {
    private String name;
    private String value;

    public Header() {
    }

    public Header(String name, String value) {
        this.name = name;
        this.value = value;
    }
}
