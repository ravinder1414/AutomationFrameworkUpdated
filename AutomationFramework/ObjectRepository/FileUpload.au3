;~ ControlFocus("Open", "", "Edit1")
;~ ControlSetText("Open", "", "Edit1", $CmdLine[1])
;~ Sleep(500)
;~ ControlClick("Open", "", "Button1")
;~ Sleep(500)



#include <Debug.au3>

;~ AutoItSetOption("WinTitleMatchMode","2")

Global $WA_PID
Global $iCount = 0

$WA_PID = ProcessExists("FileUpload.exe")
	if (ProcessExists($WA_PID)) Then
		$iCount = 0
			WinWait("File Upload","","1")
			$hdl =WinActivate("File Upload")
			if $hdl <> 0 Then
				ControlFocus("File Upload", "", "Edit1")
				ControlSetText("File Upload", "", "Edit1", $CmdLine[1])
				Sleep(500)
				ControlClick("File Upload", "", "Button1")
				Sleep(500)
			EndIf
				WinWait("Open, - Google Chrome","","1") ; this is the name of the window, according to AUTOIT v3 window info
				$hdl =WinActivate("Open, - Google Chrome")
			If $hdl <> 0 Then
				ControlFocus("Open", "", "Edit1")
				ControlSetText("Open", "", "Edit1", $CmdLine[1])
				Sleep(500)
				ControlClick("Open", "", "Button1")
				Sleep(500)
			EndIf
				WinWait("Choose File to Upload","","5")
				$hdl =WinActivate("Choose File to Upload")
			If $hdl <> 0 Then
				ControlFocus("Choose File to Upload", "", "Edit1")
				ControlSetText("Choose File to Upload", "", "Edit1", $CmdLine[1])
				Sleep(500)
				ControlClick("Choose File to Upload", "", "Button1")
				Sleep(500)
			EndIf
			Sleep(1000)
	EndIf