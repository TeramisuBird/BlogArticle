package blogarticle.app.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import blogarticle.app.models.Account;
import blogarticle.app.models.Post;
import blogarticle.app.services.AccountService;
import blogarticle.app.services.PostService;

@Component
public class InitialData implements CommandLineRunner
{
  @Autowired
  private PostService postService;

  @Autowired
  private AccountService accountService;

  @Override
  public void run(String... args) throws Exception
  {
    List<Post> posts = postService.getAll();

    if (0 == posts.size())
    {

      Account account1 = new Account();
      Account account2 = new Account();

      account1.setFirstName("Anonymous");
      account1.setLastName("user");
      account1.setEmail("user.user@domain.com");
      account1.setPassword("password");

      account2.setFirstName("Anonymous");
      account2.setLastName("admin");
      account2.setEmail("admin.admin@domain.com");
      account2.setPassword("password");

      accountService.save(account1);
      accountService.save(account2);

      Post post1 = new Post();
      post1.setTitle("The title of Comment 1");
      post1.setBody("This comment is a test comment posted by anonymous user.");
      post1.setAccount(account1);

      Post post2 = new Post();
      post2.setTitle("The title of Comment 2");
      post2.setBody("This is the body of the second comment post by the anonymous admin.");
      post2.setAccount(account2);

      postService.save(post1);
      postService.save(post2);
    }

  }
}
