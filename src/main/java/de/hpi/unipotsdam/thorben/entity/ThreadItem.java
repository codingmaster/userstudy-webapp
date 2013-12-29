package de.hpi.unipotsdam.thorben.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "item", uniqueConstraints = {@UniqueConstraint(columnNames = {"thread_id", "article_id"})})
@NamedQueries({
  @NamedQuery(name="ThreadItem.selectItemsSortedByArticle", 
      query="SELECT i from ThreadItem i WHERE i.thread.id = :threadId ORDER BY i.article.publicationDate ASC"),
  @NamedQuery(name="ThreadItem.selectItemsSortedByOrder", 
      query="SELECT i from ThreadItem i WHERE i.thread.id = :threadId ORDER BY i.logicalOrder ASC")
})
public class ThreadItem {

  private Long id;
  private NewsThread thread;
  private Article article;
  private Integer logicalOrder;
  
  @Id
  @GeneratedValue(generator="increment")
  @GenericGenerator(name="increment", strategy = "increment")
  public Long getId() {
    return id;
  }
  public void setId(Long id) {
    this.id = id;
  }
  
  @Column(name = "logical_order")
  public Integer getLogicalOrder() {
    return logicalOrder;
  }
  public void setLogicalOrder(Integer logicalOrder) {
    this.logicalOrder = logicalOrder;
  }
  
  @ManyToOne(optional = false)
  @JoinColumn(name = "thread_id")
  public NewsThread getThread() {
    return thread;
  }
  public void setThread(NewsThread thread) {
    this.thread = thread;
  }
  
  @ManyToOne(optional = false)
  @JoinColumn(name = "article_id")
  public Article getArticle() {
    return article;
  }
  public void setArticle(Article article) {
    this.article = article;
  }
  
  
  
}
