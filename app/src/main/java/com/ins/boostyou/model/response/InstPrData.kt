package com.ins.boostyou.model.response

import com.google.gson.annotations.SerializedName

data class InstPrData(
    val data: Data? = null,
    val status: String? = null,
    @SerializedName("user")
    val user: User? = null
)

data class Data(
    @SerializedName("user")
    val user: User
)

data class User(
    @SerializedName("ai_agent_type")
    val aiAgentType: Any?,
    val biography: String,
    @SerializedName("bio_links")
    val bioLinks: List<Any?>,
    @SerializedName("fb_profile_biolink")
    val fbProfileBiolink: Any?,
    @SerializedName("biography_with_entities")
    val biographyWithEntities: BiographyWithEntities,
    @SerializedName("blocked_by_viewer")
    val blockedByViewer: Boolean,
    @SerializedName("restricted_by_viewer")
    val restrictedByViewer: Any?,
    @SerializedName("country_block")
    val countryBlock: Boolean,
    @SerializedName("eimu_id")
    val eimuId: String,
    @SerializedName("external_url")
    val externalUrl: Any?,
    @SerializedName("external_url_linkshimmed")
    val externalUrlLinkshimmed: Any?,
    @SerializedName("edge_followed_by")
    val edgeFollowedBy: EdgeFollowedBy,
    val fbid: String,
    @SerializedName("followed_by_viewer")
    val followedByViewer: Boolean,
    @SerializedName("edge_follow")
    val edgeFollow: EdgeFollow,
    @SerializedName("follows_viewer")
    val followsViewer: Boolean,
    @SerializedName("full_name")
    val fullName: String,
    @SerializedName("group_metadata")
    val groupMetadata: Any?,
    @SerializedName("has_ar_effects")
    val hasArEffects: Boolean,
    @SerializedName("has_clips")
    val hasClips: Boolean,
    @SerializedName("has_guides")
    val hasGuides: Boolean,
    @SerializedName("has_channel")
    val hasChannel: Boolean,
    @SerializedName("has_blocked_viewer")
    val hasBlockedViewer: Boolean,
    @SerializedName("highlight_reel_count")
    val highlightReelCount: Long,
    @SerializedName("has_requested_viewer")
    val hasRequestedViewer: Boolean,
    @SerializedName("hide_like_and_view_counts")
    val hideLikeAndViewCounts: Boolean,
    val id: String,
    @SerializedName("is_business_account")
    val isBusinessAccount: Boolean,
    @SerializedName("is_professional_account")
    val isProfessionalAccount: Boolean,
    @SerializedName("is_supervision_enabled")
    val isSupervisionEnabled: Boolean,
    @SerializedName("is_guardian_of_viewer")
    val isGuardianOfViewer: Boolean,
    @SerializedName("is_supervised_by_viewer")
    val isSupervisedByViewer: Boolean,
    @SerializedName("is_supervised_user")
    val isSupervisedUser: Boolean,
    @SerializedName("is_embeds_disabled")
    val isEmbedsDisabled: Boolean,
    @SerializedName("is_joined_recently")
    val isJoinedRecently: Boolean,
    @SerializedName("guardian_id")
    val guardianId: Any?,
    @SerializedName("business_address_json")
    val businessAddressJson: Any?,
    @SerializedName("business_contact_method")
    val businessContactMethod: String,
    @SerializedName("business_email")
    val businessEmail: Any?,
    @SerializedName("business_phone_number")
    val businessPhoneNumber: Any?,
    @SerializedName("business_category_name")
    val businessCategoryName: String,
    @SerializedName("overall_category_name")
    val overallCategoryName: Any?,
    @SerializedName("category_enum")
    val categoryEnum: Any?,
    @SerializedName("category_name")
    val categoryName: String,
    @SerializedName("is_private")
    val isPrivate: Boolean,
    @SerializedName("is_verified")
    val isVerified: Boolean,
    @SerializedName("is_verified_by_mv4b")
    val isVerifiedByMv4b: Boolean,
    @SerializedName("is_regulated_c18")
    val isRegulatedC18: Boolean,
    @SerializedName("edge_mutual_followed_by")
    val edgeMutualFollowedBy: EdgeMutualFollowedBy,
    @SerializedName("pinned_channels_list_count")
    val pinnedChannelsListCount: Long,
    @SerializedName("profile_pic_url")
    val profilePicUrl: String,
    @SerializedName("profile_pic_url_hd")
    val profilePicUrlHd: String,
    @SerializedName("requested_by_viewer")
    val requestedByViewer: Boolean,
    @SerializedName("should_show_category")
    val shouldShowCategory: Boolean,
    @SerializedName("should_show_public_contacts")
    val shouldShowPublicContacts: Boolean,
    @SerializedName("show_account_transparency_details")
    val showAccountTransparencyDetails: Boolean,
    @SerializedName("remove_message_entrypoint")
    val removeMessageEntrypoint: Boolean,
    @SerializedName("transparency_label")
    val transparencyLabel: Any?,
    @SerializedName("transparency_product")
    val transparencyProduct: Any?,
    val username: String,
    @SerializedName("connected_fb_page")
    val connectedFbPage: Any?,
    val pronouns: List<Any?>,
    @SerializedName("edge_felix_video_timeline")
    val edgeFelixVideoTimeline: EdgeFelixVideoTimeline,
    @SerializedName("edge_owner_to_timeline_media")
    val edgeOwnerToTimelineMedia: EdgeOwnerToTimelineMedia,
    @SerializedName("edge_saved_media")
    val edgeSavedMedia: EdgeSavedMedia,
    @SerializedName("edge_media_collections")
    val edgeMediaCollections: EdgeMediaCollections,
    @SerializedName("edge_related_profiles")
    val edgeRelatedProfiles: EdgeRelatedProfiles,
)

