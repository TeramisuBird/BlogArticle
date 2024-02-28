package blogarticle.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import blogarticle.app.models.Post;
import blogarticle.app.services.PostService;

@Controller
public class ArticleController
{
  @Autowired
  private PostService postService;

  @GetMapping("/")
  public String article(Model model)
  {
    List<Post> posts = postService.getAll();
    model.addAttribute("posts", posts);
    return "article";
  }
}
