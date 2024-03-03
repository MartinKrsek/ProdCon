package sk.krsek.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sk.krsek.exception.NonUniqueEntityException;
import sk.krsek.model.User;

public class UserRepositoryTest {

  private UserRepository userRepository;

  @BeforeEach
  public void beforeEach() {
    userRepository = new UserRepository();
  }

  @AfterEach
  public void afterEach() {
    userRepository.remove();
  }

  @Test
  public void createUser() {
    User user = new User(1, UUID.randomUUID().toString(), "Name" + 1);
    User createdUser = userRepository.save(user);

    assertEquals(user.getName(), createdUser.getName());
    assertEquals(user.getId(), createdUser.getId());
    assertEquals(user.getGuid(), createdUser.getGuid());
  }

  @Test
  public void createUser_nonUniqueId_throws() {
    User user = new User(1, UUID.randomUUID().toString(), "Name" + 1);
    userRepository.save(user);
    User user2 = new User(1, UUID.randomUUID().toString(), "Name" + 2);
    assertThrows(NonUniqueEntityException.class, () -> userRepository.save(user2));
  }

  @Test
  public void findAllUsers() {
    userRepository.save(new User(1, UUID.randomUUID().toString(), "Name" + 1));
    userRepository.save(new User(2, UUID.randomUUID().toString(), "Name" + 2));

    Collection<User> userList = userRepository.get();

    assertEquals(2, userList.size());
  }

  @Test
  public void deleteAllUsers() {
    userRepository.save(new User(1, UUID.randomUUID().toString(), "Name" + 1));
    userRepository.save(new User(2, UUID.randomUUID().toString(), "Name" + 2));

    userRepository.remove();

    Collection<User> userList = userRepository.get();
    assertTrue(userList.isEmpty());
  }

  @Test
  public void findUserById() {
    User user = new User(1, UUID.randomUUID().toString(), "Name" + 1);
    userRepository.save(user);

    Optional<User> foundUser = userRepository.findUserById(user.getId());

    assertTrue(foundUser.isPresent());
    assertEquals(user.getName(), foundUser.get().getName());
    assertEquals(user.getId(), foundUser.get().getId());
    assertEquals(user.getGuid(), foundUser.get().getGuid());
  }

}
