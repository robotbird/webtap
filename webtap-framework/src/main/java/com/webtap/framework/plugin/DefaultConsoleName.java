package com.webtap.framework.plugin;

import org.springframework.stereotype.Component;

/**
 * 默认的主程序输入名称接口实现
 *
 * @author zhangzhuo
 * @version 1.0
 */
@Component
public class DefaultConsoleName implements ConsoleName{
    @Override
    public String name() {
        return "My name is Main-start-DefaultConsoleName";
    }
}
