package xyz.urac.bitcoin.encoding

class BaseX {
  def ALPHABET
  def BASE
  def LEADER

  BaseX() {
    this('0123456789')
  }

  BaseX(ALPHABET) {
    this.ALPHABET = ALPHABET
    BASE = ALPHABET.length()
    LEADER = ALPHABET[0]
  }

  String encode(byte[] source) {
    if (!source) return ''
    def digits = [0]
    for (def i = 0; i < source.length; i++) {
      def carry = 0xFF & source[i]
      for (def j = 0; j < digits.size(); j++) {
        carry += digits[j] << 8
        digits[j] = carry % BASE
        carry = carry.intdiv BASE
      }
      while (carry) {
        digits << carry % BASE
        carry = carry.intdiv BASE
      }
    }
    // deal with leading zeros
    for (def k = 0; k < source.length - 1 && source[k] == 0; k++) {
      digits << 0
    }
    // convert digits to a string
    def jj = digits.size() - 1
    for (def ii = 0; ii <= jj; ii++) {
      def tmp = ALPHABET[digits[ii]]
      digits[ii] = ALPHABET[digits[jj]]
      digits[jj--] = tmp
    }
    digits.join ''
  }

  byte[] decode(String digits) {
    if (!digits) return []
    def source = [0]
    for (def i = 0; i < digits.length(); i++) {
      def carry = ALPHABET.indexOf digits[i]
      if (carry == -1) {
        throw new Error("Non-base$BASE character")
      }
      for (def j = 0; j < source.size(); j++) {
        carry += source[j] * BASE
        source[j] = 0xFF & carry
        carry >>= 8
      }
      while (carry > 0) {
        source << (0xFF & carry)
        carry >>= 8
      }
    }
    // deal with leading zeros
    for (def k = 0; k < digits.length() - 1 && digits[k] == LEADER; k++) {
      source << 0
    }
    source.reverse()
  }
}
