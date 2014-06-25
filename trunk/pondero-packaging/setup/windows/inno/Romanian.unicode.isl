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
InformationTitle=Informații
ConfirmTitle=Confirmare
ErrorTitle=Eroare


; *** Common wizard text
ClickNext=Apasați butonul [Continuare] pentru a avansa sau butonul [Anulare] pentru a abandona.
BeveledLabel=
BrowseDialogTitle=Alegere Destinație
BrowseDialogLabel=Selectați un dosar din lista de mai jos, apoi apasați butonul [OK]
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
WelcomeLabel1=Bun venit la instalarea aplicației [name]
WelcomeLabel2=Aplicația [name/ver] va fi instalată pe calculatorul dumneavoastră.%n%nEste sugerată închiderea tuturor celelalte aplicații înainte de a continua.


; *** "License Agreement" wizard page
WizardLicense=Acord de Licențiere
LicenseLabel=Următoarele informații sunt importante. Vă rugăm să le citiți înaite de a continua.
LicenseLabel3=Acesta este Acordul de Licențiere. Este necesară acceptarea termenilor acestui acord înainte ca instalarea să poată continua:
LicenseAccepted=&Accept termenii
LicenseNotAccepted=&Refuz termenii


; *** "Select Destination Location" wizard page
WizardSelectDir=Selectarea dosarului de destinație a aplicației
SelectDirDesc=Unde doriți să fie instalată aplicația [name]?
SelectDirLabel3=Aplicația [name] va fi instalată în dosarul specificat mai jos.
SelectDirBrowseLabel=Apasați butonul [Continuare] pentru a avansa.%nPentru alegerea un alt dosar apasați butonul [Explorare...]
DiskSpaceMBLabel=Este necesar un spațiu liber de stocare de cel puțin [mb] MB
CannotInstallToNetworkDrive=Instalarea nu poate fi realizată de pe un dispozitiv de rețea.
CannotInstallToUNCPath=Instalarea nu poate fi realizată de pe o cale în format UNC.
InvalidPath=Trebuie specificată o cale completă, incluzând litera dispozitivului; de exemplu:%n%nC:\APP%n%nsau o cale UNC de forma:%n%n\\server\share
InvalidDrive=Dispozitivul sau partajul UNC selectat nu exista sau nu este accesibil. Vă rugăm să specificați o altă opțiune.
DiskSpaceWarningTitle=Spațiu de stocare insuficient
DiskSpaceWarning=Instalarea necesita cel puțin %1 KB de spațiu de stocare liber, însă dispozitivul selectat are doar %2 KB disponibili.%n%nDoriți să continuați chiar și în aceste condiții?
DirNameTooLong=Numele dosarului sau al căii este prea lung.
InvalidDirName=Numele dosarului nu este valid.
BadDirName32=Numele dosarelor nu pot include nici unul dintre urmatoarele caractere:%n%n%1
DirExistsTitle=Dosar Existent
DirExists=Dosarul:%n%n%1%n%nexistă deja. Doriți totuși să instalați aplicația în acest dosar?
DirDoesntExistTitle=Dosarul Inexistent
DirDoesntExist=Dosarul:%n%n%1%n%nnu există. Doriți crearea sa?


; *** "Select Start Menu Folder" wizard page
WizardSelectProgramGroup=Selectarea secțiunii din Meniul de Start
SelectStartMenuFolderDesc=Unde doriți să fie create scurtăturile programului?
SelectStartMenuFolderLabel3=Scurtăturile vor fi plasate în secțiunea Meniului de Start specificată mai jos.
SelectStartMenuFolderBrowseLabel=Apasați butonul [Continuare] pentru a avansa.%nDaca doriți alegerea unei alte secțiuni apasați butonul [Explorare...]
MustEnterGroupName=Este necesară specificarea numelui dosaului de destinație.
GroupNameTooLong=Numele dosarului sau al căii este prea lung.
InvalidGroupName=Numele dosarului nu este valid.
BadGroupName=Numele dosarului nu poate include nici unul dintre caracterele urmatoarele:%n%n%1
NoProgramGroupCheck2=Crearea unui dosar în Meniul de Start nu este posibilă


; *** "Select Additional Tasks" wizard page
WizardSelectTasks=Selectarea sarcinilor suplimentare
SelectTasksDesc=Ce sarcini suplimentare doriți să fie îndeplinite?
SelectTasksLabel2=Selectați sarcinile suplimentare care doriți să fie îndeplinite în timpul instalării aplicației [name], apoi apăsați butonul [Continuare]


