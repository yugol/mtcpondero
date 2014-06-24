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
InformationTitle=Informaþii
ConfirmTitle=Confirmare
ErrorTitle=Eroare


; *** Common wizard text
ClickNext=Apasaþi butonul [Continuare] pentru a avansa sau butonul [Anulare] pentru a abandona.
BeveledLabel=
BrowseDialogTitle=Alegere Destinaþie
BrowseDialogLabel=Selectaþi un dosar din lista de mai jos, apoi apasaþi butonul [OK]
NewFolderName=Dosar Nou


; *** Buttons
ButtonBack=< &Întoarcere
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
WelcomeLabel1=Bun venit la instalarea aplicaþiei [name]
WelcomeLabel2=Aplicaþia [name/ver] va fi instalatã pe calculatorul dumneavoastrã.%n%nEste sugeratã închiderea tuturor celelalte aplicaþii înainte de a continua.


; *** "License Agreement" wizard page
WizardLicense=Acord de Licenþiere
LicenseLabel=Urmãtoarele informaþii sunt importante. Vã rugãm sã le citiþi înaite de a continua.
LicenseLabel3=Acesta este Acordul de Licenþiere. Este necesarã acceptarea termenilor acestui acord înainte ca instalarea sã poatã continua:
LicenseAccepted=&Accept termenii
LicenseNotAccepted=&Refuz termenii


; *** "Select Destination Location" wizard page
WizardSelectDir=Selectarea dosarului de destinaþie a aplicaþiei
SelectDirDesc=Unde doriþi sã fie instalatã aplicaþia [name]?
SelectDirLabel3=Aplicaþia [name] va fi instalatã în dosarul specificat mai jos.
SelectDirBrowseLabel=Apasaþi butonul [Continuare] pentru a avansa.%nPentru alegerea un alt dosar apasaþi butonul [Explorare...]
DiskSpaceMBLabel=Este necesar un spaþiu liber de stocare de cel puþin [mb] MB
CannotInstallToNetworkDrive=Instalarea nu poate fi realizatã de pe un dispozitiv de reþea.
CannotInstallToUNCPath=Instalarea nu poate fi realizatã de pe o cale în format UNC.
InvalidPath=Trebuie specificatã o cale completã, incluzând litera dispozitivului; de exemplu:%n%nC:\APP%n%nsau o cale UNC de forma:%n%n\\server\share
InvalidDrive=Dispozitivul sau partajul UNC selectat nu exista sau nu este accesibil. Vã rugãm sã specificaþi o altã opþiune.
DiskSpaceWarningTitle=Spaþiu de stocare insuficient
DiskSpaceWarning=Instalarea necesita cel puþin %1 KB de spaþiu de stocare liber, însã dispozitivul selectat are doar %2 KB disponibili.%n%nDoriþi sã continuaþi chiar ºi în aceste condiþii?
DirNameTooLong=Numele dosarului sau al cãii este prea lung.
InvalidDirName=Numele dosarului nu este valid.
BadDirName32=Numele dosarelor nu pot include nici unul dintre urmatoarele caractere:%n%n%1
DirExistsTitle=Dosar Existent
DirExists=Dosarul:%n%n%1%n%nexistã deja. Doriþi totuºi sã instalaþi aplicaþia în acest dosar?
DirDoesntExistTitle=Dosarul Inexistent
DirDoesntExist=Dosarul:%n%n%1%n%nnu existã. Doriþi crearea sa?


; *** "Select Start Menu Folder" wizard page
WizardSelectProgramGroup=Selectarea secþiunii din Meniul de Start
SelectStartMenuFolderDesc=Unde doriþi sã fie create scurtãturile programului?
SelectStartMenuFolderLabel3=Scurtãturile vor fi plasate în secþiunea Meniului de Start specificatã mai jos.
SelectStartMenuFolderBrowseLabel=Apasaþi butonul [Continuare] pentru a avansa.%nDaca doriþi alegerea unei alte secþiuni apasaþi butonul [Explorare...]
MustEnterGroupName=Este necesarã specificarea numelui dosaului de destinaþie.
GroupNameTooLong=Numele dosarului sau al cãii este prea lung.
InvalidGroupName=Numele dosarului nu este valid.
BadGroupName=Numele dosarului nu poate include nici unul dintre caracterele urmatoarele:%n%n%1
NoProgramGroupCheck2=Crearea unui dosar în Meniul de Start nu este posibilã


; *** "Select Additional Tasks" wizard page
WizardSelectTasks=Selectarea sarcinilor suplimentare
SelectTasksDesc=Ce sarcini suplimentare doriþi sã fie îndeplinite?
SelectTasksLabel2=Selectaþi sarcinile suplimentare care doriþi sã fie îndeplinite în timpul instalãrii aplicaþiei [name], apoi apãsaþi butonul [Continuare]


; *** "Ready to Install" wizard page
WizardReady=Instalarea este pregãtitã
ReadyLabel1=Toate datele necesare pentru instalarea aplicaþiei [name] au fost colectate.
ReadyLabel2a=Apasaþi butonul [Instalare] pentru a avansa sau butonul [Întoarcere] dacã doriþi revizuirea/schimbarea configuraþiei de instalare.
ReadyLabel2b=Apãsaþi butonul [Instalare] pentru a continua cu instalarea.
ReadyMemoUserInfo=Informaþii utilizator:
ReadyMemoDir=Dosarul de destinaþie:
ReadyMemoType=Tip de instalare:
ReadyMemoComponents=Componente selectate:
ReadyMemoGroup=Secþiunea din Meniul de Start:
ReadyMemoTasks=Sarcini suplimentare:


