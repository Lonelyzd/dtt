import request from '@/utils/request'

export function getMenuList(data) {
  return request({
    url: '/sys/menu/list',
    method: 'get',
    params:  data
  })
}
