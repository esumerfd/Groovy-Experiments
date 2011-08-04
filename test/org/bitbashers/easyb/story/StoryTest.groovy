package org.bitbashers.easyb.story

scenario "fred can be assigned", {

  before "user", { fred = 2 }

  scenario "starts at 1", {

    given "fred", { fred = 1}

    then "is one", { fred.shouldBe 1 }
  }
}
