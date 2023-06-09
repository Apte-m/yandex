import extention.FileExtension
import extention.JsonToObject
import io.qameta.allure.Allure
import io.qameta.allure.Allure.ThrowableRunnableVoid
import io.qameta.allure.AllureId
import io.qameta.allure.Owner
import io.restassured.RestAssured.get
import io.restassured.RestAssured.given
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import pojo.Rename
import utils.Variable


@ExtendWith(*[Specification::class, FileExtension::class])
class YandexImagePositiveTest {

    @ParameterizedTest
    @DisplayName("смена имени файла")
    @ValueSource(strings = ["rename/rename.json"])
    @Owner("Одинцов")
    @AllureId("1")
    fun renameImage(@JsonToObject rename: Rename) {

        Allure.step("Переименование картинки", ThrowableRunnableVoid {
            given().`when`().queryParams(rename.toMap()).post("/move").then().statusCode(201)

        })

        Allure.step("Проверка, что файл переименован", ThrowableRunnableVoid {
            val name =
                get("/files?limit=100").then().statusCode(200).extract().body().jsonPath().getList<String>("items.name")
            assertThat(name).contains(Variable.IMAGE_NAME.value)
        })

    }
}