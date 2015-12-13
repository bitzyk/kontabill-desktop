KontabillMain
	- contine functia main
	- parsare de atribute din linia de comanda - args
	- instantiereApp

Kontabill
	- reprezinta instanta de aplicatie
	scop:
		- initializareLayout
			- instantiata structura layout initiala
				-> LAYOUT:
					- construieste layout-ul initial/structura
					    - construieste meniu
					- nu este singleton deoarece poate fi obtinut prinn Kontabill.getInstance.getLayout()
					- primeteste dependinta Kontabill app, deoarece oricand poate apela metode ce va ajuta layout-ul sa stie
					ce sa contruiasca. i.e. widget-uri pe baza controller-ului curent


		- initializare mvc
			MVC
					- nu este singleton deoarece poate fi obtinut prinn Kontabill.getInstance.getMVC()
					- primeste dependinta kontabill app, deoarece oricand poate apela metode din cadrul
					  app -> prin definitie mvc depinde de app si layout -> layoutu folosit la view. obiectul de layout este
					  incapsulat in obiectul de app
					- contine intodeauna controller curent
					- defineste controller ul initial pe baza de config
					- are metoda de setController -> controller action listener are dreptul sa creeze controller si sa il seteza in layout
					    - injecteaza in controller modelul si kontabill app
					- ii paseaza un model default controller-ului (regula de numer)
					- are zona de widget-uir
					- controllers, view si models extinnd toate baseAbstractController, baseAbstractView, baseAbstractModel

				-> Controller
					- extine mainController
						- are action default (index)
						- primeste injectat din MVC kontabill app
						- isi creeaza model-ul defauilt
							- este incapsulat in cadrul controller-ului
							- orice coontroller care extinde mainController are acces la model
						- la instantiere se creeza vie ppt actionul default
						- poate contine mai multe view-uri in cadrul lui
						- are metoda de removeAllViews, view-urile au identificator string pe baza de regula de nume (controller.action)
						- setView seteaza view in layut
				-> View
					- orice view exite MainView
						-> repaint/ setvisible este apelat automat dupa dispatch view din MainView
					- exista un view default pentru controller dar pot exista si alte view-uri pentru action-uri
					- view-ul contineu un JPnael cu comonente in cadrul lui


				http://crunchify.com/java-properties-file-how-to-read-config-properties-values-in-java/


- inchide proces la main exit - ok
- creare padding general pt controller panel - ok
- panel control add row - ok
- creare structura componente - ok
- set button different width - ok
- set border bottom thicker bottom for submit button - ok
- set border radius button - nu este builtin in java - no
- set size of font - ok

- adaugare chenar formular in control panel - ok
    - adaugare layout specific pt a usura constructia de formular

    - bug padding button
    	- solutie 1: incapsulare button intr-un panel propriu, adaugare margin pt panel - FAIL - nu se poate in clasa de button,
    		pannel-ul nu se adauga in niciun frame
    		- eliminare referentiere panel in clasa de button

    	- decuplare adugare box rigid si button in clasa de view (metoda)
    		- eliminare referentiere panel in clasa de button

    				- creare clasa ViewUtils de care folosesc toate view-urile - ok
    					- adaugare metoda addComponentPadding - ok
    						- metoda primeste tipButton care este un enum model si referinta Jpanel - nenecesar
    							- metoda adauga compoenenta apoi spatiul rigid ok

    - refactorizare addRow in controlPanel pt a adauga layoput-ul -> ! logica (instantierea de layout) nu trebuie
    plasata in view => tine de ViewLayout - ok

- apelare action controller cu state diferit butone cautare/adaugare
	- creare concept state
		- state
			- controller state -> set controller
			- action state -> invoke


Request

	requestData array asociativ (map)

	- setRequestData (key, object)
	- clearRequestData



- creare clasa formular
	-
- creare clasa formularLayout
	- primeste structura compusa
			- label
			- tip input



- creare pagina delegati - ok
	- copiere view - ok
	- refactorizare
			- butoane top meniu - ok
				- meniu activ - ok
				- butoane actiune active
				- subactiuni active

- derby tutorial


