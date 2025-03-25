package gic.cinema;

import gic.cinema.action.impl.BookTicketsAction;
import gic.cinema.action.impl.CheckBookingsAction;
import gic.cinema.action.impl.ExitAction;
import gic.cinema.input.model.impl.CinemaHallInput;
import gic.cinema.input.model.impl.OptionInput;
import gic.cinema.input.model.impl.children.ColumnsInput;
import gic.cinema.input.model.impl.children.RowsInput;
import gic.cinema.input.model.impl.children.TitleInput;
import gic.cinema.input.util.InputOutputUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class BookingEngineTest {
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
    void start_Select_Book_Tickets_Action() throws NoSuchFieldException, IllegalAccessException {
        // Given
        InputOutputUtil mockInputOutUtil = mock(InputOutputUtil.class);
        BookingEngine bookingEngine = new BookingEngine(mockInputOutUtil);

        BookTicketsAction mockBookTicketsAction = mock(BookTicketsAction.class);
        TestHelper.setActionInMap(1, mockBookTicketsAction, bookingEngine);

        CinemaHallInput cinemaHallInput = new CinemaHallInput();
        ((TitleInput) cinemaHallInput.getChildren().get(0)).setValue("Inception");
        ((RowsInput) cinemaHallInput.getChildren().get(1)).setValue(8);
        ((ColumnsInput) cinemaHallInput.getChildren().get(2)).setValue(10);

        OptionInput optionInput1 = new OptionInput();
        optionInput1.setValue(1);
        OptionInput optionInput3 = new OptionInput();
        optionInput3.setValue(3);

        // When
        when(mockInputOutUtil.getCinemaHallInputs()).thenReturn(cinemaHallInput);
        when(mockInputOutUtil.getOptionSelection(any(), anyString(), any())).thenReturn(optionInput1).thenReturn(optionInput3);
        when(mockInputOutUtil.getBookTicketsInput(anyInt())).thenReturn(null);

        // Then
        bookingEngine.start();

        verify(mockBookTicketsAction, times(1)).perform(any(), any());
    }

    @Test
    void start_Select_Check_Bookings_Action() throws NoSuchFieldException, IllegalAccessException {
        // Given
        InputOutputUtil mockInputOutUtil = mock(InputOutputUtil.class);
        BookingEngine bookingEngine = new BookingEngine(mockInputOutUtil);

        CheckBookingsAction checkBookingsAction = mock(CheckBookingsAction.class);
        TestHelper.setActionInMap(2, checkBookingsAction, bookingEngine);

        CinemaHallInput cinemaHallInput = new CinemaHallInput();
        ((TitleInput) cinemaHallInput.getChildren().get(0)).setValue("Inception");
        ((RowsInput) cinemaHallInput.getChildren().get(1)).setValue(8);
        ((ColumnsInput) cinemaHallInput.getChildren().get(2)).setValue(10);

        OptionInput optionInput2 = new OptionInput();
        optionInput2.setValue(2);
        OptionInput optionInput3 = new OptionInput();
        optionInput3.setValue(3);

        // When
        when(mockInputOutUtil.getCinemaHallInputs()).thenReturn(cinemaHallInput);
        when(mockInputOutUtil.getOptionSelection(any(), anyString(), any())).thenReturn(optionInput2).thenReturn(optionInput3);
        when(mockInputOutUtil.getCheckBookingsInput()).thenReturn(null);

        // Then
        bookingEngine.start();

        verify(checkBookingsAction, times(1)).perform(any(), any());
    }

    @Test
    void start_Select_Exit_Action() throws NoSuchFieldException, IllegalAccessException {
        // Given
        InputOutputUtil mockInputOutUtil = mock(InputOutputUtil.class);
        BookingEngine bookingEngine = new BookingEngine(mockInputOutUtil);

        ExitAction exitAction = mock(ExitAction.class);
        TestHelper.setActionInMap(3, exitAction, bookingEngine);

        CinemaHallInput cinemaHallInput = new CinemaHallInput();
        ((TitleInput) cinemaHallInput.getChildren().get(0)).setValue("Inception");
        ((RowsInput) cinemaHallInput.getChildren().get(1)).setValue(8);
        ((ColumnsInput) cinemaHallInput.getChildren().get(2)).setValue(10);

        OptionInput optionInput3 = new OptionInput();
        optionInput3.setValue(3);

        // When
        when(mockInputOutUtil.getCinemaHallInputs()).thenReturn(cinemaHallInput);
        when(mockInputOutUtil.getOptionSelection(any(), anyString(), any())).thenReturn(optionInput3);

        // Then
        bookingEngine.start();

        verify(exitAction, times(1)).perform(any(), any());
    }
}