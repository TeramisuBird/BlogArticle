package blogarticle.app.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import blogarticle.app.models.Account;

/**
 * A database or repository that saves Account models. Must use the AccountService to interact with
 * it.
 * 
 * @version 1.0
 * 
 */
@Repository
public interface AccountRepository extends JpaRepository<Account, Long>
{
  /**
   * Searches for a single email in the repository.
   * 
   * @param email
   *          The email name to search for.
   * @return An account object if found, otherwise null.
   */
  public Optional<Account> findOneByEmail(String email);
}
