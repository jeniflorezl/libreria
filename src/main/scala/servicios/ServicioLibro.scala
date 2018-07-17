package servicios

import entidades.{Libro, Persona}
import utilidades.{CassandraConnectionUri, Helper}

import scala.util.Try

object ServicioLibro{
  private val uri = CassandraConnectionUri("cassandra://localhost:9042/libreria")
  private val session = Helper.createSessionAndInitKeyspace(uri)
  def crearLibro(titulo: String, isbn: String, estado: String): Try[Boolean] = {
    ImplementacionDAO.crearLibro(session, Libro(titulo, isbn, estado))
  }
  def prestar(isbn: String, prestador_correo: String): Try[Boolean] = {

    if (libro.estado == "D"){
      ImplementacionDAO.prestarLibro(session, libro, prestador)
    }else{
      Try{false}
    }
  }

  def devolver(libro: Libro) = {
    if (libro.estado == "P"){
      ImplementacionDAO.devolverLibro(session, libro)
    }else{
      Try{false}
    }
  }

  def listarLibros():Option[List[Libro]] = {
    ImplementacionDAO.ListarLibros(session)
  }

  def listarDisponibles():Option[List[Libro]] = {
    ImplementacionDAO.ListarDisponibles(session)
  }

  def listarPrestados():Option[List[Libro]] = {
    ImplementacionDAO.ListarPrestados(session)
  }

  def librosPersona(persona: Persona): Option[List[Libro]] = {
    ImplementacionDAO.LibrosPersona(session, persona)
  }

}