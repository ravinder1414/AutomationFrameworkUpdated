#include <Array.au3>
#include <Debug.au3>
HotKeySet("{ESC}", "_Quit")

Global $FF_PID, $avMozList, $sMsg
$FF_PID = ProcessExists("firefox.exe")
If $FF_PID <> -1 Then
    ConsoleWrite("Firefox PID = " & $FF_PID & @LF)
    if (ProcessExists($FF_PID)) Then
        ; List all Mozilla windows
        $avMozList = WinList("[REGEXPCLASS:Mozilla.+]", "")

        ; Delete non-Firefox and non-visible Mozilla windows from list
        For $n = $avMozList[0][0] To 1 Step -1
		   ConsoleWrite("Dialog List = " & $avMozList[$n][0] & @LF)
            If (WinGetProcess($avMozList[$n][1], "") = $FF_PID) And _
                    BitAND(WinGetState($avMozList[$n][1]), 0x2) Then
					Local $iPosition = StringInStr($avMozList[$n][0], "Opening")
					 if ($iPosition > 0) Then
						ConsoleWrite("Click on  " & $avMozList[$n][0] & @LF)
						; wait for 8 seconds to appear download and save dialog. Used class property of download dialog.
						;WinWait("[CLASS:#MozillaDialogClass]","",8)
						WinActivate($avMozList[$n][0])
						Sleep(1000)
						; Perform keyboard ALT key down + s + ALT key Up action to select Save File Radio button using keyboard sortcut.
						Send("{ALTDOWN}s{ALTUP}")
						; Wait for 3 seconds
						Sleep(3000)
						; Press Keyboard ENTER button.
						Send("{ENTER}")
						ExitLoop
					 EndIf
					 $iPosition = StringInStr($avMozList[$n][0], "Authentication Required")
					 if ($iPosition > 0) Then
						ConsoleWrite("Click on  " & $avMozList[$n][0] & @LF)
						; wait for 8 seconds to appear download and save dialog. Used class property of download dialog.
						;WinWait("[CLASS:#MozillaDialogClass]","",8)
						WinActivate($avMozList[$n][0])
						Sleep(1000)
						; Perform keyboard ALT key down + s + ALT key Up action to select Save File Radio button using keyboard sortcut.
						Send("bharat.sethi{TAB}")
						Send("Clarity3{TAB}")
						; Wait for 3 seconds
						Sleep(3000)
						; Press Keyboard ENTER button.
						Send("{ENTER}")
						ExitLoop
					 EndIf
                ContinueLoop
			 Else
				  _ArrayDelete($avMozList, $n)
            EndIf
        Next
        $avMozList[0][0] = UBound($avMozList) - 1

        ; Display results
        ;$sMsg = $avMozList[0][0] & " Firefox windows:" & @CRLF
        ;For $n = 1 To $avMozList[0][0]
        ;    $sMsg &= @TAB & $n & " = " & $avMozList[$n][0] & " (" & $avMozList[$n][1] & ")" & @CRLF
						; Retrieve the character position of where the string 'white' first occurs in the sentence.
		;	Local $iPosition = StringInStr($sMsg, "Opening Adhoc")
			;if ($iPosition > 0) Then
			;   ConsoleWrite("Click on  " & $sMsg & @LF)
			   ; wait for 8 seconds to appear download and save dialog. Used class property of download dialog.
			   ;WinWait("[CLASS:#MozillaDialogClass]","",8)
			;   WinActivate($avMozList[$n][0])
			   ; Perform keyboard ALT key down + s + ALT key Up action to select Save File Radio button using keyboard sortcut.
			;   Send("{ALTDOWN}s{ALTUP}")
			   ; Wait for 3 seconds
			;   Sleep(3000)
			;   ExitLoop
			   ; Press Keyboard ENTER button.
			;   Send("{ENTER}")
			;EndIf
		 ;Next
		 ;ConsoleWrite("Dialog header = " & $sMsg & @LF)
        ;ToolTip($sMsg)
        ;Sleep(250)
    EndIf
Else
    ConsoleWrite("Firefox is not running")
    Exit
EndIf

Func _Quit()
    Exit
EndFunc   ;==>_Quit