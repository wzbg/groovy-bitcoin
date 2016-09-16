package xyz.urac.bitcoin.encoding

import org.bouncycastle.util.encoders.Hex
import org.junit.Assert
import org.slf4j.LoggerFactory

class BaseXTests extends GroovyTestCase {
  final logger = LoggerFactory.getLogger getClass()

  final ALPHABET = '123456789ABCDEFGHJKLMNPQRSTUVWXYZabcdefghijkmnopqrstuvwxyz'
  final SOURCE = Hex.decode '00086eaa677895f92d4a6c5ef740c168932b5e3f442b14dbdb'

  void testBase10() {
    def baseX = new BaseX()
    def decimal = baseX.encode SOURCE
    logger.info "decimal: $decimal"
    assert new BigInteger(1, SOURCE) == new BigInteger(decimal)
    def source = baseX.decode decimal
    assert SOURCE == source
  }

  void testBase58() {
    def baseX = new BaseX(ALPHABET)
    def digits = baseX.encode SOURCE
    logger.info "digits: $digits"
    def source = baseX.decode digits
    assert SOURCE == source
  }
}
