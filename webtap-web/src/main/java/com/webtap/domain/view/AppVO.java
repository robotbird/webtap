package com.webtap.domain.view;

import com.webtap.domain.entity.App;

/**
 * @author robotbird
 * @version 1.0
 * @website http://webtap.cn
 * @date 2020-05-18 21:42
 **/
public class AppVO extends BaseVO {
    private App app;

    public App getApp() {
        return app;
    }

    public void setApp(App app) {
        this.app = app;
    }
}
