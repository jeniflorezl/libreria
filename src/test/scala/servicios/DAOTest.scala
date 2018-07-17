package servicios

import org.scalatest.FunSuite
import org.scalatest.Matchers._

class DAOTest extends FunSuite{

  test("crear libro"){
    val result = ServicioLibro.crearLibro("Crepusculo", "7137SFH2983", "D")
    result.isSuccess should be(true)
  }

  test("prestar libro"){
    val result = ServicioLibro.prestar()
    result.isSuccess should be(true)
  }

}
