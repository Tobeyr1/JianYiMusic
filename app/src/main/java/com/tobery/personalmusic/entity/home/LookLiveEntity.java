package com.tobery.personalmusic.entity.home;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Package: com.tobery.personalmusic.entity.home
 * @ClassName: LookLiveEntity
 * @Author: Tobey_r1
 * @CreateDate: 2022/6/27 21:38
 * @Description: java类作用描述
 * @UpdateUser: 更新者
 * @UpdateDate: 2022/6/27 21:38
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@NoArgsConstructor
@Data
public class LookLiveEntity {
    private int liveId;
    private String title;
    private double anchorId;
    private long coverId;
    private String cover;
    private LiveUrlEntity liveUrl;
    private Object playBackUrl;
    private int orientationScope;
    private int onlineNumber;
    private int liveStatus;
    private long startTime;
    private int endTime;
    private int roomId;
    private Object channelId;
    private int liveType;
    private int appKeyType;
    private int type;
    private int startStreamTag;
    private int agoraRoomNo;
    private String bgCoverUrl;
    private Object backgroundAnimateUrl;
    private int rtcSupplierType;
    private int popularity;
    private long verticalCoverId;
    private String verticalCover;
    private UserInfoEntity userInfo;
    private RecLiveDTOEntity recLiveDTO;
    private String coverTag;
    private String privateTag;
    private Object borderTag;
    private String startStreamTagName;
    private Object tags;
    private Object dynamicCover;
    private List<String> audioCoverIds;
    private SupplementParamToClientEntity supplementParamToClient;
    private CloudMusicMyFollowRecInfoEntity cloudMusicMyFollowRecInfo;
    private Object adSpreadDto;

    @NoArgsConstructor
    @Data
    public static class LiveUrlEntity {
        private String httpPullUrl;
        private String hlsPullUrl;
        private String rtmpPullUrl;
    }

    @NoArgsConstructor
    @Data
    public static class UserInfoEntity {
        private double userId;
        private String nickname;
        private String avatarUrl;
        private int authStatus;
        private int userType;
        private Object authName;
        private int liveRoomNo;
        private int vipType;
        private int gender;
        private Object artistName;
    }

    @NoArgsConstructor
    @Data
    public static class RecLiveDTOEntity {
        private String skipUrl;
        private String typeDesc;
        private int cardType;
        private String alg;
        private String anchorId;
        private int liveRoomNo;
        private int songId;
        private int accompanimentId;
        private SupplemetParamEntity supplemetParam;
        private String ops;
        private long recCoverId;
        private String recCover;
        private String coverTag;
        private int segId;

        @NoArgsConstructor
        @Data
        public static class SupplemetParamEntity {
            private String hp_moduletitle;
            private String coverID;
            private String liveOnlineNumber;
        }
    }

    @NoArgsConstructor
    @Data
    public static class SupplementParamToClientEntity {
        private String ops;
    }

    @NoArgsConstructor
    @Data
    public static class CloudMusicMyFollowRecInfoEntity {
    }
}
