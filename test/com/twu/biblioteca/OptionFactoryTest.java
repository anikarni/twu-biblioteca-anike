package com.twu.biblioteca;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OptionFactoryTest {
    OptionFactory subject = new OptionFactory();
    @Mock Library library;
    @Mock Option option;

    @Test
    public void createsListBooksOption() throws Exception {
        when(option.is(OptionFactory.LIST_BOOKS)).thenReturn(true);

        Option actual = subject.selectOption(option, library);

        assertThat(actual, instanceOf(ListItemsOption.class));
    }

    @Test
    public void createsListMoviesOption() throws Exception {
        when(option.is(OptionFactory.LIST_MOVIES)).thenReturn(true);

        Option actual = subject.selectOption(option, library);

        assertThat(actual, instanceOf(ListItemsOption.class));
    }

    @Test
    public void createsLoginOption() throws Exception {
        when(option.is(OptionFactory.LOGIN)).thenReturn(true);

        Option actual = subject.selectOption(option, library);

        assertThat(actual, instanceOf(LoginOption.class));
    }

    @Test
    public void createsCheckoutBookOption() throws Exception {
        when(option.is(OptionFactory.CHECKOUT_BOOK)).thenReturn(true);

        Option actual = subject.selectOption(option, library);

        assertThat(actual, instanceOf(CheckoutOption.class));
    }

    @Test
    public void createsCheckoutMovieOption() throws Exception {
        when(option.is(OptionFactory.CHECKOUT_MOVIE)).thenReturn(true);

        Option actual = subject.selectOption(option, library);

        assertThat(actual, instanceOf(CheckoutOption.class));
    }

    @Test
    public void createsReturnBookOption() throws Exception {
        when(option.is(OptionFactory.RETURN_BOOK)).thenReturn(true);

        Option actual = subject.selectOption(option, library);

        assertThat(actual, instanceOf(ReturnOption.class));
    }

    @Test
    public void createsReturnMovieOption() throws Exception {
        when(option.is(OptionFactory.RETURN_MOVIE)).thenReturn(true);

        Option actual = subject.selectOption(option, library);

        assertThat(actual, instanceOf(ReturnOption.class));
    }

    @Test
    public void createsMyProfileOption() throws Exception {
        when(option.is(OptionFactory.VIEW_MY_PROFILE)).thenReturn(true);

        Option actual = subject.selectOption(option, library);

        assertThat(actual, instanceOf(MyProfileOption.class));
    }

    @Test
    public void createsListRentalsOption() throws Exception {
        when(option.is(OptionFactory.VIEW_CUSTOMER_RENTALS)).thenReturn(true);

        Option actual = subject.selectOption(option, library);

        assertThat(actual, instanceOf(ListRentalsOption.class));
    }

    @Test
    public void createsQuitOption() throws Exception {
        when(option.is(OptionFactory.QUIT)).thenReturn(true);

        Option actual = subject.selectOption(option, library);

        assertThat(actual, instanceOf(QuitOption.class));
    }

    @Test
    public void invalidatesOption() throws Exception {
        when(option.is(anyString())).thenReturn(false);

        Option actual = subject.selectOption(option, library);

        assertThat(actual, instanceOf(Option.class));
    }
}