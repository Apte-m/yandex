import extention.FileExtension
import extention.JsonToObject
import io.restassured.RestAssured.get
import io.restassured.RestAssured.given
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import org.junit.jupiter.params.provider.ValueSource
import pojo.File
import pojo.Image
import pojo.Rename
import utils.Variable.*


@ExtendWith(*[Specification::class, FileExtension::class])
class YandexImagePositiveTest {


    @ValueSource(strings = ["image/image.first.json", "image/image.second.json"])
    @ParameterizedTest
    @DisplayName("создание файла и  проверка его создания")
//      Тут конвертируется любой  json  в обьект, который нужен и может быть любое количество
    fun createImage(@JsonToObject image: Image) {
//      Загрузка картинки
        given().queryParams(image.toMap()).`when`().post("/upload")
//      Проверка, что картинка загружена
        val name = get("/files").then().extract().body().jsonPath().getList<String>("items.name")
        assertThat(name).contains(image.path)
    }


    @ParameterizedTest
    @DisplayName("смена имени файла")
    @ValueSource(strings = ["rename/rename.first.json"])

    fun renameImage(@JsonToObject rename: Rename) {
//      Переименование
        given().`when`().queryParams(rename.toMap()).post("/move")
//      Получение и проверка
        val name = get("/files").then().extract().body().jsonPath().getList<String>("items.name")
        assertThat(name).contains(rename.from?.replace("/", ""))

    }


}