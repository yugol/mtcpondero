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
LastErrorMessage=%1.%n%nEroare %2: %3
SetupFileMissing=Fi�ierul "%1" lipse�te din dosarul de instalare. Se recomand� desc�rcarea unei noi copii a programului
SetupFileCorrupt=Fi�ierele de instalare sunt corupte. Se recomand� desc�rcarea unei noi copii a programului
SetupFileCorruptOrWrongVer=Fi�ierele de instalare sunt corupte sau incompatibile cu aceast� versiune a programului de instalare. Se recomand� desc�rcarea unei noi copii a programului
InvalidParameter=Un parametru transmis liniei de comand� este invalid:%n%n%1
SetupAlreadyRunning=Instalarea se afl� deja in execu�ie
WindowsVersionNotSupported=Acest program nu suport� versiunea de Windows care ruleaz� momentan pe system
WindowsServicePackRequired=Acest program necesit� cel pu�in %1 Service Pack %2
NotOnThisPlatform=Acest program nu ruleaz� pe %1.
OnlyOnThisPlatform=Acest program ruleaz� pe %1.
OnlyOnTheseArchitectures=Acest program poate fi instalat doar pe versiuni de Windows proiectate pentru urm�toarele arhitecturi de procesor:%n%n%1
MissingWOW64APIs=Versiunea de Windows care ruleaz� pe sistem nu include func�ionalitatea cerut� pentru a realiza o instalare pe 64-bi�i. Pentru a corecta problema, va fi necesar� instalarea Service Pack %1.
WinVersionTooLowError=Acest program necesit� %1 versiunea %2 sau mai nou�.
WinVersionTooHighError=Acest program nu poate fi instalat pe %1 versiunea %2 sau mai nou�.
AdminPrivilegesRequired=Instalarea aplica�iei necesit� drepturi de Administrator.
PowerUserPrivilegesRequired=Apartenen�a la grupul de Administratori sau la cea a grupului de Utilizatori Speciali ("Power Users") este necesar� pentru instalarea acestei aplica�ii.
SetupAppRunningError=S-a detectat c� %1 ruleaza �n acest moment.%n%n�nchid�i toate instan�ele programului respectiv apoi ap�sa�i [OK] pentru a continua sau [Anuleaz�] pentru a abandona instalarea.
UninstallAppRunningError=�n timpul dezinstal�rii s-a detectat ca %1 ruleaza �n acest moment.%n%n�nchide�i toate instantele programului respectiv apoi ap�sa�i [OK] pentru a continua sau [Anuleaza] pentru a abandona dezinstalarea.


; *** Misc. errors
ErrorCreatingDir=Dosarul "%1" nu a putut fi creat
ErrorTooManyFilesInDir=Num�rul maxim de fi�iere suportate de dosarul "%1" a fost dep�it


; *** Setup common messages
ExitSetupTitle=Abandonarea Instal�rii
ExitSetupMessage=Instalarea aplica�iei nu este finalizat�. Dac� abandona�i acum, programul nu va fi instalat.%n%nProgramul de instalare poate fi executat din nou la o data ulterioar� pentru a completa instalarea.%n%nSigur doriti s� abandona�i instalarea?
AboutSetupMenuItem=&Despre aplica�ia de instalare...
AboutSetupTitle=Date despre aplica�ia de instalare
AboutSetupMessage=%1 versiunea %2%n%3%n%n%1 adresa paginii web este:%n%4
AboutSetupNote=
TranslatorNote=


; *** "Select Language" dialog messages
SelectLanguageTitle=Selectarea Limbii Aplica�iei de Instalare
SelectLanguageLabel=Selecta�i limba folosit� pentru instalare:


; *** "Password" wizard page
WizardPassword=Parola
PasswordLabel1=Aceast� instalare este protejata prin parol�
PasswordLabel3=Completa�i parola, apoi ap�sa�i pe butonul [Continuare] pentru a merge mai departe. Tipul literelor din parol� (Majuscule/minuscule) este semnificativ
PasswordEditLabel=&Parola:
IncorrectPassword=Parola introdus� nu este corect�. V� rug�m s� mai incerca�i o dat�


; *** "Information" wizard pages
WizardInfoBefore=Informa�ii
InfoBeforeLabel=V� rug�m s� citi�i informa�iile urm�toare �nainte de a continua, sunt importante.
InfoBeforeClickLabel=C�nd sunte�i gata s� ini�ia�i instalarea, v� rug�m s� ap�sa�i butonul [Continu�].
WizardInfoAfter=Informa�ii
InfoAfterLabel=V� rug�m s� citi�i informa�iile urm�toare �nainte de a continua, sunt importante.
InfoAfterClickLabel=C�nd sunte�i gata s� ini�ia�i instalarea, v� rug�m s� ap�sa�i butonul [Continu�].


