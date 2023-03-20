package extention

import io.restassured.RestAssured
import org.junit.jupiter.api.extension.AfterAllCallback
import org.junit.jupiter.api.extension.ExtensionContext

class FileExtension : AfterAllCallback {
    override fun afterAll(context: ExtensionContext?) {
        RestAssured.given().delete("ТестоваяКартинка")
    }
}