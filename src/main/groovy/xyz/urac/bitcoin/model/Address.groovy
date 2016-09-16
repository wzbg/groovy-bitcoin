package xyz.urac.bitcoin.model

import java.security.MessageDigest
import java.security.Security

import org.bouncycastle.jce.provider.BouncyCastleProvider
import org.bouncycastle.util.encoders.Hex

import xyz.urac.bitcoin.encoding.Base58Check

/**
 * 地址
 * @author zyc ￠幻冰
 * @date 2016年9月16日 下午7:03:30
 */
class Address {
  private static sha256
  private static ripemd160

  static {
    Security.addProvider new BouncyCastleProvider()
    sha256 = MessageDigest.getInstance 'SHA-256'
    ripemd160 = MessageDigest.getInstance 'RipeMD160'
  }

  /**
   * 获取地址
   * @param pubKey
   * @return
   */
  static getPubAddr(pubKey) {
    if (pubKey instanceof String) {
      pubKey = Hex.decode pubKey
    }
    def hash = sha256.digest pubKey
    def data = ripemd160.digest hash
    Base58Check.encode data
  }

  /**
   * 生成地址
   * @param compressed
   * @param format
   * @return
   */
  static genPubAddr(compressed = true, format = null) {
    def map = Key.genKeys compressed, format
    map.addr = getPubAddr map.pubKey
    map
  }
}