; *** "User Information" wizard page
WizardUserInfo=Informa�ii despre Utilizator
UserInfoDesc=V� rug�m s� completa�i informa�iile cerute.
UserInfoName=&Utilizator:
UserInfoOrg=&Organiza�ie:
UserInfoSerial=Num�r de &Serie:
UserInfoNameRequired=Este necesar� introducerea unui nume.


; *** "Select Components" wizard page
WizardSelectComponents=Selectarea Componentelor
SelectComponentsDesc=V� rug�m s� specifica�i care dintre urm�toarele componente dori�i s� fie instalate?
SelectComponentsLabel2=Selec�ia indic� acele componente care urmeaz� a fi instalate; deselectarea marcheaz� contrariul. Ap�sa�i [Continu�] pentru a trece la urm�torul pas.
FullInstallation=Instalare Complet�
; if possible don't translate 'Compact' as 'Minimal' (I mean 'Minimal' in your language)
CompactInstallation=Instalare Compact�
CustomInstallation=Instalare Personalizat�
NoUninstallWarningTitle=Componentele sunt deje existente
NoUninstallWarning=S-a detectat c� urm�toarele componente sunt deja instalate pe calculator:%n%n%1%n%nDeselectarea lor nu le va dezinstala.%n%nDori�i s� continua�i?
ComponentSize1=%1 KB
ComponentSize2=%1 MB
ComponentsDiskSpaceMBLabel=Configura�ia curent� necesit� cel pu�in [mb] MB spa�iu de stocare.


; *** "Preparing to Install" wizard page
WizardPreparing=Preg�tire pentru Instalare
PreparingDesc=Se preg�te�te pregateste instalarea aplic�iei [name] pe calculator.
PreviousInstallNotCompleted=Instalarea/dezinstalarea anterioar� a unui program nu a fost terminat�. Va fi necesar� repornirea calculatorul pentru a termina opera�ia precedent�.%n%nDup� repornirea calculatorului, rula�i din nou programul de instalare pentru a realiza instalarea aplica�iei [name].
CannotContinue=Instalarea nu poate continua. Ap�sa�i pe [Anuleaz�] pentru a o �nchide.
ApplicationsFound=Aplica�iile urm�toare folosesc fi�iere care trebuiesc actualizate de c�tre aplica�ia de instalare. Se recomand� s� permiti programului de instalare s� �nchid� �n mod automat aplica�iile respective.
ApplicationsFound2=Aplica�iile urm�toare folosesc fi�iere care trebuiesc actualizate de c�tre aplica�ia de instalare. Se recomand� s� permiti programului de instalare s� �nchid� �n mod automat aplica�iile respective. Dupa ce finalizarea instal�rii, se va �ncerca sa repornirea aplica�iilor.
CloseApplications=�nchide aplica�iile �n mod &automat
DontCloseApplications=Nu �nchi&de aplica�iile
ErrorCloseApplications=Programul de instalare nu a putut �nchide �n mod automat toate aplica�iile. �nainte de a continua, se recomand� �nchiderea manual� a tuturor aplica�iilor care folosesc fi�iere ce trebuiesc actualizate �n timpul instal�rii.


; *** "Setup Needs the Next Disk" stuff
ChangeDiskTitle=Instalarea Necesit� Introducerea Discului Urm�tor
SelectDiskLabel2=Introduce�i Discul %1 �i ap�sa�i butonul [OK].%n%nDac� fi�ierele de pe acest disc pot fi g�site �ntr-un alt dosar dec�t cel afi�at mai jos, v� invit�m s� introduce�i calea corecta sau s� folosi�i butonul [Exploreaz�].
PathLabel=&Cale:
FileNotInDir2=Fi�ierul "%1" nu poate fi gasit �n "%2". V� rug�m s� introduce�i discul corect sau s� alege�i alt dosar.
SelectDirectoryLabel=V� rug�m s� specifica�i loca�ia discului urm�tor.