; *** "Ready to Install" wizard page
WizardReady=Instalarea este pregătită
ReadyLabel1=Toate datele necesare pentru instalarea aplicației [name] au fost colectate.
ReadyLabel2a=Apasați butonul [Instalare] pentru a avansa sau butonul [Întoarcere] dacă doriți revizuirea/schimbarea configurației de instalare.
ReadyLabel2b=Apăsați butonul [Instalare] pentru a continua cu instalarea.
ReadyMemoUserInfo=Informații utilizator:
ReadyMemoDir=Dosarul de destinație:
ReadyMemoType=Tip de instalare:
ReadyMemoComponents=Componente selectate:
ReadyMemoGroup=Secțiunea din Meniul de Start:
ReadyMemoTasks=Sarcini suplimentare:


; *** "Installing" wizard page
WizardInstalling=Procesul de instalare se află în desfășurare
InstallingLabel=Vă rugăm să așteaptați finalizarea instalării aplicației [name] pe calculator.


; *** "Setup Completed" wizard page
FinishedHeadingLabel=Finalizarea Instalării [name]
FinishedLabelNoIcons=Instalarea aplicației [name] s-a terminat cu succes.
FinishedLabel=Instalarea aplicației [name] pe calculator s-a terminat cu succes. Aplicația poate fi lansată prin actionarea scurtăturilor adăugate.
ClickFinish=Apasați butonul [Finalizeaza] pentru a parasi Aplicația de Instalare.
FinishedRestartLabel=Pentru a termina instalarea aplicației [name], trebuie repornit calculatorul. Doriți să efectuați această operație chiar acum?
FinishedRestartMessage=Pentru a termina instalarea aplicației [name], trebuie repornit calculatorul.%n%nDoriți să efectuați această operație chiar acum?
ShowReadmeCheck=Da, doresc afișarea paginii informative ("README")
YesRadio=&Da, repornește calculatorul acum
NoRadio=&Nu, voi reporni calculatorul mai trziu
; used for example as 'Run MyProg.exe'
RunEntryExec=Rulează %1
; used for example as 'View Readme.txt'
RunEntryShellExec=Vezi %1


; *** SetupLdr messages
SetupLdrStartupMessage=Aplicația "%1" urmează să fie instalată. Doriți să continuați?
LdrCannotCreateTemp=Nu se poate crea un fișier temporar. Instalarea va fi abandonată
LdrCannotExecTemp=Nu se poate executa un fișier din dosarul temporar. Instalarea va fi abandonată


; *** Startup error messages
LastErrorMessage=%1.%n%nEroare %2: %3
SetupFileMissing=Fișierul "%1" lipsește din dosarul de instalare. Se recomandă descărcarea unei noi copii a programului
SetupFileCorrupt=Fișierele de instalare sunt corupte. Se recomandă descărcarea unei noi copii a programului
SetupFileCorruptOrWrongVer=Fișierele de instalare sunt corupte sau incompatibile cu această versiune a programului de instalare. Se recomandă descărcarea unei noi copii a programului
InvalidParameter=Un parametru transmis liniei de comandă este invalid:%n%n%1
SetupAlreadyRunning=Instalarea se află deja in execuție
WindowsVersionNotSupported=Acest program nu suportă versiunea de Windows care rulează momentan pe system
WindowsServicePackRequired=Acest program necesită cel puțin %1 Service Pack %2
NotOnThisPlatform=Acest program nu rulează pe %1.
OnlyOnThisPlatform=Acest program rulează pe %1.
OnlyOnTheseArchitectures=Acest program poate fi instalat doar pe versiuni de Windows proiectate pentru următoarele arhitecturi de procesor:%n%n%1
MissingWOW64APIs=Versiunea de Windows care rulează pe sistem nu include funcționalitatea cerută pentru a realiza o instalare pe 64-biți. Pentru a corecta problema, va fi necesară instalarea Service Pack %1.
WinVersionTooLowError=Acest program necesită %1 versiunea %2 sau mai nouă.
WinVersionTooHighError=Acest program nu poate fi instalat pe %1 versiunea %2 sau mai nouă.
AdminPrivilegesRequired=Instalarea aplicației necesită drepturi de Administrator.
PowerUserPrivilegesRequired=Apartenența la grupul de Administratori sau la cea a grupului de Utilizatori Speciali ("Power Users") este necesară pentru instalarea acestei aplicații.
SetupAppRunningError=S-a detectat că %1 ruleaza în acest moment.%n%nÎnchidți toate instanțele programului respectiv apoi apăsați [OK] pentru a continua sau [Anulează] pentru a abandona instalarea.
UninstallAppRunningError=În timpul dezinstalării s-a detectat ca %1 ruleaza în acest moment.%n%nÎnchideți toate instantele programului respectiv apoi apăsați [OK] pentru a continua sau [Anuleaza] pentru a abandona dezinstalarea.


