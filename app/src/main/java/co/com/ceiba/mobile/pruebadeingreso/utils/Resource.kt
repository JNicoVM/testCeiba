package co.com.ceiba.mobile.pruebadeingreso.utils

/**
 * Resource control the success or error possible results
 * of a call from a data source (local, remote)
 */
sealed class Resource<T> ( val data: T?, val message: String?) {

    class Success<T>(data: T) : Resource<T>(data, null)
    class Error<T>(message:String) : Resource<T>(null, message)
}