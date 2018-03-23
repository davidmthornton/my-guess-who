import scala.io.StdIn

object Game {
  def main(args: Array[String]): Unit = {
    val randomGen = scala.util.Random
    val hairColours = Seq(Some("Brown"), Some("Black"), Some("Blonde"), Some("Grey"))
    val names = Seq("David", "Thomas", "Scully", "James", "Ryan", "Paul", "Stacey", "Jim", "Tommy", "Jhansi", "Steve")


    def peopleGenerator(num:Int):Seq[Person] = {

      def generatePeople(ppl:Seq[Person]):Seq[Person] = {
        val ppl2 = ppl :+ new Person(names(randomGen.nextInt(names.size)), hairColours(randomGen.nextInt(hairColours.size)), if(randomGen.nextInt(1)==0){ true}else{false})
        if(!(ppl2.size == num)) generatePeople(ppl2)
        else ppl2
      }


      generatePeople(Seq(new Person(names(randomGen.nextInt(names.size)), hairColours(randomGen.nextInt(hairColours.size)), if(randomGen.nextInt(1)==0){ true}else{false})))

    }

    println("Starting Guess Who...")
    println("Generating 10 random people...")

    val people = peopleGenerator(10)
    val targetPerson: Person = people(randomGen.nextInt(people.size - 1))
    //wait(1000)
    println("Choosing my person...")
    //wait(1000)
    println("OK... I've chosen someone. You can give commands in the following formats:-")
    println("hair:colour")
    println("moustache:true")
    println("name:name")

    awaitInput(people)

    def awaitInput(ppl: Seq[Person]){

      if(ppl.size == 1) {
        println("Well done - correct answer!")
      }
      else{

          ppl.map {
            println
          }


        val in = StdIn.readLine()
        if (in.contains("hair:")) {
          println("Checking for anyone with " + in.split(":")(1).trim + " hair")
          awaitInput(Guess.guessHair(Some(in.split(":")(1).trim), ppl, targetPerson))
        } else if (in.contains("name:")) {
          println("Checking for anyone with the name " + in.split(":")(1).trim)
          awaitInput(Guess.guessName(in.split(":")(1).trim, ppl, targetPerson))
        } else if (in.contains("moustache:")) {
          val assertion: Boolean = in.split(":")(1).trim.toBoolean
          if (assertion) {
            println("Checking for anyone who has a moustache")
          } else if (!assertion) {
            println("Checking for anyone who does not have a moustache")
          } else {
            println("I don't understand '" + in + "'")
          }

          awaitInput(Guess.hasMoustache(assertion, ppl, targetPerson))

        }

      }

    }




  }
}





