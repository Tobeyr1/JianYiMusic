package com.tobery.personalmusic.entity;


import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class UserDetailEntity {

    public Integer level;
    public Integer listenSongs;
    public UserPointEntity userPoint;
    public Boolean mobileSign;
    public Boolean pcSign;
    public ProfileEntity profile;
    public Boolean peopleCanSeeMyPlayRecord;
    public List<BindingsEntity> bindings;
    public Boolean adValid;
    public Integer code;
    public Long createTime;
    public Integer createDays;
    public ProfileVillageInfoEntity profileVillageInfo;

    @NoArgsConstructor
    @Data
    public static class UserPointEntity {
        public Integer userId;
        public Integer balance;
        public Long updateTime;
        public Integer version;
        public Integer status;
        public Integer blockBalance;
    }

    @NoArgsConstructor
    @Data
    public static class ProfileEntity {
        public PrivacyItemUnlimitEntity privacyItemUnlimit;
        public Object avatarDetail;
        public Object remarkName;
        public Integer authStatus;
        public String detailDescription;
        public ExpertsEntity experts;
        public Object expertTags;
        public String description;
        public String avatarImgIdStr;
        public String backgroundImgIdStr;
        public Long createTime;
        public Boolean defaultAvatar;
        public Long birthday;
        public String nickname;
        public String avatarUrl;
        public Long backgroundImgId;
        public String backgroundUrl;
        public Integer userType;
        public Long avatarImgId;
        public Integer province;
        public Integer city;
        public Integer gender;
        public Boolean followed;
        public Boolean mutual;
        public Integer djStatus;
        public Integer accountStatus;
        public Integer vipType;
        public Integer userId;
        public String signature;
        public Integer authority;
        public Integer followeds;
        public Integer follows;
        public Boolean blacklist;
        public Integer eventCount;
        public Integer allSubscribedCount;
        public Integer playlistBeSubscribedCount;
        public String avatarImgId_str;
        public Object followTime;
        public Boolean followMe;
        public List<?> artistIdentity;
        public Integer cCount;
        public Boolean inBlacklist;
        public Integer sDJPCount;
        public Integer playlistCount;
        public Integer sCount;
        public Integer newFollows;

        @NoArgsConstructor
        @Data
        public static class PrivacyItemUnlimitEntity {
            public Boolean area;
            public Boolean college;
            public Boolean age;
            public Boolean villageAge;
        }

        @NoArgsConstructor
        @Data
        public static class ExpertsEntity {
        }
    }

    @NoArgsConstructor
    @Data
    public static class ProfileVillageInfoEntity {
        public String title;
        public String imageUrl;
        public String targetUrl;
    }

    @NoArgsConstructor
    @Data
    public static class BindingsEntity {
        public Integer expiresIn;
        public Integer refreshTime;
        public Long bindingTime;
        public Object tokenJsonStr;
        public Boolean expired;
        public String url;
        public Integer userId;
        public Integer id;
        public Integer type;
    }
}
