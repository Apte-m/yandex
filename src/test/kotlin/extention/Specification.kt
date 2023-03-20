import io.qameta.allure.restassured.AllureRestAssured
import io.restassured.RestAssured
import io.restassured.builder.RequestSpecBuilder
import io.restassured.http.ContentType
import org.junit.jupiter.api.extension.BeforeEachCallback
import org.junit.jupiter.api.extension.ExtensionContext
import utils.Variable

class Specification() : BeforeEachCallback {

    override fun beforeEach(context: ExtensionContext?) {
        RestAssured.requestSpecification = null
        RestAssured.requestSpecification = RequestSpecBuilder()
            .setRelaxedHTTPSValidation()
            .addHeader("Authorization", "OAuth ${Variable.TOKEN.value}")
            .setBaseUri(Variable.URL.value)
            .setContentType(ContentType.JSON)
//            .addFilter(ResponseLoggingFilter())
//            .addFilter(RequestLoggingFilter())
            .addFilter(AllureRestAssured())
            .build()
    }

}
