package pojo

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.ObjectMapper

data class Image(

    @field:JsonProperty("path")
    val path: String? = null,

    @field:JsonProperty("url")
    val url: String? = null
) {
    fun toMap(): Map<String, Any> {
        return ObjectMapper().convertValue(this, Map::class.java) as Map<String, Any>
    }
}

