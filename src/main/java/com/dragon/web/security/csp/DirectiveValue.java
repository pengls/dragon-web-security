package com.dragon.web.security.csp;

import java.util.HashSet;
import java.util.Set;

/**
 * @ClassName: DirectiveValue
 * @Description: DirectiveValue
 * @Author: pengl
 * @Date: 2020/5/16 14:42
 * @Version V1.0
 */
public class DirectiveValue {
    private static final String SCHEME_SOURCE_HTTP = "http:";
    private static final String SCHEME_SOURCE_HTTPS = "https:";
    private static final String SCHEME_SOURCE_DATA = "data:";
    private static final String SCHEME_SOURCE_MEDIA_STREAM = "mediastream:";
    private static final String SCHEME_SOURCE_BLOB = "blob:";
    private static final String SCHEME_SOURCE_FILE_SYSTEM = "filesystem:";

    private Set<String> sourceDomains = new HashSet<>();

    public DirectiveValue addSource(String source) {
        sourceDomains.add(source);
        return this;
    }

    public DirectiveValue addDefaultSource() {
        addKeySource(SourceKey.SELF);
        return this;
    }

    public DirectiveValue addKeySource(SourceKey sourceKey) {
        sourceDomains.add(sourceKey.val());
        return this;
    }

    public DirectiveValue addHttpSource(boolean ssl) {
        sourceDomains.add(ssl ? SCHEME_SOURCE_HTTPS : SCHEME_SOURCE_HTTP);
        return this;
    }

    public DirectiveValue addDataSource(String source) {
        sourceDomains.add(SCHEME_SOURCE_DATA + ContentSecurityPolicy.SOURCE_SEPARATOR + source);
        return this;
    }

    public DirectiveValue addMediaStreamSource(String source) {
        sourceDomains.add(SCHEME_SOURCE_MEDIA_STREAM + ContentSecurityPolicy.SOURCE_SEPARATOR + source);
        return this;
    }

    public DirectiveValue addBlobSource(String source) {
        sourceDomains.add(SCHEME_SOURCE_BLOB + ContentSecurityPolicy.SOURCE_SEPARATOR + source);
        return this;
    }

    public DirectiveValue addFileSystemSource(String source) {
        sourceDomains.add(SCHEME_SOURCE_FILE_SYSTEM + ContentSecurityPolicy.SOURCE_SEPARATOR + source);
        return this;
    }

    public String getSource() {
        if (sourceDomains.size() > 0) {
            StringBuffer res = new StringBuffer();
            sourceDomains.forEach(s -> res.append(s + ContentSecurityPolicy.SOURCE_SEPARATOR));
            return res.toString();
        }
        return null;
    }

    public enum SourceKey {
        NONE("'none'"),
        SELF("'self'"),
        UNSAFE_INLINE("'unsafe-inline'"),
        UNSAFE_EVAL("'unsafe-eval'");

        private String value;

        SourceKey(String value) {
            this.value = value;
        }

        public String val() {
            return value;
        }
    }
}
