import org.scalatest.{WordSpec, Matchers}

class PersonSpec extends WordSpec with Matchers{

  val david = new Person("David", Some("Brown"), false)
  val john = new Person("John", None, false)
  val sophie = new Person("Sophie", Some("Black"), false)
  val brian = new Person("Brian", Some("Grey"), true)

  val people:Seq[Person] = Seq(david, john, sophie, brian)

  // Name tests

  "Guess name" when {
    "guess is David but target is John" should {
      "return list without David" in {
        Guess.guessName("David", people, john) shouldBe Seq(john, sophie, brian)
      }
    }
    "guess is John but target is David" should {
      "return list without John" in {
        Guess.guessName("John", people, david) shouldBe Seq(david, sophie, brian)
      }
    }
    "guess is John and target is John" should {
      "return list with just John" in {
        Guess.guessName("John", people, john) shouldBe Seq(john)
      }
    }
  }

    // Hair colour tests

  "Guess hair colour" when {
    "guess is Brown but target has no hair" should {
      "return list without anyone with Brown hair" in {
        Guess.guessHair(Some("Brown"), people, john) shouldBe Seq(john, sophie, brian)
      }
    }
  }

  "Guess hair colour" when {
    "guess is Grey but target has Brown hair" should {
      "return list without anyone with Grey hair" in {
        Guess.guessHair(Some("Grey"), people, brian) shouldBe Seq(david, john, sophie)
      }
    }

    "guess is None but target has Brown hair" should {
      "return list without anyone without hair" in {
        Guess.guessHair(None, people, david) shouldBe Seq(david, sophie, brian)
      }
    }
  }

    // Moustache tests

  "Guess hasMoustache" when {
    "guess is doesn't have moustache and target doesn't have moustache" should {
      "return list of people without moustaches" in {
        Guess.hasMoustache(false, people, david) shouldBe Seq(david, john, sophie)
      }
    }
    "guess is has moustache and target has a moustache" should {
      "return list of people without moustaches" in {
        Guess.hasMoustache(true, people, brian) shouldBe Seq(brian)
      }
    }
    "guess is has moustache and target doesn't have a moustache" should {
      "return list of people without moustaches" in {
        Guess.hasMoustache(true, people, john) shouldBe Seq(david, john, sophie)
      }
    }
  }

  // Multiples tests

    "guess is has a moustache and grey hair and target has a moustache and grey hair" should {
      "return brian" in {
        Guess.hasMoustacheAndHairColour(true, Some("Grey"), people, brian) shouldBe Seq(brian)
      }
  }

}

