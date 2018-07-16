package servicios

import entidades.{Libro, Persona}

object ServicioLibro{
  def prestar(libro: Libro, prestador: Persona): Option[Libro] = {
    if (libro.estado == "D") Some(Libro(libro.titulo, libro.isbn, "P", Some(prestador.correo)))
    else None
  }

  def devolver(libro: Libro) = {
    if (libro.estado == "P") Some(Libro(libro.titulo, libro.isbn, "D", None))
  }

  def listarLibros(libros: List[Libro]) =
    libros.foreach(x => println(x))

  def listarDisponibles(libros: List[Libro]) ={
    val librosD = libros.filter(libro => libro.estado == "D")
    librosD
  }

  def listarPrestados(libros: List[Libro]) ={
    val librosD = libros.filter(libro => libro.estado == "P")
    librosD
  }

  def librosPersona(persona: Persona, libros: List[Libro]) = {
    val result = libros.filter(libro => libro.persona == persona.correo)
  }

}