; *** Misc. errors
ErrorCreatingDir=Dosarul "%1" nu a putut fi creat
ErrorTooManyFilesInDir=Numărul maxim de fișiere suportate de dosarul "%1" a fost depășit


; *** Setup common messages
ExitSetupTitle=Abandonarea Instalării
ExitSetupMessage=Instalarea aplicației nu este finalizată. Dacă abandonați acum, programul nu va fi instalat.%n%nProgramul de instalare poate fi executat din nou la o data ulterioară pentru a completa instalarea.%n%nSigur doriti să abandonați instalarea?
AboutSetupMenuItem=&Despre aplicația de instalare...
AboutSetupTitle=Date despre aplicația de instalare
AboutSetupMessage=%1 versiunea %2%n%3%n%n%1 adresa paginii web este:%n%4
AboutSetupNote=
TranslatorNote=


; *** "Select Language" dialog messages
SelectLanguageTitle=Selectarea Limbii Aplicației de Instalare
SelectLanguageLabel=Selectați limba folosită pentru instalare:


; *** "Password" wizard page
WizardPassword=Parola
PasswordLabel1=Această instalare este protejata prin parolă
PasswordLabel3=Completați parola, apoi apăsați pe butonul [Continuare] pentru a merge mai departe. Tipul literelor din parolă (Majuscule/minuscule) este semnificativ
PasswordEditLabel=&Parola:
IncorrectPassword=Parola introdusă nu este corectă. Vă rugăm să mai incercați o dată


; *** "Information" wizard pages
WizardInfoBefore=Informații
InfoBeforeLabel=Vă rugăm să citiți informațiile următoare înainte de a continua, sunt importante.
InfoBeforeClickLabel=Când sunteți gata să inițiați instalarea, vă rugăm să apăsați butonul [Continuă].
WizardInfoAfter=Informații
InfoAfterLabel=Vă rugăm să citiți informațiile următoare înainte de a continua, sunt importante.
InfoAfterClickLabel=Când sunteți gata să inițiați instalarea, vă rugăm să apăsați butonul [Continuă].


; *** "User Information" wizard page
WizardUserInfo=Informații despre Utilizator
UserInfoDesc=Vă rugăm să completați informațiile cerute.
UserInfoName=&Utilizator:
UserInfoOrg=&Organizație:
UserInfoSerial=Număr de &Serie:
UserInfoNameRequired=Este necesară introducerea unui nume.


; *** "Select Components" wizard page
WizardSelectComponents=Selectarea Componentelor
SelectComponentsDesc=Vă rugăm să specificați care dintre următoarele componente doriți să fie instalate?
SelectComponentsLabel2=Selecția indică acele componente care urmează a fi instalate; deselectarea marchează contrariul. Apăsați [Continuă] pentru a trece la următorul pas.
FullInstallation=Instalare Completă
; if possible don't translate 'Compact' as 'Minimal' (I mean 'Minimal' in your language)
CompactInstallation=Instalare Compactă
CustomInstallation=Instalare Personalizată
NoUninstallWarningTitle=Componentele sunt deje existente
NoUninstallWarning=S-a detectat că următoarele componente sunt deja instalate pe calculator:%n%n%1%n%nDeselectarea lor nu le va dezinstala.%n%nDoriți să continuați?
ComponentSize1=%1 KB
ComponentSize2=%1 MB
ComponentsDiskSpaceMBLabel=Configurația curentă necesită cel puțin [mb] MB spațiu de stocare.


; *** "Preparing to Install" wizard page
WizardPreparing=Pregătire pentru Instalare
PreparingDesc=Se pregătește pregateste instalarea aplicției [name] pe calculator.
PreviousInstallNotCompleted=Instalarea/dezinstalarea anterioară a unui program nu a fost terminată. Va fi necesară repornirea calculatorul pentru a termina operația precedentă.%n%nDupă repornirea calculatorului, rulați din nou programul de instalare pentru a realiza instalarea aplicației [name].
CannotContinue=Instalarea nu poate continua. Apăsați pe [Anulează] pentru a o închide.
ApplicationsFound=Aplicațiile următoare folosesc fișiere care trebuiesc actualizate de către aplicația de instalare. Se recomandă să permiti programului de instalare să închidă în mod automat aplicațiile respective.
ApplicationsFound2=Aplicațiile următoare folosesc fișiere care trebuiesc actualizate de către aplicația de instalare. Se recomandă să permiti programului de instalare să închidă în mod automat aplicațiile respective. Dupa ce finalizarea instalării, se va încerca sa repornirea aplicațiilor.
CloseApplications=Închide aplicațiile în mod &automat
DontCloseApplications=Nu închi&de aplicațiile
ErrorCloseApplications=Programul de instalare nu a putut închide în mod automat toate aplicațiile. Înainte de a continua, se recomandă închiderea manuală a tuturor aplicațiilor care folosesc fițiere ce trebuiesc actualizate în timpul instalării.