data class BiographyWithEntities(
    @SerializedName("raw_text")
    val rawText: String,
    val entities: List<Any?>,
)

data class EdgeFollowedBy(
    val count: Long,
)

data class EdgeFollow(
    val count: Long,
)

data class EdgeMutualFollowedBy(
    val count: Long,
    val edges: List<Any?>,
)

data class EdgeFelixVideoTimeline(
    val count: Long,
    @SerializedName("page_info")
    val pageInfo: PageInfo,
    val edges: List<Any?>,
)

data class PageInfo(
    @SerializedName("has_next_page")
    val hasNextPage: Boolean,
    @SerializedName("end_cursor")
    val endCursor: Any?,
)

data class EdgeOwnerToTimelineMedia(
    val count: Long,
    @SerializedName("page_info")
    val pageInfo: PageInfo2,
    val edges: List<Edge>,
)

data class PageInfo2(
    @SerializedName("has_next_page")
    val hasNextPage: Boolean,
    @SerializedName("end_cursor")
    val endCursor: String,
)

data class Edge(
    val node: Node,
)

data class Node(
    @SerializedName("__typename")
    val typename: String,
    val id: String,
    val shortcode: String,
    val dimensions: Dimensions,
    @SerializedName("display_url")
    val displayUrl: String,
    @SerializedName("edge_media_to_tagged_user")
    val edgeMediaToTaggedUser: EdgeMediaToTaggedUser,
    @SerializedName("fact_check_overall_rating")
    val factCheckOverallRating: Any?,
    @SerializedName("fact_check_information")
    val factCheckInformation: Any?,
    @SerializedName("gating_info")
    val gatingInfo: Any?,
    @SerializedName("sharing_friction_info")
    val sharingFrictionInfo: SharingFrictionInfo,
    @SerializedName("media_overlay_info")
    val mediaOverlayInfo: Any?,
    @SerializedName("media_preview")
    val mediaPreview: String?,
    val owner: Owner,
    @SerializedName("is_video")
    val isVideo: Boolean,
    @SerializedName("has_upcoming_event")
    val hasUpcomingEvent: Boolean,
    @SerializedName("accessibility_caption")
    val accessibilityCaption: Any?,
    @SerializedName("dash_info")
    val dashInfo: DashInfo?,
    @SerializedName("has_audio")
    val hasAudio: Boolean?,
    @SerializedName("tracking_token")
    val trackingToken: String?,
    @SerializedName("video_url")
    val videoUrl: String?,
    @SerializedName("video_view_count")
    val videoViewCount: Long?,
    @SerializedName("edge_media_to_caption")
    val edgeMediaToCaption: EdgeMediaToCaption,
    @SerializedName("edge_media_to_comment")
    val edgeMediaToComment: EdgeMediaToComment,
    @SerializedName("comments_disabled")
    val commentsDisabled: Boolean,
    @SerializedName("taken_at_timestamp")
    val takenAtTimestamp: Long,
    @SerializedName("edge_liked_by")
    val edgeLikedBy: EdgeLikedBy,
    @SerializedName("edge_media_preview_like")
    val edgeMediaPreviewLike: EdgeMediaPreviewLike,
    val location: Location?,
    @SerializedName("nft_asset_info")
    val nftAssetInfo: Any?,
    @SerializedName("thumbnail_src")
    val thumbnailSrc: String,
    @SerializedName("thumbnail_resources")
    val thumbnailResources: List<ThumbnailResource>,
    @SerializedName("felix_profile_grid_crop")
    val felixProfileGridCrop: Any?,
    @SerializedName("coauthor_producers")
    val coauthorProducers: List<Any?>,
    @SerializedName("pinned_for_users")
    val pinnedForUsers: List<Any?>,
    @SerializedName("viewer_can_reshare")
    val viewerCanReshare: Boolean,
    @SerializedName("product_type")
    val productType: String?,
    @SerializedName("clips_music_attribution_info")
    val clipsMusicAttributionInfo: ClipsMusicAttributionInfo?,
    @SerializedName("edge_sidecar_to_children")
    val edgeSidecarToChildren: EdgeSidecarToChildren?,
)

