package dio.dev_week.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dio.dev_week.domain.model.User;

@Repository // nem é necessáio 
public interface UserRepository extends JpaRepository<User, Long> {
                        /* User é a classe que vamos usar, o id
                         * foi classificado como Long
                         */

}
