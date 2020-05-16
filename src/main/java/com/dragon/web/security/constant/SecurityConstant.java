package com.dragon.web.security.constant;

/**
 * @ClassName: SecurityConstant
 * @Description: constant class
 * @Author: pengl
 * @Date: 2020/5/16 21:17
 * @Version V1.0
 */
public final class SecurityConstant {
    public final class SecurityHeader {
        public static final String CSP_HEADER = "Content-Security-Policy";
        public static final String CSP_REPORTONLY_HEADER = "Content-Security-Policy-Report-Only";
        public static final String X_FRAME_OPTIONS_HEADER = "X-Frame-Options";
    }

    public final class HeaderFilterConfig {
        /**
         * Content-Security-Policy
         */
        public static final String ENABLE_CSP_FIELD = "enable_csp";
        public static final String REPORT_ONLY_FIELD = "report_only";
        public static final String CSP_CONF_FIELD = "csp_config";

        /**
         * X-Frame-Options
         */
        public static final String ENABLE_FRAME_OPTIONS_FIELD = "enable_frame_options";
        public static final String FRAME_OPTIONS_CONF_FIELD = "frame_options_config";
    }
}
