package ru.ponkin.billing.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @author Alexey Ponkin
 */

@Entity
@Getter
@Setter
@EqualsAndHashCode(of = {"id"})
@JsonInclude(JsonInclude.Include.NON_NULL)
@ToString
public class Account {

  @Id
  @GeneratedValue(strategy= GenerationType.AUTO)
  @Column(name = "ID")
  Long id;

  @NotNull
  @Column(name = "AMOUNT", nullable = false)
  BigDecimal amount = BigDecimal.ZERO;
}


