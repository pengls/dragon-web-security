package com.dragon.web.security.filter;

import com.dragon.web.security.constant.SecurityConstant;
import com.dragon.web.security.csp.*;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;

import com.dragon.web.security.constant.SecurityConstant.HeaderFilterConfig;
import org.apache.commons.lang3.StringUtils;

/**
 * @ClassName: SecurityHeaderFilter
 * @Description: 添加安全Header过滤器
 * @Author: pengl
 * @Date: 2020/5/16 8:56
 * @Version V1.0
 */
public class SecurityHeaderFilter implements Filter {
    private boolean enable_csp = true;
    private boolean report_only = false;
    private String csp_config;

    private boolean enable_frame_options = true;
    private String frame_options_config;

    @Override
    public void init(FilterConfig filterConfig) {
        enable_csp = getBooleanConf(filterConfig, HeaderFilterConfig.ENABLE_CSP_FIELD);
        report_only = getBooleanConf(filterConfig, HeaderFilterConfig.REPORT_ONLY_FIELD);
        csp_config = getConf(filterConfig, HeaderFilterConfig.CSP_CONF_FIELD);

        enable_frame_options = getBooleanConf(filterConfig, HeaderFilterConfig.ENABLE_FRAME_OPTIONS_FIELD);
        frame_options_config = getConf(filterConfig, HeaderFilterConfig.FRAME_OPTIONS_CONF_FIELD);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) {
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
        if (enable_csp) {
            Header header = StringUtils.isBlank(csp_config) ?
                    getDefaultCsp() : ContentSecurityPolicy.builder().reportOnly(report_only).fullPolicy(csp_config).build().getCSPHeader();
            httpResponse.addHeader(header.getName(), header.getValue());
        }

    }

    @Override
    public void destroy() {

    }

    private boolean getBooleanConf(FilterConfig filterConfig, String paramName) {
        return "true".equalsIgnoreCase(filterConfig.getInitParameter(paramName));
    }

    private String getConf(FilterConfig filterConfig, String paramName) {
        return filterConfig.getInitParameter(paramName);
    }

    private Header getDefaultCsp() {
        return ContentSecurityPolicy.builder()
                .reportOnly(false)
                .directive(new Directive(DirectiveEnum.DEFAULT_SRC, new DirectiveValue().addDefaultSource()))
                .build()
                .getCSPHeader();
    }

    private Header getDefaultFrameOptions(){
        return new Header(SecurityConstant.SecurityHeader.X_FRAME_OPTIONS_HEADER, "SAMEORIGIN");
    }
}
