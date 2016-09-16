package xyz.urac.bitcoin.crypto

import org.bouncycastle.util.encoders.Hex
import org.junit.Assert;
import org.slf4j.LoggerFactory

class Secp256k1Tests extends GroovyTestCase {
  final logger = LoggerFactory.getLogger getClass()

  final PRIVATE_KEY = Hex.decode '1a3cf5e11d8de9ff4e5cd61c4c37a2e4e543042c4279db3f56326040cf0c10f9'

  void testPrivateKeyVerify() {
    assertTrue Secp256k1.privateKeyVerify(PRIVATE_KEY)
  }

  void testPrivateKeyCreate() {
    def privateKey = Secp256k1.privateKeyCreate()
    def privateKeyHex = Hex.toHexString privateKey
    logger.info "Private Key (Hex): $privateKeyHex"
    assert 32 == privateKey.length
    assert 64 == privateKeyHex.length()
  }

  void testPublicKeyCreate() {
    def publicKey = Secp256k1.publicKeyCreate PRIVATE_KEY, false
    def publicKeyHex = Hex.toHexString publicKey
    logger.info "Key: $publicKeyHex"
    assert 65 == publicKey.length
    assert 130 == publicKeyHex.length()
  }

  void testPublicKeyCreateCompressed() {
    def publicKey = Secp256k1.publicKeyCreate PRIVATE_KEY
    def publicKeyHex = Hex.toHexString publicKey
    logger.info "Key (Compressed): $publicKeyHex"
    assert 33 == publicKey.length
    assert 66 == publicKeyHex.length()
  }
}
