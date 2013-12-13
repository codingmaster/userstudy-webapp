package de.hpi.unipotsdam.thorben.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "rating", uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "item_id", "question_id"})})
public class Rating {

  private Long id;
  private Integer rating;
  private Participant participant;
  private ThreadItem threadItem;
  private QuestionItem question;
  
  @Id
  @GeneratedValue(generator="increment")
  @GenericGenerator(name="increment", strategy = "increment")
  public Long getId() {
    return id;
  }
  public void setId(Long id) {
    this.id = id;
  }
  
  @Column
  public Integer getRating() {
    return rating;
  }
  public void setRating(Integer rating) {
    this.rating = rating;
  }
  
  @ManyToOne(cascade = {})
  @JoinColumn(name = "user_id")
  public Participant getParticipant() {
    return participant;
  }
  public void setParticipant(Participant participant) {
    this.participant = participant;
  }
  
  @ManyToOne
  @JoinColumn(name = "item_id")
  public ThreadItem getThreadItem() {
    return threadItem;
  }
  public void setThreadItem(ThreadItem threadItem) {
    this.threadItem = threadItem;
  }
  
  @ManyToOne
  @JoinColumn(name = "question_id")
  public QuestionItem getQuestionItem() {
    return question;
  }
  
  public void setQuestionItem(QuestionItem questionItem) {
    this.question = questionItem;
  }
  
  
}
