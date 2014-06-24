; *** Inno Setup version 5.5.3+ Romanian messages ***
; Translator : Alexandru Bogdan Munteanu (muntealb@gmail.com)
;
; To download user-contributed translations of this file, go to:
;   http://www.jrsoftware.org/files/istrans/
;
; Note: When translating this text, do not add periods (.) to the end of
; messages that didn't have them already, because on those messages Inno
; Setup adds the periods automatically (appending a period would result in
; two periods being displayed).

[LangOptions]
; The following three entries are very important. Be sure to read and
; understand the '[LangOptions] section' topic in the help file.
LanguageName=Rom<00E2>n<0103>
LanguageID=$0418
LanguageCodePage=1250
; If the language you are translating to requires special font faces or
; sizes, uncomment any of the following entries and change them accordingly.
;DialogFontName=
DialogFontSize=10
;WelcomeFontName=Verdana
WelcomeFontSize=14
;TitleFontName=Arial
TitleFontSize=29
;CopyrightFontName=Arial
;CopyrightFontSize=8


[Messages]


; *** Application titles
SetupAppTitle=Instalare
SetupWindowTitle=Instalare - %1
UninstallAppTitle=Dezinstalare
UninstallAppFullTitle=Dezinstalare - %1


; *** Misc. common
InformationTitle=Informa�ii
ConfirmTitle=Confirmare
ErrorTitle=Eroare


; *** Common wizard text
ClickNext=Apasa�i butonul [Continuare] pentru a avansa sau butonul [Anulare] pentru a abandona.
BeveledLabel=
BrowseDialogTitle=Alegere Destina�ie
BrowseDialogLabel=Selecta�i un dosar din lista de mai jos, apoi apasa�i butonul [OK]
NewFolderName=Dosar Nou


; *** Buttons
ButtonBack=< &�ntoarcere
ButtonNext=&Continuare >
ButtonInstall=&Instalare
ButtonOK=OK
ButtonCancel=Anulare
ButtonYes=&Da
ButtonYesToAll=Da la &Tot
ButtonNo=&Nu
ButtonNoToAll=N&u la Tot
ButtonFinish=&Finalizare
ButtonBrowse=&Explorare...
ButtonWizardBrowse=Explo&rare...
ButtonNewFolder=Creea&za Dosar Nou


; *** "Welcome" wizard page
WelcomeLabel1=Bun venit la instalarea aplica�iei [name]
WelcomeLabel2=Aplica�ia [name/ver] va fi instalat� pe calculatorul dumneavoastr�.%n%nEste sugerat� �nchiderea tuturor celelalte aplica�ii �nainte de a continua.


; *** "License Agreement" wizard page
WizardLicense=Acord de Licen�iere
LicenseLabel=Urm�toarele informa�ii sunt importante. V� rug�m s� le citi�i �naite de a continua.
LicenseLabel3=Acesta este Acordul de Licen�iere. Este necesar� acceptarea termenilor acestui acord �nainte ca instalarea s� poat� continua:
LicenseAccepted=&Accept termenii
LicenseNotAccepted=&Refuz termenii


; *** "Select Destination Location" wizard page
WizardSelectDir=Selectarea dosarului de destina�ie a aplica�iei
SelectDirDesc=Unde dori�i s� fie instalat� aplica�ia [name]?
SelectDirLabel3=Aplica�ia [name] va fi instalat� �n dosarul specificat mai jos.
SelectDirBrowseLabel=Apasa�i butonul [Continuare] pentru a avansa.%nPentru alegerea un alt dosar apasa�i butonul [Explorare...]
DiskSpaceMBLabel=Este necesar un spa�iu liber de stocare de cel pu�in [mb] MB
CannotInstallToNetworkDrive=Instalarea nu poate fi realizat� de pe un dispozitiv de re�ea.
CannotInstallToUNCPath=Instalarea nu poate fi realizat� de pe o cale �n format UNC.
InvalidPath=Trebuie specificat� o cale complet�, incluz�nd litera dispozitivului; de exemplu:%n%nC:\APP%n%nsau o cale UNC de forma:%n%n\\server\share
InvalidDrive=Dispozitivul sau partajul UNC selectat nu exista sau nu este accesibil. V� rug�m s� specifica�i o alt� op�iune.
DiskSpaceWarningTitle=Spa�iu de stocare insuficient
DiskSpaceWarning=Instalarea necesita cel pu�in %1 KB de spa�iu de stocare liber, �ns� dispozitivul selectat are doar %2 KB disponibili.%n%nDori�i s� continua�i chiar �i �n aceste condi�ii?
DirNameTooLong=Numele dosarului sau al c�ii este prea lung.
InvalidDirName=Numele dosarului nu este valid.
BadDirName32=Numele dosarelor nu pot include nici unul dintre urmatoarele caractere:%n%n%1
DirExistsTitle=Dosar Existent
DirExists=Dosarul:%n%n%1%n%nexist� deja. Dori�i totu�i s� instala�i aplica�ia �n acest dosar?
DirDoesntExistTitle=Dosarul Inexistent
DirDoesntExist=Dosarul:%n%n%1%n%nnu exist�. Dori�i crearea sa?


