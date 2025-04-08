package school.hei.patrimoine.cas;

import org.junit.jupiter.api.Test;
import school.hei.patrimoine.cas.example.BakoCas;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class Tests {
  @Test
  void test_31_decembre() {
    final var echeance = LocalDate.of(
      2025, Month.DECEMBER, 31
    );

    var cas = new BakoCas();

    var argent = cas.possessions()
        .stream()
        .map(possession -> possession.projectionFuture(echeance).valeurComptable().ppMontant())
        .mapToLong(v -> {
          Double e = Double.parseDouble(v);
          return e.longValue();
        })
        .sum();

    assertTrue(argent > 13_000_000);
  }
}
