package com.tobery.personalmusic.entity;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Package: com.tobery.personalmusic.entity
 * @ClassName: LoginEntity
 * @Author: Tobey_r1
 * @CreateDate: 2022/6/23 21:11
 * @Description: java类作用描述
 * @UpdateUser: 更新者
 * @UpdateDate: 2022/6/23 21:11
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@NoArgsConstructor
@Data
public class LoginEntity {

    private int loginType;
    private int code;
    private AccountEntity account;
    private String token;
    private ProfileEntity profile;
    private List<BindingsEntity> bindings;
    private String cookie;

    @NoArgsConstructor
    @Data
    public static class AccountEntity {
        private long id;
        private String userName;
        private int type;
        private int status;
        private int whitelistAuthority;
        private long createTime;
        private String salt;
        private int tokenVersion;
        private int ban;
        private int baoyueVersion;
        private int donateVersion;
        private int vipType;
        private long viptypeVersion;
        private boolean anonimousUser;
        private boolean uninitialized;
    }

    @NoArgsConstructor
    @Data
    public static class ProfileEntity {
        private String backgroundImgIdStr;
        private long userId;
        private String avatarImgIdStr;
        private boolean followed;
        private String backgroundUrl;
        private String detailDescription;
        private int userType;
        private int vipType;
        private int gender;
        private int accountStatus;
        private long avatarImgId;
        private String nickname;
        private long backgroundImgId;
        private long birthday;
        private int city;
        private String avatarUrl;
        private boolean defaultAvatar;
        private int province;
        private Object expertTags;
        private ExpertsEntity experts;
        private boolean mutual;
        private Object remarkName;
        private int authStatus;
        private int djStatus;
        private String description;
        private String signature;
        private int authority;
        private String avatarImgId_str;
        private int followeds;
        private int follows;
        private int eventCount;
        private Object avatarDetail;
        private int playlistCount;
        private int playlistBeSubscribedCount;

        @NoArgsConstructor
        @Data
        public static class ExpertsEntity {
        }
    }

    @NoArgsConstructor
    @Data
    public static class BindingsEntity {
        private int userId;
        private String url;
        private boolean expired;
        private String tokenJsonStr;
        private long bindingTime;
        private int expiresIn;
        private int refreshTime;
        private long id;
        private int type;
    }
}
