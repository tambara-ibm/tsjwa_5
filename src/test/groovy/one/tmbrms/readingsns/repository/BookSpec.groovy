package one.tmbrms.readingsns.repository

import spock.lang.*
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment
import org.springframework.beans.factory.annotation.Autowired
import one.tmbrms.readingsns.entity.Book
import one.tmbrms.readingsns.repository.BookRepository

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class BookSpec extends Specification {
    @Autowired
    BookRepository bookRepository

    def "test book creation"() {
        given: "a new book"
        def books = bookRepository.findAll()

        expect: 
        books.collect{it.name}.sort().first() == "AIリスク教本　攻めのディフェンスで危機回避＆ビジネス加速"
    }
}