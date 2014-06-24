; Script generated by the Inno Setup Script Wizard.
; SEE THE DOCUMENTATION FOR DETAILS ON CREATING INNO SETUP SCRIPT FILES!

#define MyAppName "Pondero"
#define MyAppVersion "0.1"
#define MyAppPublisher "Mindtrips Communications"
#define MyAppURL "http://www.purl.org/net/pondero/home"
#define MyAppExeName "pondero.bat"
#define PonderoRoot "F:\Workspace\Pondero"

[Setup]
; NOTE: The value of AppId uniquely identifies this application.
; Do not use the same AppId value in installers for other applications.
; (To generate a new GUID, click Tools | Generate GUID inside the IDE.)
AppId={{19A9B2D8-1B89-4136-9006-BE0A30FF834B}
AppName={#MyAppName}
AppVersion={#MyAppVersion}
;AppVerName={#MyAppName} {#MyAppVersion}
AppPublisher={#MyAppPublisher}
AppPublisherURL={#MyAppURL}
AppSupportURL={#MyAppURL}
AppUpdatesURL={#MyAppURL}
DefaultDirName=D:\{#MyAppName}
DefaultGroupName={#MyAppName}
LicenseFile={#PonderoRoot}\res\lgpl-brief.ro.txt
OutputDir={#PonderoRoot}
OutputBaseFilename=pondero-setup
Compression=lzma
SolidCompression=yes

[Languages]
Name: "romanian"; MessagesFile: "{#PonderoRoot}\Romanian.isl"

[Tasks]
Name: "desktopicon"; Description: "{cm:CreateDesktopIcon}"; GroupDescription: "{cm:AdditionalIcons}"; Flags: unchecked

[Files]
Source:   "{#PonderoRoot}\pondero.bat";                DestDir: "{app}";       Flags: ignoreversion
Source:   "{#PonderoRoot}\bin\pondero.jar";            DestDir: "{app}\bin";   Flags: ignoreversion
Source:   "{#PonderoRoot}\bin\pondero-libs.jar";       DestDir: "{app}\bin";   Flags: ignoreversion
Source:   "{#PonderoRoot}\bin\pondero-install.jar";    DestDir: "{app}\bin";   Flags: ignoreversion
Source:   "{#PonderoRoot}\res\pondero-48x48.ico";      DestDir: "{app}\res";   Flags: ignoreversion
Source:   "{#PonderoRoot}\res\pondero-license.txt";    DestDir: "{app}\res";   Flags: ignoreversion
Source:   "{#PonderoRoot}\tests\pondero-test-ic.jar";  DestDir: "{app}\tests"; Flags: ignoreversion
; Source:   "{#PonderoRoot}\jre\*";                      DestDir: "{app}\jre";   Flags: ignoreversion recursesubdirs createallsubdirs
; NOTE: Don't use "Flags: ignoreversion" on any shared system files

[Dirs]
Name: "{app}\results"

[Icons]
Name: "{group}\{#MyAppName}";         Filename: "{app}\{#MyAppExeName}";         IconFilename: "{app}\res\pondero-48x48.ico"
Name: "{group}\Licenta";              Filename: "{app}\res\pondero-license.txt"
Name: "{group}\Test Results";      Filename: "{app}\results"
Name: "{commondesktop}\{#MyAppName}"; Filename: "{app}\{#MyAppExeName}"; IconFilename: "{app}\res\pondero-48x48.ico"; Tasks: desktopicon
; Name: "{group}\{cm:UninstallProgram,{#MyAppName}}"; Filename: "{uninstallexe}"

[Run]
Filename: "{app}\{#MyAppExeName}"; Description: "{cm:LaunchProgram,{#StringChange(MyAppName, '&', '&&')}}"; Flags: shellexec postinstall skipifsilent

