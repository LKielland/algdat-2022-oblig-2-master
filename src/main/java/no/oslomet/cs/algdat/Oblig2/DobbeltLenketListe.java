package no.oslomet.cs.algdat.Oblig2;


////////////////// class DobbeltLenketListe //////////////////////////////


import java.util.*;


public class DobbeltLenketListe<T> implements Liste<T> {

    /**
     * Node class
     *
     * @param <T> m  nmbbnn nbghb
     */
    private static final class Node<T> {
        private T verdi;                   // nodens verdi
        private Node<T> forrige, neste;    // pekere

        private Node(T verdi, Node<T> forrige, Node<T> neste) {
            this.verdi = verdi;
            this.forrige = forrige;
            this.neste = neste;
        }

        private Node(T verdi) {
            this(verdi, null, null);
        }
    }

    // instansvariabler
    private Node<T> hode;          // peker til den første i listen
    private Node<T> hale;          // peker til den siste i listen
    private int antall;            // antall noder i listen
    private int endringer;         // antall endringer i listen

    public DobbeltLenketListe() {

    }

    public DobbeltLenketListe(T[] a) {
        Objects.requireNonNull(a, "Tabellen a er null!");              // godtar ikke a = null; a = {} er greit.
        if (a.length > 0) {                                                     // så lenge a.length er større enn 0
            int i = 0;                                                          // initieres i
            for (; i < a.length && a[i] == null; i++);                          // teller opp i fra 0 til første verdi som ikke er null
            if (i < a.length) {                                                 // så lenge i er mindre enn lengden til tabellen
                Node<T> p = hode = new Node<>(a[i], null, null);   // setter første verdi som ikke er null til hode som første node
                antall++;                                                       // øker antall
                i++;                                                            // øker i, da første verdi som ikke er null er funnet
                for (; i < a.length; i++) {                                     // for løkke på resten av a
                    if (a[i] != null) {                                         // så lenge a[i] ikke er en null verdi
                        p.neste = new Node<>(a[i]);                             // lager en node på p sin neste
                        p.neste.forrige = p;                                    // setter peker til neste p til forrige
                        p = p.neste;                                            // gjør p lik p sin neste
                        antall++;                                               // øker antall
                    }
                }
                hale = p;                                                       // p er siste node, gjør hale lik p
                hale.forrige = p.forrige;                                       // gjør hale sin forrige lik p sin forrige
                hale.neste = null;                                              // gjør hale sin neste lik null
                endringer++;                                                    // å legge inn et array = 1 endring
            }
        }
    }

    public Liste<T> subliste(int fra, int til) {
        fratilKontroll(antall,fra,til);                                 // Kontrollerer rekkevidden

        int start = 1;                                                  // løpevariabel
        Node<T> denne = hode;                                           // Avatar-node opprettes

        if(fra <= antall/2) {                                           // Passer på å begynne på et fornuftig sted
            for(int i = 0; i < fra;i++) {                               // Leter frem "start"-noden
                denne = denne.neste;
            }
        } else {                                                        // Leter frem startnoden (fra halen)
            denne = hale;
            for(int i = antall-1; i > fra;i--) {
                denne = denne.forrige;
            }
        }
        DobbeltLenketListe<T> subListe = new DobbeltLenketListe<>();    // Oppretter subLista
        subListe.antall = 0;                                            // Setter antallet

        while(start<= til-fra) {                                        // Løper gjennom og tilegner
            subListe.leggInn(denne.verdi);
            denne = denne.neste;
            start++;
        }
        return subListe;
    }

    @Override
    public int antall() {
        return antall;                         // returnerer antall noder i listen.
    }

    @Override
    public boolean tom() {
        return antall == 0;                   // dersom antall er lik 0. så listen er tom.
    }

    @Override
    public boolean leggInn(T verdi) {
        Objects.requireNonNull(verdi, "Ikke tillatt med null-verider!");    // godtar ikke null-verdier
        if(antall == 0){                                                            // hvis antallet er 0, så er det ingen verdier i listen
            hode = hale = new Node<>(verdi, null, null);               // da blir hale lik hode lik den aller første noden
            antall++;                                                               // øker antall
            endringer++;                                                            // øker endringer
            return true;                                                            // returnerer true
        }
        hale.neste = new Node<>(verdi);                                             // hvis antallet er større enn 0 lager vi neste node etter hale
        hale.neste.forrige = hale;                                                  // setter forrige-pekeren til siste node til noden hale peker på
        hale = hale.neste;                                                          // setter hale til å peke på siste node
        antall++;                                                                   // øker antall
        endringer++;                                                                // øker endringer
        return true;

    }

