package one.tmbrms.readingsns.repository

import spock.lang.*
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment
import org.springframework.beans.factory.annotation.Autowired
import one.tmbrms.readingsns.entity.User

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserSpec extends Specification {
    @Autowired
    UserRepository userRepository

    def "Userテーブルが読めるか確認"() {
        given: 
        def arimoto = userRepository.findById(3)

        expect: 
        arimoto.get().name == "harimoto"
    }
}