package com.dragon.web.security.filter;

import com.dragon.web.security.constant.SecurityConstant;
import com.dragon.web.security.csp.*;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dragon.web.security.constant.SecurityConstant.HeaderFilterConfig;
import com.dragon.web.security.xss.XssHttpServletRequestWrapper;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;

/**
 * @ClassName: SecurityFilter
 * @Description: Security filter
 * @Author: pengl
 * @Date: 2020/5/16 8:56
 * @Version V1.0
 */
public class SecurityFilter implements Filter {
    private boolean enable_csp;
    private boolean report_only;
    private String csp_config;
    private boolean enable_frame_options;
    private String frame_options_config;
    private boolean enable_content_type_options;
    private boolean enable_strict_transport_security;
    private boolean enable_xss_protect;

    @Override
    public void init(FilterConfig filterConfig) {
        enable_csp = getBooleanConf(filterConfig, HeaderFilterConfig.ENABLE_CSP_FIELD, true);
        report_only = getBooleanConf(filterConfig, HeaderFilterConfig.REPORT_ONLY_FIELD, false);
        csp_config = getConf(filterConfig, HeaderFilterConfig.CSP_CONF_FIELD);
        enable_frame_options = getBooleanConf(filterConfig, HeaderFilterConfig.ENABLE_FRAME_OPTIONS_FIELD, true);
        frame_options_config = getConf(filterConfig, HeaderFilterConfig.FRAME_OPTIONS_CONF_FIELD);
        enable_content_type_options = getBooleanConf(filterConfig, HeaderFilterConfig.ENABLE_CONTENT_TYPE_OPTIONS_FIELD, true);
        enable_strict_transport_security = getBooleanConf(filterConfig, HeaderFilterConfig.ENABLE_STRICT_TRANSPORT_SECURITY_FIELD, true);
        enable_xss_protect = getBooleanConf(filterConfig, HeaderFilterConfig.ENABLE_XSS_PROTECT_FIELD, true);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
        if(enable_xss_protect){
            httpRequest = new XssHttpServletRequestWrapper(httpRequest);
        }

        if (enable_csp) {
            Header header = StringUtils.isBlank(csp_config) ?
                    getDefaultCsp() : ContentSecurityPolicy.builder().reportOnly(report_only).fullPolicy(csp_config).build().getCSPHeader();
            httpResponse.addHeader(header.getName(), header.getValue());
        }

        if (enable_frame_options) {
            httpResponse.addHeader(SecurityConstant.SecurityHeader.X_FRAME_OPTIONS.getName(),
                    StringUtils.isBlank(frame_options_config) ? SecurityConstant.SecurityHeader.X_FRAME_OPTIONS.getDefaultVal() : frame_options_config);
        }

        if (enable_content_type_options) {
            httpResponse.addHeader(SecurityConstant.SecurityHeader.X_CONTENT_TYPE_OPTIONS.getName(), SecurityConstant.SecurityHeader.X_CONTENT_TYPE_OPTIONS.getDefaultVal());
        }

        httpResponse.addHeader(SecurityConstant.SecurityHeader.X_XSS_PROTECTION.getName(), SecurityConstant.SecurityHeader.X_XSS_PROTECTION.getDefaultVal());

        if(enable_strict_transport_security){
            httpResponse.addHeader(SecurityConstant.SecurityHeader.STRICT_TRANSPORT_SECURITY.getName(), SecurityConstant.SecurityHeader.STRICT_TRANSPORT_SECURITY.getDefaultVal());
        }

        filterChain.doFilter(httpRequest, httpResponse);
    }

    @Override
    public void destroy() {

    }

    private boolean getBooleanConf(FilterConfig filterConfig, String paramName, boolean defaultVal) {
        String val = filterConfig.getInitParameter(paramName);
        if (StringUtils.isEmpty(val)) {
            return defaultVal;
        }
        return "true".equalsIgnoreCase(val);
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
}