; *** "Select Start Menu Folder" wizard page
WizardSelectProgramGroup=Selectarea sec�iunii din Meniul de Start
SelectStartMenuFolderDesc=Unde dori�i s� fie create scurt�turile programului?
SelectStartMenuFolderLabel3=Scurt�turile vor fi plasate �n sec�iunea Meniului de Start specificat� mai jos.
SelectStartMenuFolderBrowseLabel=Apasa�i butonul [Continuare] pentru a avansa.%nDaca dori�i alegerea unei alte sec�iuni apasa�i butonul [Explorare...]
MustEnterGroupName=Este necesar� specificarea numelui dosaului de destina�ie.
GroupNameTooLong=Numele dosarului sau al c�ii este prea lung.
InvalidGroupName=Numele dosarului nu este valid.
BadGroupName=Numele dosarului nu poate include nici unul dintre caracterele urmatoarele:%n%n%1
NoProgramGroupCheck2=Crearea unui dosar �n Meniul de Start nu este posibil�


; *** "Select Additional Tasks" wizard page
WizardSelectTasks=Selectarea sarcinilor suplimentare
SelectTasksDesc=Ce sarcini suplimentare dori�i s� fie �ndeplinite?
SelectTasksLabel2=Selecta�i sarcinile suplimentare care dori�i s� fie �ndeplinite �n timpul instal�rii aplica�iei [name], apoi ap�sa�i butonul [Continuare]


; *** "Ready to Install" wizard page
WizardReady=Instalarea este preg�tit�
ReadyLabel1=Toate datele necesare pentru instalarea aplica�iei [name] au fost colectate.
ReadyLabel2a=Apasa�i butonul [Instalare] pentru a avansa sau butonul [�ntoarcere] dac� dori�i revizuirea/schimbarea configura�iei de instalare.
ReadyLabel2b=Ap�sa�i butonul [Instalare] pentru a continua cu instalarea.
ReadyMemoUserInfo=Informa�ii utilizator:
ReadyMemoDir=Dosarul de destina�ie:
ReadyMemoType=Tip de instalare:
ReadyMemoComponents=Componente selectate:
ReadyMemoGroup=Sec�iunea din Meniul de Start:
ReadyMemoTasks=Sarcini suplimentare:


; *** "Installing" wizard page
WizardInstalling=Procesul de instalare se afl� �n desf�urare
InstallingLabel=V� rug�m s� a�teapta�i finalizarea instal�rii aplica�iei [name] pe calculator.


; *** "Setup Completed" wizard page
FinishedHeadingLabel=Finalizarea Instal�rii [name]
FinishedLabelNoIcons=Instalarea aplica�iei [name] s-a terminat cu succes.
FinishedLabel=Instalarea aplica�iei [name] pe calculator s-a terminat cu succes. Aplica�ia poate fi lansat� prin actionarea scurt�turilor ad�ugate.
ClickFinish=Apasa�i butonul [Finalizeaza] pentru a parasi Aplica�ia de Instalare.
FinishedRestartLabel=Pentru a termina instalarea aplica�iei [name], trebuie repornit calculatorul. Dori�i s� efectua�i aceast� opera�ie chiar acum?
FinishedRestartMessage=Pentru a termina instalarea aplica�iei [name], trebuie repornit calculatorul.%n%nDori�i s� efectua�i aceast� opera�ie chiar acum?
ShowReadmeCheck=Da, doresc afi�area paginii informative ("README")
YesRadio=&Da, reporne�te calculatorul acum
NoRadio=&Nu, voi reporni calculatorul mai trziu
; used for example as 'Run MyProg.exe'
RunEntryExec=Ruleaz� %1
; used for example as 'View Readme.txt'
RunEntryShellExec=Vezi %1


