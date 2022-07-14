package com.tobery.personalmusic.entity.user;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Package: com.tobery.personalmusic.entity.user
 * @ClassName: UserPlayEntity
 * @Author: Tobey_r1
 * @CreateDate: 2022/7/14 0:40
 * @Description: java类作用描述
 * @UpdateUser: 更新者
 * @UpdateDate: 2022/7/14 0:40
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@NoArgsConstructor
@Data
public class UserPlayEntity {

    private String version;
    private boolean more;
    private List<PlaylistEntity> playlist;
    private int code;

    @NoArgsConstructor
    @Data
    public static class PlaylistEntity {
        private List<?> subscribers;
        private boolean subscribed;
        private CreatorEntity creator;
        private Object artists;
        private Object tracks;
        private Object updateFrequency;
        private long backgroundCoverId;
        private Object backgroundCoverUrl;
        private long titleImage;
        private Object titleImageUrl;
        private Object englishTitle;
        private boolean opRecommend;
        private Object recommendInfo;
        private int subscribedCount;
        private int cloudTrackCount;
        private int userId;
        private int totalDuration;
        private long coverImgId;
        private int privacy;
        private long trackUpdateTime;
        private int trackCount;
        private long updateTime;
        private String commentThreadId;
        private String coverImgUrl;
        private int specialType;
        private boolean anonimous;
        private long createTime;
        private boolean highQuality;
        private boolean newImported;
        private long trackNumberUpdateTime;
        private long playCount;
        private int adType;
        private Object description;
        private List<?> tags;
        private boolean ordered;
        private int status;
        private String name;
        private long id;
        private String coverImgId_str;
        private Object sharedUsers;
        private Object shareStatus;

        @NoArgsConstructor
        @Data
        public static class CreatorEntity {
            private boolean defaultAvatar;
            private int province;
            private int authStatus;
            private boolean followed;
            private String avatarUrl;
            private int accountStatus;
            private int gender;
            private int city;
            private int birthday;
            private int userId;
            private int userType;
            private String nickname;
            private String signature;
            private String description;
            private String detailDescription;
            private long avatarImgId;
            private long backgroundImgId;
            private String backgroundUrl;
            private int authority;
            private boolean mutual;
            private Object expertTags;
            private Object experts;
            private int djStatus;
            private int vipType;
            private Object remarkName;
            private int authenticationTypes;
            private Object avatarDetail;
            private boolean anchor;
            private String avatarImgIdStr;
            private String backgroundImgIdStr;
            private String avatarImgId_str;
        }
    }
}
