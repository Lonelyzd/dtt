package cn.iceblue.core.domain.po;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author : IceBlue
 * @date : 2025/6/10 下午1:57
 **/
@Data
@ApiModel(value = "用户登录模型",description = "")
public class LoginVo {

    @NotBlank(message="用户名不能为空")
    @ApiModelProperty(name = "username",notes = "登录用户名")
    private String username;

    @NotBlank(message="密码不能为空")
    @ApiModelProperty(name = "password",notes = "登录密码")
    private String password;
}
