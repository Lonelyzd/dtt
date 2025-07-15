package cn.iceblue.api.controller.sys;

import cn.iceblue.core.domain.enums.dict.IDictItem;
import cn.iceblue.core.domain.vo.DictVo;
import cn.iceblue.core.domain.vo.R;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 数据字典信息获取接口
 *
 * @author : IceBlue
 * @date : 2025/7/14 下午3:34
 **/
@RestController
@RequestMapping("/sys/dict/data")
public class SysDictDataController {


    /**
     * 根据字典类型查询字典数据信息
     */
    @GetMapping("/type/{dictType}")
    public R<List<DictVo>> dictType(@PathVariable String dictType) {
        List<IDictItem> subClass = IDictItem.getSubClass(dictType);

        List<DictVo> collect = subClass.stream().map(item -> {
            DictVo dictVo = new DictVo();
            dictVo.setKey(item.getCode());
            dictVo.setValue(item.getText());
            return dictVo;
        }).collect(Collectors.toList());

        return R.ok(collect);
    }

}
