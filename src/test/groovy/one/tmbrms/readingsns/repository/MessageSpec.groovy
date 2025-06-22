package one.tmbrms.readingsns.repository

import spock.lang.*
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment
import org.springframework.beans.factory.annotation.Autowired
import one.tmbrms.readingsns.entity.User

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class MessageSpec extends Specification {
    @Autowired
    MessageRepository messageRepository

    def "Messageテーブルが読めるか確認"() {
        given: 
        def messages = messageRepository.findAll()

        expect: 
        messages.find{it.id == 101}.user.name == "tambara" 
        messages.find{it.id == 102}.book.isbn == "9784296204083"
        messages.find{it.id == 103}.content == "ワタシ、リナックスチョットデキル"


    }
}