package xyz.urac.bitcoin.model

import org.bouncycastle.util.encoders.Hex

import xyz.urac.bitcoin.crypto.Secp256k1
import xyz.urac.bitcoin.encoding.Base58Check

/**
 * 密钥
 * @author zyc ￠幻冰
 * @date 2016年9月16日 下午5:55:20
 */
class Key {
  /**
   * 获取私钥
   * @param privKey
   * @param compressed
   * @param format
   * @return
   */
  static getPrivKey(privKey, compressed = true, format = null) {
    if (!(privKey instanceof byte[])) {
      throw new Error('"privKey" argument must be an Array of Bytes')
    }
    if (!Secp256k1.privateKeyVerify(privKey)) {
      throw new Error('Invalid privateKey')
    }
    if (compressed) {
      privKey = [privKey, new Byte('01')].flatten() as byte[]
    }
    if (format ==~ /(?i)Hex/) {
      privKey = Hex.toHexString privKey
    } else if (format ==~ /(?i)WIF/) {
      privKey = Base58Check.encode privKey, '80'
    }
    privKey
  }

  /**
   * 生成私钥
   * @param compressed
   * @param format
   * @return
   */
  static genPrivKey(compressed = true, format = null) {
    def privKey = Secp256k1.privateKeyCreate()
    getPrivKey privKey, compressed, format
  }

  /**
   * 获取公钥
   * @param privKey
   * @param compressed
   * @param format
   * @return
   */
  static getPubKey(privKey, compressed = true, format = null) {
    if (!(privKey instanceof byte[])) {
      if (format ==~ /(?i)Hex/) {
        privKey = Hex.decode privKey
      } else if (format ==~ /(?i)WIF/) {
        privKey = Base58Check.decode(privKey).data
      }
    }
    if (compressed) {
      privKey = privKey[0..-2] as byte[]
    }
    def pubKey = Secp256k1.publicKeyCreate privKey, compressed
    if (format) {
      pubKey = Hex.toHexString pubKey
    }
    return pubKey
  }

  /**
   * 生成密钥对
   * @param compressed
   * @param format
   * @return
   */
  static genKeys(compressed = true, format = null) {
    def privKey = genPrivKey compressed, format
    def pubKey = getPubKey privKey, compressed, format
    [privKey: privKey, pubKey: pubKey]
  }
}
