package cn.iceblue.core.domain.vo;

import lombok.Data;

import java.io.Serializable;

/** 字典视图类
 * @author : IceBlue
 * @date : 2025/7/14 下午4:54
 **/
@Data
public class DictVo {

    private Serializable key;

    private String value;
}
