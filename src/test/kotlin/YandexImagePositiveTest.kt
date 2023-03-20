
import extention.FileExtension
import extention.JsonToObject
import io.restassured.RestAssured
import io.restassured.RestAssured.get
import io.restassured.RestAssured.given
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import pojo.File
import pojo.Image
import pojo.Rename
import utils.Variable.*


@ExtendWith(*[Specification::class,FileExtension::class])
class YandexImagePositiveTest {

    private var f = File()


    @Test
    @DisplayName("поиск файла")
    fun getInfo() {
        f = get("/files").`as`(File::class.java)
        assertTrue(f.items?.any { item -> item?.name == "Москва.jpg" } ?: false)
    }



    @ValueSource(strings = ["image/image.first.json", "image/image.second.json"])
    @ParameterizedTest
    @DisplayName("создание файла и  проверка его создания")
    // Тут конвертируется любой  json  в обьект, который нужен и может быть любое количество
    fun createImage(@JsonToObject image: Image) {
        //        Загрузка картинки
        RestAssured.given().queryParams(image.toMap()).`when`().post("/upload")
        //        Проверка, что картинка загружена

        f = get("/files").`as`(File::class.java)
        assertTrue(f.items?.any { item -> item?.name == "${image.path}" } ?: false)
    }


    @ParameterizedTest
    @DisplayName("смена имени файла")
    @ValueSource(strings = ["rename/rename.first.json", "rename/rename.second.json"])
    fun renameImage(@JsonToObject rename: Rename) {

        given().`when`().queryParams(rename.toMap()).post("/move")


        f = get("/files").`as`(File::class.java)
        val names = f.items?.mapNotNull { it?.name }
        assertThat(names).contains("ТестоваяКартинка")
    }


}