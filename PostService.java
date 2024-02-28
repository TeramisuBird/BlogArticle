package blogarticle.app.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import blogarticle.app.models.Post;
import blogarticle.app.repositories.PostRepository;

/**
 * Loads and stores to and from the Post repository.
 * 
 * @version 1.0
 *
 */
@Service
public class PostService
{
  @Autowired
  private PostRepository postRepository;

  public Optional<Post> getById(Long id)
  {
    return postRepository.findById(id);
  }

  public List<Post> getAll()
  {
    return postRepository.findAll();
  }

  public Post save(Post post)
  {
    if (null == post.getId())
    {
      post.setCreatedAt(LocalDateTime.now());
    }
    post.setUpdatedAt(LocalDateTime.now());
    return postRepository.save(post);
  }

  public void delete(Post post)
  {
    postRepository.delete(post);
  }
}
