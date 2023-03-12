package pojo

import com.fasterxml.jackson.annotation.JsonProperty

data class File(

	@field:JsonProperty("offset")
	val offset: Int? = null,

	@field:JsonProperty("limit")
	val limit: Int? = null,

	@field:JsonProperty("items")
	val items: List<ItemsItem?>? = null
)

data class Exif(

	@field:JsonProperty("date_time")
	val dateTime: String? = null
)

data class ItemsItem(

	@field:JsonProperty("preview")
	val preview: String? = null,

	@field:JsonProperty("sha256")
	val sha256: String? = null,

	@field:JsonProperty("created")
	val created: String? = null,

	@field:JsonProperty("type")
	val type: String? = null,

	@field:JsonProperty("antivirus_status")
	val antivirusStatus: String? = null,

	@field:JsonProperty("revision")
	val revision: Long? = null,

	@field:JsonProperty("path")
	val path: String? = null,

	@field:JsonProperty("file")
	val file: String? = null,

	@field:JsonProperty("size")
	val size: Int? = null,

	@field:JsonProperty("mime_type")
	val mimeType: String? = null,

	@field:JsonProperty("media_type")
	val mediaType: String? = null,

	@field:JsonProperty("name")
	val name: String? = null,

	@field:JsonProperty("resource_id")
	val resourceId: String? = null,

	@field:JsonProperty("modified")
	val modified: String? = null,

	@field:JsonProperty("comment_ids")
	val commentIds: CommentIds? = null,

	@field:JsonProperty("exif")
	val exif: Exif? = null,

	@field:JsonProperty("md5")
	val md5: String? = null
)

data class CommentIds(

	@field:JsonProperty("private_resource")
	val privateResource: String? = null,

	@field:JsonProperty("public_resource")
	val publicResource: String? = null
)
