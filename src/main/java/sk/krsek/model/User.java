package sk.krsek.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Table(name = "SUSERS",
    uniqueConstraints = {@UniqueConstraint(columnNames = {"USER_ID"})})
@Entity
@ToString
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class User {

  @Id
  @Column(name = "USER_ID", nullable = false, unique = true)
  int id;
  @Column(name = "USER_GUID")
  String guid;
  @Column(name = "USER_NAME")
  String name;
}