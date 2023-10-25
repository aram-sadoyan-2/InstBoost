package com.ins.engage.model.response

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class InstaProfileModel(
    @SerializedName("graphql") val graphql: Graphql? = null,
    @SerializedName("logging_page_id") val loggingPageId: String? = null
)

class Graphql : Serializable {
    @SerializedName("user")
    var user: User? = null
}

class User : Serializable {
    @SerializedName("edge_owner_to_timeline_media")
    val edgeOwnerToTimeLineMedia: EdgeOwnerToTimeLineMedia = EdgeOwnerToTimeLineMedia()

    @SerializedName("full_name")
    var fullName: String? = null

    @SerializedName("id") /* renamed from: id */
    val id = ""

    @SerializedName("profile_pic_url")
    var profilePictureUrl: String? = null

    @SerializedName("username")
    val userName: String? = null
}

class EdgeOwnerToTimeLineMedia : Serializable {
    @SerializedName("count")
    val count = 0

    @SerializedName("edges")   // todo post list
    val edgesList: List<Edges>? = null
}

class Edges : Serializable {
    @SerializedName("node")
    val node: Node? = null
}

class Node : Serializable {
    @SerializedName("display_url")  //todo post picture url
    val displayUrl: String? = null

    @SerializedName("edge_liked_by")
    val edgeLikedBy: EdgeLikedBy? = null

    @SerializedName("edge_media_to_comment")
    val edgeMediaToComment: EdgeMediaToComment? = null

    @SerializedName("is_video")
    val isVideo = false

    @SerializedName("shortcode")
    val shortCode: String? = null

    @SerializedName("thumbnail_src")
    val thumbnailUrl: String? = null
}

class EdgeLikedBy : Serializable {
    @SerializedName("count")
    val likeCount = -1 // todo posts like count
}

class EdgeMediaToComment : Serializable {
    @SerializedName("count")
    val commentCount = -1
}