    @Override
    public void leggInn(int indeks, T verdi) {
        Objects.requireNonNull(verdi, "Ikke tillatt med null-verdier!");    // godtar ikke null-verdier
        indeksKontroll(indeks, true);                                        // se Liste interface, true = antall er lovlig;
        if(antall == 0 && indeks == 0) {                                            // hvis antall er 0 og indeks er 0
            hode = hale = new Node<>(verdi, null, null);               // da blir hode lik hale lik den aller første noden
            antall++;                                                               // øker antall
            endringer++;                                                            // øker endringer
        } else if (antall > 0 && indeks == 0) {                                     // hvis antall er større enn 0 og indeks er 0, da skal verdi inn foran hode og bli nytt hode
            Node<T> p = hode;                                                       // lagrer hode i en p variabel
            hode = new Node<> (verdi, null, p);                              // gjør hode lik verdi som peker til forrige som er null, og neste som er p
            p.forrige = hode;                                                       // gjør slik at p sin forrige peker på hode
            antall++;                                                               // øker antall
            endringer++;                                                            // øker endringer
        } else if (indeks == antall) {                                              // hvis indeks er lik antall skal verdien inn bakerst
            hale.neste = new Node<>(verdi);                                         // hvis antallet er større enn 0 lager vi neste node etter hale
            hale.neste.forrige = hale;                                              // setter forrige-pekeren til siste node til noden hale peker på
            hale = hale.neste;                                                      // setter hale til å peke på siste node
            antall++;                                                               // øker antall
            endringer++;                                                            // øker endringer
        } else {                                                                    // ellers befinner indeks seg et sted midt inne i lista
            Node<T> p = hode;
            if(indeks < antall/2) {                                                 // finner ut om indeks er mindre enn antall / 2, hvis mindre, traverser fra hode
                for(int i = 1; i < indeks; i++) {                                   // for løkke løper gjennom fra hode
                    p = p.neste;                                                    // p er lik p sin neste node opp til indeks - 1, da er p.neste hvor verdi skal inn
                }
                Node<T> q = p.neste;                                                // lagrer noden p.neste i q, for å ta vare på noden p.neste
                p.neste = new Node<>(verdi);                                        // lager en node på p sin neste, fordi p.neste = indeks
                p.neste.forrige = p;                                                // setter peker til den nye noden til p sin forrige
                p = p.neste;                                                        // gjør p lik p sin neste, som er den nye noden
                p.neste = q;                                                        // setter p sin neste lik q
                q.forrige = p;                                                      // setter q sin forrige lik p
            } else {                                                                // hvis indeks er større enn antall / 2, traverser fra hale
                p = hale;                                                           // gjør p lik hale
                for(int i = antall-1; i > indeks; i--) {                            // for løkke løper gjennom fra hale fra antall-1 ned til indeks+1
                    p = p.forrige;                                                  // p er lik p sin forrige node opp til ned til indeks+1, da er p.forrige = indeks
                }
                Node<T> q = p.forrige;                                              // lagrer noden p.neste i q, for å ta vare på p.neste
                p.forrige = new Node<>(verdi);                                      // lager en node på p sin forrige, da p.forrige = indeks
                p.forrige.neste = p;                                                // setter peker til den nye noden til p sin neste
                p = p.forrige;                                                      // gjør p lik p sin forrige
                p.forrige = q;                                                      // setter p sin forrige lik q
                q.neste = p;                                                        // setter q sin neste lik p
            }
            antall++;                                                               // øker antall
            endringer++;                                                            // øker endringer
        }
    }

    @Override
    public boolean inneholder(T verdi) {
        return indeksTil(verdi) != -1;                  // kaller indeksTil() metoden og returnerer true
    }                                                   // dersom verdien finnes i listen/metoden ikke returnerer -1

    @Override
    public T hent(int indeks) {
        indeksKontroll(indeks , false);
        return finnNode(indeks).verdi;
    }

    @Override
    public int indeksTil(T verdi) {
        Node<T> node=hode;                              // lagrer hode i node og itererer ved å bruke node.neste gjennom listen
        int indeks=-1;                                  // initieres returnvariabel(indeks) til -1
        boolean funnet=false;                           // initieres variabel funnet til false
        while (node!=null && verdi!=null){              // sålenge node ikke null og input verdi ikke null lopper gjennom listen
            if (verdi.equals(node.verdi)){              // hvis verdi er lik node.verdi. Da har vi funnet verdi i listen.
                indeks++;                               // oppdaterer indeks variabelen
                funnet=true;                            // oppdaterer variabelen funnet til true
                break;                                  // verdi er funnet i listen- avslutter
            }
            node = node.neste;                          // sålenge verdi ikke funnet i listen og node ikke null, itererer gjennom listen med node.neste.
            indeks++;                                   // indeks med økes for hver iterasjon
        }
        if (funnet) {                                   // hvis verdi er funnet i listen, returnerer vi indeks
            return indeks;
        } else {
            return -1;                                  // hvis verdi ikke funnet i listen, returnerer vi -1
        }
    }

