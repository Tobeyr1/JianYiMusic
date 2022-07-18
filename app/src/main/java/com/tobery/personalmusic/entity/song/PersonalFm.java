package com.tobery.personalmusic.entity.song;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Package: com.tobery.personalmusic.entity.song
 * @ClassName: PersonalFm
 * @Author: Tobey_r1
 * @CreateDate: 2022/7/18 23:02
 * @Description: java类作用描述
 * @UpdateUser: 更新者
 * @UpdateDate: 2022/7/18 23:02
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@NoArgsConstructor
@Data
public class PersonalFm {

    private boolean popAdjust;
    private List<DataEntity> data;
    private int code;

    @NoArgsConstructor
    @Data
    public static class DataEntity {
        private String name;
        private int id;
        private int position;
        private List<?> alias;
        private int status;
        private int fee;
        private int copyrightId;
        private String disc;
        private int no;
        private List<ArtistsEntity> artists;
        private AlbumEntity album;
        private boolean starred;
        private int popularity;
        private int score;
        private int starredNum;
        private int duration;
        private int playedNum;
        private int dayPlays;
        private int hearTime;
        private Object sqMusic;
        private Object hrMusic;
        private String ringtone;
        private Object crbt;
        private Object audition;
        private String copyFrom;
        private String commentThreadId;
        private Object rtUrl;
        private int ftype;
        private List<?> rtUrls;
        private int copyright;
        private Object transName;
        private Object sign;
        private int mark;
        private int originCoverType;
        private Object originSongSimpleData;
        private int single;
        private Object noCopyrightRcmd;
        private int rtype;
        private Object rurl;
        private int mvid;
        private BMusicEntity bMusic;
        private Object mp3Url;
        private HMusicEntity hMusic;
        private MMusicEntity mMusic;
        private LMusicEntity lMusic;
        private String reason;
        private PrivilegeEntity privilege;
        private String alg;
        private String s_ctrp;

        @NoArgsConstructor
        @Data
        public static class AlbumEntity {
            private String name;
            private int id;
            private String type;
            private int size;
            private long picId;
            private String blurPicUrl;
            private int companyId;
            private long pic;
            private String picUrl;
            private long publishTime;
            private String description;
            private String tags;
            private String company;
            private String briefDesc;
            private ArtistEntity artist;
            private List<?> songs;
            private List<?> alias;
            private int status;
            private int copyrightId;
            private String commentThreadId;
            private List<ArtistsEntity> artists;
            private String subType;
            private Object transName;
            private boolean onSale;
            private int mark;
            private int gapless;
            private String picId_str;

            @NoArgsConstructor
            @Data
            public static class ArtistEntity {
                private String name;
                private int id;
                private int picId;
                private int img1v1Id;
                private String briefDesc;
                private String picUrl;
                private String img1v1Url;
                private int albumSize;
                private List<?> alias;
                private String trans;
                private int musicSize;
                private int topicPerson;
            }

            @NoArgsConstructor
            @Data
            public static class ArtistsEntity {
                private String name;
                private int id;
                private int picId;
                private int img1v1Id;
                private String briefDesc;
                private String picUrl;
                private String img1v1Url;
                private int albumSize;
                private List<?> alias;
                private String trans;
                private int musicSize;
                private int topicPerson;
            }
        }

        @NoArgsConstructor
        @Data
        public static class BMusicEntity {
            private Object name;
            private long id;
            private int size;
            private String extension;
            private int sr;
            private int dfsId;
            private int bitrate;
            private int playTime;
            private int volumeDelta;
        }

        @NoArgsConstructor
        @Data
        public static class HMusicEntity {
            private Object name;
            private long id;
            private int size;
            private String extension;
            private int sr;
            private int dfsId;
            private int bitrate;
            private int playTime;
            private int volumeDelta;
        }

        @NoArgsConstructor
        @Data
        public static class MMusicEntity {
            private Object name;
            private long id;
            private int size;
            private String extension;
            private int sr;
            private int dfsId;
            private int bitrate;
            private int playTime;
            private int volumeDelta;
        }

        @NoArgsConstructor
        @Data
        public static class LMusicEntity {
            private Object name;
            private long id;
            private int size;
            private String extension;
            private int sr;
            private int dfsId;
            private int bitrate;
            private int playTime;
            private int volumeDelta;
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
        public static class ArtistsEntity {
            private String name;
            private int id;
            private int picId;
            private int img1v1Id;
            private String briefDesc;
            private String picUrl;
            private String img1v1Url;
            private int albumSize;
            private List<?> alias;
            private String trans;
            private int musicSize;
            private int topicPerson;
        }
    }
}
