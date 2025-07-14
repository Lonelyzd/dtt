package cn.iceblue.core.domain.dto;

import cn.iceblue.core.pojo.entity.SysMenuEntity;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : IceBlue
 * @date : 2025/7/10 下午1:45
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "系统菜单视图",description = "")
public class SysMenuDto extends SysMenuEntity {

    private List<SysMenuDto> children =new ArrayList<>();

}
