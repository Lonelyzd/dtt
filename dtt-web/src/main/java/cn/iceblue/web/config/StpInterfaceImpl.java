package cn.iceblue.web.config;

import cn.dev33.satoken.stp.StpInterface;
import cn.iceblue.data.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义权限加载接口实现类
 */
@Component    // 保证此类被 SpringBoot 扫描，完成 Sa-Token 的自定义权限验证扩展
public class StpInterfaceImpl implements StpInterface {

	@Autowired
	SysUserService sysUserService;


	/**
	 * 返回一个账号所拥有的权限码集合
	 */
	@Override
	public List<String> getPermissionList(Object loginId, String loginType) {

		/*List<RacMenuTreeEntity> racMenuTreeList = userService.getUserOperateByCahce(String.valueOf(loginId));
		// 本 list 仅做模拟，实际项目中要根据具体业务逻辑来查询权限
		if (!CollectionUtils.isEmpty(racMenuTreeList)) {
			return racMenuTreeList.stream()
				.map(RacMenuTreeEntity::getHref)
				.collect(Collectors.toList());
		}*/

		return null;
	}

	/**
	 * 返回一个账号所拥有的角色标识集合 (权限与角色可分开校验)
	 */
	@Override
	public List<String> getRoleList(Object loginId, String loginType) {
		// 本 list 仅做模拟，实际项目中要根据具体业务逻辑来查询角色
		//本系统目前没有根据角色鉴权
		List<String> list = new ArrayList<String>();
		list.add("admin");
		list.add("super-admin");
		return list;
	}

}
