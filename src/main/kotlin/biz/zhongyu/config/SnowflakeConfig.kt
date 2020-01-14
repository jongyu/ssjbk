package biz.zhongyu.config

import cn.hutool.core.lang.Snowflake
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 * @author yuzhong
 * @date 2020/1/14
 * @desc Snowflake唯一ID
 */
@Configuration
class SnowflakeConfig {

    @Bean
    fun createSnowflake(): Snowflake {
        return Snowflake(1, 1)
    }

}