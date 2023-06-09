package extention

import io.restassured.RestAssured
import org.junit.jupiter.api.extension.AfterAllCallback
import org.junit.jupiter.api.extension.BeforeAllCallback
import org.junit.jupiter.api.extension.ExtensionContext
import utils.MyProperties
import utils.Variable

class FileExtension : AfterAllCallback,BeforeAllCallback {

    val mp = MyProperties()
    override fun afterAll(context: ExtensionContext?) {
        RestAssured.given().
        `when`().
        queryParam("path", Variable.IMAGE_NAME.value).
        delete()
    }

    override fun beforeAll(context: ExtensionContext?) {
        //      Загрузка картинки
        RestAssured.given().
        queryParams("path", "ТестоваяКартинка", "url", mp.getUrl())
            .`when`().
            post("/upload").
            then().
            statusCode(202)
    }
}