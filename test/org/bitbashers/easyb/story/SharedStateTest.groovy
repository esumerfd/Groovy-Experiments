package org.bitbashers.easyb.story

scenario "easby stories",{

  given "a story",{
    story = [name: "story name"]
  }
  when "story has a description", { story.description = "this is a cool story" }
  then "it is accessible", { story.description.shouldBe "this is a cool story" }
}

scenario "before", {
  before "an it", {
    story = [name: "before"]
  }
  then "can access to the story", { story.shouldNotBe null }
}

scenario "root before", {
  before "default value", { story = "default" }
  then "is the default", { story.shouldBe "default" }

  scenario "nested before", {
    before "alternate value", { story = "alternate" }
    then "should be alternate", { story.shouldBe "alternate" }
  }
}

scenario "nested", {
  scenario "initial scenario", {
    given "a story", { nestedStory = true }
  }

  scenario "separate", {
    then "story is not accessible", { nestedStory.shouldBe null }
  }
}