; *** SetupLdr messages
SetupLdrStartupMessage=Aplica�ia "%1" urmeaz� s� fie instalat�. Dori�i s� continua�i?
LdrCannotCreateTemp=Nu se poate crea un fi�ier temporar. Instalarea va fi abandonat�
LdrCannotExecTemp=Nu se poate executa un fi�ier din dosarul temporar. Instalarea va fi abandonat�


; *** Startup error messages
LastErrorMessage=%1.%n%nEroarea %2: %3
SetupFileMissing=Fisierul "%1" lipseste din dosarul de instalare. Se recomanda descarcarea unei noi copii a programului
SetupFileCorrupt=Fisierele de instalare sunt dezafectate. Se recomanda descarcarea unei noi copii a programului
SetupFileCorruptOrWrongVer=Fisierele de instalare sunt dezafectate sau incompatibile cu aceasta versiune a programului de instalare. Se recomanda descarcarea unei noi copii a programului
InvalidParameter=Un parametru transmis liniei de comanda este invalid:%n%n%1
SetupAlreadyRunning=Instalarea se afla deja in executie
WindowsVersionNotSupported=Acest program nu suporta versiunea de Windows care ruleaza momentan
WindowsServicePackRequired=Acest program necesita cel putin %1 Service Pack %2
NotOnThisPlatform=Acest program nu ruleaza pe %1.
OnlyOnThisPlatform=Acest program ruleaza pe %1.
OnlyOnTheseArchitectures=Acest program poate fi instalat doar pe versiuni de Windows proiectate pentru urmatoarele arhitecturi de procesor:%n%n%1
MissingWOW64APIs=Versiunea de Windows pe care o rulezi nu include functionalitatea ceruta de Instalator pentru a realiza o instalare pe 64-biti. Pentru a corecta problema, va trebui sa instalezi Service Pack %1.
WinVersionTooLowError=Acest program necesita %1 versiunea %2 sau mai noua.
WinVersionTooHighError=Acest program nu poate fi instalat pe %1 versiunea %2 sau mai noua.
AdminPrivilegesRequired=Trebuie sa fii logat ca Administrator pentru instalarea acestui program.
PowerUserPrivilegesRequired=Trebuie sa fii logat ca Administrator sau ca Membru al Grupului de Utilizatori Priceputi ("Power Users") pentru a instala acest program.
SetupAppRunningError=Instalatorul a detectat ca %1 ruleaza in acest moment.%n%n�nchide toate instantele programului respectiv, apoi clicheaza OK pentru a continua sau Anuleaza pentru a abandona instalarea.
UninstallAppRunningError=Dezinstalatorul a detectat ca %1 ruleaza n acest moment.%n%n�nchide toate instantele programului respectiv, apoi clicheaza OK pentru a continua sau Anuleaza pentru a abandona dezinstalarea.


; *** Misc. errors
ErrorCreatingDir=Dosarul "%1" nu a putu fi creat
ErrorTooManyFilesInDir=Numarul maxim de fisiere din dosarul "%1" a fost depasit


; *** Setup common messages
ExitSetupTitle=Abandonarea Instalarii
ExitSetupMessage=Instalarea aplicatiei nu este finalizata. Daca abandonati acum, programul nu va fi instalat.%n%nProgramul de instalare poate fi executat din nou la o data ulterioara pentru a completa instalarea.%n%nSigur doriti sa abandonati instalarea?
AboutSetupMenuItem=&Despre aplicatia de instalare...
AboutSetupTitle=Date despre aplicatia de instalare
AboutSetupMessage=%1 versiunea %2%n%3%n%n%1 sit:%n%4
AboutSetupNote=
TranslatorNote=


