package com.dragon.web.security.csp;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * @ClassName: Directive
 * @Description:
 * @Author: pengl
 * @Date: 2020/5/16 14:32
 * @Version V1.0
 */
@Accessors(chain = true)
@Getter
@Setter
public class Directive {
    private DirectiveEnum directiveEnum;
    private DirectiveValue directiveValue;

    public Directive() {
    }

    public Directive(DirectiveEnum directiveEnum, DirectiveValue directiveValue) {
        this.directiveEnum = directiveEnum;
        this.directiveValue = directiveValue;
    }
}