- creare repository git pt aplicatie

- redare exacta a paginii de catalog client din kontabill - pana la talbel
- create entiate client
	- creare baza de date si implementare in aplicatie
		- creare tabel clienti
- pasare controlller in constructor view (fiecare in parte)



- defineste clasa Database - singleton - ok
	- referinta connexiune - ok

- defineste creare structura tabele - fiecare tabel fisier
	- 2 metode: create table - addTestData - ok

- defineste model in delegati - ok
	- apeleaza metoda getDelegates ok
	- hidrateaza entitate - ok

- refactorizeaza model db table - ok
	- documenteaza hydrate entity din db pt java
	-  documenteaza database abstraction java
- refactorizeaza model -> aduaga base - ok



- ce intoarce metoda get delegates ?
	- referinta catre un array map cu delegati ce va fi populat cand thread ul termina executia
		- PRO:
			- simplitate la verificare referinta cand nu mai e null - populare table
		- CONTRA
			- verificarea referintei de tabel este to executata sincron


	- thread ul primeste injectat un obiect observer - ok
		- cand sql este executat toti subiectii sunt instiintati - ok
		- la setare jtabel verifica referinta de delegati - ok
			- daca este nula se inregistreaza ca subiect in obiectul observer - ok
			- daca thread-ul care a exutat sql a finalizat proceul construieste tabelul - ok

		- returneaza obiect hashmap custom cu delegati ca prop din cadrul lui ce extine hashmap - ok
		 	- implementeaza observer pattern - ok
		- instantiata thread - ok
		- ii da start - ok
		- intoarce obiect subscriebabelhamp - ok
		- paseaza referinta subscribeablehashmap to thread - ok


- butoane control tabel aliniate la dreapta - ok
- stil butoane specifice - ok

- adauga linie despartitoare intre panel controller si panel tabel - ok
- adauga multi select (folositor la stergere)




// http://stackoverflow.com/questions/18928112/create-jtable-from-arraylist-of-objects-java
// https://docs.oracle.com/javase/tutorial/uiswing/components/table.html
// https://docs.oracle.com/javase/tutorial/uiswing/components/table.html#data
// http://stackoverflow.com/questions/2257309/how-to-use-hashmap-with-jtable


- adauga coloana nr - ok

// http://stackoverflow.com/questions/7137786/how-can-i-put-a-control-in-the-jtableheader-of-a-jtable
// http://docs.oracle.com/javase/tutorial/uiswing/components/table.html#editrender
// http://stackoverflow.com/questions/4615741/jtable-clickable-column-header

- adauga check all + tooltip table header select all - ok
- normalizeaza lungime coloane -- procente - ok
- align text center in table column - ok


- sterge delegat
- do redirect generic controller - action in clasa request
- do helper function redirect for controllers
- repair request reset when selected rows in table and button add and serch form are pressed - ok

- adauga sortare randuri tabel
- adauga coloana actiuni
	-- adauga actiune sterge
- adauga bulk sterge
	-- adauga actiune editeaza


- remove ambigous id in legalEntity and entityTypes select - ok
- adauga referinta legalEntity types in obiectul legal entityes = ok
	-> updateaza select si hydrate - ok
	@ lazy loading get delegat type etity - ok
- cand stergi legalEntityTypes asigura-te ca nu stergi si referinta pt al tip (reprezentant legal, client) - ok
	-> nu sterge pe baza de legal entity id foreign key ci pe baza de cheie primara a tabelului - ok
- delete delegates - ok

/* ai ramas aici */
- adaugare delegat
	- implementare validatori pattern
		-> http://www.javaworld.com/article/2076272/java-se/validation-with-pure-java.html
		-> https://dzone.com/articles/avoiding-many-if-blocks
		-> decorator pattern for validatoprs: http://stackoverflow.com/questions/46029/data-validation-design-patterns

	draft
		-> elementInput has listern validators
			- on submit is valid
				-> call for all validators
					-> validator has inputdata as dependency - nothing more

	- afisare erori validatori
	 	- cuplat cu obiect formular deoarece in dialog window comoprtamentul trebuie sa fie acelasi

	- implementare getInputData in obiect formular

	- repara label submit button