; *** "Select Language" dialog messages
SelectLanguageTitle=Selectarea Limbii Aplicatiei de Instalare
SelectLanguageLabel=Selectati limba folosita pentru instalare:


; *** "Password" wizard page
WizardPassword=Parola
PasswordLabel1=Aceasta instalare este protejata prin parola
PasswordLabel3=Completati parola, apoi apasati pe butonul [Continuare] pentru a merge mai departe. Tipul literelor din parola (Majuscule/minuscule) este semnificativ
PasswordEditLabel=&Parola:
IncorrectPassword=Parola introdusa nu este corecta. Va rugam sa mai incercati o data


; *** "Information" wizard pages
WizardInfoBefore=Informatii
InfoBeforeLabel=Citeste informatiile urmatoare nainte de a continua, snt importante.
InfoBeforeClickLabel=Cnd esti gata de a trece la Instalare, clicheaza pe Continua.
WizardInfoAfter=Informatii
InfoAfterLabel=Citeste informatiile urmatoare nainte de a continua, snt importante.
InfoAfterClickLabel=Cnd esti gata de a trece la Instalare, clicheaza pe Continua.


; *** "User Information" wizard page
WizardUserInfo=Informatii despre Utilizator
UserInfoDesc=Completeaza informatiile cerute.
UserInfoName=&Utilizator:
UserInfoOrg=&Organizatie:
UserInfoSerial=Numar de &Serie:
UserInfoNameRequired=Trebuie sa introduci un nume.


; *** "Select Components" wizard page
WizardSelectComponents=Selectarea Componentelor
SelectComponentsDesc=Care dintre componente trebuie instalate?
SelectComponentsLabel2=Selecteaza componentele de instalat; deselecteaza componentele care nu trebuie instalate. Clicheaza pe Continua pentru a merge mai departe.
FullInstallation=Instalare Completa
; if possible don't translate 'Compact' as 'Minimal' (I mean 'Minimal' in your language)
CompactInstallation=Instalare Compacta
CustomInstallation=Instalare Personalizata
NoUninstallWarningTitle=Componentele Exista
NoUninstallWarning=Instalatorul a detectat ca urmatoarele componente snt deja instalate pe calculator:%n%n%1%n%nDeselectarea lor nu le va dezinstala.%n%nVrei sa continui oricum?
ComponentSize1=%1 KB
ComponentSize2=%1 MB
ComponentsDiskSpaceMBLabel=Selectia curenta necesita cel putin [mb] MB spatiu de stocare.


; *** "Preparing to Install" wizard page
WizardPreparing=Pregatire pentru Instalare
PreparingDesc=Instalatorul pregateste instalarea [name] pe calculator.
PreviousInstallNotCompleted=Instalarea/dezinstalarea anterioara a unui program nu a fost terminata. Va trebui sa repornesti calculatorul pentru a termina operatia precedenta.%n%nDupa repornirea calculatorului, ruleaza Instalatorul din nou pentru a realiza instalarea [name].
CannotContinue=Instalarea nu poate continua. Clicheaza pe Anuleaza pentru a o nchide.
ApplicationsFound=Aplicatiile urmatoare folosesc file care trebuie actualizate de catre Instalator. Este recomandat sa permiti Instalatorului sa nchida automat aplicatiile respective.
ApplicationsFound2=Aplicatiile urmatoare folosesc file care trebuie actualizate de catre Instalator. Este recomandat sa permiti Instalatorului sa nchida automat aplicatiile respective. Dupa ce instalarea e terminata, Instalatorul va ncerca sa reporneasca aplicatiile.
CloseApplications=�nchide &automat aplicatiile
DontCloseApplications=Nu nchi&de aplicatiile
ErrorCloseApplications=Instalatorul nu a putut nchide automat toate aplicatiile. �nainte de a continua, e recomandat sa nchizi manual toate aplicatiile care folosesc file ce trebuie actualizate de Instalator.


