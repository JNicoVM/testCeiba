package co.com.ceiba.mobile.pruebadeingreso.view.post

import androidx.lifecycle.*
import co.com.ceiba.mobile.pruebadeingreso.domain.AppRepository
import co.com.ceiba.mobile.pruebadeingreso.domain.Repository
import co.com.ceiba.mobile.pruebadeingreso.domain.models.Post
import co.com.ceiba.mobile.pruebadeingreso.domain.usescases.GetPostsFromNetworkUseCase
import co.com.ceiba.mobile.pruebadeingreso.rest.Const
import co.com.ceiba.mobile.pruebadeingreso.utils.DispatcherProvider
import co.com.ceiba.mobile.pruebadeingreso.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(
    private val repository: Repository,
    savedStateHandle: SavedStateHandle
) : ViewModel(){

    private var _name = ""
    val name: String
        get() = _name
    private var _phone = ""
    val phone: String
        get() = _phone

    private var _email = ""
    val email: String
        get() = _email

    private var _id = -1
    val id: Int
        get() = _id

    init {
        _name = savedStateHandle.get<String>(Const.NAME) ?:""
        _phone = savedStateHandle.get<String>(Const.PHONE) ?:""
        _email = savedStateHandle.get<String>(Const.EMAIL) ?:""
        _id = savedStateHandle.get<Int>(Const.ID) ?: -1
    }

    /**
     * Sealed class @PostEvent then the @PostViewModel has all necessary characteristics
     * of an abstract PostActivity data request
     */
    sealed class PostEvent {
        class Success(val posts: List<Post>) : PostEvent()
        class Failure(val errorText: String) : PostEvent()
        object Loading : PostEvent()
        object Empty :PostEvent()
    }

    private val _postCall = MutableLiveData<PostEvent>(PostEvent.Empty)
    val postCall: LiveData<PostEvent> = _postCall
    private var _localPostList: List<Post> = listOf()
    val localPostList: List<Post>
        get() = _localPostList

    /**
     *  Get Post List
     *   It calls @GetPostNetWorkUsesCase and then shares the data to the view
     *   once the data arrives
     */
    fun getPostList() {
        viewModelScope.launch(Dispatchers.IO) {
            _postCall.postValue(PostEvent.Loading)
            when (val postsResponse = repository.getPostsFromNetwork(_id)) {
                is Resource.Error -> _postCall.postValue(
                    PostEvent.Failure(
                        postsResponse.message ?: "No data found"
                    )
                )
                is Resource.Success -> {
                    _localPostList = postsResponse.data ?: listOf()
                    _postCall.postValue(PostEvent.Success(_localPostList))
                }
            }
        }
    }

}