// clasa form
	- elemente incapsulate si instantintiate
		- buton submit
	- elemente paseate la  layout form
		- adaugarea in panel


- adaugare referinta formLayout in clasa form

	- form layout trebie sa primeasca referinta form ? sua form sa primesca referinta layout ?

		triggere action input trebuie instantiate in layout
		la trigger form:validate trebuie apelat -> form referinta in layout
		form layout trebuie initializat in form -> form referinta in layout


- executa mai tarziu validare - nope la focus
- injecteaza key in constructor clasa keyListener - ok
- form validate on loose focus - ok


- implementare concreta validator required
- implementare concreta validator min length
- implementare concreta validator max length
- verificare afisare prijmma eroare a validatorului adaugat
- passare info label camp pt a avea referinta la afisarea erorilor
	- unde il instantiez ?
		- la apelare functie validate fiindca atunci sti ce element validezi
				- dezavantaj: setare inutila la fiecare apelare a functiei: validate - mixing concerns
		- la instantierea validatorului - in clasa concreta e form, fiindca acolo ai definite campurile
				- avantaj. instantiezi unde sunt definite si label-urile - cel mai aproape de sursa - pare corecct
				- cum il instantiezi ?
					- il pasezi ca variabila in cosntructor clasei de Validator Config ?
						- label ul elementului validat este cu adevarat un parametru validator config ? reprezinta o configurarare ?
							- nu! acesta primeste validator type si string de configurare pt initializarea concreta obiectuluui
							de validare
					- il pasezi ca dependinta atunci cand se creeaza concret validatorul
						- pare corect..

						- initializaarea deo formConfigu se realizeaza in clasa concreta de form sau inainte de apelarea createFormElm ?
						 	- este de un ajutor si alte situatii existenta obiectului FormConfig in clasa concreta de for, ?
						 		- foarte probabil ca da
						 	- in ce forma este incapsulat formConfig in clasa concreta de form ?
						 		- fara adaugare de overhead ar trebui refactorizat ElementDefinition type String[][]
							- !! vezi solutie mai jos, pctul 3
-----

- interfata de FormElement trebuie sa defineasca setLabel si gelLabel (setLabel reeturn this, si primeste string, get Label returneaza String si nu primeste nimic) - ok
	- implementarile concrete de FormElement trebuie sa implementeze aceste metode - ok


- form element factory nu trebuie sa primesasca doar input type ca parametru, ci FormConfig object - ok
		- formConfigObject incapsuleaza inputType si alte informatii ca label name (Open cloase principle is respected) etc ok
		- innitializarea de label se creeaza intr-un singur loc, in factory-ul de createFormElement - ok


- initializare formConfig ca hash map in clasa BaseAbstractForm - nope - obiect separat - ok
	- toate necesitatile de label pt element, type pt element se realieaza prin citirea acestei variabile map - prin citirea obiectului separat
		- initFormElementConfig
			- dependinta configurare element (apelare metoda abstracta in form) - se foloseste de consntele defininte in clasa de form - ok
			continue o singura proprietate map<string><formConfig> - ok
			- presupuune adaugarea metodelor
						- geInputTupeOfElement
						- getLabelOfElement

-----

- pasare value pentru textcontent input - implementare reala functie getValue - ok
- validare in ordinea definita per field, afisare erorii pentur prim ul validator in cazul mai multor invalidari per field - ok
- implementare concreta validator max- ok
- bug tooltip afisat la modificare formular adauga/cauta - ok - implementat prin Observer pattern ControllerJPanel - ok

- implement concrete numeric validator - ok
- implement concrete alpha-numeric validator - ok
	- adauga validatori noi la nobuletin serie buletin - ok

- implementare buton adaugare diferit de buton submit, label diferit, actiune diferita - ok
	- adaugare actiune la butoane catre controller adauugare / cautare - ok

- refactorizare tooltip in clasa formLayoutBaseAbstract - ok
	- bug validare form / loose focus - ok



