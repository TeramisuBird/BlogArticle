package blogarticle.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import blogarticle.app.models.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long>
{

}