    @Override
    public T oppdater(int indeks, T nyverdi) {
        indeksKontroll(indeks , true);                                           // Denne skal sjekke om indeks er "innenfor"
        Objects.requireNonNull(nyverdi, "Ikke tillatt med null-verider!");

        if(indeks >= antall) {                                                          // Men det funker ikke helt slik jeg forventer, så denne er også her
            throw new IndexOutOfBoundsException("Indeksen " + indeks + " er for stor for lista");
        }

        Node<T> denne = hode;                                                           // Oppretter avatar-Node
        if(indeks < antall/2){                                                          // Sørger for at søket begynner i riktig ende av lista
            for(int i = 0; i < indeks; i++) {                                           // Cruiser gjennom
                denne = denne.neste;
            }
            T temp = denne.verdi;                                                       // Verdien som skal oppdateres lagres i temp
            denne.verdi = nyverdi;                                                      // NyVerdi settes inn
            endringer++;
            return temp;                                                                // Temp returneres
        } else {                                                                        // Her skjer det samme men fra halen og nedover
            denne = hale;
            for(int i = antall -1; i > indeks;i--) {
                denne = denne.forrige;
            }
            T temp = denne.verdi;
            denne.verdi = nyverdi;
            endringer++;
            return temp;
        }
    }

    @Override
    public boolean fjern(T verdi) {
        Node<T> node;

        boolean hodet = false;                                      // Oppretter to boolske variabler som settes dersom verdi befinner seg i hodet eller halen
        boolean halen = false;

        node = hode;                                                // Avatar-node opprettes
        node.forrige = null;                                        // Vasker opp litt

        boolean funnet = false;                                     // Variabel som settes dersom "verdi" blir funnet

        while (node!=null && verdi!=null){                          // Går gjennom lista å leter etter "verdi"
            if (verdi.equals(node.verdi)){
                funnet=true;
                if(node.forrige == null) {                          // Hodet og halen settes
                    hodet = true;
                }
                if (node.neste == null) {
                    halen = true;
                }
                break;
            }
            node = node.neste;
        }

        if (funnet) {
            if(hodet && halen) {                                    // Her følger en rekke if'er som skal sørge for at korrekt serie gjennomføres
                node = null;                                        // Dersom verdi er både hodet og halen er det bare å tilintetgjøree
                antall--;
                endringer++;
                return true;
            } else if(hodet) {                                      // Dersom verdi er hodet må hodet flyttes til .neste
                hode = node.neste;
                hode.forrige = null;
                antall--;
                endringer++;
                return true;
            } else if (halen) {                                     // Dersom verdi er halen må halen flyttes til .forrige
                hale = node.forrige;
                hale.neste = null;
                antall--;
                endringer++;
                return true;
            } else {                                                // Hvis verdi hverken er i hodet eller halen må pekerne rundt verdi endres
                node.forrige.neste = node.neste;
                node.neste.forrige = node.forrige;
                antall--;
                endringer++;
                return true;
            }
        }
        return false;                                               // Dette skjer bare dersom verdien ikke ble funnet
    }

    @Override
    public T fjern(int indeks) {
        indeksKontroll(indeks, false);                      // Sjekker om indeks er gyldig
        T temp;

        if (indeks == 0) {
            temp = hode.verdi;                                      // Lagrer verdien som skal fjernes
            hode = hode.neste;                                      // Hode flyttes

            if (antall == 1) {
                hale = hode;
            }
        } else {
            Node<T> forran = finnNode(indeks - 1);           // "forran" er noden foran den som skal fjernes
            Node<T> gjeldende = forran.neste;                       // "gjeldende" skal fjernes

            temp = gjeldende.verdi;                                 // tar vare på verdien som skal fjernes

            if (gjeldende == hale) {
                hale = forran;
                hale.forrige = forran.forrige;
            } else {
                Node<T> etter = gjeldende.neste;                    // "etter" er noden som kommer etter den som skal fjernes
                etter.forrige = forran;
            }
            forran.neste = gjeldende.neste;                         // "hopper over"
            //forran.forrige = forran;
        }
        antall--;                                                   // reduserer antallet
        endringer++;
        return temp;                                                // returner fjernet verdi
    }

