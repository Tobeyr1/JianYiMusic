package com.tobery.personalmusic.entity.home;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Package: com.tobery.personalmusic.entity.home
 * @ClassName: DailySongsEntity
 * @Author: Tobey_r1
 * @CreateDate: 2022/7/11 22:05
 * @Description: java类作用描述
 * @UpdateUser: 更新者
 * @UpdateDate: 2022/7/11 22:05
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@NoArgsConstructor
@Data
public class DailySongsEntity {

    private int code;
    private DataEntity data;

    @NoArgsConstructor
    @Data
    public static class DataEntity {
        private List<SongsEntity> dailySongs;
        private List<?> orderSongs;
        private List<RecommendReasonsEntity> recommendReasons;

        @NoArgsConstructor
        @Data
        public static class SongsEntity {
            private String name;
            private long id;
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
            private int rtype;
            private Object rurl;
            private int mst;
            private int cp;
            private int mv;
            private long publishTime;
            private String reason;
            private List<String> tns;
            private PrivilegeEntity privilege;
            private String alg;
            private String s_ctrp;

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
                private double vd;
                private int sr;
            }

            @NoArgsConstructor
            @Data
            public static class MEntity {
                private int br;
                private int fid;
                private int size;
                private double vd;
                private int sr;
            }

            @NoArgsConstructor
            @Data
            public static class LEntity {
                private int br;
                private int fid;
                private int size;
                private double vd;
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
                private PrivilegeEntity.FreeTrialPrivilegeEntity freeTrialPrivilege;
                private List<PrivilegeEntity.ChargeInfoListEntity> chargeInfoList;

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
                private List<?> tns;
                private List<?> alias;
            }
        }

        @NoArgsConstructor
        @Data
        public static class RecommendReasonsEntity {
            private int songId;
            private String reason;
        }
    }
}
