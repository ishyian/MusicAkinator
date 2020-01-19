package codeninjas.musicakinator.data.audd.responseModel


import com.google.gson.annotations.SerializedName

data class GetSongsByLyricResponseModel(
    @SerializedName("result")
    val result: List<Result>,
    @SerializedName("status")
    val status: String
) {
    data class Result(
        @SerializedName("artist")
        val artist: String,
        @SerializedName("artist_id")
        val artistId: String,
        @SerializedName("full_title")
        val fullTitle: String,
        @SerializedName("lyrics")
        val lyrics: String,
        @SerializedName("song_id")
        val songId: String,
        @SerializedName("title")
        val title: String,
        @SerializedName("title_with_featured")
        val titleWithFeatured: String
    )
}