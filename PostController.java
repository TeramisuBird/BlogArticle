package blogarticle.app.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import blogarticle.app.models.Account;
import blogarticle.app.models.Post;
import blogarticle.app.services.AccountService;
import blogarticle.app.services.PostService;

/**
 * Handles the web interaction logic of the blog posts.
 * <p>
 * For loading and storing of model data, uses services.
 * 
 * @version 1.0
 * 
 */
@Controller
public class PostController
{
  @Autowired
  private PostService postService;

  @Autowired
  private AccountService accountService;

  /**
   * Retrieves a published webpage by ID number.
   * 
   * @param id
   *          The sequential number of a blog post.
   * @param model
   *          The Post model that has been published to the webpage.
   * @return A text name of the html file to access.
   */
  @GetMapping("/posts/{id}")
  public String getPost(@PathVariable Long id, Model model)
  {
    Optional<Post> optionalPost = postService.getById(id);
    if (optionalPost.isEmpty())
    {
      return "404";
    }
    Post post = optionalPost.get();
    model.addAttribute("post", post);
    return "post";
  }

  /**
   * Publishes a new blog post to its own webpage.
   * <p>
   * Must be logged in to successfully publish.
   * 
   * @param model
   *          The Post model to publish onto a new webpage.
   * @return A text name of the html file to access.
   */
  @GetMapping("/posts/new")
  public String createNewPost(Model model)
  {
    Optional<Account> optionalAccount = accountService.findByEmail("user.user@domain.com");
    if (optionalAccount.isEmpty())
    {
      return "404";
    }
    Post post = new Post();
    post.setAccount(optionalAccount.get());
    model.addAttribute("post", post);
    return "post_new";
  }

  /**
   * Adds the new blog post into the PostRepository.
   * 
   * @param post
   *          The Post model to store into the repository.
   * @return A link redirecting to the newly generated blog post.
   */
  @PostMapping("/posts/new")
  public String saveNewPost(@ModelAttribute Post post)
  {
    postService.save(post);
    return "redirect:/posts/" + post.getId();
  }

  /**
   * Edits a current blog post if it exists in the PostRepository.
   * 
   * @param id
   *          The sequential origin id number of the post.
   * @param model
   *          The Post model to store into the repository.
   * @return A text name of the html file to access.
   */
  @GetMapping("/posts/{id}/edit")
  public String getPostForEdit(@PathVariable Long id, Model model)
  {
    Optional<Post> optionalPost = postService.getById(id);
    if (optionalPost.isEmpty())
    {
      return "404";
    }
    Post post = optionalPost.get();
    model.addAttribute("post", post);
    return "post_edit";
  }

  /**
   * Adds the updated blog post into the PostRepository.
   * 
   * @param id
   *          The sequential origin id number of the post.
   * @param post
   *          The exact post which is being updated.
   * @param result
   *          The post after being updated, ready to save.
   * @param model
   *          The Post model which stores updated information.
   * @return A link redirecting to the newly updated blog post.
   */
  @PostMapping("/posts/{id}")
  public String updatePost(@PathVariable Long id, Post post, BindingResult result, Model model)
  {
    Optional<Post> optionalPost = postService.getById(id);
    if (optionalPost.isPresent())
    {
      Post existingPost = optionalPost.get();
      existingPost.setTitle(post.getTitle());
      existingPost.setBody(post.getBody());
      postService.save(existingPost);
    }
    return "redirect:/posts/" + post.getId();
  }

  /**
   * Removes an existing blog post from the PostRepository.
   * 
   * @param id
   *          The sequential origin id number of the post.
   * @return A link redirecting if successful, otherwise the 404 html file name.
   */
  @GetMapping("/posts/{id}/delete")
  public String deletePost(@PathVariable Long id)
  {
    Optional<Post> optionalPost = postService.getById(id);
    if (optionalPost.isEmpty())
    {
      return "404";
    }
    Post post = optionalPost.get();
    postService.delete(post);
    return "redirect:/";
  }
}
