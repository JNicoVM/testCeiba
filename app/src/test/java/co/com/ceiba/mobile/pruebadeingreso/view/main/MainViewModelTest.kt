package co.com.ceiba.mobile.pruebadeingreso.view.main

import co.com.ceiba.mobile.pruebadeingreso.domain.FakeAppRepository
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.Dispatchers
import org.junit.Before
import org.junit.Test

class MainViewModelTest{
    private lateinit var viewModel: MainViewModel

    @Before
    fun setUp(){
        viewModel = MainViewModel(FakeAppRepository())
    }

    /**
     * The following test return true if there is data
     */
    @Test
    fun `get all user from network return true with data`(){
        viewModel.getUserList()
       val isListNotEmpy =  viewModel.localUserList.isEmpty()
        assertThat(isListNotEmpy).isEqualTo(false)
    }
}