; *** "Setup Needs the Next Disk" stuff
ChangeDiskTitle=Instalarea Necesită Introducerea Discului Următor
SelectDiskLabel2=Introduceți Discul %1 și apăsați butonul [OK].%n%nDacă fișierele de pe acest disc pot fi găsite într-un alt dosar decât cel afișat mai jos, vă invităm să introduceți calea corecta sau să folosiți butonul [Explorează].
PathLabel=&Cale:
FileNotInDir2=Fișierul "%1" nu poate fi gasit în "%2". Vă rugăm să introduceți discul corect sau să alegeți alt dosar.
SelectDirectoryLabel=Vă rugăm să specificați locația discului următor.


; *** Installation phase messages
SetupAborted=Instalarea nu a fost finalizată.%n%nDupă corectarea problemei puteți încerca o nouă instalare.
EntryAbortRetryIgnore=Apăsați pe [Reîncercare] pentru a încerca o nouă instalare, pe [Ignorare] pentru a continua cu situația actuală, sau pe [Abandonare] pentru a anula instalarea.


; *** Installation status messages
StatusClosingApplications=Se închid aplicațiile...
StatusCreateDirs=Se crează dosarele...
StatusExtractFiles=Se copie fișirele aplicației...
StatusCreateIcons=Se crează scurtăturile...
StatusCreateIniEntries=Se crează intrările INI...
StatusCreateRegistryEntries=Se crează intrările în registru...
StatusRegisterFiles=Se înregistrează fișierele...
StatusSavingUninstall=Se salvează informațiile de dezinstalare...
StatusRunProgram=Se finalizează instalarea...
StatusRestartingApplications=Se repornesc aplicațiile...
StatusRollback=Se restaurează starea inițială, prin anularea modificărilor efectuate în timpul instalării...


; *** Misc. errors
ErrorInternal2=Eroare internă: %1
ErrorFunctionFailedNoCode=%1 a eșuat
ErrorFunctionFailed=%1 a eșuat; cod %2
ErrorFunctionFailedWithMessage=%1 a eșuat; cod %2.%n%3
ErrorExecutingProgram=Nu pot executa:%n%1


; *** Registry errors
ErrorRegOpenKey=Eroare la deschiderea cheii de registru:%n%1\%2
ErrorRegCreateKey=Eroare la crearea cheii de registru:%n%1\%2
ErrorRegWriteKey=Eroare la scrierea în cheia de registru:%n%1\%2


; *** INI errors
ErrorIniEntry=Eroare la crearea intrării INI în fișierul "%1".


; *** File copying errors
FileAbortRetryIgnore=Apăsați pe [Reîncercare] pentru a încerca din nou, pe [Ignorare] pentru a trece peste aceast fișier (nerecomandat), sau pe [Abandonare] pentru a anula instalarea.
FileAbortRetryIgnore2=Apăsați pe [Reîncercare] pentru a încerca din nou, pe [Ignorare] pentru a trece peste aceast fișier (nerecomandat), sau pe [Abandonare] pentru a anula instalarea.
SourceIsCorrupted=Fișierul sursă este corupt
SourceDoesntExist=Fișierul sursă "%1" nu există
ExistingFileReadOnly=Fișierul deja existent nu poate fi scris ("readonly").%n%nApăsați pe [Reîncercare] pentru a înlătura atributul "readonly" și a încerca din nou, pe [Ignorare] pentru a sări aceast fișier, sau pe [Abandonare] pentru a anula instalarea.
ErrorReadingExistingDest=A aparut o eroare în timpul citirii fișierului deja existent:
FileExists=Fișierul există deja.%n%Doriți suprascrierea sa în timpul instalării?
ExistingFileNewer=Fișierul deja existent este mai nou decât cel care trebuie instalat. Se recomandă versiunea deja existentă.%n%nDoriți păstrarea fișierului deja existent?
ErrorChangingAttr=A aparut o eroare în timpul schimbării atributelor fișierului deja existent:
ErrorCreatingTemp=A aparut o eroare în timpul creării fișierului în dosarul de destinație:
ErrorReadingSource=A aparut o eroare în timpul citirii fișierului sursă:
ErrorCopying=A aparut o eroare în timpul copierii fișierului:
ErrorReplacingExistingFile=A aparut o eroare în timpul înlocuirii fișierului deja existent:
ErrorRestartReplace=Repornirea/Înlocuirea a eșuat:
ErrorRenamingTemp=A aparut o eroare în timpul redenumirii unui fișier din dosarul de destinație:
ErrorRegisterServer=A aparut o eroare în timpul înregistării unei componente DLL/OCX: %1
ErrorRegSvr32Failed=RegSvr32 a eșuat, având codul de ieșire %1
ErrorRegisterTypeLib=Nu pot înregistra biblioteca de tipuri: %1


