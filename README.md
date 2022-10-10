# Obligatorisk oppgave 2 i Algoritmer og Datastrukturer

Denne oppgaven er en innlevering i Algoritmer og Datastrukturer. 
Oppgaven er levert av følgende studenter:

* Desale Okbastion Tekle, s322511, s322511@oslomet.no
* Pål André Holdahl, s147098, s147098@oslomet.no
* Leif Esten Grimstad Kielland, s362057, s362057@oslomet.no

# Arbeidsfordeling

I oppgaven har vi hatt følgende arbeidsfordeling:
* Desale har hatt hovedansvar for oppgave 1, 4, og 7. 
* Pål har hatt hovedansvar for oppgave 2, 5, og 8. 
* Leif har hatt hovedansvar for oppgave 3, 6, 9. 
* Vi har i fellesskap løst oppgave 10. 

# Oppgavebeskrivelse

I oppgave 1 så gikk vi frem ved å ...

I oppgave 2 a) lagde vi en toString() metode som tar i bruk StringBuilder. Den sjekker først om antallet er lik 0, 
i så fall returnerer den bare "[]". Ellers så initierer den en p-variabel til å traversere, legger inn den første verdien,
og henter ut resten gjennom en for-løkka. omvendtString fungerer på akkurat samme måte, bare at den henter ut 
baklengs, da vil for-løkken starte på antall-1 og traverse nedover i stedet for oppover.

I oppgave 2 b) la vi først inn en Objects.requireNonNull for å sjekke at verdien som skal inn ikke er null. Deretter har 
vi en if sjekk på om antallet i lista er 0, i så fall blir dette den første verdien, og hale skal være lik hode som skal peke
på denne noden, og forrige og neste pekerene blir så satt til å peke på null. Deretter økes antall og endringer. Hvis antall
er mer enn null, så skal verdi legges inn bakerst. Da er det hale.neste som blir den nye noden, deretter settes pekerne slik
at den nye noden sin forrige peker på halen sin node, at hale sin node peker på den nye noden, og at halen blir satt til å være
den nye noden. Antall og endringer økes.

I oppgave 5 la vi først inn en Objects.requireNonNull da den ikke godtar null-verdier. Deretter bruker vi indeksKontrollmetoden
til Liste interfacet med leggInn = true, fordi denne metoden får lov til å legge inn nye verdier. Så går metoden gjennom en 
if/else if statement for å sjekke grenseområdene. Først om antall og indeks = 0, da blir dette den første, og hale  = hode = ny node.
ellers hvis antall er større enn 0 og indeks er 0, da oppdateres hode til å inneholde den nye verdien, og pekerne ordnes deretter.
ellers, indeks er lik antallet, da skal den nye verdien legges inn bakerst, vi endrer dermed hale til å bli lik hale.neste som blir
den nye noden med den nye verdien, og ordner pekerne deretter. Hvis ingen av disse grenseområdene gjelder, da må vi traversere
gjennom lista. Vi starter med å sjekke om indeks er mindre enn antall/ 2, i så fall traverserer vi fra hodet, finner riktig posisjon
oppretter ny node og setter pekerne deretter. Hvis ikke, er indeks større enn antall/2, og da traverserer vi fra halen, finner riktig posisjon, 
oppretter ny node og setter pekerne deretter. Antall og endringer økes.

I oppgave 8 a) la vi først inn enn sjekk av itereatorendringer og endringer. Hvis disse 2 er ulike, så kastes et unntak. 
Deretter har vi lagt inn en sjekk på hasNext(), hvis denne er false, så kastes et unntak. Hvis unntakene ikke kastes, så 
endres fjernOK til true, denne sin verdi lagres i en temp variabel p, denne går videre til denne.neste, og p returneres.

I oppgave 8 b) la vi inn en return av DobbeltLenketListeIterator().

I oppgave 8 c) la vi inn en sjekk for om indeks er 0, da er i så fall denne = hode. Elles så må vi traverse, men først finner 
vi ut om indeks er mindre enn halvparten av antallet, fordi da skal vi traversere fra hode, hvis indeks er større enn halvparten
av antallet, da skal vi traversere fra halen. I for løkken som traverserer fra halen, setter vi p = p.forrige en gang til, slik
at denne = p.neste vil stemme for hale traversering.

I oppgave 8 d) la vi inn en indeksKontroll med leggInn lik false, fordi den skal ikke legge inn nye verdier ,
og returnerte DobbeltLenkeListeIterator.

I oppgave 10 bruker vi en klassisk insertion sort algoritme hvor vi sammenlikner verdien på plass i, med verdien på plass
j = i - 1. Hvis verdien på plass j er større enn verdien på plass i, så bytter vi om verdiene. Dette gjør vi så med å bruke
to for løkker, en ytre, og en indre. Vi henter ut verdiene med liste sin hent() metode, oppdaterer med liste sin oppdater() metode,
og sammenlikner verdiene med en Comparator. 
