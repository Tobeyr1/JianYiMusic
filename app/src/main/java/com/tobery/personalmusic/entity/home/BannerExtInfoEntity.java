package com.tobery.personalmusic.entity.home;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class BannerExtInfoEntity {
      private List<BannersEntity> banners;

                @NoArgsConstructor
                @Data
                public static class BannersEntity {
                    private Object adLocation;
                    private Object monitorImpress;
                    private String bannerId;
                    private Object extMonitor;
                    private Object pid;
                    private String pic;
                    private Object program;
                    private Object video;
                    private Object adurlV2;
                    private Object adDispatchJson;
                    private Object dynamicVideoData;
                    private Object monitorType;
                    private Object adid;
                    private String titleColor;
                    private String requestId;
                    private boolean exclusive;
                    private String scm;
                    private Object event;
                    private String alg;
                    private String s_ctrp;
                    private SongEntity song;
                    private long targetId;
                    private boolean showAdTag;
                    private Object adSource;
                    private Object showContext;
                    private int targetType;
                    private String typeTitle;
                    private String url;
                    private String encodeId;
                    private Object extMonitorInfo;
                    private Object monitorClick;
                    private Object monitorImpressList;
                    private Object monitorBlackList;
                    private Object monitorClickList;

                    @NoArgsConstructor
                    @Data
                    public static class SongEntity {
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
                        private SqEntity sq;
                        private HrEntity hr;
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
                        private Object tagPicList;
                        private boolean resourceState;
                        private int version;
                        private Object songJumpInfo;
                        private Object entertainmentTags;
                        private int single;
                        private Object noCopyrightRcmd;
                        private int mst;
                        private int cp;
                        private int mv;
                        private int rtype;
                        private Object rurl;
                        private long publishTime;
                        private VideoInfoEntity videoInfo;
                        private List<String> tns;
                        private String alg;

                        @NoArgsConstructor
                        @Data
                        public static class AlEntity {
                            private int id;
                            private String name;
                            private String picUrl;
                            private List<String> tns;
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
                        public static class SqEntity {
                            private int br;
                            private int fid;
                            private int size;
                            private int vd;
                            private int sr;
                        }

                        @NoArgsConstructor
                        @Data
                        public static class HrEntity {
                            private int br;
                            private int fid;
                            private int size;
                            private int vd;
                            private int sr;
                        }

                        @NoArgsConstructor
                        @Data
                        public static class VideoInfoEntity {
                            private boolean moreThanOne;
                            private Object video;
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
