package com.dragon.web.security;

import com.dragon.web.security.csp.*;
import org.junit.Test;

/**
 * @ClassName: DirectiveTest
 * @Description: TODO
 * @Author: pengl
 * @Date: 2020/5/16 15:57
 * @Version V1.0
 */
public class DirectiveTest {
    @Test
    public void t1(){
        Header header = ContentSecurityPolicy.builder()
                .directive(new Directive(DirectiveEnum.DEFAULT_SRC, new DirectiveValue().addDefaultSource().addHttpSource(true).addSource("https://*.baidu.com *.github.com").addSource("csdn.net")))
                .directive(new Directive(DirectiveEnum.CHILD_SRC, new DirectiveValue().addDefaultSource().addHttpSource(false)))
                .directive(new Directive(DirectiveEnum.SCRIPT_SRC, new DirectiveValue().addDefaultSource().addHttpSource(true).addKeySource(DirectiveValue.SourceKey.UNSAFE_EVAL).addKeySource(DirectiveValue.SourceKey.UNSAFE_INLINE)))
                .directive(new Directive(DirectiveEnum.CONNECT_SRC, new DirectiveValue().addDefaultSource().addDataSource("").addBlobSource("")))
                .build()
                .getCSPHeader();
        System.out.println(header.getName());
        System.out.println(header.getValue());
    }
}
