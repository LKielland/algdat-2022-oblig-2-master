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

I oppgave 1 kaster vi nullpointerException hvis tabellen a er null.Videre initieres variabel i og teller opp i fra 0 til første verdi
som ikke er null så lenge a.length er større enn 0. Hvis vi finner verdi ikke lik null og i er mindre enn a.length, setter vi første verdi 
som ikke er null til p=hode.Da øker vi antall og i. Siden første verdi som ikke er null er funnet, brukes videre for løkke på resten av a 
så lenge verdien av a[i] ikke er null - Lager en node p.neste og setter p.neste.forrige til p og dermed p til p.neste og øker antall.Når vi 
har gått gjennom alle verdier i tabellen, p er lik hale og ble gjort en endring. I antlall() metoden returnerer vi antall, og i tom()
metoden returnerer vi true dersom antall er lik 0, ellers false.


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


I Oppgave 3 a) var en slibrig liten oppgave. Løsningen av finnNod() var grei nok, og fungerte som en fin liten 
introduksjon/oppfrisker til hvordan en dobbeltlenket liste er satt sammen. Sammen med hent() handlet det stort sett om 
å sjonglere pekerne på riktig måte også falt brikkene påplass. Oppdater() derimot! Her hadde jeg et salig strev med å få ting
til å fungere, og endte opp med å kaste inn en ekstra if(indeks >= antall) siden indekskontroll ikke fungerte som forventet.
Når jeg ser på koden nå forstår jeg ikke hva problemet var, men dette var, av en eller annen merkelig grunn, som å få en sitron
klemt inn under kraniet.

I oppgave 3 b) ble jeg sittende lenge å prøve meg frem før jeg tok til fornuft og tegnet opp hvordan jeg så for meg at metoden
ville fungere. Jeg laget en skisse som ble grunnlaget for hvordan jeg forsto flyten i metoden og deretter ble mesteparten av
tiden brukt på å feilsøke. Det er overraskende hvor mange obskure småfeil som kan oppstå i så små metoder som disse.


I oppgave 4 indeksTil() metoden: initieres variabel indeks til -1 og funnet til false. videre lager hode i node og traverserer
gjennom listen med node.neste sålenge node og verdi ikke er null. For hver gang verdien ikke finnes i listen inkrementerer vi
indeks med 1. Dersom verdien finnes i listen inkrementerer vi indeks, oppdaterer funnet til true og avslutter. Tilslutt
returnerer vi indeks dersom funnet er true, og ellers -1.

I oppgave 4 inneholder() metoden: kaller vi indeksTil() metoden. Dersom indeksTil() metoden returnerer -1, returnerer 
inneholder() metoden false, ellers true.


I oppgave 5 la vi først inn en Objects.requireNonNull da den ikke godtar null-verdier. Deretter bruker vi indeksKontrollmetoden
til Liste interfacet med leggInn = true, fordi denne metoden får lov til å legge inn nye verdier. Så går metoden gjennom en 
if/else if statement for å sjekke grenseområdene. Først om antall og indeks = 0, da blir dette den første, og hale  = hode = ny node.
ellers hvis antall er større enn 0 og indeks er 0, da oppdateres hode til å inneholde den nye verdien, og pekerne ordnes deretter.
ellers, indeks er lik antallet, da skal den nye verdien legges inn bakerst, vi endrer dermed hale til å bli lik hale.neste som blir
den nye noden med den nye verdien, og ordner pekerne deretter. Hvis ingen av disse grenseområdene gjelder, da må vi traversere
gjennom lista. Vi starter med å sjekke om indeks er mindre enn antall/ 2, i så fall traverserer vi fra hodet, finner riktig posisjon
oppretter ny node og setter pekerne deretter. Hvis ikke, er indeks større enn antall/2, og da traverserer vi fra halen, finner riktig posisjon, 
oppretter ny node og setter pekerne deretter. Antall og endringer økes.


I oppgave 6 ble en utholdenhetsprøve mer enn noe annet. Det gikk fort å stable grunnmuren påplass. Men det var stadig noe som 
ikke fungerte. Etter mye prøving og feiling med node.neste = node.forrige.forrige.neste osv. fikk jeg ideen om å opprette 
booleanverdier for hodet og halen. Dette gjorde noe med selve strukturen i koden som gjorde at det hele var fungerende få minutter
senere.


I oppgave 7 måte 1: lager vi først hode i en ny node(node1) og deklarerer node2 som brukes til å lagre node1.neste i løkken. 
Så treverserer vi gjennom listen sålenge node1 er ikke null. Da nuller vi node1.neste, node1.forrige og node1.verdi. Så
oppdaterer vi node1 til node1.neste/node2. Tilslutt endringer økes med en for hver node vi fjerner, når alle nodene er 
fjernet oppdaterer vi antall til 0.

I oppgave 7 måte2: Bruker vi for-løkke og kaller fjern() metoden. Starter å kalle fjern() metoden med indeks til hale
(antall -1) og fortsetter til indeksen til hode(0);

I oppgave 7 skulle vi velge den metoden som er mest effektiv: måte 1 er mer effektiv enn måte 2. testet metodene ved å bruke 
randperm() metode. når n er lik 10^3 ga måte 1: 1 sec while måte 2: 2 sec. når n er lik 10^5 ga måte1: 2 sec while måte2:
4 sec. Det gir også mening ettersom måte2 må først kalle fjern() metoden og videre funnNode() metoden, og dettte må gjøres
for alle noder. Mens i måte1 fjerner man hode, hode.neste også videre.


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


I oppgave 9 fikk jeg problemer med nullpointers, og brukte litt tid på å finne ut av det. Det viste seg at jeg brukte if,
og ikke else if. Jeg ville jo ikke til neste if dersom den forrige var true. Det ville jo vært bare fjas.
Deretter brukte jeg litt tid på å forstå kriteriene for at det skal være "tillatt å kalle metoden". 
Her måtte det en kopp kakao til, men så gav oppgaveteksten plutselig mening.


I oppgave 10 bruker vi en klassisk insertion sort algoritme hvor vi sammenlikner verdien på plass i, med verdien på plass
j = i - 1. Hvis verdien på plass j er større enn verdien på plass i, så bytter vi om verdiene. Dette gjør vi så med å bruke
to for løkker, en ytre, og en indre. Vi henter ut verdiene med liste sin hent() metode, oppdaterer med liste sin oppdater() metode,
og sammenlikner verdiene med en Comparator. 

Warnings:
Vi har en for statement på linje 46, som skal være slik for å telle opp til første verdi som ikke er null.
Vi 
