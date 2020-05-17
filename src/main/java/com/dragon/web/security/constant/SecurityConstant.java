package com.dragon.web.security.constant;

/**
 * @ClassName: SecurityConstant
 * @Description: constant class
 * @Author: pengl
 * @Date: 2020/5/16 21:17
 * @Version V1.0
 */
public final class SecurityConstant {
    public enum SecurityHeader {
        CONTENT_SECURITY_POLICY("Content-Security-Policy", ""),
        CONTENT_SECURITY_POLICY_REPORT_ONLY("Content-Security-Policy-Report-Only", ""),
        X_FRAME_OPTIONS("X-Frame-Options", "SAMEORIGIN"),
        X_CONTENT_TYPE_OPTIONS("X-Content-Type-Options", "nosniff"),
        X_XSS_PROTECTION("X-XSS-Protection", "1; mode=block"),
        STRICT_TRANSPORT_SECURITY("Strict-Transport-Security", "max-age=31536000;");

        private String name;
        private String defaultVal;

        SecurityHeader(String name, String defaultVal) {
            this.name = name;
            this.defaultVal = defaultVal;
        }

        public String getName() {
            return name;
        }

        public String getDefaultVal() {
            return defaultVal;
        }
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

        /**
         * X-Content-Type-Options
         */
        public static final String ENABLE_CONTENT_TYPE_OPTIONS_FIELD = "enable_content_type_options";

        /**
         * Strict-Transport-Security
         */
        public static final String ENABLE_STRICT_TRANSPORT_SECURITY_FIELD = "enable_strict_transport_security";

        /**
         * xss filter
         */
        public static final String ENABLE_XSS_PROTECT_FIELD = "enable_xss_protect";
    }
}
