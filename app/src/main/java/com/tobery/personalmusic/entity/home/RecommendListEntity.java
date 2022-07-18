package com.tobery.personalmusic.entity.home;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Package: com.tobery.personalmusic.entity.home
 * @ClassName: RecommendListEntity
 * @Author: Tobey_r1
 * @CreateDate: 2022/7/9 11:33
 * @Description: java类作用描述
 * @UpdateUser: 更新者
 * @UpdateDate: 2022/7/9 11:33
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@NoArgsConstructor
@Data
public class RecommendListEntity {

    private int code;
    private Object relatedVideos;
    private PlaylistEntity playlist;
    private Object urls;
    private List<PrivilegesEntity> privileges;
    private Object sharedPrivilege;
    private Object resEntrance;

    @NoArgsConstructor
    @Data
    public static class PlaylistEntity {
        private long id;
        private String name;
        private long coverImgId;
        private String coverImgUrl;
        private String coverImgId_str;
        private int adType;
        private long userId;
        private long createTime;
        private int status;
        private boolean opRecommend;
        private boolean highQuality;
        private boolean newImported;
        private long updateTime;
        private int trackCount;
        private int specialType;
        private int privacy;
        private long trackUpdateTime;
        private String commentThreadId;
        private long playCount;
        private long trackNumberUpdateTime;
        private int subscribedCount;
        private int cloudTrackCount;
        private boolean ordered;
        private String description;
        private List<String> tags;
        private Object updateFrequency;
        private long backgroundCoverId;
        private Object backgroundCoverUrl;
        private long titleImage;
        private Object titleImageUrl;
        private Object englishTitle;
        private Object officialPlaylistType;
        private List<SubscribersEntity> subscribers;
        private boolean subscribed;
        private CreatorEntity creator;
        private List<TracksEntity> tracks;
        private Object videoIds;
        private Object videos;
        private List<TrackIdsEntity> trackIds;
        private int shareCount;
        private int commentCount;
        private Object remixVideo;
        private Object sharedUsers;
        private Object historySharedUsers;
        private String gradeStatus;
        private Object score;
        private Object algTags;

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
            private long userId;
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
            private List<String> expertTags;
            private ExpertsEntity experts;
            private int djStatus;
            private int vipType;
            private Object remarkName;
            private int authenticationTypes;
            private AvatarDetailEntity avatarDetail;
            private String avatarImgIdStr;
            private String backgroundImgIdStr;
            private boolean anchor;
            private String avatarImgId_str;

            @NoArgsConstructor
            @Data
            public static class ExpertsEntity {
                @SerializedName("1")
                private String _$1;
                @SerializedName("2")
                private String _$2;
            }

            @NoArgsConstructor
            @Data
            public static class AvatarDetailEntity {
                private int userType;
                private int identityLevel;
                private String identityIconUrl;
            }
        }

        @NoArgsConstructor
        @Data
        public static class SubscribersEntity {
            private boolean defaultAvatar;
            private int province;
            private int authStatus;
            private boolean followed;
            private String avatarUrl;
            private int accountStatus;
            private int gender;
            private int city;
            private int birthday;
            private long userId;
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
            private String avatarImgIdStr;
            private String backgroundImgIdStr;
            private boolean anchor;
            private String avatarImgId_str;
        }

        @NoArgsConstructor
        @Data
        public static class TracksEntity {
            private String name;
            private int id;
            private int pst;
            private int t;
            private List<ArEntity> ar;
            private List<String> alia;
            private int pop;
            private int st;
            private String rt;
            private int fee;
            private int v;
            private Object crbt;
            private String cf;
            private AlEntity al;
            private int dt;
            private HEntity h;
            private MEntity m;
            private LEntity l;
            private Object sq;
            private Object hr;
            private Object a;
            private String cd;
            private int no;
            private Object rtUrl;
            private int ftype;
            private List<?> rtUrls;
            private int djId;
            private int copyright;
            private int s_id;
            private long mark;
            private int originCoverType;
            private Object originSongSimpleData;
            private Object tagPicList;
            private boolean resourceState;
            private int version;
            private Object songJumpInfo;
            private Object entertainmentTags;
            private int single;
            private Object noCopyrightRcmd;
            private Object alg;
            private int mst;
            private int cp;
            private int mv;
            private int rtype;
            private Object rurl;
            private long publishTime;

            @NoArgsConstructor
            @Data
            public static class AlEntity {
                private int id;
                private String name;
                private String picUrl;
                private List<?> tns;
                private String pic_str;
                private long pic;
            }

            @NoArgsConstructor
            @Data
            public static class HEntity {
                private int br;
                private int fid;
                private int size;
                private int vd;
                private int sr;
            }

            @NoArgsConstructor
            @Data
            public static class MEntity {
                private int br;
                private int fid;
                private int size;
                private int vd;
                private int sr;
            }

            @NoArgsConstructor
            @Data
            public static class LEntity {
                private int br;
                private int fid;
                private int size;
                private int vd;
                private int sr;
            }

            @NoArgsConstructor
            @Data
            public static class ArEntity {
                private int id;
                private String name;
                private List<?> tns;
                private List<?> alias;
            }
        }

        @NoArgsConstructor
        @Data
        public static class TrackIdsEntity {
            private int id;
            private int v;
            private int t;
            private long at;
            private Object alg;
            private long uid;
            private String rcmdReason;
            private Object sc;
        }
    }

    @NoArgsConstructor
    @Data
    public static class PrivilegesEntity {
        private int id;
        private int fee;
        private int payed;
        private int realPayed;
        private int st;
        private int pl;
        private int dl;
        private int sp;
        private int cp;
        private int subp;
        private boolean cs;
        private int maxbr;
        private int fl;
        private Object pc;
        private boolean toast;
        private int flag;
        private boolean paidBigBang;
        private boolean preSell;
        private int playMaxbr;
        private int downloadMaxbr;
        private String maxBrLevel;
        private String playMaxBrLevel;
        private String downloadMaxBrLevel;
        private String plLevel;
        private String dlLevel;
        private String flLevel;
        private Object rscl;
        private FreeTrialPrivilegeEntity freeTrialPrivilege;
        private List<ChargeInfoListEntity> chargeInfoList;

        @NoArgsConstructor
        @Data
        public static class FreeTrialPrivilegeEntity {
            private boolean resConsumable;
            private boolean userConsumable;
            private Object listenType;
        }

        @NoArgsConstructor
        @Data
        public static class ChargeInfoListEntity {
            private int rate;
            private Object chargeUrl;
            private Object chargeMessage;
            private int chargeType;
        }
    }
}
