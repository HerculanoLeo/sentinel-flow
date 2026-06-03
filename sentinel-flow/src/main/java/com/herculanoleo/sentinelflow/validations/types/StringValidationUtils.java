package com.herculanoleo.sentinelflow.validations.types;

import org.apache.commons.lang3.StringUtils;

import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.UnknownHostException;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.regex.Pattern;

final class StringValidationUtils {

    static final Pattern EMAIL_REGEX = Pattern.compile("^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,}$");
    static final Pattern ALPHA_REGEX = Pattern.compile("^[a-zA-Z]+$");
    static final Pattern ALPHA_NUMERIC_REGEX = Pattern.compile("^[a-zA-Z0-9]+$");
    static final Pattern UUID_REGEX = Pattern.compile("^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$");
    static final Pattern SLUG_REGEX = Pattern.compile("^[a-z0-9]+(?:-[a-z0-9]+)*$");
    static final Pattern IPV4_REGEX = Pattern.compile(
            "^((25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]?\\d)\\.){3}(25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]?\\d)$");

    private StringValidationUtils() {
    }

    static boolean isValidUrl(String value) {
        try {
            URI uri = new URI(value);
            if (StringUtils.isBlank(uri.getScheme())) {
                return false;
            }
            return StringUtils.isNotBlank(uri.getHost()) || StringUtils.isNotBlank(uri.getAuthority());
        } catch (URISyntaxException e) {
            return false;
        }
    }

    static boolean isValidHttps(String value) {
        try {
            URI uri = new URI(value);
            return "https".equalsIgnoreCase(uri.getScheme()) && isValidUrl(value);
        } catch (URISyntaxException e) {
            return false;
        }
    }

    static Set<String> normalizeExtensions(Set<String> extensions) {
        Set<String> allowed = new HashSet<>();
        if (null == extensions) {
            return allowed;
        }

        extensions.stream()
                .filter(StringUtils::isNotBlank)
                .map(extension -> StringUtils.removeStart(extension.toLowerCase(), "."))
                .forEach(allowed::add);

        return allowed;
    }

    static boolean isValidUuid(String value) {
        if (UUID_REGEX.matcher(value).matches()) {
            try {
                UUID.fromString(value);
                return true;
            } catch (IllegalArgumentException ignored) {
                return false;
            }
        }
        return false;
    }

    static boolean isValidIpAddress(String value) {
        if (StringUtils.isBlank(value)) {
            return false;
        }
        if (IPV4_REGEX.matcher(value).matches()) {
            return true;
        }
        if (!value.contains(":")) {
            return false;
        }
        try {
            InetAddress address = InetAddress.getByName(value);
            return address instanceof Inet6Address;
        } catch (UnknownHostException e) {
            return false;
        }
    }

}
