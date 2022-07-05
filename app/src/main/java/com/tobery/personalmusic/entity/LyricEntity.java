package com.tobery.personalmusic.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Package: com.tobery.personalmusic.entity
 * @ClassName: LyricEntity
 * @Author: Tobey_r1
 * @CreateDate: 2022/7/5 22:14
 * @Description: java类作用描述
 * @UpdateUser: 更新者
 * @UpdateDate: 2022/7/5 22:14
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@NoArgsConstructor
@Data
public class LyricEntity {

    private boolean sgc;
    private boolean sfy;
    private boolean qfy;
    private LrcEntity lrc;
    private KlyricEntity klyric;
    private TlyricEntity tlyric;
    private RomalrcEntity romalrc;
    private int code;

    @NoArgsConstructor
    @Data
    public static class LrcEntity {
        private int version;
        private String lyric;
    }

    @NoArgsConstructor
    @Data
    public static class KlyricEntity {
        private int version;
        private String lyric;
    }

    @NoArgsConstructor
    @Data
    public static class TlyricEntity {
        private int version;
        private String lyric;
    }

    @NoArgsConstructor
    @Data
    public static class RomalrcEntity {
        private int version;
        private String lyric;
    }
}
