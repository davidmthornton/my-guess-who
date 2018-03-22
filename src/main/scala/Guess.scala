object Guess {
  def hasMoustacheAndHairColour(hasMoustache: Boolean, hairColour: Some[String], people: Seq[Person], person: Person):Seq[Person] = {
    people.filter{
      prsn => prsn match{
        case a => a.hasMoustache == hasMoustache && a.hairColour.getOrElse("No hair").equalsIgnoreCase(hairColour.getOrElse("No hair"))
      }
    }
  }

  def hasMoustache(hasMoustache: Boolean, people: Seq[Person], person: Person) = {
    if(hasMoustache && person.hasMoustache){
      people.filter{
        prsn => prsn.hasMoustache
      }
    }else{
      people.filterNot{
        prsn => prsn.hasMoustache
      }
    }
  }


  def guessHair(hairColour: Option[String], people: Seq[Person], target: Person):Seq[Person] = {

    people.filterNot {
      person => //println(person.hairColour.getOrElse(""))
        person.hairColour.getOrElse("no hair").equalsIgnoreCase(hairColour.getOrElse("no hair"))
    }
  }

  def guessName(name: String, people: Seq[Person], target: Person): Seq[Person] ={

    if (name.equalsIgnoreCase(target.name)){
      people.filter {
        prsn => prsn.name.equalsIgnoreCase(name)
      }
    }else {
      people.filterNot {
        prsn => prsn.name.equalsIgnoreCase(name)
      }
    }
  }
}