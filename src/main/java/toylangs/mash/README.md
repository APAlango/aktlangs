# Hulgateoreetiline MASH

Mash on lihtne imperatiivne keel, mis võimaldab peale aritmeetiliste ja loogiliste operatsioonide ka
mõndasid tehteid `mush` andmestruktuuri peal.
`Mush` on selle keele andmestruktuur, mis on midagi Java'st tuntud ArrayListi ja Array vahepealset.
`Mush`i suurus on dünaamiline ja nendele saab rakendada hulgateoreetilisi tehteid nagu ühisosa-, ühendi- ja vahe leidmist.

---

Mash'i poolt lubatud operatsioonid `mush`idel näevad välja järgmised.
```
let mush1 = [1,2,3];
mas1 add 4;
let mush2 = [3,4,5,6];

let ühisosa = mush1 intersect mush2;    # [3,4]
let ühend = mush1 union mush2;          # [1,2,3,4,5,6]
let vahe = mush1 except mush2;          # [1,2]
let kokkuliidetud = mas1 mash mas2;     # [1,2,3,4,3,4,5,6]
```
Nagu näites näha on, on `intersect`, `union` ja `except` väljund justkui hulk, s.t. korduvaid elemente pole.  
Lisaks neile on ka operatsioonid `mash` ja `add` mida on täpsemalt kirjeldatud allpool. 

---

## Süntaks
### Literaalid
Mash keeles leidub kahte tüüpi literaale:
* Sõneliteraalid `"saabas"`
* Täisarvuliteraalid `544845`
### Tõeväärtused
Tõeväärtused Mash'is on klassikalise tähendusega `true` ja `false`.
### Muutujad
Et võimaldada keerukate probleemide lahendamist, saab defineerida muutujaid. Mash kasutab dünaamilist tüübikontrolli.
Omistamine käib tavapärasel viisil võrdusmärgiga `=`. Muutujad võivad endas hoida kas literaale või massiive,  
kuid _EI TOHI_ hoida endas tühiväärtusi. Et vältida tühiväärtuste omistamist muutujatele, visatakse kompileerimise ajal erind.
Et defineerida uut muutujat, tuleb kasutada võtmesõna `let`. Kui üritada omistada väärtust muutujale, mida pole veel defineeritud `let` võtmesõnaga, siis visatakse samuti erind.
```
let arv = 42;
```
### Massiivid
Mash keele massiivid on ühedimensioonilised ja kõik elemendid on sama literaalitüübiga.  
Massiivide elemendid ei pea olema unikaalsed, need on indekseeritud ja esimese elemendi indeks on 0.  
Et kätte saada mingit `mush` elementi kindlal indeksil, saab kasutada binaarset operatsiooni `<mush> grab <indeks>`,
mille tagastuseks on literaal.
Literaalide lisamiseks massiivi saab kasutada binaarset operatsiooni `add`.  
Selle operatsiooni tagastus on tühiväärtus, kuid lisamise tulemus kajastub massiivil, millel seda rakendati.
Näiteks:
```
let a = 10;     
let mush = [];          # Defineerime ja algväärtustame tühja mushi
mush add 1;             # mush sisuks on [1]
mush add mush grab 0;   # mush sisuks on nüüd [1,1], korduvad elemendid on lubatud
let x = mas add 2       # Visatakse erind, kuna 'mush add 2' tagastab tühiväärtuse ja tühiväärtused pole lubatud muutujates.
```
### Tehted massiividel
Mash võimaldab viite binaarset operatsiooni `mush`ide manipuleerimiseks: `intersect`, `union`, `except`, `mash` ja `add`.  
Nelja esimese operatsiooni tagastuseks on uus `mush`, ja viimase operatsiooni `add` tagastus on tühiväärtus, mida ei 
tohi muutujatele omistada ega kasutada binaartehetes literaalidega. See 
lisab lihtsalt literaali antud `mush`i viimaseks elemendiks.
```
let mush1 = [1,2,3];
mush1 add 4;
let mush2 = [3,4,5,6];

let ühisosa = mush1 intersect mush2;  # [3,4]
let ühend = mush1 union mush2;        # [1,2,3,4,5,6]
let vahe = mush1 except mush2;        # [1,2]
let kokkuliidetud = mush1 mash mush2; # [1,2,3,4,3,4,5,6]
```
### Sisseehitatud meetodid
Mash võimaldab väärtusi standardväljastusse printida meetodiga `print(<avaldis>)`.  
Sulgudes oleva avaldise väärtus prinditakse välja. See väärtus võib olla sõneliteraal, arvuliteraal või `mush`.
### Teised keelekonstruktsioonid
Mash programmis saab kasutada sarnaselt paljude teiste keeltega `if` ja `while` konstruktsioone.  
#### if - elseif - else
`if` lause sisu täidetakse juhul, kui mingi tingimus on tõene. Kui esimene tingimus on väär, saab laskuda teise harusse, mis on valikulise lause `elseif` taga.  
Peale määramata arvu `elseif` harusid saab viimaks ka kirja panna `else` haru, millel pole tingimust, ja mis täidetakse siis, kui eelnevad tingimused pole tõesed olnud.  
`if` lause termineerib võtmesõna `endif`. 
```
let tv = 3;
if(tv == 1)                             # Tingimus on väär
    print("Ma olen siin!");
elseif(tv == 2)                         # Tingimus on väär
    print("Ma olen hoopis siin!");
else                                    # Täidetakse else haru keha
    print("Ma olin tegelikult siin..");
endif;
```
#### while
Tsüklid on Mash'is võimalikud tänu `while` konstruktsioonile.  
Tsükli keha täidetakse, kui tingimus on tõene, ning peale keha läbimist kontrollitakse uuesti tingimuse kehtivust.
`while` konstruktsiooni termineerib võtmesõna `endwhile`.
```
let i = 1;
while(i < 3)
    i = i + 1;
endwhile;
```
### Aritmeetilised tehted arvudel
Peale unaarse miinuse `-` on Mash võimalike tehete hulgas palju binaarseid tehteid:
* Liitmine `+`
* Lahutamine `-`
* Jagamine `/`
* Korrutamine `*`
* Astendamine `**`
### Loogilised tehted tõeväärtustel
Binaarsed loogilised tehted:
* Konjunktsioon `&`
* Disjunktsioon `|`
  
Peale nende on ka eitus `!`, mis on unaarne tehe ja mis vastandab hüüumärgile järgneva avaldise väärtuse.
### Loogilised tehted literaalidel
_Järgnevates näidetes on üheselt mõistetavuse huvides kõikide avaldiste väärtused tõesed._

Võrdlustehted, mida saab rakendada vaid arvuliteraalidel ja muutujatel, mis hoiavad arvuliteraale:
* `4 > 2`
* `1 < 3`
Võrdlustehted, mida saab rakendada nii arvude kui sõnede puhul:
* `"aabits" == "aabits"` või `1 == 1`
* `"aabits" <> "aaBits"` või `1 <> 2`