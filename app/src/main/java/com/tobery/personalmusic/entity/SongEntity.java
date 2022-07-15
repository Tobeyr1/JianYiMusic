package com.tobery.personalmusic.entity;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Package: com.tobery.personalmusic.entity
 * @ClassName: SongEntity
 * @Author: Tobey_r1
 * @CreateDate: 2022/7/15 22:04
 * @Description: java类作用描述
 * @UpdateUser: 更新者
 * @UpdateDate: 2022/7/15 22:04
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@NoArgsConstructor
@Data
public class SongEntity {

    private List<DataEntity> data;
    private int code;

    @NoArgsConstructor
    @Data
    public static class DataEntity {
        private int id;
        private String url;
        private int br;
        private int size;
        private String md5;
        private int code;
        private int expi;
        private String type;
        private double gain;
        private int fee;
        private Object uf;
        private int payed;
        private int flag;
        private boolean canExtend;
        private Object freeTrialInfo;
        private String level;
        private String encodeType;
        private FreeTrialPrivilegeEntity freeTrialPrivilege;
        private FreeTimeTrialPrivilegeEntity freeTimeTrialPrivilege;
        private int urlSource;
        private int rightSource;

        @NoArgsConstructor
        @Data
        public static class FreeTrialPrivilegeEntity {
            private boolean resConsumable;
            private boolean userConsumable;
            private Object listenType;
        }

        @NoArgsConstructor
        @Data
        public static class FreeTimeTrialPrivilegeEntity {
            private boolean resConsumable;
            private boolean userConsumable;
            private int type;
            private int remainTime;
        }
    }
}