; *** Installation phase messages
SetupAborted=Instalarea nu a fost finalizat�.%n%nDup� corectarea problemei pute�i �ncerca o nou� instalare.
EntryAbortRetryIgnore=Ap�sa�i pe [Re�ncercare] pentru a �ncerca o nou� instalare, pe [Ignorare] pentru a continua cu situa�ia actual�, sau pe [Abandonare] pentru a anula instalarea.


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
FileAbortRetryIgnore=Ap�sa�i pe [Re�ncercare] pentru a �ncerca din nou, pe [Ignorare] pentru a trece peste aceast fi�ier (nerecomandat), sau pe [Abandonare] pentru a anula instalarea.
FileAbortRetryIgnore2=Ap�sa�i pe [Re�ncercare] pentru a �ncerca din nou, pe [Ignorare] pentru a trece peste aceast fi�ier (nerecomandat), sau pe [Abandonare] pentru a anula instalarea.
SourceIsCorrupted=Fi�ierul surs� este corupt
SourceDoesntExist=Fi�ierul surs� "%1" nu exist�
ExistingFileReadOnly=Fi�ierul deja existent nu poate fi scris ("readonly").%n%nAp�sa�i pe [Re�ncercare] pentru a �nl�tura atributul "readonly" �i a �ncerca din nou, pe [Ignorare] pentru a s�ri aceast fi�ier, sau pe [Abandonare] pentru a anula instalarea.
ErrorReadingExistingDest=A aparut o eroare �n timpul citirii fi�ierului deja existent:
FileExists=Fi�ierul exist� deja.%n%Dori�i suprascrierea sa �n timpul instal�rii?
ExistingFileNewer=Fi�ierul deja existent este mai nou dec�t cel care trebuie instalat. Se recomand� versiunea deja existent�.%n%nDori�i p�strarea fi�ierului deja existent?
ErrorChangingAttr=A aparut o eroare �n timpul schimb�rii atributelor fi�ierului deja existent:
ErrorCreatingTemp=A aparut o eroare �n timpul cre�rii fi�ierului �n dosarul de destina�ie:
ErrorReadingSource=A aparut o eroare �n timpul citirii fi�ierului surs�:
ErrorCopying=A aparut o eroare �n timpul copierii fi�ierului:
ErrorReplacingExistingFile=A aparut o eroare �n timpul �nlocuirii fi�ierului deja existent:
ErrorRestartReplace=Repornirea/�nlocuirea a e�uat:
ErrorRenamingTemp=A aparut o eroare �n timpul redenumirii unui fi�ier din dosarul de destina�ie:
ErrorRegisterServer=A aparut o eroare �n timpul �nregist�rii unei componente DLL/OCX: %1
ErrorRegSvr32Failed=RegSvr32 a e�uat, av�nd codul de ie�ire %1
ErrorRegisterTypeLib=Nu pot �nregistra biblioteca de tipuri: %1


; *** Post-installation errors
ErrorOpeningReadme=A aparut o eroare la deschiderea fi�ierului de informare (README).
ErrorRestartingComputer=Programul de instalare nu a putut reporni calculatorul. Va trebui s�-l reporni�i manual.


; *** Shutdown block reasons
ShutdownBlockReasonInstallingApp=Se instaleaz� %1.
ShutdownBlockReasonUninstallingApp=Se dezinstaleaz� %1.

; The custom messages below aren't used by Setup itself, but if you make
; use of them in your scripts, you'll want to translate them.


; *** Uninstaller messages
ConfirmUninstall=Cu siguran�� dori�i dezinstalarea complet� a aplica�iei %1 �i tuturor componentelor sale?
UninstallNotFound=Fi�ierul "%1" nu exist�. Dezinstalarea nu poate fi efectuat�
UninstallOpenError=Fi�ierul "%1" nu poate fi deschis. Dezinstalarea nu poate fi efectuat�
UninstallUnsupportedVer=Fi�ierul "%1" con�in�nd jurnalul de dezinstalare este �ntr-un format nerecunoscut de aceast� versiune a programului de dezinstalare. Dezinstalarea nu poate fi efectuat�
UninstallUnknownEntry=A fost �nt�lnit� o intrare necunoscuta (%1) �n jurnalul de dezinstalare
UninstallOnlyOnWin64=Dezinstalarea poate fi efectuat� doar de pe un sistem Windows pe 64-bi�i.
OnlyAdminCanUninstall=Dezinstalarea poate fi efectuat� doar de catre un utilizator cu drepturi de Administrator.
UninstallStatusLabel=V� rug�m a�tepta�i pentru ca %1 s� fie �nl�turat de pe calculator.
UninstalledAll=Aplica�ia %1 a fost dezinstalat� cu succes de pe calculatorul dumneavoastr�.
UninstalledMost=Dezinstalarea aplica�iei %1 s-a finalizat.%n%nAnumite elemente nu au putut fi eliminate. Acestea pot fi �terse �n mod manual.
UninstalledAndNeedsRestart=Pentru a termina dezinstalarea plica�iei %1, calculatorul trebuie repornit.%n%nDori�i efectuarea acestei opera�ii chiar acum?
UninstallDataCorrupted=Fi�ierul "%1" este corupt. Dezinstalarea nu poate fi efectuat�


; *** Uninstallation phase messages
WizardUninstalling=Situa�ia Dezinstal�rii
StatusUninstalling=Se dezinstaleaz� aplica�ia %1...
ConfirmDeleteSharedFileTitle=�tergere Fi�ier Partajat?
ConfirmDeleteSharedFile2=Sistemul indic� faptul c� urm�torul fi�ier partajat pare sa nu mai fie folosit de vreun alt program. Dori�i �teargerea aceastui fi�ier partajat?%n%nDac� totu�i mai exista programe care folosesc acest fi�ier �i el este �ters, acele programe ar putea s� func�ioneze �n mod eronat. �n caz de incertitudine, alege�i [Nu]. P�strarea fi�ierului �n sistem nu va aduce inconveniente.
SharedFileNameLabel=Nume fi�ier:
SharedFileLocationLabel=Loca�ie:


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
