package extention

import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.params.converter.ArgumentConversionException
import org.junit.jupiter.params.converter.SimpleArgumentConverter
import java.io.IOException

class ConverterFromJson : SimpleArgumentConverter() {
    private val objectMapper = ObjectMapper()
    override fun convert(source: Any, targetType: Class<*>): Any {
        if (source !is String) {
            throw IllegalArgumentException("Ошибка значение должно быть строкой")
        }
        return try {
            val inputStream = this::class.java.classLoader.getResourceAsStream(source)
                ?: throw IllegalArgumentException("Фаайл $source не найден")
            objectMapper.readValue(inputStream, targetType)
        } catch (e: IOException) {
            throw ArgumentConversionException("Ошибка конвертируемого типа", e)
        }
    }
}
