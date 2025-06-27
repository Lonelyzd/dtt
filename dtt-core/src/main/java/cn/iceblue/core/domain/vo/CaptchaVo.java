package cn.iceblue.core.domain.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/** 验证码
 * @author : IceBlue
 * @date : 2025/6/26 下午3:59
 **/
@Data
public class CaptchaVo {

    @ApiModelProperty(name = "captchaEnabled", notes = "验证码开关")
    private Boolean captchaEnabled;

    @ApiModelProperty(name = "uuid", notes = "验证码ID")
    private String uuid;

    @ApiModelProperty(name = "img", notes = "验证码图片Base64")
    private String img;
}
