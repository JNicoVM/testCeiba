package co.com.ceiba.mobile.pruebadeingreso.view.post

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import co.com.ceiba.mobile.pruebadeingreso.rest.Const
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class PostViewModel @Inject constructor(
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


}