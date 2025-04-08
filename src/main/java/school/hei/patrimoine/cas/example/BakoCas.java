package school.hei.patrimoine.cas.example;

import school.hei.patrimoine.cas.Cas;
import school.hei.patrimoine.modele.Argent;
import school.hei.patrimoine.modele.Devise;
import school.hei.patrimoine.modele.Personne;
import school.hei.patrimoine.modele.possession.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.Set;

public class BakoCas extends Cas {
  private static LocalDate start() {
    return LocalDate.of(2025, Month.APRIL, 8);
  }

  public BakoCas() {
    super(
      start(),
      start().plusYears(1),
      new Personne("Bako")
    );
  }

  @Override
  protected Devise devise() {
    return Devise.MGA;
  }

  @Override
  protected String nom() {
    return "Cas de Bako";
  }

  @Override
  protected void init() {}

  @Override
  protected void suivi() {}

  @Override
  public Set<Possession> possessions() {
    final var BNI = new Compte("BNI", start(), Argent.ariary(2_000_000));
    final var BMOI = new Compte("BMOI", start(), Argent.ariary(625_000));
    final var coffre = Argent.ariary(1_750_000);

    final var CDI = new FluxArgent(
      "Salaire de CDI vers BMOI",
      BNI,
      start(),
      LocalDate.MAX,
      2,
      Argent.ariary(2_125_000)
    );

    final var virement = new TransfertArgent(
      "virement mensuel du 3",
      BNI,
      BMOI,
      start(),
      LocalDate.MAX,
      3,
      Argent.ariary(200_000)
    );

    final var collocation = new FluxArgent(
      "collocation",
      BNI,
      start(),
      LocalDate.MAX,
      2,
      Argent.ariary(-600_000)
    );

    final var trainDeVie = new FluxArgent(
      "Nourriture, transport, etc...",
      BNI,
      start(),
      LocalDate.MAX,
      1,
      Argent.ariary(-700_000)
    );

    final var ordinateur = new Materiel(
      "Ordinateur",
      start(),
      start(),
      Argent.ariary(3_000_000),
      -0.12
    );

    return Set.of(
      BNI,
      BMOI,
      new Materiel("argent du coffre", start(), start(), coffre, 0),
      ordinateur
    );
  }
}
