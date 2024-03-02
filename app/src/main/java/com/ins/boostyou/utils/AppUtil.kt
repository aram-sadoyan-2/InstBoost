package com.ins.boostyou.utils

import java.net.URLDecoder
import java.util.regex.Pattern

fun extractCodeFromJsonUrl(url: String): String {
    val pattern = "code%3D([^%]+)%23"
    val regex = Pattern.compile(pattern)
    val matcher = regex.matcher(url)

    if (matcher.find()) {
        val encodedCode = matcher.group(1)
        // URL-decode the code
        return URLDecoder.decode(encodedCode, "UTF-8")
    }

    return ""
}
