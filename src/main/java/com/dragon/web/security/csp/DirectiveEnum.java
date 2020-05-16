package com.dragon.web.security.csp;

public enum DirectiveEnum {
    /**
     * default src
     */
    DEFAULT_SRC("default-src"),

    //============== Get directive Begin ==============///
    FRAME_SRC("frame-src"),
    CHILD_SRC("child-src"),
    WORKER_SRC("worker-src"),
    CONNECT_SRC("connect-src"),
    FONT_SRC("font-src"),
    IMG_SRC("img-src"),
    MEDIA_SRC("media-src"),
    MANIFEST_SRC("manifest-src"),
    OBJECT_SRC("object-src"),
    SCRIPT_SRC("script-src"),
    STYLE_SRC("style-src"),
    WEBRTC_SRC("webrtc-src"),
    PREFETCH_SRC("prefetch-src"),
    //============== Get directive End ==============//

    //============== Document directive Begin ==============//
    BASE_URI("base-uri"),
    PLUGIN_TYPES("plugin-types"),
    SAND_BOX("sandbox"),
    //============== Document directive End ==============//

    //============== Navigate directive Begin ==============//
    FORM_ACTION("form-action"),
    FRAME_ANCESTORS("frame-ancestors"),
    NAVIGATION_TO("navigation-to"),
    //============== Navigate directive End ==============//

    //============== Report directive Begin ==============//
    REPORT_URI("report-uri"),
    REPORT_TO("report-to"),
    //============== Report directive End ==============//

    //other
    BLOCK_ALL_MIXED_CONTENT("block-all-mixed-content"),
    REQUIRE_SRI_FOR("require-sri-for"),
    UPGRADE_INSECURE_REQUESTS("upgrade-insecure-requests");

    private String value;

    DirectiveEnum(String value) {
        this.value = value;
    }

    public String val() {
        return value;
    }
}
