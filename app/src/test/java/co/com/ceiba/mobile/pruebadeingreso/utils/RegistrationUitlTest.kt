package co.com.ceiba.mobile.pruebadeingreso.utils

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class RegistrationUitlTest{

    @Test
    fun `empty username returns false`(){
        val result = RegistrationUitl.validatorRegistrationInput(
            username = "",
            password = "123",
            confirmedPassword = "123"
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `valid username and correctly repeated password returns true`(){
        val result = RegistrationUitl.validatorRegistrationInput(
            username = "Philip",
            password = "123",
            confirmedPassword = "123"
        )
        assertThat(result).isTrue()
    }

    @Test
    fun `empty password return false`(){
        val result = RegistrationUitl.validatorRegistrationInput(
            username = "Loro",
            password = "",
            confirmedPassword = "123"
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `empty confirmedPassword return false`(){
        val result = RegistrationUitl.validatorRegistrationInput(
            username = "Loro",
            password = "123",
            confirmedPassword = ""
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `confirmedPassword is repeated correctly return true`(){
        val result = RegistrationUitl.validatorRegistrationInput(
            username = "Karlo",
            password = "123",
            confirmedPassword = "123"
        )
        assertThat(result).isTrue()
    }

    @Test
    fun `password contains less than 2 digits return false`(){
        val result = RegistrationUitl.validatorRegistrationInput(
            username = "Gao",
            password = "rewr1",
            confirmedPassword = "rewr1"
        )
        assertThat(result).isFalse()
    }

}