    @Override
    public void nullstill() {

        // måte 1
        Node<T> node1=hode, node2;            // oppretter node1 og node2. lagrer hode i noede1
        while (node1!=null){                  // sålenge node1 ikke null, utføres følgene
            node2=node1.neste;                // looper gjennom listen og lagrer noden som skal slettes i node2.
            node1.neste=null;                 // nuller node1 sin neste peker
            node1.forrige=null;               // nuller node1 sin forrige peker
            node1.verdi=null;                 // nuller node1 sin verdi
            node1= node2;                     // oppdaterer node1 til node2
            endringer++;                      // øker endringer hver gang vi sletter node
        }
        antall=0;                             // ettersom alle noder er slettet antall er lik null og hode er lik hale.

        /* måte 2
        for (int i=antall-1; i>=0;i--){
               fjern(i);
               } */
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();      // bruker stringBuilder
        s.append("[");                              // starter med  [
        if(antall == 0) {                           // så lenge antallet er 0
            s.append("]");                          // slenger vi bare på en til ]
            return s.toString();                    // returnerer
        }
        Node<T> p = hode;                           // hvis antallet er mer enn 0 lager vi en p variabel til å traversere fra hode til halen
        s.append(p.verdi);                          // legger til den første verdien
        for(int i = 1; i < antall; i++) {           // bruker en for-løkke for å traversere og hente ut resten, i = 1, fordi s fikk første verdi før for løkka
            p = p.neste;                            // går videre til neste
            s.append(", ").append(p.verdi);         // legger til , og neste verdi
        }
        s.append("]");                              // etter for løkken legges ] til
        return s.toString();                        // returnerer
    }

    public String omvendtString() {
        StringBuilder s = new StringBuilder();      // bruker stringBuilder
        s.append("[");                              // starter med  [
        if(antall == 0) {                           // så lenge antallet er 0
            s.append("]");                          // slenger vi bare på en til ]
            return s.toString();                    // returnerer
        }
        Node<T> p = hale;                           // hvis antallet er mer enn 0 lager vi en p variabel til å traversere fra halen til hode
        s.append(p.verdi);                          // legger til verdien i halen
        for(int i = antall-1; i > 0; i--) {         // for-løkke for å hente ut resten, begynner på antall-1, siden s fikk første(siste) verdi før for-løkka
            p = p.forrige;                          // går videre til den forrige
            s.append(", ").append(p.verdi);         // legger til , og forrige verdi
        }
        s.append("]");                              // etter for løkken legges ] til
        return s.toString();                        // returnerer
    }

    public Node<T> finnNode(int indeks){
        Node<T> denne = hode;                           // Oppretter avatar-node

        if (indeks < antall/2) {                        // Sørger for at letingen begynner i en fornuftig ende av lista
            int teller = 0;
            while(indeks > teller) {                    // Trasker gjennom lista til korrekt indeks er nådd
                denne = denne.neste;
                teller++;
            }
            return denne;
        }  else {
            denne = hale;                               // Gjør det samme som over men fra halen
            int teller = antall -1;
            while(indeks < teller) {
                denne = denne.forrige;
                teller--;
            }
            return denne;
        }
    }

    public static void fratilKontroll(int antall, int fra, int til)
    {
        if (fra < 0)                                                // fra er negativ
            throw new IndexOutOfBoundsException
                    ("fra(" + fra + ") er negativ!");

        if (til > antall)                                           // til er utenfor tabellen
            throw new IndexOutOfBoundsException
                    ("til(" + til + ") > antall(" + antall + ")");

        if (fra > til)                                              // fra er større enn til
            throw new IllegalArgumentException
                    ("fra(" + fra + ") > til(" + til + ") - illegalt intervall!");
    }

    @Override
    public Iterator<T> iterator() {                                                    // klassisk iterator returnerer DobbeltLenkeListeIterator()
        return new DobbeltLenketListeIterator();
    }

    public Iterator<T> iterator(int indeks) {                                          // iterator med indeks input
        indeksKontroll(indeks, false);
        return new DobbeltLenketListeIterator(indeks);
    }

    private class DobbeltLenketListeIterator implements Iterator<T> {                   // intern klasse dobbeltLenkeListeIterator som implementerer interfacet Iterator
        private Node<T> denne;
        private boolean fjernOK;
        private int iteratorendringer;

        private DobbeltLenketListeIterator() {
            denne = hode;                                                               // p starter på den første i listen
            fjernOK = false;                                                            // blir sann når next() kalles
            iteratorendringer = endringer;                                              // teller endringer
        }

