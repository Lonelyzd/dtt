package cn.iceblue.web.config;

import cn.dev33.satoken.stp.StpUtil;
import cn.iceblue.core.pojo.entity.BaseEntity;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.context.annotation.Configuration;

import java.util.Date;

/**
 * MyBaits 配置
 *
 * @author : IceBlue
 * @date : 2025/6/18 下午3:58
 **/
@Configuration
public class MybatisPlusConfig implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        metaObject.setValue(BaseEntity.CREATE_BY, "0");
        try {
            String userId = StpUtil.getLoginIdAsString();
            metaObject.setValue(BaseEntity.CREATE_BY, userId);
        } catch (Exception e) {

        }

        metaObject.setValue(BaseEntity.TENANT_ID, "-");
        metaObject.setValue(BaseEntity.CREATE_TIME, new Date());

    }

    @Override
    public void updateFill(MetaObject metaObject) {
        setFieldValByName(BaseEntity.UPDATE_BY, "0", metaObject);
        try {
            String userId = StpUtil.getLoginIdAsString();
            setFieldValByName(BaseEntity.UPDATE_BY, userId, metaObject);
        } catch (Exception e) {

        }
        setFieldValByName(BaseEntity.UPDATE_TIME, new Date(), metaObject);
    }

}
