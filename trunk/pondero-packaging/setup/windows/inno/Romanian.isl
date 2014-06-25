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
LastErrorMessage=%1.%n%nEroare %2: %3
SetupFileMissing=Fiºierul "%1" lipseºte din dosarul de instalare. Se recomandã descãrcarea unei noi copii a programului
SetupFileCorrupt=Fiºierele de instalare sunt corupte. Se recomandã descãrcarea unei noi copii a programului
SetupFileCorruptOrWrongVer=Fiºierele de instalare sunt corupte sau incompatibile cu aceastã versiune a programului de instalare. Se recomandã descãrcarea unei noi copii a programului
InvalidParameter=Un parametru transmis liniei de comandã este invalid:%n%n%1
SetupAlreadyRunning=Instalarea se aflã deja in execuþie
WindowsVersionNotSupported=Acest program nu suportã versiunea de Windows care ruleazã momentan pe system
WindowsServicePackRequired=Acest program necesitã cel puþin %1 Service Pack %2
NotOnThisPlatform=Acest program nu ruleazã pe %1.
OnlyOnThisPlatform=Acest program ruleazã pe %1.
OnlyOnTheseArchitectures=Acest program poate fi instalat doar pe versiuni de Windows proiectate pentru urmãtoarele arhitecturi de procesor:%n%n%1
MissingWOW64APIs=Versiunea de Windows care ruleazã pe sistem nu include funcþionalitatea cerutã pentru a realiza o instalare pe 64-biþi. Pentru a corecta problema, va fi necesarã instalarea Service Pack %1.
WinVersionTooLowError=Acest program necesitã %1 versiunea %2 sau mai nouã.
WinVersionTooHighError=Acest program nu poate fi instalat pe %1 versiunea %2 sau mai nouã.
AdminPrivilegesRequired=Instalarea aplicaþiei necesitã drepturi de Administrator.
PowerUserPrivilegesRequired=Apartenenþa la grupul de Administratori sau la cea a grupului de Utilizatori Speciali ("Power Users") este necesarã pentru instalarea acestei aplicaþii.
SetupAppRunningError=S-a detectat cã %1 ruleaza în acest moment.%n%nÎnchidþi toate instanþele programului respectiv apoi apãsaþi [OK] pentru a continua sau [Anuleazã] pentru a abandona instalarea.
UninstallAppRunningError=În timpul dezinstalãrii s-a detectat ca %1 ruleaza în acest moment.%n%nÎnchideþi toate instantele programului respectiv apoi apãsaþi [OK] pentru a continua sau [Anuleaza] pentru a abandona dezinstalarea.


; *** Misc. errors
ErrorCreatingDir=Dosarul "%1" nu a putut fi creat
ErrorTooManyFilesInDir=Numãrul maxim de fiºiere suportate de dosarul "%1" a fost depãºit


; *** Setup common messages
ExitSetupTitle=Abandonarea Instalãrii
ExitSetupMessage=Instalarea aplicaþiei nu este finalizatã. Dacã abandonaþi acum, programul nu va fi instalat.%n%nProgramul de instalare poate fi executat din nou la o data ulterioarã pentru a completa instalarea.%n%nSigur doriti sã abandonaþi instalarea?
AboutSetupMenuItem=&Despre aplicaþia de instalare...
AboutSetupTitle=Date despre aplicaþia de instalare
AboutSetupMessage=%1 versiunea %2%n%3%n%n%1 adresa paginii web este:%n%4
AboutSetupNote=
TranslatorNote=


; *** "Select Language" dialog messages
SelectLanguageTitle=Selectarea Limbii Aplicaþiei de Instalare
SelectLanguageLabel=Selectaþi limba folositã pentru instalare:


; *** "Password" wizard page
WizardPassword=Parola
PasswordLabel1=Aceastã instalare este protejata prin parolã
PasswordLabel3=Completaþi parola, apoi apãsaþi pe butonul [Continuare] pentru a merge mai departe. Tipul literelor din parolã (Majuscule/minuscule) este semnificativ
PasswordEditLabel=&Parola:
IncorrectPassword=Parola introdusã nu este corectã. Vã rugãm sã mai incercaþi o datã


; *** "Information" wizard pages
WizardInfoBefore=Informaþii
InfoBeforeLabel=Vã rugãm sã citiþi informaþiile urmãtoare înainte de a continua, sunt importante.
InfoBeforeClickLabel=Când sunteþi gata sã iniþiaþi instalarea, vã rugãm sã apãsaþi butonul [Continuã].
WizardInfoAfter=Informaþii
InfoAfterLabel=Vã rugãm sã citiþi informaþiile urmãtoare înainte de a continua, sunt importante.
InfoAfterClickLabel=Când sunteþi gata sã iniþiaþi instalarea, vã rugãm sã apãsaþi butonul [Continuã].


