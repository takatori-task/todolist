package models

import anorm._
import anorm.SqlParser._
import play.api.db._
import play.api.Play.current

case class Task(id: Long, user_id: String, label: String)

object Task {

  def all(): List[Task] = DB.withConnection { implicit c =>
    SQL("select * from task").as(task *)
  }

  def user(user_id: String): List[Task] = DB.withConnection { implicit c =>
    SQL("select * from task where user_id = {user_id}").on(
      'user_id -> user_id
    ).as(task *)
  }

  def create(user_id: Option[String], label: String) {

    val name = user_id match {
      case Some(u) => user_id.get
      case None => ""
    }

    DB.withConnection { implicit c =>
      SQL("insert into task(user_id, label) values ({user_id},{label})").on(
        'user_id -> name,
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
    get[String]("user_id") ~
    get[String]("label") map {
      case id ~ user_id ~ label => Task(id, user_id,  label)
    }
  }
}