; *** "Setup Needs the Next Disk" stuff
ChangeDiskTitle=Instalatorul Necesita Discul Urmator
SelectDiskLabel2=Introdu Discul %1 si clicheaza pe OK.%n%nDaca filele de pe acest disc pot fi gasite ntr-un alt dosar dect cel afisat mai jos, introdu calea corecta sau clicheaza pe Exploreaza.
PathLabel=&Cale:
FileNotInDir2=Fila "%1" nu poate fi gasita n "%2". Introdu discul corect sau selecteaza alt dosar.
SelectDirectoryLabel=Specifica locul discului urmator.


; *** Installation phase messages
SetupAborted=Instalarea nu a fost terminata.%n%nCorecteaza problema si apoi ruleaza Instalarea din nou.
EntryAbortRetryIgnore=Clicheaza pe Rencearca pentru a ncerca din nou, pe Ignora pentru a continua oricum, sau pe Abandoneaza pentru a anula instalarea.


; *** Installation status messages
StatusClosingApplications=Se �nchid aplica�iile...
StatusCreateDirs=Se creaz� dosarele...
StatusExtractFiles=Se copie fi�irele aplica�iei...
StatusCreateIcons=Se creaz� scurt�turile...
StatusCreateIniEntries=Se creaz� intr�rile INI...
StatusCreateRegistryEntries=Se creaz� intr�rile �n registru...
StatusRegisterFiles=Se �nregistreaz� fi�ierele...
StatusSavingUninstall=Se salveaz� informa�iile de dezinstalare...
StatusRunProgram=Se finalizeaz� instalarea...
StatusRestartingApplications=Se repornesc aplica�iile...
StatusRollback=Se restaureaz� starea ini�ial�, prin anularea modific�rilor efectuate �n timpul instal�rii...


; *** Misc. errors
ErrorInternal2=Eroare intern�: %1
ErrorFunctionFailedNoCode=%1 a e�uat
ErrorFunctionFailed=%1 a e�uat; cod %2
ErrorFunctionFailedWithMessage=%1 a e�uat; cod %2.%n%3
ErrorExecutingProgram=Nu pot executa:%n%1

; *** Registry errors
ErrorRegOpenKey=Eroare la deschiderea cheii de registru:%n%1\%2
ErrorRegCreateKey=Eroare la crearea cheii de registru:%n%1\%2
ErrorRegWriteKey=Eroare la scrierea �n cheia de registru:%n%1\%2

; *** INI errors
ErrorIniEntry=Eroare la crearea intr�rii INI �n fi�ierul "%1".

; *** File copying errors
FileAbortRetryIgnore=Clicheaza pe Rencearca pentru a ncerca din nou, pe Ignora pentru a sari aceasta fila (nerecomandat), sau pe Abandoneaza pentru a anula instalarea.
FileAbortRetryIgnore2=Clicheaza pe Rencearca pentru a ncerca din nou, pe Ignora pentru a continua oricum (nerecomandat), sau pe Abandoneaza pentru a anula instalarea.
SourceIsCorrupted=Fila sursa este stricata (corupta)
SourceDoesntExist=Fila sursa "%1" nu exista
ExistingFileReadOnly=Fila deja existenta este marcata doar-citire.%n%nClicheaza pe Rencearca pentru a nlatura atributul doar-citire si a ncerca din nou, pe Ignora pentru a sari aceasta fila, sau pe Abandoneaza pentru a anula instalarea.
ErrorReadingExistingDest=A aparut o eroare n timpul citirii filei deja existente:
FileExists=Fila exista deja.%n%Vrei ca ea sa fie suprascrisa de Instalator?
ExistingFileNewer=Fila deja existenta este mai noua dect cea care trebuie instalata. Este recomandat s-o pastrezi pe cea existenta.%n%nVrei sa pastrezi fila deja existenta?
ErrorChangingAttr=A aparut o eroare n timpul schimbarii atributelor filei deja existente:
ErrorCreatingTemp=A aparut o eroare n timpul crearii filei n dosarul de destinatie:
ErrorReadingSource=A aparut o eroare n timpul citirii filei sursa:
ErrorCopying=A aparut o eroare n timpul copierii filei:
ErrorReplacingExistingFile=A aparut o eroare n timpul nlocuirii filei deja existente:
ErrorRestartReplace=Repornirea/�nlocuirea a esuat:
ErrorRenamingTemp=A aparut o eroare n timpul renumirii unei file din dosarul de destinatie:
ErrorRegisterServer=Nu pot nregistra DLL/OCX: %1
ErrorRegSvr32Failed=RegSvr32 a esuat, avnd codul de iesire %1
ErrorRegisterTypeLib=Nu pot nregistra biblioteca de tipuri: %1

