package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void konstruktoriToimii() {
        Varasto v = new Varasto(10);
        assertEquals(0, v.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void luoVarastonSaldolla() {
        Varasto v = new Varasto(10, 1.0);
        assertEquals(1.0, v.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void luoVarastonTyhjallaSaldolla() {
        Varasto v = new Varasto(10, 0.0);
        assertEquals(0.0, v.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void konstruktoriLuoVarastonVaillaTilaa() {
        Varasto v = new Varasto(0.0);
        assertEquals(0.0, v.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void kaksiParametrinenKonstruktoriLuoVarastonVaillaTilaa() {
        Varasto v = new Varasto(0.0, 0.0);
        assertEquals(0.0, v.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void josSaldoSuurmempiKuinTilaNiinTilaOnSaldo() {
        double tilavuus = 3.0;
        Varasto v = new Varasto(tilavuus, 4.0);
        assertEquals(tilavuus, v.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void tyhjäAloitusSaldoNollataan() {
        Varasto v = new Varasto(10.0, -4.0);
        assertEquals(1, v.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void eiVoiLisätäNegatiivista() {
        double saldo = varasto.getSaldo();
        varasto.lisaaVarastoon(-3.3);
        assertEquals(saldo, varasto.getSaldo(), vertailuTarkkuus);
    }


    @Test
    public void eiVoiPoistaaNegatiivista() {
        double saldo = varasto.getSaldo();
        varasto.otaVarastosta(-3.3);
        assertEquals(saldo, varasto.getSaldo(), vertailuTarkkuus);
    }


    @Test
    public void eiVoiOttaaEnempääKuinSaldo() {
        Varasto v = new Varasto(10);
        v.lisaaVarastoon(4.0);
        double otettu = v.otaVarastosta(22);
        assertEquals(4.0, otettu, vertailuTarkkuus);
        assertEquals(10.0, v.paljonkoMahtuu(), vertailuTarkkuus);
    }


    @Test
    public void toStringPalauttaaJärkevätArvot() {
        String s = varasto.toString();
        assertTrue(s.contains("saldo"));
        assertTrue(s.contains("vielä tilaa"));
        assertTrue(s.contains(Double.toString(varasto.getSaldo())));
        assertTrue(s.contains(Double.toString(varasto.getTilavuus())));
    }
}