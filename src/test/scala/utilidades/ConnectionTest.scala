package utilidades

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

  test("test connection"){
    val uri = CassandraConnectionUri("cassandra://localhost:9042/test")
    val session = Helper.createSessionAndInitKeyspace(uri)

    session.execute("CREATE TABLE IF NOT EXISTS things (id int, name text, PRIMARY KEY (id))")
    session.execute("INSERT INTO things (id, name) VALUES (1, 'foo');")

    /*val selectStmt = select().column("name")
      .from("things")
      .where(QueryBuilder.eq("id", 1))
      .limit(1)

    val resultSet = session.execute(selectStmt)
    val row = resultSet.one()
    row.getString("name") should be("foo")
    session.execute("DROP TABLE things;")*/
  }
}
