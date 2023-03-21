import io.qameta.allure.restassured.AllureRestAssured
import io.restassured.RestAssured
import io.restassured.builder.RequestSpecBuilder
import io.restassured.filter.log.RequestLoggingFilter
import io.restassured.filter.log.ResponseLoggingFilter
import io.restassured.http.ContentType
import org.junit.jupiter.api.extension.BeforeAllCallback
import org.junit.jupiter.api.extension.ExtensionContext
import utils.MyProperties

class Specification() : BeforeAllCallback {
    val mp = MyProperties()
    override fun beforeAll(context: ExtensionContext?) {
        RestAssured.requestSpecification = null
        RestAssured.requestSpecification = RequestSpecBuilder()
            .setRelaxedHTTPSValidation()
            .addHeader("Authorization", "OAuth ${mp.getToken()}")
            .setBaseUri(mp.getBaseUrl())
            .setContentType(ContentType.JSON)
            .addFilter(ResponseLoggingFilter())
            .addFilter(RequestLoggingFilter())
            .addFilter(AllureRestAssured())
            .build()
    }

}
