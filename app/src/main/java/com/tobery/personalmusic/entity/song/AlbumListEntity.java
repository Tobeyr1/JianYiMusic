package com.tobery.personalmusic.entity.song;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Package: com.tobery.personalmusic.entity.song
 * @ClassName: AlbumListEntity
 * @Author: Tobey_r1
 * @CreateDate: 2022/7/18 23:34
 * @Description: java类作用描述
 * @UpdateUser: 更新者
 * @UpdateDate: 2022/7/18 23:34
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@NoArgsConstructor
@Data
public class AlbumListEntity {

    private boolean resourceState;
    private List<SongsEntity> songs;
    private int code;
    private AlbumEntity album;

    @NoArgsConstructor
    @Data
    public static class AlbumEntity {
        private List<?> songs;
        private boolean paid;
        private boolean onSale;
        private int mark;
        private Object awardTags;
        private String blurPicUrl;
        private List<?> alias;
        private List<ArtistsEntity> artists;
        private int copyrightId;
        private long picId;
        private ArtistEntity artist;
        private String company;
        private long publishTime;
        private String briefDesc;
        private String picUrl;
        private String commentThreadId;
        private long pic;
        private int companyId;
        private String description;
        private String tags;
        private int status;
        private String subType;
        private String name;
        private int id;
        private String type;
        private int size;
        private String picId_str;
        private InfoEntity info;

        @NoArgsConstructor
        @Data
        public static class ArtistEntity {
            private long img1v1Id;
            private int topicPerson;
            private boolean followed;
            private List<?> alias;
            private long picId;
            private String briefDesc;
            private int musicSize;
            private int albumSize;
            private String picUrl;
            private String img1v1Url;
            private String trans;
            private String name;
            private int id;
            private String picId_str;
            private List<String> transNames;
            private String img1v1Id_str;
        }

        @NoArgsConstructor
        @Data
        public static class InfoEntity {
            private CommentThreadEntity commentThread;
            private Object latestLikedUsers;
            private boolean liked;
            private Object comments;
            private int resourceType;
            private int resourceId;
            private int commentCount;
            private int likedCount;
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
                private Object latestLikedUsers;
                private int resourceId;
                private int resourceOwnerId;
                private String resourceTitle;

                @NoArgsConstructor
                @Data
                public static class ResourceInfoEntity {
                    private int id;
                    private int userId;
                    private String name;
                    private String imgUrl;
                    private Object creator;
                    private Object encodedId;
                    private Object subTitle;
                    private Object webUrl;
                }
            }
        }

        @NoArgsConstructor
        @Data
        public static class ArtistsEntity {
            private long img1v1Id;
            private int topicPerson;
            private boolean followed;
            private List<?> alias;
            private int picId;
            private String briefDesc;
            private int musicSize;
            private int albumSize;
            private String picUrl;
            private String img1v1Url;
            private String trans;
            private String name;
            private int id;
            private String img1v1Id_str;
        }
    }

    @NoArgsConstructor
    @Data
    public static class SongsEntity {
        private List<?> rtUrls;
        private List<ArEntity> ar;
        private AlEntity al;
        private int st;
        private Object noCopyrightRcmd;
        private Object songJumpInfo;
        private int djId;
        private int no;
        private int fee;
        private int mv;
        private int t;
        private int v;
        private HEntity h;
        private LEntity l;
        private SqEntity sq;
        private Object hr;
        private int pop;
        private Object rt;
        private int mst;
        private int cp;
        private Object crbt;
        private String cf;
        private int dt;
        private Object rtUrl;
        private int ftype;
        private int rtype;
        private Object rurl;
        private int pst;
        private List<?> alia;
        private String cd;
        private Object a;
        private MEntity m;
        private String name;
        private int id;
        private List<String> tns;
        private PrivilegeEntity privilege;

        @NoArgsConstructor
        @Data
        public static class AlEntity {
            private int id;
            private String name;
            private String picUrl;
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
        public static class LEntity {
            private int br;
            private int fid;
            private int size;
            private int vd;
            private int sr;
        }

        @NoArgsConstructor
        @Data
        public static class SqEntity {
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
        public static class PrivilegeEntity {
            private int id;
            private int fee;
            private int payed;
            private int st;
            private int pl;
            private int dl;
            private int sp;
            private int cp;
            private int subp;
            private boolean cs;
            private int maxbr;
            private int fl;
            private boolean toast;
            private int flag;
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

        @NoArgsConstructor
        @Data
        public static class ArEntity {
            private int id;
            private String name;
            private List<String> tns;
        }
    }
}
