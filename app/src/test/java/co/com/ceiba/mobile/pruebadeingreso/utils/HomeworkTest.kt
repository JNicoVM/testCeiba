package co.com.ceiba.mobile.pruebadeingreso.utils

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class HomeworkTest{

    @Test
    fun `input 0 return 0`(){
        val result = Homework.fib(0)
        assertThat(result).isEqualTo(0)
    }

    @Test
    fun `input 1 return 1`(){
        val result = Homework.fib(1)
        assertThat(result).isEqualTo(1)
    }

    @Test
    fun `input 2 return 1`(){
        val result = Homework.fib(2)
        assertThat(result).isEqualTo(1)
    }

    @Test
    fun `input 4 return 3`(){
        val result = Homework.fib(4)
        assertThat(result).isEqualTo(3)
    }

    @Test
    fun `input 5 return 3`(){
        val result = Homework.fib(5)
        assertThat(result).isEqualTo(5)
    }

    @Test
    fun `input 12 return 3`(){
        val result = Homework.fib(12)
        assertThat(result).isEqualTo(144)
    }

    @Test
    fun `input 9 return 34`(){
        val result = Homework.fib(9)
        assertThat(result).isEqualTo(34)
    }
}