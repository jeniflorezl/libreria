import org.scalatest.FunSuite
import org.scalatest.Matchers._

class ConnectionTest extends FunSuite{
  test("with a single host"){
    val cut = CassandraConnectionUri("cassandra://localhost:9042/test")
    cut.host should be ("localhost")
    cut.hosts should be (Seq("localhost"))
    cut.port should be (9042)
    cut.keyspace should be ("test")
  }
}
