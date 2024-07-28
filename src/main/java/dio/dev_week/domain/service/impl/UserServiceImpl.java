package dio.dev_week.domain.service.impl;

import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import dio.dev_week.domain.model.User;
import dio.dev_week.domain.repository.UserRepository;
import dio.dev_week.domain.service.UserService;
        // para gararntir não expor a interface de service. 
        //Boa prática de não expor mais do que deveria da interface
@Service        
public class UserServiceImpl implements UserService{

        //injeção de dependência usando o Spring Boot
        private final UserRepository userRepository;

        // constructor to inject by constructor // será injetado pelo spring
        public UserServiceImpl(UserRepository userRepository) {
                this.userRepository = userRepository;
        }
        @Override       // end point de buscar
        public User findById(Long id) {
                                                // return a native exception of java
                return userRepository.findById(id).orElseThrow(NoSuchElementException::new);
        }

        @Override       // end point para criar
        public User create(User userToCreate) {
              if(userRepository.existsByAccountNumber(userToCreate.getAccount().getNumber())){
                throw new IllegalArgumentException("This Account already exists.");
              } 
              return userRepository.save(userToCreate);
        }

}
