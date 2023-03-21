import extention.FileExtension
import extention.JsonToObject
import io.restassured.RestAssured.get
import io.restassured.RestAssured.given
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import pojo.Rename


@ExtendWith(*[Specification::class, FileExtension::class])
class YandexImagePositiveTest {

    @ParameterizedTest
    @DisplayName("смена имени файла")
    @ValueSource(strings = ["rename/rename.json"])
    fun renameImage(@JsonToObject rename: Rename) {
//      Переименование
        given().`when`().queryParams(rename.toMap()).post("/move").then().statusCode(201)
//      Получение и проверка
        val name =
            get("/files?limit=100").then().statusCode(200).extract().body().jsonPath().getList<String>("items.name")
        assertThat(name).contains(rename.path?.replace("/",""))
    }
}