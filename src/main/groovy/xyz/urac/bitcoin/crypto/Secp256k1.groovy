package xyz.urac.bitcoin.crypto

import java.security.SecureRandom

import org.bouncycastle.jce.ECNamedCurveTable
import org.slf4j.LoggerFactory

class Secp256k1 {
  private final static logger = LoggerFactory.getLogger Secp256k1.class

  private final static RANDOM_NUMBER_ALGORITHM = 'SHA1PRNG'
  private final static RANDOM_NUMBER_ALGORITHM_PROVIDER = 'SUN'

  private final static MAX_PRIVATE_KEY = new BigInteger('00FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFEBAAEDCE6AF48A03BBFD25E8CD0364140', 16)

  static privateKeyVerify(privateKey) {
    def privateKeyCheck = new BigInteger(1, privateKey)
    privateKeyCheck > 0 && privateKeyCheck <= MAX_PRIVATE_KEY
  }

  /**
   * Generate a random private key that can be used with Secp256k1.(k)
   */
  static privateKeyCreate() {
    def secureRandom
    try {
      secureRandom = SecureRandom.getInstance RANDOM_NUMBER_ALGORITHM, RANDOM_NUMBER_ALGORITHM_PROVIDER
    } catch (e) {
      logger.warn e.getMessage(), e
      secureRandom = new SecureRandom()
    }
    def privateKey = new byte[32]
    while (!privateKeyVerify(privateKey)) {
      secureRandom.nextBytes privateKey
    } 
    privateKey
  }

  /**
   * Converts a private key into its corresponding public key.(K)
   */
  static publicKeyCreate(privateKey, compressed = true) {
    def spec = ECNamedCurveTable.getParameterSpec 'secp256k1'
    def pointQ = spec.getG().multiply new BigInteger(1, privateKey)
    pointQ.getEncoded compressed
  }
}
