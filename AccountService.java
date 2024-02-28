package blogarticle.app.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import blogarticle.app.models.Account;
import blogarticle.app.repositories.AccountRepository;

/**
 * Loads from and stores into an AccountRepository.
 * 
 * @version 1.0
 *
 */
@Service
public class AccountService
{
  @Autowired
  private AccountRepository accountRepository;

  /**
   * Saves the given account into the AccountRepository.
   * 
   * @param account
   *          The account to save into the account repository.
   * @return An account object if properly saved.
   */
  public Account save(Account account)
  {
    return accountRepository.save(account);
  }

  /**
   * Finds the account given an email name within the AccountRepository.
   * 
   * @param email
   *          The email name to search for.
   * @return An account object which could possibly be null.
   */
  public Optional<Account> findByEmail(String email)
  {
    return accountRepository.findOneByEmail(email);
  }
}
