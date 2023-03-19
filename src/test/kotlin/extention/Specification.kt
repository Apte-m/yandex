import extention.BasePath
import io.qameta.allure.restassured.AllureRestAssured
import io.restassured.RestAssured
import io.restassured.builder.RequestSpecBuilder
import io.restassured.filter.log.RequestLoggingFilter
import io.restassured.filter.log.ResponseLoggingFilter
import io.restassured.http.ContentType
import io.restassured.specification.RequestSpecification
import org.junit.jupiter.api.extension.*
import utils.Variable

class Specification() : BeforeEachCallback, TestInstancePostProcessor, ParameterResolver {

    override fun beforeEach(context: ExtensionContext?) {
        val basePathAnnotationC = context?.requiredTestClass?.getAnnotation(BasePath::class.java)
        val basePathAnnotationM = context?.requiredTestMethod?.getAnnotation(BasePath::class.java)


        val basePath = basePathAnnotationM?.path ?: basePathAnnotationC?.path ?: ""
        RestAssured.requestSpecification = null
        RestAssured.requestSpecification = RequestSpecBuilder()
            .setRelaxedHTTPSValidation()
            .addHeader("Authorization", "OAuth ${Variable.TOKEN.value}")
            .setBaseUri(Variable.URL.value)
            .setBasePath(basePath)
            .setContentType(ContentType.JSON)
            .addFilter(ResponseLoggingFilter())
            .addFilter(RequestLoggingFilter())
            .addFilter(AllureRestAssured())
            .build()
    }

    override fun postProcessTestInstance(testInstance: Any, context: ExtensionContext) {
        val rs = RestAssured.requestSpecification
        context.getStore(ExtensionContext.Namespace.GLOBAL).put("specification", rs)
    }

    override fun supportsParameter(parameterContext: ParameterContext, extensionContext: ExtensionContext): Boolean {
        return parameterContext.parameter.type == RequestSpecification::class.java
    }

    override fun resolveParameter(parameterContext: ParameterContext, extensionContext: ExtensionContext): Any {
        return extensionContext.getStore(ExtensionContext.Namespace.GLOBAL).get("specification", RequestSpecification::class.java)
    }

    fun setCustomBasePath(basePath: String) {
        RestAssured.requestSpecification = RequestSpecBuilder().setBasePath(basePath).build()
    }


}
