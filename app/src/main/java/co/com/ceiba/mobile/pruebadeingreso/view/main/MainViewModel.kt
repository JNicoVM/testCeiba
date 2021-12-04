package co.com.ceiba.mobile.pruebadeingreso.view.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.com.ceiba.mobile.pruebadeingreso.data.network.models.UserResponse
import co.com.ceiba.mobile.pruebadeingreso.domain.models.User
import co.com.ceiba.mobile.pruebadeingreso.domain.repositories.main.MainRepository
import co.com.ceiba.mobile.pruebadeingreso.utils.DispatcherProvider
import co.com.ceiba.mobile.pruebadeingreso.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainRepository: MainRepository,
    private val dispatchers: DispatcherProvider
) : ViewModel() {

    /**
     * Sealed class @MainEvent then the @MainViewModel has all necessary characteristics
     * of an abstract MainActivity data request
     */
    sealed class MainEvent {
        class Success(val users: List<User>) : MainEvent()
        class Failure(val errorText: String) : MainEvent()
        object Loading : MainEvent()
        object Empty : MainEvent()
    }

    private val _userList = MutableLiveData<MainEvent>(MainEvent.Empty)
    val userList: LiveData<MainEvent> = _userList

    fun getUserList() {
        viewModelScope.launch(dispatchers.io) {
            _userList.postValue(MainEvent.Loading)
            when (val usersResponse = mainRepository.getUsers()) {
                is Resource.Error -> _userList.postValue(
                    MainEvent.Failure(
                        usersResponse.message ?: "No data found"
                    )
                )
                is Resource.Success -> {
                    _userList.postValue(MainEvent.Success(usersResponse.data!!))
                }
            }
        }
    }
}