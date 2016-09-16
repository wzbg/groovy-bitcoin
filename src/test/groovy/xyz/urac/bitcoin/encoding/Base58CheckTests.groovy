package xyz.urac.bitcoin.encoding

import org.slf4j.LoggerFactory

class Base58CheckTests extends GroovyTestCase {
  final logger = LoggerFactory.getLogger getClass()

  final DATA = '086eaa677895f92d4a6c5ef740c168932b5e3f44'
  final PRIVATE_KEY = '1a3cf5e11d8de9ff4e5cd61c4c37a2e4e543042c4279db3f56326040cf0c10f9'

  void testAddr() {
    def string = Base58Check.encode DATA
    logger.info string
    def map = Base58Check.decode string, true
    assert '00' == map.prefix
    assert DATA == map.data
  }

  void testPrivKey() {
    def string = Base58Check.encode PRIVATE_KEY, '80'
    logger.info string
    def map = Base58Check.decode string, true
    assert '80' == map.prefix
    assert PRIVATE_KEY == map.data
  }
}