; *** "User Information" wizard page
WizardUserInfo=Informaþii despre Utilizator
UserInfoDesc=Vã rugãm sã completaþi informaþiile cerute.
UserInfoName=&Utilizator:
UserInfoOrg=&Organizaþie:
UserInfoSerial=Numãr de &Serie:
UserInfoNameRequired=Este necesarã introducerea unui nume.


; *** "Select Components" wizard page
WizardSelectComponents=Selectarea Componentelor
SelectComponentsDesc=Vã rugãm sã specificaþi care dintre urmãtoarele componente doriþi sã fie instalate?
SelectComponentsLabel2=Selecþia indicã acele componente care urmeazã a fi instalate; deselectarea marcheazã contrariul. Apãsaþi [Continuã] pentru a trece la urmãtorul pas.
FullInstallation=Instalare Completã
; if possible don't translate 'Compact' as 'Minimal' (I mean 'Minimal' in your language)
CompactInstallation=Instalare Compactã
CustomInstallation=Instalare Personalizatã
NoUninstallWarningTitle=Componentele sunt deje existente
NoUninstallWarning=S-a detectat cã urmãtoarele componente sunt deja instalate pe calculator:%n%n%1%n%nDeselectarea lor nu le va dezinstala.%n%nDoriþi sã continuaþi?
ComponentSize1=%1 KB
ComponentSize2=%1 MB
ComponentsDiskSpaceMBLabel=Configuraþia curentã necesitã cel puþin [mb] MB spaþiu de stocare.


; *** "Preparing to Install" wizard page
WizardPreparing=Pregãtire pentru Instalare
PreparingDesc=Se pregãteºte pregateste instalarea aplicþiei [name] pe calculator.
PreviousInstallNotCompleted=Instalarea/dezinstalarea anterioarã a unui program nu a fost terminatã. Va fi necesarã repornirea calculatorul pentru a termina operaþia precedentã.%n%nDupã repornirea calculatorului, rulaþi din nou programul de instalare pentru a realiza instalarea aplicaþiei [name].
CannotContinue=Instalarea nu poate continua. Apãsaþi pe [Anuleazã] pentru a o închide.
ApplicationsFound=Aplicaþiile urmãtoare folosesc fiºiere care trebuiesc actualizate de cãtre aplicaþia de instalare. Se recomandã sã permiti programului de instalare sã închidã în mod automat aplicaþiile respective.
ApplicationsFound2=Aplicaþiile urmãtoare folosesc fiºiere care trebuiesc actualizate de cãtre aplicaþia de instalare. Se recomandã sã permiti programului de instalare sã închidã în mod automat aplicaþiile respective. Dupa ce finalizarea instalãrii, se va încerca sa repornirea aplicaþiilor.
CloseApplications=Închide aplicaþiile în mod &automat
DontCloseApplications=Nu închi&de aplicaþiile
ErrorCloseApplications=Programul de instalare nu a putut închide în mod automat toate aplicaþiile. Înainte de a continua, se recomandã închiderea manualã a tuturor aplicaþiilor care folosesc fiþiere ce trebuiesc actualizate în timpul instalãrii.


; *** "Setup Needs the Next Disk" stuff
ChangeDiskTitle=Instalarea Necesitã Introducerea Discului Urmãtor
SelectDiskLabel2=Introduceþi Discul %1 ºi apãsaþi butonul [OK].%n%nDacã fiºierele de pe acest disc pot fi gãsite într-un alt dosar decât cel afiºat mai jos, vã invitãm sã introduceþi calea corecta sau sã folosiþi butonul [Exploreazã].
PathLabel=&Cale:
FileNotInDir2=Fiºierul "%1" nu poate fi gasit în "%2". Vã rugãm sã introduceþi discul corect sau sã alegeþi alt dosar.
SelectDirectoryLabel=Vã rugãm sã specificaþi locaþia discului urmãtor.


