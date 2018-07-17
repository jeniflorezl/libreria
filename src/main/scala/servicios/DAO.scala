package servicios

import com.datastax.driver.core.{Row, Session}
import com.datastax.driver.core.querybuilder.QueryBuilder
import com.datastax.driver.core.querybuilder.QueryBuilder.select
import entidades.{Libro, Persona}
import scala.collection.JavaConverters._

import collection.mutable._
import scala.collection.mutable.ListBuffer
import scala.util.Try

trait DAO {
  def crearLibro(session: Session, libro: Libro) : Try[Boolean]
  def consultarLibro(session: Session, isbn: String): Option[Libro]
  def prestarLibro(session: Session, libro: Libro, persona: Persona): Try[Boolean]
  def devolverLibro(session: Session, libro: Libro): Try[Boolean]
  def ListarLibros(session: Session): Option[List[Libro]]
  def ListarDisponibles(session: Session): Option[List[Libro]]
  def ListarPrestados(session: Session): Option[List[Libro]]
  def LibrosPersona(session: Session, persona: Persona): Option[List[Libro]]
}

object ImplementacionDAO extends DAO{
  override def crearLibro(session: Session, libro: Libro): Try[Boolean] = {
    val result = session.execute("INSERT INTO libro (isbn, titulo, estado) VALUES ('"+libro.isbn+"','"+libro.titulo+"','"+libro.estado+"');")
    Try{result.wasApplied()}
  }

  override def consultarLibro(session: Session, isbn: String): Option[Libro] = {
    val selectStmt = select()
      .from("libros")
      .where(QueryBuilder.eq("isbn", isbn))
    val resultSet = session.execute(selectStmt)
    val libro: Option[Row] = Some(resultSet.one())
    if (libro Some(Libro())

  }

  override def prestarLibro(session: Session, libro: Libro, persona: Persona): Try[Boolean] = {
    val result = session.execute("INSERT INTO prestamo (titulo_libro, correo_persona) VALUES ('"+libro.titulo+"','"+persona.correo+"');")
    if (result.wasApplied()){
      val result2 = session.execute("UPDATE libro SET estado = P WHERE titulo = '" + libro.titulo+ "'")
      Try{result2.wasApplied()}
    }else{
      Try{result.wasApplied()}
    }
  }

  override def devolverLibro(session: Session, libro: Libro): Try[Boolean] = {
    val result = session.execute("UPDATE libro SET estado = D WHERE titulo = '" + libro.titulo+ "'")
    Try{result.wasApplied()}
  }

  override def ListarLibros(session: Session): Option[List[Libro]] = {
    val selectStmt = select()
      .from("libros")

    val resultSet = session.execute(selectStmt)
    //val rows: Iterable[Row] = resultSet.map(_.asScala.toStream.map(parseEntity))

    Some(List.empty)
  }

  override def ListarDisponibles(session: Session): Option[List[Libro]] = {
    val selectStmt = select()
      .from("libros")
      .where(QueryBuilder.eq("estado", "D"))

    val resultSet = session.execute(selectStmt)
    Some(List.empty)
  }

  override def ListarPrestados(session: Session): Option[List[Libro]] = {
    val selectStmt = select()
      .from("libros")
      .where(QueryBuilder.eq("estado", "P"))

    val resultSet = session.execute(selectStmt)
    Some(List.empty)
  }

  override def LibrosPersona(session: Session, persona: Persona): Option[List[Libro]] = {
    val selectStmt = select()
      .from("prestamos")
      .where(QueryBuilder.eq("correo", persona.correo))

    val resultSet = session.execute(selectStmt)
    Some(List.empty)
  }
}