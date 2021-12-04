package co.com.ceiba.mobile.pruebadeingreso.utils

object Homework {
    fun fib(n:Int):Long{
        if(n==0 || n == 1){
            return n.toLong()
        }
        var a = 0L
        var b = 1L
        var c = 1L

        (1 until n).forEach { i: Int ->
            c = a + b
            a = b
            b = c
        }
        return c
    }
}