; *** Installation phase messages
SetupAborted=Instalarea nu a fost finalizatã.%n%nDupã corectarea problemei puteþi încerca o nouã instalare.
EntryAbortRetryIgnore=Apãsaþi pe [Reîncercare] pentru a încerca o nouã instalare, pe [Ignorare] pentru a continua cu situaþia actualã, sau pe [Abandonare] pentru a anula instalarea.


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
FileAbortRetryIgnore=Apãsaþi pe [Reîncercare] pentru a încerca din nou, pe [Ignorare] pentru a trece peste aceast fiºier (nerecomandat), sau pe [Abandonare] pentru a anula instalarea.
FileAbortRetryIgnore2=Apãsaþi pe [Reîncercare] pentru a încerca din nou, pe [Ignorare] pentru a trece peste aceast fiºier (nerecomandat), sau pe [Abandonare] pentru a anula instalarea.
SourceIsCorrupted=Fiºierul sursã este corupt
SourceDoesntExist=Fiºierul sursã "%1" nu existã
ExistingFileReadOnly=Fiºierul deja existent nu poate fi scris ("readonly").%n%nApãsaþi pe [Reîncercare] pentru a înlãtura atributul "readonly" ºi a încerca din nou, pe [Ignorare] pentru a sãri aceast fiºier, sau pe [Abandonare] pentru a anula instalarea.
ErrorReadingExistingDest=A aparut o eroare în timpul citirii fiºierului deja existent:
FileExists=Fiºierul existã deja.%n%Doriþi suprascrierea sa în timpul instalãrii?
ExistingFileNewer=Fiºierul deja existent este mai nou decât cel care trebuie instalat. Se recomandã versiunea deja existentã.%n%nDoriþi pãstrarea fiºierului deja existent?
ErrorChangingAttr=A aparut o eroare în timpul schimbãrii atributelor fiºierului deja existent:
ErrorCreatingTemp=A aparut o eroare în timpul creãrii fiºierului în dosarul de destinaþie:
ErrorReadingSource=A aparut o eroare în timpul citirii fiºierului sursã:
ErrorCopying=A aparut o eroare în timpul copierii fiºierului:
ErrorReplacingExistingFile=A aparut o eroare în timpul înlocuirii fiºierului deja existent:
ErrorRestartReplace=Repornirea/Înlocuirea a eºuat:
ErrorRenamingTemp=A aparut o eroare în timpul redenumirii unui fiºier din dosarul de destinaþie:
ErrorRegisterServer=A aparut o eroare în timpul înregistãrii unei componente DLL/OCX: %1
ErrorRegSvr32Failed=RegSvr32 a eºuat, având codul de ieºire %1
ErrorRegisterTypeLib=Nu pot înregistra biblioteca de tipuri: %1


; *** Post-installation errors
ErrorOpeningReadme=A aparut o eroare la deschiderea fiºierului de informare (README).
ErrorRestartingComputer=Programul de instalare nu a putut reporni calculatorul. Va trebui sã-l reporniþi manual.


; *** Shutdown block reasons
ShutdownBlockReasonInstallingApp=Se instaleazã %1.
ShutdownBlockReasonUninstallingApp=Se dezinstaleazã %1.

; The custom messages below aren't used by Setup itself, but if you make
; use of them in your scripts, you'll want to translate them.


; *** Uninstaller messages
ConfirmUninstall=Cu siguranþã doriþi dezinstalarea completã a aplicaþiei %1 ºi tuturor componentelor sale?
UninstallNotFound=Fiºierul "%1" nu existã. Dezinstalarea nu poate fi efectuatã
UninstallOpenError=Fiºierul "%1" nu poate fi deschis. Dezinstalarea nu poate fi efectuatã
UninstallUnsupportedVer=Fiºierul "%1" conþinând jurnalul de dezinstalare este într-un format nerecunoscut de aceastã versiune a programului de dezinstalare. Dezinstalarea nu poate fi efectuatã
UninstallUnknownEntry=A fost întâlnitã o intrare necunoscuta (%1) în jurnalul de dezinstalare
UninstallOnlyOnWin64=Dezinstalarea poate fi efectuatã doar de pe un sistem Windows pe 64-biþi.
OnlyAdminCanUninstall=Dezinstalarea poate fi efectuatã doar de catre un utilizator cu drepturi de Administrator.
UninstallStatusLabel=Vã rugãm aºteptaþi pentru ca %1 sã fie înlãturat de pe calculator.
UninstalledAll=Aplicaþia %1 a fost dezinstalatã cu succes de pe calculatorul dumneavoastrã.
UninstalledMost=Dezinstalarea aplicaþiei %1 s-a finalizat.%n%nAnumite elemente nu au putut fi eliminate. Acestea pot fi ºterse în mod manual.
UninstalledAndNeedsRestart=Pentru a termina dezinstalarea plicaþiei %1, calculatorul trebuie repornit.%n%nDoriþi efectuarea acestei operaþii chiar acum?
UninstallDataCorrupted=Fiºierul "%1" este corupt. Dezinstalarea nu poate fi efectuatã


; *** Uninstallation phase messages
WizardUninstalling=Situaþia Dezinstalãrii
StatusUninstalling=Se dezinstaleazã aplicaþia %1...
ConfirmDeleteSharedFileTitle=ªtergere Fiºier Partajat?
ConfirmDeleteSharedFile2=Sistemul indicã faptul cã urmãtorul fiºier partajat pare sa nu mai fie folosit de vreun alt program. Doriþi ºteargerea aceastui fiºier partajat?%n%nDacã totuºi mai exista programe care folosesc acest fiºier ºi el este ºters, acele programe ar putea sã funcþioneze în mod eronat. În caz de incertitudine, alegeþi [Nu]. Pãstrarea fiºierului în sistem nu va aduce inconveniente.
SharedFileNameLabel=Nume fiºier:
SharedFileLocationLabel=Locaþie:


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