; *** Post-installation errors
ErrorOpeningReadme=A aparut o eroare la deschiderea filei de informare (README).
ErrorRestartingComputer=Instalatorul nu a putut reporni calculatorul. Va trebui sa-l repornesti manual.


; *** Shutdown block reasons
ShutdownBlockReasonInstallingApp=Instalez %1.
ShutdownBlockReasonUninstallingApp=Dezinstalez %1.

; The custom messages below aren't used by Setup itself, but if you make
; use of them in your scripts, you'll want to translate them.


; *** Uninstaller messages
ConfirmUninstall=Sigur doriti dezinstalarea completa a aplicatiei %1 si tuturor componentelor sale?
UninstallNotFound=Fila "%1" nu exista. Dezinstalarea nu poate fi facuta.
UninstallOpenError=Fila "%1" nu poate fi deschisa. Dezinstalarea nu poate fi facuta
UninstallUnsupportedVer=Fila "%1" ce contine jurnalul de dezinstalare este ntr-un format nerecunoscut de aceasta versiune a dezinstalatorului. Dezinstalarea nu poate fi facuta
UninstallUnknownEntry=A fost ntlnita o intrare necunoscuta (%1) n jurnalul de dezinstalare
UninstallOnlyOnWin64=Aceasta instalare poate fi dezinstalata doar pe un sistem Windows 64-biti.
OnlyAdminCanUninstall=Aceasta instalare poate fi dezinstalata doar de catre un utilizator cu drepturi de Administrator.
UninstallStatusLabel=Asteapta ca %1 sa fie nlaturat de pe calculator.
UninstalledAll=Aplicatia %1 a fost dezinstalata cu succes de pe calculatorul dumneavoastra.
UninstalledMost=Dezinstalare completa a %1.%n%nAnumite elemente nu au putut fi eliminate. Acestea pot fi sterse in mod manual.
UninstalledAndNeedsRestart=Pentru a termina dezinstalarea %1, calculatorul trebuie repornit.%n%nVrei sa fie repornit acum?
UninstallDataCorrupted=Fila "%1" este stricata (corupta). Dezinstalarea nu poate fi facuta


; *** Uninstallation phase messages
WizardUninstalling=Starea Dezinstalarii
StatusUninstalling=Se dezinstaleaza aplicatia %1...
ConfirmDeleteSharedFileTitle=Stergere Fisier Partajat?
ConfirmDeleteSharedFile2=Sistemul indica faptul ca fila partajata urmatoare pare sa nu mai fie folosita de vreun alt program. Vrei ca Dezinstalatorul sa stearga aceasta fila partajata?%n%nDaca totusi mai exista programe care folosesc fila si ea este stearsa, acele programe ar putea sa functioneze gresit. Daca nu esti sigur, alege Nu. Lasarea filei n sistem nu va produce nici o neplacere.
SharedFileNameLabel=Nume fisier:
SharedFileLocationLabel=Loc:


[CustomMessages]

NameAndVersion=%1 (v%2)
AdditionalIcons=Ad�ugare de scurt�turi suplimentare:
CreateDesktopIcon=Creeaza o iconi�� pe &Ecranul Principal ("Desktop")
CreateQuickLaunchIcon=Creeaza o iconi�� �n Bara de Lansare &Rapid� ("Quick Launch")
ProgramOnTheWeb=%1 pe Internet
UninstallProgram=Dezinstaleaz� %1
LaunchProgram=Lanseaz� %1
AssocFileExtension=&Asociaz� %1 fi�ierelor cu extensia %2
AssocingFileExtension=Asociez %1 fi�ierelor cu extensia %2...
AutoStartProgramGroupDescription=Pornire:
AutoStartProgram=Porne�te �n mod automat %1
AddonHostProgramNotFound=%1 nu poate fi g�sit �n dosarul ales.%n%nDori�i s� continua�i opera�iunea?