- preluare valori pentru inserare in tabela
	- Strategii:
			- in clasa concreta de formaular metoda HydrateEntity cu dependinta concreta de entity - ok
				- metoda absracta in BaseAbstractForm cu dependinta Entity Base - ok
					(excelenta idee)

			- detallii implementare: - ok
						- pe baze de constante key se preiau valorile din map formElements - ok


	- foarte bine, genial! bv!

- inserare in tabel a delegatului pe baza de entitate
	- definire tabel info persoana (de avut in vedere info pers fizica/juridica = de ales strategia potrivita 1 sau 2 tabele)
	- insert sql


tabela detailLegalEntities
	- testare insert - ok
	- testare neadaugare legalEntity cand apare o exceptie in al doi-lea insert  ok

---------------------------------------

- ai ramas aici --
	- definire tabel legal entities detail - ok
		- creare dbTable - ok
	- insert legalEntitiesDetail la adaugareDelegat - ok
	- reinitializare controler -> mvc->runcController dupa aduagare delegat (afisare cursiva) - ok
	- la stergere delegt, sterge si detalii persoana - ok

	- afisare serie si numarul buletinul in tabel delegat si nu cnp - ok
		- modificare select sa includa legalEntititesDetail la getDelegat - ok

- buton submit hover background - ok
- input focus background - ok

-- ai ramas aici --
- adaugare formular la apasare key enter - behaviour adaugat in parinte layoutform - ok

	- nu se poata apela submit button din formLayout - refactorizare
			- integrare buton submit in formLayout - ok
					- definire label submit button in clasa concreta de form -ok
					- definire trigger submit function in clasa concreta de form (callback) - ok
					 		- formlayout asculta enter key si executa callback (nu mai este necesara executia directa FormLayout:validate) - ok
			- adaugare buton submit intr-un panel specific se realizeaza in afara formLayout - ok
					- getter submit button (setter-ul nu este necesar) -lk

	- tooltip form la enter defocus si afisare 2 tooltipuri - ok
			- refactorizare: FormTooltipManager class - ok
					- map ce pastreaza referitne tooltip <keyInput><tooltip> - ok
							- metodaAddTooltip(errorString) - singurul entrypoint pentru adaugare tooltip - isi sterge automat referentia anterioare de tooltip din map - ok


- principii dialog form:
	- width si height automat generat pe baza lungimii formularului (vezi pack) - ok
	- adaugarea de dialogForm unei instrante de form prin initializare setLayout obiectului de form -ok


- despartire HR intre titlu si formular, formular si butoane - ok

- metoda abstracta populate form in ForbBAseAbstract(Entity)
	- implementare concreta populate pe baza de entitate

- optiune editare multipla - ok
	- maxim 5 - ok
	- normalizare height din afara pentru vizualizare in acordeon - ok

- ai ramas aici  -> - setare titlu dialog din view (window) si titlu in Jpanel (detaliat)

- preluare entity checked la editare - similar delete - ok


- butoane actiune dialog form -> CANCEL | EDITEAZA
	- CANCEL
			-> inchide dialog - ok
			-> elibereaza memorie - ok
			-> adaugare listener la inchidere frame -> apelarea aceleiasi functionalitati ca butonul de inchdere custom - ok
	- EDIT  -> valideaza, - ok
			-> editeaza delegat
			-> inchide dialog dupa editare in db - ok
			-> semnalizeaza liniile editate - ok


- refactorizeaza adaugare linie
	- Line Object independent - ok


- edit delegat action - call controller - ok
	- bug: executie dubla action controller - ok

	 - editeza delegat db - ok
	 - eroare stergere delegat
	 - pricipiu: redirect after edit -> test if window is closed - ok


	 - close window after controller executed with success ?? se face oricum redirect ? cum main inchizi ok
	 	- work smoothly and fine

-> semnalizeaza liniile editate
	-> implementare concept data per sesisune in request object - ok
		-> la primul get se sterg - new object in request main object - ko

	- semnalizare in tabel
		table layout abstract - ok
			- set layoutStyle -ok
					- edited - ok
					- added -ok
			- concept identificator per rand - nu exista -> implementat getRowIndexAtEntity - ok


-- ai ramas aici --