        private DobbeltLenketListeIterator(int indeks) {
            if (indeks == 0) {                                                          // hvis indeks = 0
                denne = hode;                                                           // er denne lik hode
            } else {
                Node<T> p = hode;                                                       // lager en p verdi til å traversere
                if (indeks < antall / 2) {                                              // hvis indeks er mindre enn halvparten av antallet
                    for (int i = 1; i < indeks; i++) {                                  // løper vi gjennom fra hode
                        p = p.neste;                                                    // p er lik p sin neste node opp til indeks - 1, da skal denne være lik p.neste
                    }
                } else {
                    p = hale;                                                           // hvis indeks er større enn halvparten av antellet, er p lik hale
                    for (int i = antall-1; i > indeks; i--) {                           // for løkke løper gjennom fra hale
                        p = p.forrige;                                                  // p er lik p sin forrige node ned til indeks +1
                    }
                    p = p.forrige;                                                      // for at det skal passe med denne = p.neste, så må p her være  p = p.forrige
                }
                denne = p.neste;                                                        // gjør denne om til p.neste, som er indeks
                fjernOK = false;                                                        // blir sann når next() kalles
                iteratorendringer = endringer;                                          // gjør iteratorendringer lik endringer
            }
        }


        @Override
        public boolean hasNext() {
            return denne != null;
        }

        @Override
        public T next() {
            if(iteratorendringer != endringer) {                                        // hvis iteratorendringer er ulik endringer
                throw new ConcurrentModificationException("Listen er endret!");         // kaster unntak
            }
            if(!hasNext()) {                                                            // hvis hasNext er false
                throw new NoSuchElementException("Ingen verdier!");                     // kaster unntak
            }
            fjernOK = true;                                                             // fjernOK endres til true
            T p = denne.verdi;                                                          // lagrer denne sin verdi
            denne = denne.neste;                                                        // går videre til denne sin neste

            return p;                                                                   // returnerer denne sin verdi
        }

        @Override
        public void remove() {
            if(!fjernOK) {throw new IllegalStateException("Du kan ikke fjerne denne Noden. Kanskje den ikke eksisterer..?");}
            if(endringer != iteratorendringer) {throw new ConcurrentModificationException("Endringer: '" +endringer+ "', og iteratorendringer: '" +iteratorendringer+ "', matcher ikke.");}
            if(antall == 0) { throw new IllegalStateException("Man kan ikke fjerne en node i en tom liste. Antall: '" +antall+ "'.");}

            fjernOK = false;

            if(antall == 1) {                                                           // Etter å ha kjørt gjennom en serie med tester or å avgjøre om
                hode = hale = null;                                                     // remove kan kjøre kommer en liten rekke
            } else if(denne == null) {                                                  // if'er som har som jobb å sørge for at de korrekte
                hale = hale.forrige;                                                    // referansene blir satt
                hale.neste = null;
            } else if(denne.forrige == hode) {
                hode = denne;
                hode.forrige = null;
            } else {
                denne.forrige = denne.forrige.forrige;
                denne.forrige.neste = denne;
            }
            antall--;                                                                   // tilslutt oppdateres variablene
            endringer++;
            iteratorendringer++;
        }

    } // class DobbeltLenketListeIterator

    public static <T> void sorter(Liste<T> liste, Comparator<? super T> c) {
        // bruker en klassisk insertion sort algoritme                          Sammenlikner verdier på plassen etter med plassen før og bytter hvis plassen før er større
        for(int i = 1; i < liste.antall(); i++) {                               // ytre for løkke, i starter på 1, opp til liste.antall()
            T v1 = liste.hent(i);                                               // lagrer verdien på plass i, i variabelen v1
            int j = i - 1;                                                      // initierer j som i - 1, (i = 1, j = 0), (i = 2, j = 1)
            for(; j >= 0 && c.compare(v1,liste.hent(j)) < 0; j--) {             // indre for-løkke kjører bare hvis j er større enn eller lik 0, og hvis v1 er mindre enn verdien på plass j
                T v2 = liste.hent(j);                                           // lagrer verdien på plass j, i variabelen v2
                liste.oppdater(j+1,v2);                                  // oppdaterer plass j+1 til få verdien v2 (j+1 er ofte i, men ikke alltid)
            }
            liste.oppdater(j+1, v1);                                     // oppdaterer plass j+1 til å få verdien v1 (Husk at her har vi gjort j-- i den indre for-løkka)
        }                                                                      // legg merke til c.compare(v1,liste.hent(j)) < 0 i den indre for løkka:
    }                                                                          // den må hente verdien der inne i sammenlikningen og ikke utenfor, foran for løkka
                                                                               // da .hent(j) endrer seg fra iterasjon til iterasjon.
} // class DobbeltLenketListe


