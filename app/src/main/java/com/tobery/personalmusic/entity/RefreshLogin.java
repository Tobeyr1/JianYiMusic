package com.tobery.personalmusic.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Package: com.tobery.personalmusic.entity
 * @ClassName: refreshLogin
 * @Author: Tobey_r1
 * @CreateDate: 2022/7/12 23:01
 * @Description: java类作用描述
 * @UpdateUser: 更新者
 * @UpdateDate: 2022/7/12 23:01
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@NoArgsConstructor
@Data
public class RefreshLogin {

    private int code;
    private String cookie;
}
