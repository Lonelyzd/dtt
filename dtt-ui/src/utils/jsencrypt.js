const JSEncrypt = require('node-jsencrypt')

import settings from '@/settings'

const jsencrypt = new JSEncrypt()
jsencrypt.setPublicKey(settings.publicKey) // publicKey 是后台给的一个密钥

export default jsencrypt


