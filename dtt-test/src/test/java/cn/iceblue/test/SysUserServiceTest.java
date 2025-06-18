package cn.iceblue.test;

import cn.iceblue.core.pojo.entity.SysUserEntity;
import cn.iceblue.data.service.SysUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author : IceBlue
 * @date : 2025/6/18 下午3:36
 **/
@SpringBootTest
public class SysUserServiceTest {

    @Autowired
    SysUserService sysUserService;

    @Test
    public void insertTest() {
        SysUserEntity entity = new SysUserEntity();

        entity.setUserCode("002");
        entity.setUserName("策划用户2");
        entity.setUserPassword("11K896pLR8r0ACIwuGNJVQ==");

        sysUserService.save(entity);
    }
}
