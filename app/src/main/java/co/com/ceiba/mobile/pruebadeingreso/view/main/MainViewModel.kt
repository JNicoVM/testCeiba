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

    private val _userCall = MutableLiveData<MainEvent>(MainEvent.Empty)
    val userCall: LiveData<MainEvent> = _userCall
    private var _localUserList: List<User> = listOf()
    val localUserList: List<User>
        get() = _localUserList

    fun getUserList() {
        viewModelScope.launch(dispatchers.io) {
            _userCall.postValue(MainEvent.Loading)
            when (val usersResponse = mainRepository.getUsers()) {
                is Resource.Error -> _userCall.postValue(
                    MainEvent.Failure(
                        usersResponse.message ?: "No data found"
                    )
                )
                is Resource.Success -> {
                    _localUserList = usersResponse.data ?: listOf()
                    _userCall.postValue(MainEvent.Success(_localUserList))
                }
            }
        }
    }

    fun getUserBySearchInput(searchInput: String) {
        viewModelScope.launch(dispatchers.io) {
            _userCall.postValue(MainEvent.Loading)
            val filteredList = _localUserList.filter {
                it.name.lowercase().contains(searchInput.lowercase())
            }
            _userCall.postValue(MainEvent.Success(filteredList))
        }
    }
}