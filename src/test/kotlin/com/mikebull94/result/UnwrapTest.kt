package com.mikebull94.result

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test

internal class UnwrapTest {
    @Test
    internal fun `unwrap should return the result value if ok`() {
        val value = ok(5000).unwrap()
        assertThat(value, equalTo(5000))
    }

    @Test
    internal fun `unwrap should throw an UnwrapException if not ok`() {
        assertThrows(UnwrapException::class.java, {
            err(5000).unwrap()
        }, "called Result.wrap on an Error value 5000")
    }

    @Test
    internal fun `expect should return the result value if ok`() {
        val value = ok(1994).expect("the year should be 1994")
        assertThat(value, equalTo(1994))
    }

    @Test
    internal fun `expect should throw an UnwrapException with a specified message if not ok`() {
        assertThrows(UnwrapException::class.java, {
            err(1994).expect("the year should be 1994")
        }, "the year should be 1994")
    }

    @Test
    internal fun `unwrapError should throw an UnwrapException if ok`() {
        assertThrows(UnwrapException::class.java, {
            ok("example").unwrapError()
        }, "called Result.unwrapError on an Ok value example")
    }

    @Test
    internal fun `unwrapError should return the result error if not ok`() {
        val error = err("example").unwrapError()
        assertThat(error, equalTo("example"))
    }

    @Test
    internal fun `expectError should throw an UnwrapException with a specified message if ok`() {
        assertThrows(UnwrapException::class.java, {
            ok(2010).expectError("the year should be 2010")
        }, "the year should be 2010")
    }

    @Test
    internal fun `expectError should return the result error if not ok`() {
        val error = err(2010).expectError("the year should be 2010")
        assertThat(error, equalTo(2010))
    }
}