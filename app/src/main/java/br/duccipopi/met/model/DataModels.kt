package br.duccipopi.met.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

data class SearchResult(
    val total: Int,
    @Json(name = "objectIDs") val ids: List<Int>?
)

data class DepartmentResult(
    val departments: List<Department>
)

@Entity(tableName = "department_table")
data class Department(
    @PrimaryKey @Json(name = "departmentId") val id: Int,
    @Json(name = "displayName") val name: String
)

@Entity(tableName = "artwork_table")
data class Artwork(
    @PrimaryKey @Json(name = "objectID") val id: Int,
    val isHighlight: Boolean,
    val accessionYear: String,
    val isPublicDomain: Boolean,
    @Json(name = "primaryImage") val image: String,
    @Json(name = "primaryImageSmall") val smallImage: String,
    val department: String,
    @Json(name = "objectName") val type: String,
    val title: String,
    val culture: String,
    val period: String,
    @Json(name = "artistDisplayName") val artist: String,
    @Json(name = "artistDisplayBio") val artistBio: String,
    @Json(name = "artistWikidata_URL") val artistWiki: String,
    @Json(name = "objectDate") val date: String,
    val dimensions: String,
    val country: String,
    @Json(name = "rightsAndReproduction") val rights: String,
    @Json(name = "objectURL") val link: String,
    @Json(name = "objectWikidata_URL") val wikiLink: String

)