; *** Post-installation errors
ErrorOpeningReadme=A aparut o eroare la deschiderea fișierului de informare (README).
ErrorRestartingComputer=Programul de instalare nu a putut reporni calculatorul. Va trebui să-l reporniți manual.


; *** Shutdown block reasons
ShutdownBlockReasonInstallingApp=Se instalează %1.
ShutdownBlockReasonUninstallingApp=Se dezinstalează %1.

; The custom messages below aren't used by Setup itself, but if you make
; use of them in your scripts, you'll want to translate them.


; *** Uninstaller messages
ConfirmUninstall=Cu siguranță doriți dezinstalarea completă a aplicației %1 și tuturor componentelor sale?
UninstallNotFound=Fișierul "%1" nu există. Dezinstalarea nu poate fi efectuată
UninstallOpenError=Fișierul "%1" nu poate fi deschis. Dezinstalarea nu poate fi efectuată
UninstallUnsupportedVer=Fișierul "%1" conținând jurnalul de dezinstalare este într-un format nerecunoscut de această versiune a programului de dezinstalare. Dezinstalarea nu poate fi efectuată
UninstallUnknownEntry=A fost întâlnită o intrare necunoscuta (%1) în jurnalul de dezinstalare
UninstallOnlyOnWin64=Dezinstalarea poate fi efectuată doar de pe un sistem Windows pe 64-biți.
OnlyAdminCanUninstall=Dezinstalarea poate fi efectuată doar de catre un utilizator cu drepturi de Administrator.
UninstallStatusLabel=Vă rugăm așteptați pentru ca %1 să fie înlăturat de pe calculator.
UninstalledAll=Aplicația %1 a fost dezinstalată cu succes de pe calculatorul dumneavoastră.
UninstalledMost=Dezinstalarea aplicației %1 s-a finalizat.%n%nAnumite elemente nu au putut fi eliminate. Acestea pot fi șterse în mod manual.
UninstalledAndNeedsRestart=Pentru a termina dezinstalarea plicației %1, calculatorul trebuie repornit.%n%nDoriți efectuarea acestei operații chiar acum?
UninstallDataCorrupted=Fișierul "%1" este corupt. Dezinstalarea nu poate fi efectuată


; *** Uninstallation phase messages
WizardUninstalling=Situația Dezinstalării
StatusUninstalling=Se dezinstalează aplicația %1...
ConfirmDeleteSharedFileTitle=Ștergere Fișier Partajat?
ConfirmDeleteSharedFile2=Sistemul indică faptul că următorul fișier partajat pare sa nu mai fie folosit de vreun alt program. Doriți șteargerea aceastui fișier partajat?%n%nDacă totuși mai exista programe care folosesc acest fișier și el este șters, acele programe ar putea să funcționeze în mod eronat. În caz de incertitudine, alegeți [Nu]. Păstrarea fișierului în sistem nu va aduce inconveniente.
SharedFileNameLabel=Nume fișier:
SharedFileLocationLabel=Locație:


[CustomMessages]

NameAndVersion=%1 (v%2)
AdditionalIcons=Adăugare de scurtături suplimentare:
CreateDesktopIcon=Creeaza o iconiță pe &Ecranul Principal ("Desktop")
CreateQuickLaunchIcon=Creeaza o iconiță în Bara de Lansare &Rapidă ("Quick Launch")
ProgramOnTheWeb=%1 pe Internet
UninstallProgram=Dezinstalează %1
LaunchProgram=Lansează %1
AssocFileExtension=&Asociază %1 fișierelor cu extensia %2
AssocingFileExtension=Asociez %1 fișierelor cu extensia %2...
AutoStartProgramGroupDescription=Pornire:
AutoStartProgram=Pornește în mod automat %1
AddonHostProgramNotFound=%1 nu poate fi găsit în dosarul ales.%n%nDoriți să continuați operațiunea?
