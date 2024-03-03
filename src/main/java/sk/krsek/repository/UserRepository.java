package sk.krsek.repository;

import java.util.Collection;
import java.util.Optional;
import sk.krsek.model.User;

public class UserRepository extends Repository implements RepositoryInterface<User> {

  final Class<User> object = User.class;

  public Optional<User> findUserById(int id) {
    return findById(id, object).map(obj -> (User) obj);
  }

  @Override
  public User save(User user) {
    return (User) create(user);
  }

  @Override
  public Collection<User> get() {
    return (Collection<User>) findAll(object);
  }

  @Override
  public void remove() {
    deleteAll(object);
  }
}
