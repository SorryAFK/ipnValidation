package com.shpp.pzobenko.todolist.validators;

import com.shpp.pzobenko.todolist.usersinformation.StatusOfAim;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CheckStatusTest {

    @Test
    void isValid() {
        CheckStatus forTest = new CheckStatus();
        assertFalse(forTest.isValid(StatusOfAim.DONE, null));
        assertFalse(forTest.isValid(StatusOfAim.IN_PROCESS, null));
        assertFalse(forTest.isValid(StatusOfAim.CANCELED, null));
    }
}