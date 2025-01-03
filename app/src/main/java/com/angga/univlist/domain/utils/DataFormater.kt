package com.angga.univlist.domain.utils

fun extractDomainNames(input: String): String {
    val regex = Regex("https?://(www\\.)?([a-zA-Z0-9-]+)\\.")
    return regex.find(input)?.groupValues?.get(2)?.replaceFirstChar { it.uppercaseChar() } ?: ""
}