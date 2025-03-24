package cosc250.assignmentOne

/**

  Challenge 4 introduces Maps.

  In this, we're introducing a new immutable data structure: Map[K, T]
  A "map" associates a key with a value.

  One of the ways you can make a map is from a List or Seq of tuples.
  val list = List(1 -> 'A', 2 -> 'B', 3 -> 'C')
  val map = list.toMap

  or you can create one directly
  val map = Map(1 -> 'A', 2 -> 'B', 3 -> 'C')

  you can also get a Seq or List of tuples from a map by calling toSeq
  val seq:Seq(Int, Char) = map.toSeq

  You can get a new map with altered values by calling updated
  val withD = map.updated(4, 'D')
  val replaced = withD.updated(4, 'd')

  You can get a value from a map (given its key) by calling apply
  val d = replaced.apply(4)
  or, as by convention you don't have to say the word "apply"
  val d = replaced(4)

  */
object Challenge4 {

  // We're going to need the alphabet for this, because we're going to produce a cypher
  val alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"

  /**
   Given a map, produce the inverse map. 
    So if the map is Map(1 -> 'A', 2 -> 'B', 3 -> 'C') produce Map('A' -> 1, 'B' -> 2, 'C' -> 3)

    Don't worry about duplicate entries.
    */
  def inverse[K, V](map:Map[K,V]):Map[V, K] = {
    map.map((v, i) => (i, v))
  }

  /**
   A vignere table is a sequence of strings. Each string contains the alphabet shifted by
    1 more character. 
    
    So, 
    vignere(0) is ABCDEFGHIJKLMNOPQRSTUVWXYZ
    vignere(1) is BCDEFGHIJKLMNOPQRSTUVWXYZA
    vignere(2) is CDEFGHIJKLMNOPQRSTUVWXYZAB
    etc.

    You should produce these programmatically rather than hardcoding all 26 values.

    Hint: slice(from, to) produces a substring. 
    eg, alphabet.slice(13, 26) is "NOPQRSTUVWXYZ"

    Hint: ++ can be used to concatenate strings.

    Produce a vignere table as a Map[Int,String]
    */
  def vignereTable:Map[Int, String] = {
    (0 until 26).map(num => num -> (alpha.slice(num, 26) ++ alpha.slice(0, num))).toMap

    // being new to functional programming i'm not sure when a one liner is too much, 
    // or when it's just "good functional programming"
  }

  /**
   A vignere cypher relies on a key string. This is converted into a sequence of integers
    simply by taking the index of each letter in the alphabet.

    eg, "SCALA" -> Seq(18, 2, 0, 11, 0)

    Hint: String has an indexOf method that will tell you at which index a character first occurs
    eg, "SCALA".indexOf('S') is 0

    Hint: String has a toSeq method that will convert it into a Seq[Char]
    */
  def letterToNum(key:String):Seq[Int] = {
    // retireves the index where a char from a given string appears in the alphabet and adds it to a seq 
    key.map(letter => alpha.indexOf(letter)).toSeq
  }

  /**
   Suppose we have plaintext to encrypt that says "GREAT" and a key "SCALA"

    A Vignere cypher first turns the key into a sequence of indices (18, 2, 0, 11, 0)
    It then uses this to get the sequence of strings from the vignere table (vignere(18), vignere(2), vignere(0), vignere(11), vignere(0))

    Each letter of the key is then turned into its index in the alphabet
    GREAT -> (6, 17, 4, 0, 19)
    and then those numbers are used to look up a letter from the corresponding vignere string
    GREAT -> (vignere(18)(6), vignere(2)(17), vignere(0)(4), vignere(11)(0), vignere(0)(19))
    producing YTELT

    Write a function that will encode a string using a Vignere cypher and a key.
    If the key is shorter than the plaintext, it should repeat the key until it is long enough.

    Hint: "ABC" * 3 produces "ABCABCABC"

    Hint: "ABC".zip("abcd") produces Seq(('A', 'a'), ('B', 'b'), ('C', 'c'))
          ie, zipping two strings together will produce a sequence only as long as the shorter string
          the same works for zipping Seqs together

    Hint: Seq('A', 'B', 'C').mkString will produce "ABC"

    Hint: You can try a Vignere cypher out online at https://cryptii.com/pipes/vigenere-cipher
          or read up on it on Wikipedia: https://en.wikipedia.org/wiki/Vigen%C3%A8re_cipher
    */
  def encode(plaintext:String, key:String):String = {
    // Repeat the keystring for atleast the minimum length of the plaintext we want to encrypt 
    val newKey = key * ((plaintext.length() / key.length()) + 1)

    // zip the plaintext chars with there alphabet indexes and map each char to its transformed val in the vigneretable 
    plaintext.zip(letterToNum(newKey)).map((char, i) => vignereTable(i)(alpha.indexOf(char))).mkString

    // that feels messy and clumsy like it needs to be broken up... but myabe its just
    // "good succinct functional programming"??? Feels more like egyptian hieroglyphics, a haiku 
    // or a python bro trying to write a program in a single line.. but its funcational and fun!
  }

  /**
   To decode, we again need the sequence of Vignere strings that corresponds to the key.

    But this time, for each letter in the cyphertext, we look up its index in the vignere string
    and that gives us a character number to look up in the alphabet.
    */
  def decode(cyphertext:String, key:String):String = {
    // Since all this encryption algo does is shift letters forward based on a provided key 
    // we can reverse the process by shifting the key backwards, for each letter in the key 
    // we get its index in the alphabet, subtract it from 26 to get its reverse shift, then 
    // use mod to wrap it around the alphabet again incase we go outta bounds. This then 
    // gives us the decryption key and since our encode function already handles the algos 
    // encryption we can reuse it with the transformed key to effectively decrypt the 
    // cyphertext.... encryption is fun, shoutout COSC130 bby

    encode(cyphertext, key.map(letter => alpha((26 - alpha.indexOf(letter)) % 26)))
    
  }

}
