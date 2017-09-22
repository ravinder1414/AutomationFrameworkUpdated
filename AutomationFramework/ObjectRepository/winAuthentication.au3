#include <Debug.au3>

AutoItSetOption("WinTitleMatchMode","2")

Global $WA_PID
Global $iCount = 0

$WA_PID = ProcessExists("winAuthentication.exe")
If $WA_PID <> -1 Then
	ConsoleWrite("Browser authentication PID = " & $WA_PID)
	if (ProcessExists($WA_PID)) Then
		$iCount = 0
		While 1
;~ 			If $iCount > 1 Then
;~ 				ConsoleWrite("Username or Password is incorrect")
;~ 				Exit(0)
;~ 			EndIf
			WinWait("Authentication Required","","1")
			$hdl =WinActivate("Authentication Required")
			if $hdl <> 0 Then
			  $title = WinGetTitle("Authentication Required") ; retrives whole window title
			  $UN=WinGetText($title,"User Name:")
			;  ControlFocus($title, "", "User Name:")
			  ControlSend($title,"",$UN, $CmdLine[1]);Sets Username
			  $PWD=WinGetText($title,"Password:")
			  Send("{TAB 1}")
			;  ControlFocus($title, "", "Password:")
			  ControlSend($title,"",$PWD,$CmdLine[2]);Sets PWD
			  Sleep(500)
			  Send("{ENTER}")
			  $iCount = $iCount + 1
			EndIf
				WinWait("data:, - Google Chrome","","1") ; this is the name of the window, according to AUTOIT v3 window info
				$hdl =WinActivate("data:, - Google Chrome")
			If $hdl <> 0 Then
				WinActivate("data:, - Google Chrome") ; set control to the window for proxy authentication
				Send($CmdLine[1]) ; send username and press TAB
				Send("{TAB 1}")
				WinActivate("data:, - Google Chrome") ; again set control to our window, in case that we have clicked somewhere else
				Send($CmdLine[2]) ; send the password and press enter
				$iCount = $iCount + 1
			EndIf
				WinWait("Windows Security","","5")
				$hdl =WinActivate("Windows Security")
			If $hdl <> 0 Then
				ControlFocus("Windows Security","","Edit1")
				Sleep(500)
				ControlSetText("Windows Security","","Edit1",$CmdLine[1])
				Sleep(500)
				ControlFocus("Windows Security","","Edit2")
				Sleep(500)
				ControlSetText("Windows Security","","Edit2",$CmdLine[2])
				Sleep(500)
				ControlClick("Windows Security","","Button2")
				$iCount = $iCount + 1
			EndIf
			Sleep(1000)
		WEnd
	EndIf
EndIf

