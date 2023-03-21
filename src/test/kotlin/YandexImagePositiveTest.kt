import extention.FileExtension
import extention.JsonToObject
import io.qameta.allure.Allure
import io.qameta.allure.Allure.ThrowableRunnableVoid
import io.restassured.RestAssured.get
import io.restassured.RestAssured.given
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.api.function.Executable
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import pojo.Rename


@ExtendWith(*[Specification::class, FileExtension::class])
class YandexImagePositiveTest {

    @ParameterizedTest
    @DisplayName("смена имени файла")
    @ValueSource(strings = ["rename/rename.json"])
    fun renameImage(@JsonToObject rename: Rename) {

        Allure.step("Переименование картинки", ThrowableRunnableVoid {
            given().`when`().queryParams(rename.toMap()).post("/move").then().statusCode(201)

        })

        Allure.step("Проверка, что файл переименован", ThrowableRunnableVoid {
            val name =
                get("/files?limit=100").then().statusCode(200).extract().body().jsonPath().getList<String>("items.name")
            assertThat(name).contains(rename.path?.replace("/", ""))
        })



    }
}