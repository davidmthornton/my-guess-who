class Person(val name:String, val hairColour:Option[String], val hasMoustache:Boolean){

  override def toString: String = {
    "Name: " + name + ", Hair colour: " + hairColour.getOrElse("None") + ", Moustache: " + hasMoustache.toString
  }

}