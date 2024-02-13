package lectures.part2oop

object Enums {
  
  enum Permissions {
    case READ, WRITE, EXECUTE, NONE
    
    // add fields/ methods
    def openDocuments(): Unit =
      if (this == READ) println("opening documents")
      else println("reading not allowed")
  }
  
  val somePerMissions: Permissions = Permissions.READ
  
  // constructor args
  enum PermissionsWithBits(bits: Int) {
    case READ extends PermissionsWithBits(4) // 100
    case WRITE extends PermissionsWithBits(2) // 010
    case EXECUTE extends PermissionsWithBits(1) // 001
    case NONE extends PermissionsWithBits(0) // 000
  }
  
  object PermissionsWithBits {
    def fromBits(bits: Int): PermissionsWithBits = // whatever here
      PermissionsWithBits.NONE
  }
  
  // standard API
  val somePermissionsOrdinal = somePerMissions.ordinal
  val allPermissions = PermissionsWithBits.values // array of all possible values of enum
  val readPermissions: Permissions = Permissions.valueOf("READ")
  
  def main(args: Array[String]): Unit = {
    somePerMissions.openDocuments()
    println(somePermissionsOrdinal)  // output: 0
  }
  
  
}
