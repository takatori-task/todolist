package models

import anorm._
import anorm.SqlParser._
import play.api.db._
import play.api.Play.current

case class Task(id: Long, user: String, label: String)

object Task {

  def all(): List[Task] = DB.withConnection { implicit c =>
    SQL("select * from task").as(task *)
  }

  def create(user: Option[String], label: String) {
    DB.withConnection { implicit c =>
      SQL("insert into task(user, label) values ({user},{label})").on(
        'user -> user.get,
        'label -> label
      ).executeUpdate()
    }
  }

  def delete(id: Long) {
    DB.withConnection { implicit c =>
      SQL("delete from task where id = {id}").on(
        'id -> id
      ).executeUpdate()
    }
  }

  val task = {
    get[Long]("id") ~
    get[String]("user") ~
    get[String]("label") map {
      case id ~ user ~ label => Task(id, user,  label)
    }
  }
}
