package biz.zhongyu.utils

import com.github.dozermapper.core.Mapper
import java.util.ArrayList

/**
 * @author yuzhong
 * @date 2020/1/14
 * @desc DozerMapper工具类
 */
object DozerUtils {

    /**
     * @param mapper DozerMapper
     * @param sourceList 原始列表
     * @param targetObjectClass 需要转换的类
     * @return 转换后的列表
     */
    fun <T, S> mapList(mapper: Mapper, sourceList: List<S>, targetObjectClass: Class<T>?): List<T> {
        val targetList: MutableList<T> = ArrayList()
        for (source in sourceList) {
            targetList.add(mapper.map(source, targetObjectClass))
        }
        return targetList
    }

}