package com.tobery.personalmusic.entity.user;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class VipInfoEntity {

    private String message;
    private DataEntity data;
    private int code;

    @NoArgsConstructor
    @Data
    public static class DataEntity {
        private String redVipLevelIcon;
        private int redVipLevel;
        private int redVipAnnualCount;
        private MusicPackageEntity musicPackage;
        private AssociatorEntity associator;
        private String redVipDynamicIconUrl;
        private String redVipDynamicIconUrl2;

        @NoArgsConstructor
        @Data
        public static class MusicPackageEntity {
            private int vipCode;
            private long expireTime;
            private boolean isSignDeduct;
            private boolean isSign;
            private boolean isSignIapDeduct;
            private boolean isSignIap;
        }

        @NoArgsConstructor
        @Data
        public static class AssociatorEntity {
            private int vipCode;
            private long expireTime;
            private boolean isSignDeduct;
            private boolean isSign;
            private boolean isSignIapDeduct;
            private boolean isSignIap;
        }
    }
}
