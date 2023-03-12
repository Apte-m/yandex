package extention

import org.junit.jupiter.params.converter.ConvertWith



@Target(AnnotationTarget.VALUE_PARAMETER)
@Retention(AnnotationRetention.RUNTIME)
@ConvertWith(ConverterFromJson::class)
annotation class JsonToObject()
