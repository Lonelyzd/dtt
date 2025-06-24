import request from '@/utils/request'

export function getUserMenus() {
  return request({
    url: '/sys/user/menu',
    method: 'get',
  })
}
