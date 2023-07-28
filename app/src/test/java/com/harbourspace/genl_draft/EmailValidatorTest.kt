package com.harbourspace.genl_draft

import com.harbourspace.unsplash.ui.utils.isValidEmail
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import org.junit.Test

class EmailValidatorTest {

    @Test
    fun emailValidatorCorrectEmailReturnsTrue() {
        assertTrue("name@gmail.com".isValidEmail())
    }

    @Test
    fun emailValidatorCorrectEmailReturnsFalse() {
        assertFalse("namegmail.com".isValidEmail())
    }
}