package com.tobery.personalmusic.entity.home;


import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class HomeDiscoverEntity {

    private int code;
    private DataEntity data;
    private String message;

    @NoArgsConstructor
    @Data
    public static class DataEntity {
        private String cursor;
        private List<BlocksEntity> blocks;
        private boolean hasMore;
        private Object blockUUIDs;
        private PageConfigEntity pageConfig;
        private GuideToastEntity guideToast;
        private Object internalTest;
        private List<?> titles;
        private Object blockCodeOrderList;

        @NoArgsConstructor
        @Data
        public static class PageConfigEntity {
            private String refreshToast;
            private String nodataToast;
            private int refreshInterval;
            private Object title;
            private boolean fullscreen;
            private List<String> abtest;
            private List<String> songLabelMarkPriority;
            private int songLabelMarkLimit;
            private String homepageMode;
            private boolean showModeEntry;
            private String orderInfo;
        }

        @NoArgsConstructor
        @Data
        public static class GuideToastEntity {
            private boolean hasGuideToast;
            private List<?> toastList;
        }

        @NoArgsConstructor
        @Data
        public static class BlocksEntity {
            private String blockCode;
            private String showType;
            private int dislikeShowType;
            private Object extInfo;
            private boolean canClose;
            private int blockStyle;
            private String action;
            private String actionType;
            private UiElementEntity uiElement;
            private List<CreativesEntity> creatives;
            private String alg;
            private String logInfo;

            @NoArgsConstructor
            @Data
            public static class UiElementEntity {
                private SubTitleEntity subTitle;
                private ButtonEntity button;
                private String rcmdShowType;

                @NoArgsConstructor
                @Data
                public static class SubTitleEntity {
                    private String title;
                }

                @NoArgsConstructor
                @Data
                public static class ButtonEntity {
                    private String action;
                    private String actionType;
                    private String text;
                    private Object iconUrl;
                }
            }

            @NoArgsConstructor
            @Data
            public static class CreativesEntity {
                private String creativeType;
                private String creativeId;
                private String action;
                private String actionType;
                private UiElementEntity uiElement;
                private List<ResourcesEntity> resources;
                private String alg;
                private String logInfo;
                private int position;

                @NoArgsConstructor
                @Data
                public static class UiElementEntity {
                    private MainTitleEntity mainTitle;
                    private ImageEntity image;
                    private List<String> labelTexts;
                    private String rcmdShowType;

                    @NoArgsConstructor
                    @Data
                    public static class MainTitleEntity {
                        private String title;
                    }

                    @NoArgsConstructor
                    @Data
                    public static class ImageEntity {
                        private String imageUrl;
                    }
                }

                @NoArgsConstructor
                @Data
                public static class ResourcesEntity {
                    private UiElementEntity uiElement;
                    private String resourceType;
                    private String resourceId;
                    private Object resourceUrl;
                    private ResourceExtInfoEntity resourceExtInfo;
                    private String action;
                    private String actionType;
                    private boolean valid;
                    private String alg;
                    private String logInfo;

                    @NoArgsConstructor
                    @Data
                    public static class UiElementEntity {
                        private MainTitleEntity mainTitle;
                        private ImageEntity image;
                        private List<String> labelTexts;
                        private String rcmdShowType;

                        @NoArgsConstructor
                        @Data
                        public static class MainTitleEntity {
                            private String title;
                        }

                        @NoArgsConstructor
                        @Data
                        public static class ImageEntity {
                            private String imageUrl;
                        }
                    }

                    @NoArgsConstructor
                    @Data
                    public static class ResourceExtInfoEntity {
                        private int playCount;
                        private boolean highQuality;
                        private int specialType;
                    }
                }
            }
        }
    }
}

