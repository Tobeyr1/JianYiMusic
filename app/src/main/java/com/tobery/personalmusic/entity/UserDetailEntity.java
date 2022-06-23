package com.tobery.personalmusic.entity;


import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class UserDetailEntity {

    private int level;
    private int listenSongs;
    private UserPointEntity userPoint;
    private boolean mobileSign;
    private boolean pcSign;
    private ProfileEntity profile;
    private boolean peopleCanSeeMyPlayRecord;
    private List<BindingsEntity> bindings;
    private boolean adValid;
    private int code;
    private long createTime;
    private int createDays;
    private ProfileVillageInfoEntity profileVillageInfo;

    @NoArgsConstructor
    @Data
    public static class UserPointEntity {
        private int userId;
        private int balance;
        private long updateTime;
        private int version;
        private int status;
        private int blockBalance;
    }

    @NoArgsConstructor
    @Data
    public static class ProfileEntity {
        private PrivacyItemUnlimitEntity privacyItemUnlimit;
        private Object avatarDetail;
        private String backgroundImgIdStr;
        private String avatarImgIdStr;
        private String description;
        private int userId;
        private int vipType;
        private int userType;
        private long createTime;
        private String nickname;
        private String avatarUrl;
        private int gender;
        private boolean mutual;
        private boolean followed;
        private Object remarkName;
        private int authStatus;
        private String detailDescription;
        private ExpertsEntity experts;
        private Object expertTags;
        private int djStatus;
        private int accountStatus;
        private int province;
        private int city;
        private boolean defaultAvatar;
        private long backgroundImgId;
        private String backgroundUrl;
        private long birthday;
        private long avatarImgId;
        private String signature;
        private int authority;
        private int followeds;
        private int follows;
        private boolean blacklist;
        private int eventCount;
        private int allSubscribedCount;
        private int playlistBeSubscribedCount;
        private String avatarImgId_str;
        private Object followTime;
        private boolean followMe;
        private List<?> artistIdentity;
        private int cCount;
        private boolean inBlacklist;
        private int sDJPCount;
        private int playlistCount;
        private int sCount;
        private int newFollows;

        @NoArgsConstructor
        @Data
        public static class PrivacyItemUnlimitEntity {
            private boolean area;
            private boolean college;
            private boolean age;
            private boolean villageAge;
        }

        @NoArgsConstructor
        @Data
        public static class ExpertsEntity {
        }
    }

    @NoArgsConstructor
    @Data
    public static class ProfileVillageInfoEntity {
        private String title;
        private Object imageUrl;
        private String targetUrl;
    }

    @NoArgsConstructor
    @Data
    public static class BindingsEntity {
        private boolean expired;
        private String url;
        private int userId;
        private int expiresIn;
        private int refreshTime;
        private long bindingTime;
        private Object tokenJsonStr;
        private long id;
        private int type;
    }
}
