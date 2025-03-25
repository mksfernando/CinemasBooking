package gic.cinema.action;

import gic.cinema.APP_CONSTANTS;
import gic.cinema.input.util.InputOutputUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ExitActionTest {
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final PrintStream actualOutput = System.out;

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outputStream));
    }

    @AfterEach
    void tearDown() {
        System.setOut(actualOutput);
    }

    @Test
    void getDescription() {
        assertEquals(APP_CONSTANTS.ACTION_DES_EXIT, new ExitAction().getDescription());
    }

    @Test
    void perform() {
        // Given
        InputOutputUtil mockInputOutUtil = mock(InputOutputUtil.class);

        // When
        doCallRealMethod().when(mockInputOutUtil).exit();

        // Then
        new ExitAction().perform(mockInputOutUtil, null);

        assertTrue(outputStream.toString().contains(APP_CONSTANTS.EXIT_MSG));
    }
}