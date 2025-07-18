import Vue from 'vue'
import store from '@/store'
import DataDict from '@/utils/dict'
import { getDicts as getDicts } from '@/api/system/dict/data'

function searchDictByKey(dict, key) {
  if (key == null && key == '') {
    return null
  }
  try {
    for (let i = 0; i < dict.length; i++) {
      if (dict[i].key == key) {
        return dict[i].value
      }
    }
  } catch (e) {
    return null
  }
}

function install() {
  Vue.use(DataDict, {
    metas: {
      '*': {
        labelField: 'value',
        valueField: 'key',
        request(dictMeta) {
          const storeDict = searchDictByKey(store.getters.dict, dictMeta.type)
          if (storeDict) {
            return new Promise(resolve => {
              resolve(storeDict)
            })
          } else {
            return new Promise((resolve, reject) => {
              getDicts(dictMeta.type).then(data => {
                //字典请求
                store.dispatch('dict/setDict', { key: dictMeta.type, value: data })
                resolve(data)
              }).catch(error => {
                reject(error)
              })
            })
          }
        }
      }
    }
  })
}

export default {
  install
}
