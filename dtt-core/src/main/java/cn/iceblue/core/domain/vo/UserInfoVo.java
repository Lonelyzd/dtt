package cn.iceblue.core.domain.vo;

import lombok.Data;

import java.util.List;

/**
 * 用户信息,包括用户角色
 *
 * @author : IceBlue
 * @date : 2025/6/18 下午2:52
 **/
@Data
public class UserInfoVo {

    private List<String> roles;

    private String name;

    private String avatar;

    private String introduction;
}
