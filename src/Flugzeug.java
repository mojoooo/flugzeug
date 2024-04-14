public class Flugzeug
{
    private double maxGewicht;
    private double leerGewicht;
    private double ladungGewicht;
    private double reiseGeschw;
    private double flugStunden;
    private double verbrauch;
    private double tankKapazitaet;
    private double kerosinVorrat;

    public Flugzeug(double maxGewicht, double leerGewicht, double ladungGewicht, double reiseGeschw, double flugStunden,
            double verbrauch, double tankKapazitaet, double kerosinVorrat)
    {
        this.maxGewicht = maxGewicht;
        this.leerGewicht = leerGewicht;
        this.ladungGewicht = ladungGewicht;
        this.reiseGeschw = reiseGeschw;
        this.flugStunden = flugStunden;
        this.verbrauch = verbrauch;
        this.tankKapazitaet = tankKapazitaet;
        this.kerosinVorrat = kerosinVorrat > tankKapazitaet ? tankKapazitaet : kerosinVorrat;
    }

    public double getMaxGewicht()
    {
        return this.maxGewicht;
    }

    public double getLeerGewicht()
    {
        return this.leerGewicht;
    }

    public double getLadungGewicht()
    {
        return this.ladungGewicht;
    }

    public double getReiseGeschw()
    {
        return this.reiseGeschw;
    }

    public double getFlugStunden()
    {
        return this.flugStunden;
    }

    public double getVerbrauch()
    {
        return this.verbrauch;
    }

    public double getTankKapazitaet()
    {
        return this.tankKapazitaet;
    }

    public double getKerosinVorrat()
    {
        return this.kerosinVorrat;
    }

    public double getGesamtGewicht()
    {
        double gesamtGewicht = this.leerGewicht + this.ladungGewicht + this.kerosinVorrat * 0.8;

        return gesamtGewicht;
    }

    public double getReichweite()
    {
        double reichweite = (this.kerosinVorrat / this.verbrauch) * this.reiseGeschw;

        if (reichweite < 0)
        {
            reichweite = 0;
        }

        return reichweite;
    }

    public boolean fliegen(double km)
    {
        if (km < 1)
        {
            return false;
        }

        if (km > this.getReichweite() || this.getGesamtGewicht() > this.getMaxGewicht())
        {
            return false;
        }
        
        
        double aktuelleFlugzeit = km / this.getReiseGeschw();
        double aktuellerVerbrauch = this.verbrauch / this.getReiseGeschw() * km;

        this.flugStunden += aktuelleFlugzeit;
        this.kerosinVorrat -= aktuellerVerbrauch;

        return true;
    }

    public void tanken(double liter)
    {
        this.kerosinVorrat += liter;
        
        this.kerosinVorrat = this.kerosinVorrat > this.getTankKapazitaet() ? this.getTankKapazitaet() : this.kerosinVorrat < 0 ? 0 : this.kerosinVorrat;
    }

    public boolean laden(double kg)
    {
        if (this.getMaxGewicht() - this.getGesamtGewicht() < kg)
        {
            return false;
        }

        this.ladungGewicht += kg;
        
        if (this.ladungGewicht < 0)
        {
            this.ladungGewicht = 0;
        }

        return true;
    }

    public void info()
    {
        System.out.println("Flugstunden: " + this.getFlugStunden());
        System.out.println("Tankinhalt: " + this.getKerosinVorrat());
        System.out.println("Gesamtgewicht: " + this.getGesamtGewicht());
    }

    public static void main(String[] args)
    {
        Flugzeug albatros = new Flugzeug(70000, 35000, 10000, 800, 500, 2500, 25000, 8000);

        albatros.laden(1500);
        albatros.fliegen(3000);
        albatros.tanken(10000);
        albatros.fliegen(3000);
    }
}
