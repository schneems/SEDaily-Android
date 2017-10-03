package com.koalatea.thehollidayinn.softwareengineeringdaily.presentation.loginscreen;

import android.support.annotation.NonNull;

import com.koalatea.thehollidayinn.softwareengineeringdaily.R;
import com.koalatea.thehollidayinn.softwareengineeringdaily.domain.UserRepository;
import com.koalatea.thehollidayinn.softwareengineeringdaily.test.BasePresenterUnitTest;

import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mock;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

/**
 * Created by Kurian on 27-Sep-17.
 */
public class LoginPresenterImplTest extends BasePresenterUnitTest<LoginView, LoginPresenterImpl> {

    @Mock
    UserRepository userRepository;

    public LoginPresenterImplTest() {
        super(LoginView.class);
    }

    @Test
    public void presenter_tag_returns_expected() throws Exception {
        initPresenter();
        assertEquals(LoginPresenterImpl.class.getCanonicalName(), presenter.presenterTag());
    }

    @Test
    public void loginResult_invokes_view_success_when_result_true() throws Exception {
        initPresenter();
        presenter.authResult(true);
        verify(view).loginSuccess();
    }

    @Ignore
    @Test
    public void loginResult_shows_error_message_when_result_false() throws Exception {
        initPresenter();
        presenter.authResult(false);
        verify(view).showErrorMessage(anyInt());
    }

    @Test
    public void isInputValid_returns_false_when_username_is_empty() throws Exception {
        initPresenter();
        assertFalse(presenter.isLoginInputValid(null, "password"));
    }

    @Test
    public void isInputValid_returns_false_when_password_is_empty() throws Exception {
        initPresenter();
        assertFalse(presenter.isLoginInputValid("username", null));
    }

    @Test
    public void isInputValid_returns_true_when_password_and_username_is_valid() throws Exception {
        initPresenter();
        assertTrue(presenter.isLoginInputValid("username", "password"));
    }

    @Test
    public void validateUsername_shows_error_to_view_when_invalid() throws Exception {
        initPresenter();
        presenter.validateUsername(null);
        verify(view).showUsernameError(eq(R.string.sign_in_screen_required_field_error));
    }

    @Test
    public void validateUsername_does_not_show_error_when_valid_username() throws Exception {
        initPresenter();
        presenter.validateUsername("username");
        verify(view, never()).showUsernameError(eq(R.string.sign_in_screen_required_field_error));
    }

    @Test
    public void validatePassword_shows_error_to_view_when_invalid() throws Exception {
        initPresenter();
        presenter.validatePassword(null);
        verify(view).showPasswordError(eq(R.string.sign_in_screen_required_field_error));
    }

    @Test
    public void validatePassword_does_not_show_error_when_valid_password() throws Exception {
        initPresenter();
        presenter.validatePassword("password");
        verify(view, never()).showPasswordError(eq(R.string.sign_in_screen_required_field_error));
    }

    @Test
    public void doesConfirmationMatchPassword_returns_true_when_passwords_match() throws Exception {
        initPresenter();
        assertTrue(presenter.doesConfirmationMatchPassword("password", "password"));
    }

    @Test
    public void doesConfirmationMatchPassword_returns_false_when_passwords_match() throws Exception {
        initPresenter();
        assertFalse(presenter.doesConfirmationMatchPassword("password", "confirmation"));
    }

    @Test
    public void doesConfirmationMatchPassword_returns_true_when_passwords_empty() throws Exception {
        initPresenter();
        assertTrue(presenter.doesConfirmationMatchPassword("", ""));
    }

    @Test
    public void isRegistrationInputValid_returns_true_when_inputs_are_valid() throws Exception {
        initPresenter();
        final boolean result = presenter.isRegistrationInputValid("user", "password", "password");
        assertTrue(result);
    }

    @Test
    public void isRegistrationInputValid_returns_false_when_username_is_invalid() throws Exception {
        initPresenter();
        final boolean result = presenter.isRegistrationInputValid("", "password", "password");
        assertFalse(result);
    }

    @Test
    public void isRegistrationInputValid_returns_false_when_password_and_confirmation_mismatch()
            throws Exception {
        initPresenter();
        final boolean result = presenter.isRegistrationInputValid("user", "password", "confirmation");
        assertFalse(result);
    }

    @Test
    public void isRegistrationInputValid_returns_false_when_password_and_confirmation_is_empty()
            throws Exception {
        initPresenter();
        final boolean result = presenter.isRegistrationInputValid("user", "", "");
        assertFalse(result);
    }

    @Test
    public void isRegistrationInputValid_returns_false_when_password_is_empty()
            throws Exception {
        initPresenter();
        final boolean result = presenter.isRegistrationInputValid("user", "", "password");
        assertFalse(result);
    }

    @Test
    public void isRegistrationInputValid_returns_false_when_confirmation_is_empty()
            throws Exception {
        initPresenter();
        final boolean result = presenter.isRegistrationInputValid("user", "password", "");
        assertFalse(result);
    }

    @NonNull
    @Override
    protected LoginPresenterImpl createPresenter() {
        return new LoginPresenterImpl(userRepository);
    }
}