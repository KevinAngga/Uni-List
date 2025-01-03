package com.angga.univlist.domain

import com.angga.univlist.domain.utils.extractDomainNames


import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class DataFormaterTest {
    @Test
    fun `extract domain from https`() {
        val input = "https://stiker-ppni.ac.id/"
        val output = extractDomainNames(input)
        assertEquals("Stiker-ppni", output)
    }

    @Test
    fun `extract domain from http`() {
        val input = "https://binus.ac.id/"
        val output = extractDomainNames(input)
        assertEquals("Binus", output)
    }

    @Test
    fun `extract domain from empty`() {
        val input = "https://.ac.id/"
        val output = extractDomainNames(input)
        assertEquals("", output)
    }
}