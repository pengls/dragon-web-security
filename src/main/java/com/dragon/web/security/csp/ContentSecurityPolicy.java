package com.dragon.web.security.csp;

import com.dragon.web.security.constant.SecurityConstant;
import lombok.Builder;
import lombok.Data;
import lombok.Singular;
import lombok.experimental.Tolerate;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * @ClassName: ContentSecurityPolicy
 * @Description: CSP
 * @Author: pengl
 * @Date: 2020/5/16 14:32
 * @Version V1.0
 */
@Builder
@Data
public class ContentSecurityPolicy {
    public static final String SOURCE_SEPARATOR = " ";
    public static final String DIRECTIVE_SEPARATOR = ";";

    @Singular
    private List<Directive> directives;
    private boolean reportOnly;
    private String fullPolicy;

    @Tolerate
    public ContentSecurityPolicy() {
    }

    public Header getCSPHeader() {
        String val = "";
        StringBuffer valBuffer = new StringBuffer();
        if (StringUtils.isNotEmpty(fullPolicy)) {
            val = fullPolicy;
        } else {
            if (CollectionUtils.isEmpty(directives)) {
                return null;
            }
            directives.forEach(directive -> {
                valBuffer.append(directive.getDirectiveEnum().val() + SOURCE_SEPARATOR);
                valBuffer.append(directive.getDirectiveValue().getSource());
                valBuffer.append(DIRECTIVE_SEPARATOR);
            });
            val = valBuffer.toString();
        }
        return reportOnly ? new Header(SecurityConstant.SecurityHeader.CSP_REPORTONLY_HEADER, val) : new Header(SecurityConstant.SecurityHeader.CSP_HEADER, val);
    }
}
