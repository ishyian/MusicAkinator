package codeninjas.musicakinator.data.audd.responseModel

import com.google.gson.annotations.SerializedName

data class DizzerTrackResponseModel(
    val album: Album,
    val artist: Artist,
    val duration: String,
    @SerializedName("explicit_content_cover")
    val explicitContentCover: Int,
    @SerializedName("explicit_content_lyrics")
    val explicitContentLyrics: Int,
    @SerializedName("explicit_lyrics")
    val explicitLyrics: Boolean,
    val id: String,
    val link: String,
    val preview: String,
    val rank: String,
    val readable: Boolean,
    val title: String,
    @SerializedName("title_short")
    val titleShort: String,
    @SerializedName("title_version")
    val titleVersion: String,
    val type: String
)

data class Artist(
    val id: String,
    val link: String,
    val name: String,
    val picture: String,
    @SerializedName("picture_big")
    val pictureBig: String,
    @SerializedName("picture_medium")
    val pictureMedium: String,
    @SerializedName("picture_small")
    val pictureSmall: String,
    @SerializedName("picture_xl")
    val pictureXl: String,
    val tracklist: String,
    val type: String
)

data class Album(
    val cover: String,
    @SerializedName("cover_big")
    val coverBig: String,
    @SerializedName("cover_medium")
    val coverMedium: String,
    @SerializedName("cover_small")
    val coverSmall: String,
    @SerializedName("cover_xl")
    val coverXl: String,
    val id: String,
    val title: String,
    val tracklist: String,
    val type: String
)