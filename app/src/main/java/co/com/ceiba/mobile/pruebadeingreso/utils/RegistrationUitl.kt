package co.com.ceiba.mobile.pruebadeingreso.utils

object RegistrationUitl {

    private val existingUsers = listOf<String>("Pedro", "Maria")

    /**
     * the input is not valid if....
     * ...the username || password if empty
     * ...the username is already taken
     * ...the confirmed password id not the same as the real password
     * ...the password contains less than 2 digits
     */
    fun validatorRegistrationInput(
        username: String,
        password: String,
        confirmedPassword: String,
    ): Boolean {
        if(username.isEmpty() || password.isEmpty()){
            return false
        }
        if(username in existingUsers){
            return false
        }
        if(password!=confirmedPassword){
            return false
        }
        if(password.count { it.isDigit() } < 2){
            return false
        }
        return true
    }
}