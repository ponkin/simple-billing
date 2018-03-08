package ru.ponkin.billing.proto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;

/**
 * @author Alexey Ponkin
 */
@Getter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Transfer {

  Long from;
  Long to;
  BigDecimal amount;

  public Transfer(
          @JsonProperty("from") Long from,
          @JsonProperty("to") Long to,
          @JsonProperty("amount") BigDecimal amount)
  {
    this.from = from;
    this.to = to;
    this.amount = amount;
  }
}
