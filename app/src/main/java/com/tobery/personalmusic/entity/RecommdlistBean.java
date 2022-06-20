package com.tobery.personalmusic.entity;

/**
 * 用于Adapter的歌单Bean
 */
public class RecommdlistBean {

    private String playlistCoverUrl;

    private String playlistName;
    private long playcount;

    public long getPlaycount() {
        return playcount;
    }

    public void setPlaycount(long playcount) {
        this.playcount = playcount;
    }

    public String getPlaylistCoverUrl() {
        return playlistCoverUrl;
    }

    public void setPlaylistCoverUrl(String playlistCoverUrl) {
        this.playlistCoverUrl = playlistCoverUrl;
    }

    public String getPlaylistName() {
        return playlistName;
    }

    public void setPlaylistName(String playlistName) {
        this.playlistName = playlistName;
    }
}
