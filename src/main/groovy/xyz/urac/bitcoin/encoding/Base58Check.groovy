package xyz.urac.bitcoin.encoding

import java.security.MessageDigest

import org.bouncycastle.util.encoders.Hex;

class Base58Check {
  private static sha256 = MessageDigest.getInstance 'SHA-256'
  private static base58 = new Base58()

  static encode(data, prefix = '00') {
    if (data instanceof String) {
      data = Hex.decode data
    }
    if (prefix instanceof String) {
      prefix = Hex.decode(prefix)[0]
    }
    def hash = [prefix, data].flatten() as byte[]
    hash = sha256.digest hash
    hash = sha256.digest hash
    hash = [prefix, data, hash[0..3]].flatten() as byte[]
    base58.encode hash
  }

  static decode(string, hex = false) {
    def bytes = base58.decode string
    def prefix = bytes[0]
    def data = bytes[1..-5] as byte[]
    def hash = [prefix, data].flatten() as byte[]
    hash = sha256.digest hash
    hash = sha256.digest hash
    if (bytes[-4..-1] != hash[0..3]) {
      throw new Error('Invalid checksum')
    }
    if (hex) {
      prefix = [prefix]
      prefix = Hex.toHexString prefix as byte[]
      data = Hex.toHexString data
    }
    [prefix: prefix, data: data]
  }
}
