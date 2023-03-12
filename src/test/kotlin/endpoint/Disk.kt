package endpoint


import io.restassured.RestAssured.get
import io.restassured.RestAssured.given
import io.restassured.response.Response
import java.util.*


class Disk : CRUD {

    override fun getInfo(): Response {
        return get()
    }


    override fun post(o: Map<String, Any>) {
        given().queryParams(o).`when`().post()
    }

    override fun update(o: Objects) {
        TODO("Not yet implemented")
    }

    override fun delete(id: String) {
        given().param("path",id)
    }
}