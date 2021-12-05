package co.com.ceiba.mobile.pruebadeingreso.data.mappers

interface ModelMapper <T , R> {

    fun mapFromModelOrigin(model: T): R
    fun mapToModelOrigin(model: R) : T
}