package blogarticle.app.models;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;

@Entity
public class Post
{
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;
  private String title;
  @Column(columnDefinition = "TEXT")
  private String body;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;

  @NotNull
  @ManyToOne
  @JoinColumn(name = "account_id", referencedColumnName = "id", nullable = false)

  private Account account;

  public Account getAccount()
  {
    return this.account;
  }

  public void setAccount(Account account)
  {
    this.account = account;
  }

  public Long getId()
  {
    return this.id;
  }
  
  public void setId(Long id) {
    this.id = id;
  }

  public void setCreatedAt(LocalDateTime now)
  {
    this.createdAt = now;
  }

  public LocalDateTime getCreatedAt()
  {
    return this.createdAt;
  }

  public void setTitle(String title)
  {
    this.title = title;
  }

  public String getTitle()
  {
    return this.title;
  }

  public void setBody(String body)
  {
    this.body = body;
  }

  public String getBody()
  {
    return this.body;
  }
  
  public void setUpdatedAt(LocalDateTime updatedAt) {
    this.updatedAt = updatedAt;
  }
  
  public LocalDateTime getUpdatedAt() {
    return this.updatedAt;
  }
  
  @Override
  public String toString() {
    return "Post{" +
           "id=" + id +
           ", title='" + title + "'" +
           ", body='" + body + "'" +
           ", createdAt='" + createdAt + "'" +
           ", updatedAt='" + updatedAt + "'" +
           "}";
  }
}
