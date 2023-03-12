package endpoint

import io.restassured.response.Response
import java.util.Objects

interface CRUD {
    fun getInfo(): Response
    fun post(o: Map<String, Any>)
    fun update(o: Objects)
    fun delete(id: String)
}