data class Dimensions(
    val height: Long,
    val width: Long,
)

data class EdgeMediaToTaggedUser(
    val edges: List<Any?>,
)

data class SharingFrictionInfo(
    @SerializedName("should_have_sharing_friction")
    val shouldHaveSharingFriction: Boolean,
    @SerializedName("bloks_app_url")
    val bloksAppUrl: Any?,
)

data class Owner(
    val id: String,
    val username: String,
)

data class DashInfo(
    @SerializedName("is_dash_eligible")
    val isDashEligible: Boolean,
    @SerializedName("video_dash_manifest")
    val videoDashManifest: Any?,
    @SerializedName("number_of_qualities")
    val numberOfQualities: Long,
)

data class EdgeMediaToCaption(
    val edges: List<Edge2>,
)

data class Edge2(
    val node: Node2,
)

data class Node2(
    val text: String,
)

data class EdgeMediaToComment(
    val count: Long,
)

data class EdgeLikedBy(
    val count: Long,
)

data class EdgeMediaPreviewLike(
    val count: Long,
)

data class Location(
    val id: String,
    @SerializedName("has_public_page")
    val hasPublicPage: Boolean,
    val name: String,
    val slug: String,
)

data class ThumbnailResource(
    val src: String,
    @SerializedName("config_width")
    val configWidth: Long,
    @SerializedName("config_height")
    val configHeight: Long,
)

data class ClipsMusicAttributionInfo(
    @SerializedName("artist_name")
    val artistName: String,
    @SerializedName("song_name")
    val songName: String,
    @SerializedName("uses_original_audio")
    val usesOriginalAudio: Boolean,
    @SerializedName("should_mute_audio")
    val shouldMuteAudio: Boolean,
    @SerializedName("should_mute_audio_reason")
    val shouldMuteAudioReason: String,
    @SerializedName("audio_id")
    val audioId: String,
)

data class EdgeSidecarToChildren(
    val edges: List<Edge3>,
)

data class Edge3(
    val node: Node3,
)

data class Node3(
    @SerializedName("__typename")
    val typename: String,
    val id: String,
    val shortcode: String,
    val dimensions: Dimensions2,
    @SerializedName("display_url")
    val displayUrl: String,
    @SerializedName("edge_media_to_tagged_user")
    val edgeMediaToTaggedUser: EdgeMediaToTaggedUser2,
    @SerializedName("fact_check_overall_rating")
    val factCheckOverallRating: Any?,
    @SerializedName("fact_check_information")
    val factCheckInformation: Any?,
    @SerializedName("gating_info")
    val gatingInfo: Any?,
    @SerializedName("sharing_friction_info")
    val sharingFrictionInfo: SharingFrictionInfo2,
    @SerializedName("media_overlay_info")
    val mediaOverlayInfo: Any?,
    @SerializedName("media_preview")
    val mediaPreview: String,
    val owner: Owner2,
    @SerializedName("is_video")
    val isVideo: Boolean,
    @SerializedName("has_upcoming_event")
    val hasUpcomingEvent: Boolean,
    @SerializedName("accessibility_caption")
    val accessibilityCaption: Any?,
    @SerializedName("dash_info")
    val dashInfo: DashInfo2?,
    @SerializedName("has_audio")
    val hasAudio: Boolean?,
    @SerializedName("tracking_token")
    val trackingToken: String?,
    @SerializedName("video_url")
    val videoUrl: String?,
    @SerializedName("video_view_count")
    val videoViewCount: Long?,
)

data class Dimensions2(
    val height: Long,
    val width: Long,
)

data class EdgeMediaToTaggedUser2(
    val edges: List<Any?>,
)

data class SharingFrictionInfo2(
    @SerializedName("should_have_sharing_friction")
    val shouldHaveSharingFriction: Boolean,
    @SerializedName("bloks_app_url")
    val bloksAppUrl: Any?,
)

data class Owner2(
    val id: String,
    val username: String,
)

data class DashInfo2(
    @SerializedName("is_dash_eligible")
    val isDashEligible: Boolean,
    @SerializedName("video_dash_manifest")
    val videoDashManifest: Any?,
    @SerializedName("number_of_qualities")
    val numberOfQualities: Long,
)

data class EdgeSavedMedia(
    val count: Long,
    @SerializedName("page_info")
    val pageInfo: PageInfo3,
    val edges: List<Any?>,
)

data class PageInfo3(
    @SerializedName("has_next_page")
    val hasNextPage: Boolean,
    @SerializedName("end_cursor")
    val endCursor: Any?,
)

data class EdgeMediaCollections(
    val count: Long,
    @SerializedName("page_info")
    val pageInfo: PageInfo4,
    val edges: List<Any?>,
)

data class PageInfo4(
    @SerializedName("has_next_page")
    val hasNextPage: Boolean,
    @SerializedName("end_cursor")
    val endCursor: Any?,
)

data class EdgeRelatedProfiles(
    val edges: List<Any?>,
)

