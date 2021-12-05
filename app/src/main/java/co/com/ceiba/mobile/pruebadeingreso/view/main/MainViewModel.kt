package co.com.ceiba.mobile.pruebadeingreso.view.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.com.ceiba.mobile.pruebadeingreso.domain.models.User
import co.com.ceiba.mobile.pruebadeingreso.domain.usescases.GetUsersFromDbUseCase
import co.com.ceiba.mobile.pruebadeingreso.domain.usescases.GetUsersFromNetworkUseCase
import co.com.ceiba.mobile.pruebadeingreso.domain.usescases.InsertUsersIntoDbUseCase
import co.com.ceiba.mobile.pruebadeingreso.utils.DispatcherProvider
import co.com.ceiba.mobile.pruebadeingreso.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import okhttp3.Dispatcher
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getUsersFromNetworkUseCase: GetUsersFromNetworkUseCase,
    private val getUsersFromDbUseCase: GetUsersFromDbUseCase,
    private val insertUsersIntoDbUseCase: InsertUsersIntoDbUseCase,
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

    /**
     *  Get User List
     *  search at the local DB for users if
     *  ...Doesn't find users It calls @GetUserNetWorkUserCase and then save in DB once the data arrives
     *  ...Does find users It gives the view data to show users
     */
    fun getUserList() {
        viewModelScope.launch(dispatchers.io) {
            _userCall.postValue(MainEvent.Loading)
            when(val usersFromDbResponse = getUsersFromDbUseCase.invoke()){
                is Resource.Error -> _userCall.postValue(
                    MainEvent.Failure(
                        usersFromDbResponse.message ?: "No data found"
                    )
                )
                is Resource.Success -> {
                    if(usersFromDbResponse.data?.isEmpty() != false){
                        when (val usersResponse = getUsersFromNetworkUseCase.invoke()) {
                            is Resource.Error -> _userCall.postValue(
                                MainEvent.Failure(
                                    usersResponse.message ?: "No data found"
                                )
                            )
                            is Resource.Success -> {
                                _localUserList = usersResponse.data ?: listOf()
                                insertUsersIntoDbUseCase.invoke(_localUserList)
                                _userCall.postValue(MainEvent.Success(_localUserList))
                            }
                        }
                    }else{
                        _localUserList = usersFromDbResponse.data
                        _userCall.postValue(MainEvent.Success(_localUserList))
                    }

                }
            }

        }
    }

    /**
     * GET user by search input
     * By a @String search in each local list element to found out if
     * ...It contains an element, then It is shown at the view with similar elements
     */
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