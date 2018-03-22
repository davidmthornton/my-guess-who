import scala.io.StdIn

object Game {
  def main(args: Array[String]): Unit = {

    var quit = false

    println("Starting Guess Who... (enter quit to exit)")
    println("Creating list of people...")
    val people: Seq[Person] = Seq(
      new Person("Dave", Some("Brown"), false),
      new Person("Jim", Some("Blonde"), true),
      new Person("Georgina", Some("Pink"), false),
      new Person("John", None, false),
      new Person("Sophie", Some("Black"), false),
      new Person("James", Some("Grey"), true))

    val targetPerson: Person = people(scala.util.Random.nextInt(people.size - 1))

    println("OK... I've picked someone. You can give commands in the following formats:-")
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
        if (in.equals("quit")) quit = true
        else if (in.contains("hair:")) {
          println("Checking for anyone with " + in.split(":")(1) + " hair")
          awaitInput(Guess.guessHair(Some(in.split(":")(1)), ppl, targetPerson))
        } else if (in.contains("name:")) {
          println("Checking for anyone with the name " + in.split(":")(1))
          awaitInput(Guess.guessName(in.split(":")(1), ppl, targetPerson))
        } else if (in.contains("moustache:")) {
          val assertion: Boolean = in.split(":")(1).toBoolean
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





