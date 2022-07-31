package com.tobery.personalmusic.entity.follow;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Package: com.tobery.personalmusic.entity.follow
 * @ClassName: FollowListEntity
 * @Author: Tobey_r1
 * @CreateDate: 2022/7/30 22:30
 * @Description: java类作用描述
 * @UpdateUser: 更新者
 * @UpdateDate: 2022/7/30 22:30
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@NoArgsConstructor
@Data
public class FollowListEntity {

    private List<FollowEntity> follow;
    private int touchCount;
    private boolean more;
    private int code;

    @NoArgsConstructor
    @Data
    public static class FollowEntity {
        private String py;
        private int time;
        private boolean followed;
        private Object avatarDetail;
        private int userType;
        private String nickname;
        private long userId;
        private boolean mutual;
        private int follows;
        private Object remarkName;
        private int followeds;
        private int gender;
        private Object expertTags;
        private Object experts;
        private String avatarUrl;
        private int authStatus;
        private int accountStatus;
        private int vipType;
        private String signature;
        private VipRightsEntity vipRights;
        private boolean blacklist;
        private int eventCount;
        private int playlistCount;

        @NoArgsConstructor
        @Data
        public static class VipRightsEntity {
            private AssociatorEntity associator;
            private Object musicPackage;
            private int redVipAnnualCount;
            private int redVipLevel;

            @NoArgsConstructor
            @Data
            public static class AssociatorEntity {
                private int vipCode;
                private boolean rights;
            }
        }
    }
}
