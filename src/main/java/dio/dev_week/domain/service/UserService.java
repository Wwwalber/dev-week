package dio.dev_week.domain.service;

import dio.dev_week.domain.model.User;

public interface UserService {
    User findById(Long id);

    User create(User userCreate);
}
