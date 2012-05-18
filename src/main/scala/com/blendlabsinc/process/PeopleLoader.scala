package com.blendlabsinc.process

import com.blendlabsinc.facebook.Graph
import com.blendlabsinc.models.Person
import com.blendlabsinc.schema.PersonHBaseStore

object PeopleLoader extends scala.App {
  val limitFriends = if (args.length == 1) args(0).toInt else 1000

  val people = Person(id = "me", name = "Me", groups = Graph.getGroups("me")) :: Graph.getFriendsWithGroups(limitFriends = limitFriends)
  people.foreach { person =>
    PersonHBaseStore.put(person)
    println(" - " + person.name + " (id=" + person.id + ") in " + person.groups.length + " groups")
  }
}
