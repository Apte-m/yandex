import endpoint.Disk
import extention.BasePath
import extention.JsonToObject
import io.restassured.RestAssured
import io.restassured.builder.RequestSpecBuilder
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.api.extension.RegisterExtension
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import pojo.File
import pojo.Image
import pojo.Rename
import utils.Variable.*


@ExtendWith(Specification::class)
class YandexImagePositiveTest {

    private val d = Disk()
    private var f = File()


    @Test
    @BasePath("/files")
    @DisplayName("поиск файла")
    fun test() {
        f = d.getInfo().`as`(File::class.java)
        assertTrue(f.items?.any { item -> item?.name == "Москва.jpg" } ?: false)
    }


    @BasePath("/upload")
    @ValueSource(strings = ["image/image.first.json","image/image.second.json"])
    @ParameterizedTest
    @DisplayName("создание файла и  проверка его создания")
    // Тут конвертируется любой  json  в обьект, который нужен и может быть любое количество
    fun createImage(@JsonToObject image: Image) {
        //        Загрузка картинки
        d.post(image.toMap())
        //        Проверка, что картинка загружена

        Specification().setCustomBasePath("/files")
        f = d.getInfo().`as`(File::class.java)
        assertTrue(f.items?.any { item -> item?.name == "${image.path}" } ?: false)
    }

    @BasePath("/move")
    @ParameterizedTest
    @DisplayName("смена имени файла")
    @ValueSource(strings = ["rename/rename.first.json", "rename/rename.second.json"])
    fun renameImage(@JsonToObject rename: Rename) {
        d.post(rename.toMap())
    }

    @Test
    @DisplayName("Удаление картинки")
    fun deleteById() {
        d.delete("ТестоваяКартинка")
    }
}