package no.oslomet.cs.algdat.Oblig2;


////////////////// class DobbeltLenketListe //////////////////////////////


import java.util.Comparator;
import java.util.Iterator;
import java.util.Objects;


public class DobbeltLenketListe<T> implements Liste<T> {

    /**
     * Node class
     *
     * @param <T>
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
        Objects.requireNonNull(a, "Tabellen a er null!");               // godtar ikke a = null; a = {} er greit.
        if (a.length > 0) {                                                     // så lenge a.length er større enn 0
            int i = 0;                                                          // initieres i
            for (; i < a.length && a[i] == null; i++);                          // teller opp i fra 0 til første verdi som ikke er null
            if (i < a.length) {                                                 // så lenge i er mindre enn lengden til tabellen
                Node<T> p = hode = new Node<>(a[i], null, null);    // setter første verdi som ikke er null til hode som første node
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
        throw new UnsupportedOperationException();
    }

    @Override
    public int antall() {
        return antall;
    }

    @Override
    public boolean tom() {
        if (antall==0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean leggInn(T verdi) {
        Objects.requireNonNull(verdi, "Ikke tillatt med null-verider!");    // godtar ikke null-verdier
        if(antall == 0){                                                            // hvis antallet er 0, så er det ingen verdier i listen
            hode = hale = new Node<>(verdi, null, null);                // da blir hade lik hode lik den aller første noden
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
        // TODO: Her kommer det straks kode, sliter fortsatt litt med p.neste, og p.forrige og q.neste og q.forrige
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean inneholder(T verdi) {
        throw new UnsupportedOperationException();
    }

    @Override
    public T hent(int indeks) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int indeksTil(T verdi) {
        throw new UnsupportedOperationException();
    }

    @Override
    public T oppdater(int indeks, T nyverdi) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean fjern(T verdi) {
        throw new UnsupportedOperationException();
    }

    @Override
    public T fjern(int indeks) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void nullstill() {
        throw new UnsupportedOperationException();
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
        for(int i = 1; i < antall; i++) {           // bruker en for-løkke for å traversere og hente ut resten
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
        for(int i = antall-1; i > 0; i--) {         // begynner på antall-1, siden antall er det sammen som length
            p = p.forrige;                          // går videre til den forrige
            s.append(", ").append(p.verdi);         // legger til , og forrige verdi
        }
        s.append("]");                              // etter for løkken legges ] til
        return s.toString();                        // returnerer
    }

    @Override
    public Iterator<T> iterator() {
        throw new UnsupportedOperationException();
    }

    public Iterator<T> iterator(int indeks) {
        throw new UnsupportedOperationException();
    }

    public Node<T> finnNode(int indeks){
        Node<T> denne = hode;

        if (indeks < antall/2) {
            int teller = 0;
            while(indeks > teller) {
                denne = denne.neste;
                teller++;
            }
            return denne;
        } else {
            denne = hale;
            int teller = antall;
            while(indeks < teller) {
                denne = denne.forrige;
                teller--;
            }
            return denne;
        }
    }


    private class DobbeltLenketListeIterator implements Iterator<T> {
        private Node<T> denne;
        private boolean fjernOK;
        private int iteratorendringer;

        private DobbeltLenketListeIterator() {
            denne = hode;     // p starter på den første i listen
            fjernOK = false;  // blir sann når next() kalles
            iteratorendringer = endringer;  // teller endringer
        }

        private DobbeltLenketListeIterator(int indeks) {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean hasNext() {
            return denne != null;
        }

        @Override
        public T next() {
            throw new UnsupportedOperationException();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

    } // class DobbeltLenketListeIterator

    public static <T> void sorter(Liste<T> liste, Comparator<? super T> c) {
        throw new UnsupportedOperationException();
    }

} // class DobbeltLenketListe