- refactorizare setStyle when editedd. added in view - ok
	- implementare globala - ok
		- dependinte: request | model tabel - ok
		- setare identificator in controller pe baza de constanta - ok


- revizuieste nevioe de existenta a rowsIndexeseditead and addded ????? - ok

- setare culori edited, added - ok
- refactorizare culori de baza - ok


- editeaza delegat - ok
	- folosire  acelasi obiect de form - ok
	- diralog window - afisare form in dialog - centrat ok

Reguli JTable:
	- butoane deasupra tabelului ok
	- editarea deschde dialog window - ok
	- stergerea deshide alert window si permite bulk - ok



- split screen bug tooltip
- multiple tooltip bug
	- on focus tooltip with errors is shown only one at a time


- setare titlu dialog din view (window) si titlu in Jpanel (detaliat) - ok


- implementare formular search
----------------
- search delegat
	- alegere intre query in bd sau filter in tabel
		- solutie de filter in tabel optima doar daca toate entitatile se regasec in tabel
			- acesta nu va fi cazul la listare facturi, unde nu vor fi afisate in mod default toate facturile
				- cautarea se va initia mai intai printr-un query in d.b.


- table filter input activat cand thread ul de populare a tabelului a fost finalizat
- default row filter

entiatati:
	delegat
	reprezentatn legal
		- filter default dupa numer

	client - filter default dupa nume

	produs
		- filter automat dupa nume

delegat, reprezentanti si clienti - poate fi folosita baza legalEntity in sorter
problema: identifier pt produs poate diferi, nu trebuie cuplat cu baza legalEntities in niciun fel

solutie1:
	- setare getMainIdentifier in baza comuna (Entity)
		- problema
			- obliga toate entitatile sa implementeze
				- pot exista entiati pt care aceasta metoda nu are o logica imediata -> care este mainIdentifier pentru o factura
			- obliga o noua proprietate care se foloseste doar pt un comportament de filtrare

solutie2:
	- entitatile ce sunt filterable implementeza filterable
		- api-ul filterable are o metoda getFiletrableEntityValue
	- entitatile implementeaza metoda ce returneaza un Object
		- ele pot grupa un return a mai mult proprietati sau sub-obiecte (object/string)


- orice table are un obiect default tableRowSorter
		- extine TableRowSorter
	- orice TableRowSorter are un obiect default RowFilter
		- extinde RowFilter
			- orice RowFilter are o proprietate filterTerm ce semnifica identificatorul pe baza careia se va produce filtrarea
			- proprietatea are setter


- singura comunicare din view cu tabelul pt filtrare se va produce intr-un key listener la un event de key pressed
	- se va seta filterTerm
	- se va initaliza tabel::filter (functie custom)
		- tabel::filter va triggerui refilter tabel



- reguli default filter - ok
		filtrare inceputuri parti de cuvant
		ex; Andrei Anton / term: andre / true
						 / term: anto / true
						 / tem: ton / false
						 / term: andrei anto / true
						 / term: andrei anton / true
						 / term: ndrei an / false


-------------------------

-- ai ramas aici --

- lista entitati (afisare in table) - selectie initial ordonata

- dupa adaugare delegat refocusare pe primul input (in reqeust nou) - ok

- afisare tooltip eroare doar cand looseFocus se produce cu refocusare catre alt form element - ok
	- altfel tooltip cu eroare se poate afisa cand spre ex un text input isi pierde focusul in favoare unui checkbox extern formularului -ok


- reprezentati legali (functionalitate similara delegati )
	- refactorizare view
	- refactorizare model


- Learn builder pattern


Buttons to JTbale

// http://stackoverflow.com/questions/13833688/adding-jbutton-to-jtable  // ciudat ca nu merge


//        TableCellRenderer tableCellRenderer = new TableCellRenderer() {
//            @Override public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
//                JButton button = (JButton)value;
//                return button;
//            }
//        };


libraries:
	- Layout for buildinf forms
		MigLayout -> solution -> http://stackoverflow.com/questions/1832432/which-swing-layouts-do-you-recommend
		- http://www.miglayout.com/  -> tuttorial: http://www.miglayout.com/QuickStart.pdf




