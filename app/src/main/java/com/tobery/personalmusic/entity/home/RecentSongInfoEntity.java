package com.tobery.personalmusic.entity.home;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Package: com.tobery.personalmusic.entity.home
 * @ClassName: RecentSongInfoEntity
 * @Author: Tobey_r1
 * @CreateDate: 2022/7/9 12:22
 * @Description: java类作用描述
 * @UpdateUser: 更新者
 * @UpdateDate: 2022/7/9 12:22
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@NoArgsConstructor
@Data
public class RecentSongInfoEntity {

    private int code;
    private RecentDataEntity data;
    private String message;

    @NoArgsConstructor
    @Data
    public static class RecentDataEntity {
        private int total;
        private List<ListEntity> list;

        @NoArgsConstructor
        @Data
        public static class ListEntity {
            private String resourceId;
            private long playTime;
            private String resourceType;
            private DataEntity data;

            @NoArgsConstructor
            @Data
            public static class DataEntity {
                private String name;
                private int id;
                private int pst;
                private int t;
                private List<ArEntity> ar;
                private List<?> alia;
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
                private Object a;
                private String cd;
                private int no;
                private Object rtUrl;
                private int ftype;
                private List<?> rtUrls;
                private int djId;
                private int copyright;
                private int s_id;
                private int mark;
                private int originCoverType;
                private Object originSongSimpleData;
                private int single;
                private Object noCopyrightRcmd;
                private int rtype;
                private Object rurl;
                private int mst;
                private int cp;
                private int mv;
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
                }

                @NoArgsConstructor
                @Data
                public static class MEntity {
                    private int br;
                    private int fid;
                    private int size;
                    private int vd;
                }

                @NoArgsConstructor
                @Data
                public static class LEntity {
                    private int br;
                    private int fid;
                    private int size;
                    private int vd;
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
        }
    }
}
