package com.tobery.personalmusic.entity.follow;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Package: com.tobery.personalmusic.entity.follow
 * @ClassName: DynamicListEntity
 * @Author: Tobey_r1
 * @CreateDate: 2022/7/31 17:59
 * @Description: java类作用描述
 * @UpdateUser: 更新者
 * @UpdateDate: 2022/7/31 17:59
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@NoArgsConstructor
@Data
public class DynamicListEntity {

    private int code;
    private boolean more;
    private List<EventEntity> event;
    private long lasttime;

    @NoArgsConstructor
    @Data
    public static class EventEntity {
        private String actName;
        private Object pendantData;
        private int forwardCount;
        private Object lotteryEventData;
        private String discussId;
        private int insiteForwardCount;
        private InfoEntity info;
        private boolean topEvent;
        private TailMarkEntity tailMark;
        private Object typeDesc;
        private Object alterLinkUrl;
        private Object alterLinkWebviewUrl;
        private int privacySetting;
        private Object question;
        private Object voice;
        private List<?> topActivityInfos;
        private List<BottomActivityInfosEntity> bottomActivityInfos;
        private Object h5Target;
        private boolean more;
        private Object logInfo;
        private Object eventActionToast;
        private boolean relationTopic;
        private String extType;
        private Object extSource;
        private AnonymityInfoEntity anonymityInfo;
        private PointTopicInfoEntity pointTopicInfo;
        private Object commentInfo;
        private long showTime;
        private int actId;
        private String json;
        private UserEntity user;
        private String uuid;
        private long eventTime;
        private ExtJsonInfoEntity extJsonInfo;
        private int tmplId;
        private int expireTime;
        private Object rcmdInfo;
        private List<PicsEntity> pics;
        private XInfoEntity xInfo;
        private long id;
        private int type;

        @NoArgsConstructor
        @Data
        public static class InfoEntity {
            private CommentThreadEntity commentThread;
            private Object latestLikedUsers;
            private boolean liked;
            private Object comments;
            private int resourceType;
            private long resourceId;
            private int likedCount;
            private int commentCount;
            private int shareCount;
            private String threadId;

            @NoArgsConstructor
            @Data
            public static class CommentThreadEntity {
                private String id;
                private ResourceInfoEntity resourceInfo;
                private int resourceType;
                private int commentCount;
                private int likedCount;
                private int shareCount;
                private int hotCount;
                private List<LatestLikedUsersEntity> latestLikedUsers;
                private long resourceId;
                private int resourceOwnerId;
                private String resourceTitle;
                private Object extProperties;
                private Object xInfo;

                @NoArgsConstructor
                @Data
                public static class ResourceInfoEntity {
                    private long id;
                    private int userId;
                    private String name;
                    private Object imgUrl;
                    private Object creator;
                    private int artistAreaCode;
                    private Object subTitle;
                    private Object webUrl;
                    private Object resourceSpecialType;
                    private int artistId;
                    private int eventType;
                    private Object encodedId;
                    private Object nativeUrl;
                }

                @NoArgsConstructor
                @Data
                public static class LatestLikedUsersEntity {
                    private long s;
                    private long t;
                }
            }
        }

        @NoArgsConstructor
        @Data
        public static class TailMarkEntity {
            private String markTitle;
            private String markType;
            private String markResourceId;
            private String markOrpheusUrl;
            private Object extInfo;
            private CircleEntity circle;

            @NoArgsConstructor
            @Data
            public static class CircleEntity {
                private String imageUrl;
                private String postCount;
                private String member;
            }
        }

        @NoArgsConstructor
        @Data
        public static class AnonymityInfoEntity {
            private int anonymous;
            private Object name;
            private Object avatarUrl;
            private int me;
        }

        @NoArgsConstructor
        @Data
        public static class PointTopicInfoEntity {
            private Object id;
            private Object type;
            private Object subType;
            private Object name;
            private Object icon;
            private Object desc;
            private Object target;
            private Object throughInfo;
            private Object ext;
            private Object parent;
        }

