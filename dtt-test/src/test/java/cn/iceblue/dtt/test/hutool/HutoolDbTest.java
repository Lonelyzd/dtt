package cn.iceblue.dtt.test.hutool;

import cn.hutool.core.map.MapUtil;
import cn.hutool.db.Db;
import cn.hutool.db.Entity;
import cn.hutool.db.Page;
import cn.hutool.db.PageResult;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * hutool工具Db数据库操作测试
 *
 * @author : Ice_Blue
 * @date : 2025/5/7 下午1:44
 **/

@SpringBootTest
public class HutoolDbTest {

    @Autowired
    private DataSource dataSource;


    /**
     * Db操作类简单查询单表
     *
     * @return void
     * @author IceBlue
     * @date 2025/5/7 下午2:41
     **/
    @Test
    public void testDb() throws SQLException {
        // 获取数据库连接
//        List<Entity> autobuildUpdatedatainfo = Db.use(dataSource).findAll("TSS_CONFIG_SJJCDAYZ");

        List<Entity> autobuildUpdatedatainfo = Db.use(dataSource).query("SELECT  * FROm TSS_CONFIG_SJJCDAYZ");

        System.out.println("=====");
    }


    /** Db操作类简单查询 参数
    * @author IceBlue
    * @date 2025/5/7 下午3:57
    * @return void
    **/
    @Test
    public void paramDbQuery() throws SQLException {

        Map<String, Object> paramMap = MapUtil.builder("lowerbound", (Object) new Date()).put("upperbound", new Date()).build();

        List<Entity> autobuildUpdatedatainfo = Db.use(dataSource).query("SELECT\n" +
                "SURG_EXEC_RECORD.SURG_EXEC_ID       AS xh,\n" +
                "'${HLHT_YLJGDM}'        AS yljgdm,\n" +
                "OUTP_ENCOUNTER.ENCOUNTER_ID||'MZ'         AS yjlxh,\n" +
                "OUTP_ENCOUNTER.ENCOUNTER_ID||'MZ'         AS zyjlxh,\n" +
                "SURG_EXEC_RECORD.SURG_OPRT_CODE      AS ssjcz,\n" +
                "SURG_EXEC_RECORD.SURG_OPRT_NAME              AS ssjczmc,\n" +
                "OUTP_ENCOUNTER.SYS_SOID             AS sys_id,\n" +
                "OUTP_ENCOUNTER.MODIFIED_AT          AS gdsj,\n" +
                "NOW()                               AS sjscsj,\n" +
                "'0'                                 AS pcxh,\n" +
                "OUTP_ENCOUNTER.MODIFIED_AT          AS MODIFIED_AT\n" +
                "FROM SURG_EXEC_RECORD\n" +
                "INNER JOIN OUTP_ENCOUNTER ON  SURG_EXEC_RECORD.ENCOUNTER_ID=OUTP_ENCOUNTER.ENCOUNTER_ID\n" +
                "WHERE SURG_EXEC_RECORD.IS_DEL=0\n" +
                "and  COALESCE(OUTP_ENCOUNTER.MODIFIED_AT,OUTP_ENCOUNTER.CREATED_AT) BETWEEN :lowerbound  AND :upperbound",paramMap);

    }


    @Test
    public void pageDbQuery() throws SQLException {

        PageResult<Entity> page = Db.use(dataSource).page("--\n" +
                        "SELECT\n" +
                        "SURG_EXEC_RECORD.SURG_EXEC_ID       AS xh,--测试\n" +
                        "'${HLHT_YLJGDM}'        AS yljgdm,\n" +
                        "OUTP_ENCOUNTER.ENCOUNTER_ID||'MZ'         AS yjlxh,\n" +
                        "OUTP_ENCOUNTER.ENCOUNTER_ID||'MZ'         AS zyjlxh,\n" +
                        "SURG_EXEC_RECORD.SURG_OPRT_CODE      AS ssjcz,\n" +
                        "SURG_EXEC_RECORD.SURG_OPRT_NAME              AS ssjczmc,\n" +
                        "OUTP_ENCOUNTER.SYS_SOID             AS sys_id,\n" +
                        "OUTP_ENCOUNTER.MODIFIED_AT          AS gdsj,\n" +
                        "NOW()                               AS sjscsj,\n" +
                        "'0'                                 AS pcxh,\n" +
                        "OUTP_ENCOUNTER.MODIFIED_AT          AS MODIFIED_AT\n" +
                        "FROM SURG_EXEC_RECORD\n" +
                        "INNER JOIN OUTP_ENCOUNTER ON  SURG_EXEC_RECORD.ENCOUNTER_ID=OUTP_ENCOUNTER.ENCOUNTER_ID\n" +
                        "WHERE SURG_EXEC_RECORD.IS_DEL=0\n"
                , new Page(10, 20));

        System.out.println(page);
    }




}
