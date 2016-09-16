package xyz.urac.bitcoin.model

import org.bouncycastle.util.encoders.Hex

class PrivKey2PubKey2Addr extends GroovyTestCase {
  final PRIVATE_KEY = Hex.decode '1a3cf5e11d8de9ff4e5cd61c4c37a2e4e543042c4279db3f56326040cf0c10f9'

  void testGenPrivKey() {
    def side = new byte[20].collect{'1'}.sum()
    println "${side}testGenPrivKey start${side}"
    def compressed = false
    def privateKey = Key.genPrivKey compressed
    println "$privateKey Private Key"
    def publicKey = Key.getPubKey privateKey, compressed
    println "$publicKey Public Key"
    def address = Address.getPubAddr publicKey
    println "$address Address"
    println "${side}-testGenPrivKey end-${side}"
    println()
  }

  void testGenPrivKeyByCompressed() {
    def side = new byte[20].collect{'2'}.sum()
    println "${side}testGenPrivKeyByCompressed start${side}"
    def privateKey = Key.genPrivKey()
    println "$privateKey Private Key"
    def publicKey = Key.getPubKey privateKey
    println "$publicKey Public Key"
    def address = Address.getPubAddr publicKey
    println "$address Address"
    println "${side}-testGenPrivKeyByCompressed end-${side}"
    println()
  }

  void testGetPrivKeyHex() {
    def side = new byte[20].collect{'3'}.sum()
    println "${side}testGetPrivKeyHex start${side}"
    def compressed = false, format = 'hex'
    def privateKey = Key.getPrivKey PRIVATE_KEY, compressed, format
    println "$privateKey Private Key"
    def publicKey = Key.getPubKey privateKey, compressed, format
    println "$publicKey Public Key"
    def address = Address.getPubAddr publicKey
    println "$address Address"
    println "${side}-testGetPrivKeyHex end-${side}"
    println()
  }

  void testGetPrivKeyHexyByCompressed() {
    def side = new byte[20].collect{'4'}.sum()
    println "${side}testGetPrivKeyHexyByCompressed start${side}"
    def compressed = true, format = 'hex'
    def privateKey = Key.getPrivKey PRIVATE_KEY, compressed, format
    println "$privateKey Private Key"
    def publicKey = Key.getPubKey privateKey, compressed, format
    println "$publicKey Public Key"
    def address = Address.getPubAddr publicKey
    println "$address Address"
    println "${side}-testGetPrivKeyHexyByCompressed end-${side}"
    println()
  }

  void testGetPrivKeyWIF() {
    def side = new byte[20].collect{'5'}.sum()
    println "${side}testGetPrivKeyWIF start${side}"
    def compressed = false, format = 'wif'
    def privateKey = Key.getPrivKey PRIVATE_KEY, compressed, format
    println "$privateKey Private Key"
    def publicKey = Key.getPubKey privateKey, compressed, format
    println "$publicKey Public Key"
    def address = Address.getPubAddr publicKey
    println "$address Address"
    println "${side}-testGetPrivKeyWIF end-${side}"
    println()
  }

  void testGetPrivKeyWIFByCompressed() {
    def side = new byte[20].collect{'6'}.sum()
    println "${side}testGetPrivKeyWIFByCompressed start${side}"
    def compressed = true, format = 'wif'
    def privateKey = Key.getPrivKey PRIVATE_KEY, compressed, format
    println "$privateKey Private Key"
    def publicKey = Key.getPubKey privateKey, compressed, format
    println "$publicKey Public Key"
    def address = Address.getPubAddr publicKey
    println "$address Address"
    println "${side}-testGetPrivKeyWIFByCompressed end-${side}"
    println()
  }

  void testGenKeys() {
    def side = new byte[20].collect{'7'}.sum()
    println "${side}testGenKeys start${side}"
    println Key.genKeys(true, 'Hex')
    println "${side}-testGenKeys end-${side}"
    println()
  }

  void testGenPubAddr() {
    def side = new byte[20].collect{'8'}.sum()
    println "${side}testGenPubAddr start${side}"
    println Address.genPubAddr(true, 'WIF')
    println "${side}-testGenPubAddr end-${side}"
    println()
  }
}