        @NoArgsConstructor
        @Data
        public static class UserEntity {
            private boolean defaultAvatar;
            private int province;
            private int authStatus;
            private boolean followed;
            private String avatarUrl;
            private int accountStatus;
            private int gender;
            private int city;
            private long birthday;
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
            private AvatarDetailEntity avatarDetail;
            private String backgroundImgIdStr;
            private String avatarImgIdStr;
            private boolean anchor;
            private boolean urlAnalyze;
            private VipRightsEntity vipRights;
            private String avatarImgId_str;
            private Object commonIdentity;
            private int followeds;

            @NoArgsConstructor
            @Data
            public static class AvatarDetailEntity {
                private int userType;
                private int identityLevel;
                private String identityIconUrl;
            }

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

        @NoArgsConstructor
        @Data
        public static class ExtJsonInfoEntity {
            private int actId;
            private List<Integer> actIds;
            private String uuid;
            private String extType;
            private Object extSource;
            private String extId;
            private String circleId;
            private String circlePubType;
            private ExtParamsEntity extParams;
            private Object tailMark;
            private int privacySetting;
            private Object typeDesc;
            private Object questionId;
            private Object voiceInfo;
            private PointTopicInfoEntity pointTopicInfo;
            private List<ActivityInfosEntity> activityInfos;
            private AnonymityInfoEntity anonymityInfo;
            private Object titleAlias;

            @NoArgsConstructor
            @Data
            public static class ExtParamsEntity {
            }

            @NoArgsConstructor
            @Data
            public static class PointTopicInfoEntity {
                private Object id;
                private Object type;
                private Object subType;
                private Object name;
                private Object icon;
                private Object target;
                private Object h5Target;
                private Object throughInfo;
                private Object ext;
                private Object parent;
            }

            @NoArgsConstructor
            @Data
            public static class AnonymityInfoEntity {
                private int anonymous;
                private Object name;
                private Object avatarUrl;
            }

            @NoArgsConstructor
            @Data
            public static class ActivityInfosEntity {
                private String id;
                private int type;
                private int subType;
                private String name;
                private Object icon;
                private String target;
                private String h5Target;
                private String throughInfo;
                private Object ext;
                private Object parent;
            }
        }

        @NoArgsConstructor
        @Data
        public static class XInfoEntity {
            private boolean topEvent;
            private int insiteForwardCount;
            private InfoEntity info;

            @NoArgsConstructor
            @Data
            public static class InfoEntity {
                private CommentThreadEntity commentThread;
                private Object latestLikedUsers;
                private boolean liked;
                private Object comments;
                private int resourceType;
                private long resourceId;
                private int likedCount;
                private int commentCount;
                private int shareCount;
                private String threadId;

                @NoArgsConstructor
                @Data
                public static class CommentThreadEntity {
                    private String id;
                    private ResourceInfoEntity resourceInfo;
                    private int resourceType;
                    private int commentCount;
                    private int likedCount;
                    private int shareCount;
                    private int hotCount;
                    private List<LatestLikedUsersEntity> latestLikedUsers;
                    private long resourceId;
                    private int resourceOwnerId;
                    private String resourceTitle;
                    private Object extProperties;
                    private Object xInfo;

                    @NoArgsConstructor
                    @Data
                    public static class ResourceInfoEntity {
                        private long id;
                        private int userId;
                        private String name;
                        private Object imgUrl;
                        private Object creator;
                        private int artistAreaCode;
                        private Object subTitle;
                        private Object webUrl;
                        private Object resourceSpecialType;
                        private int artistId;
                        private int eventType;
                        private Object encodedId;
                        private Object nativeUrl;
                    }

                    @NoArgsConstructor
                    @Data
                    public static class LatestLikedUsersEntity {
                        private long s;
                        private long t;
                    }
                }
            }
        }

        @NoArgsConstructor
        @Data
        public static class BottomActivityInfosEntity {
            private String id;
            private int type;
            private int subType;
            private String name;
            private Object icon;
            private String target;
            private String h5Target;
            private String throughInfo;
            private Object ext;
            private Object parent;
        }

        @NoArgsConstructor
        @Data
        public static class PicsEntity {
            private int width;
            private int height;
            private String originUrl;
            private String squareUrl;
            private String rectangleUrl;
            private String pcSquareUrl;
            private String pcRectangleUrl;
            private String format;
        }
    }
}