; *** "Installing" wizard page
WizardInstalling=Procesul de instalare se aflã în desfãºurare
InstallingLabel=Vã rugãm sã aºteaptaþi finalizarea instalãrii aplicaþiei [name] pe calculator.


; *** "Setup Completed" wizard page
FinishedHeadingLabel=Finalizarea Instalãrii [name]
FinishedLabelNoIcons=Instalarea aplicaþiei [name] s-a terminat cu succes.
FinishedLabel=Instalarea aplicaþiei [name] pe calculator s-a terminat cu succes. Aplicaþia poate fi lansatã prin actionarea scurtãturilor adãugate.
ClickFinish=Apasaþi butonul [Finalizeaza] pentru a parasi Aplicaþia de Instalare.
FinishedRestartLabel=Pentru a termina instalarea aplicaþiei [name], trebuie repornit calculatorul. Doriþi sã efectuaþi aceastã operaþie chiar acum?
FinishedRestartMessage=Pentru a termina instalarea aplicaþiei [name], trebuie repornit calculatorul.%n%nDoriþi sã efectuaþi aceastã operaþie chiar acum?
ShowReadmeCheck=Da, doresc afiºarea paginii informative ("README")
YesRadio=&Da, reporneºte calculatorul acum
NoRadio=&Nu, voi reporni calculatorul mai trziu
; used for example as 'Run MyProg.exe'
RunEntryExec=Ruleazã %1
; used for example as 'View Readme.txt'
RunEntryShellExec=Vezi %1


; *** SetupLdr messages
SetupLdrStartupMessage=Aplicaþia "%1" urmeazã sã fie instalatã. Doriþi sã continuaþi?
LdrCannotCreateTemp=Nu se poate crea un fiºier temporar. Instalarea va fi abandonatã
LdrCannotExecTemp=Nu se poate executa un fiºier din dosarul temporar. Instalarea va fi abandonatã


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
SetupAppRunningError=Instalatorul a detectat ca %1 ruleaza in acest moment.%n%nÎnchide toate instantele programului respectiv, apoi clicheaza OK pentru a continua sau Anuleaza pentru a abandona instalarea.
UninstallAppRunningError=Dezinstalatorul a detectat ca %1 ruleaza n acest moment.%n%nÎnchide toate instantele programului respectiv, apoi clicheaza OK pentru a continua sau Anuleaza pentru a abandona dezinstalarea.


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
CloseApplications=Închide &automat aplicatiile
DontCloseApplications=Nu nchi&de aplicatiile
ErrorCloseApplications=Instalatorul nu a putut nchide automat toate aplicatiile. Înainte de a continua, e recomandat sa nchizi manual toate aplicatiile care folosesc file ce trebuie actualizate de Instalator.


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
StatusClosingApplications=Se închid aplicaþiile...
StatusCreateDirs=Se creazã dosarele...
StatusExtractFiles=Se copie fiºirele aplicaþiei...
StatusCreateIcons=Se creazã scurtãturile...
StatusCreateIniEntries=Se creazã intrãrile INI...
StatusCreateRegistryEntries=Se creazã intrãrile în registru...
StatusRegisterFiles=Se înregistreazã fiºierele...
StatusSavingUninstall=Se salveazã informaþiile de dezinstalare...
StatusRunProgram=Se finalizeazã instalarea...
StatusRestartingApplications=Se repornesc aplicaþiile...
StatusRollback=Se restaureazã starea iniþialã, prin anularea modificãrilor efectuate în timpul instalãrii...


; *** Misc. errors
ErrorInternal2=Eroare internã: %1
ErrorFunctionFailedNoCode=%1 a eºuat
ErrorFunctionFailed=%1 a eºuat; cod %2
ErrorFunctionFailedWithMessage=%1 a eºuat; cod %2.%n%3
ErrorExecutingProgram=Nu pot executa:%n%1

; *** Registry errors
ErrorRegOpenKey=Eroare la deschiderea cheii de registru:%n%1\%2
ErrorRegCreateKey=Eroare la crearea cheii de registru:%n%1\%2
ErrorRegWriteKey=Eroare la scrierea în cheia de registru:%n%1\%2

; *** INI errors
ErrorIniEntry=Eroare la crearea intrãrii INI în fiºierul "%1".

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
ErrorRestartReplace=Repornirea/Înlocuirea a esuat:
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
AdditionalIcons=Adãugare de scurtãturi suplimentare:
CreateDesktopIcon=Creeaza o iconiþã pe &Ecranul Principal ("Desktop")
CreateQuickLaunchIcon=Creeaza o iconiþã în Bara de Lansare &Rapidã ("Quick Launch")
ProgramOnTheWeb=%1 pe Internet
UninstallProgram=Dezinstaleazã %1
LaunchProgram=Lanseazã %1
AssocFileExtension=&Asociazã %1 fiºierelor cu extensia %2
AssocingFileExtension=Asociez %1 fiºierelor cu extensia %2...
AutoStartProgramGroupDescription=Pornire:
AutoStartProgram=Porneºte în mod automat %1
AddonHostProgramNotFound=%1 nu poate fi gãsit în dosarul ales.%n%nDoriþi sã continuaþi operaþiunea?
