package xyz.urac.bitcoin.encoding

import org.bouncycastle.util.encoders.Hex
import org.slf4j.LoggerFactory

class Base58Tests extends GroovyTestCase {
  final logger = LoggerFactory.getLogger getClass()

  final SOURCE = Hex.decode '00086eaa677895f92d4a6c5ef740c168932b5e3f442b14dbdb'

  void test() {
    def base58 = new Base58()
    def digits = base58.encode SOURCE
    logger.info "digits: $digits"
    def source = base58.decode digits
    assert SOURCE == source
  }
}
