#include <Debug.au3>

AutoItSetOption("WinTitleMatchMode","2")


While 1
   WinWait("Authentication Required","","1")
   $hdl =WinActivate("Authentication Required")
   if $hdl <> 0 Then
	  $title = WinGetTitle("Authentication Required") ; retrives whole window title
	  $UN=WinGetText($title,"User Name:")
	  ControlSend($title,"",$UN,  $CmdLine[1]);Sets Username
	  $PWD=WinGetText($title,"Password:")
	  Send("{TAB 1}")
	  ControlSend($title,"",$PWD, $CmdLine[2]);Sets PWD
	  Send("{ENTER}")
   EndIf
   WinWait("data:, - Google Chrome","","1") ; this is the name of the window, according to AUTOIT v3 window info
   $hdl =WinActivate("data:, - Google Chrome")
   If $hdl <> 0 Then
	  WinActivate("data:, - Google Chrome") ; set control to the window for proxy authentication
	  Send("bharat.sethi@impellam.com{TAB}") ; send username and press TAB
	  WinActivate("data:, - Google Chrome") ; again set control to our window, in case that we have clicked somewhere else
	  Send("Clarity2{ENTER}") ; send the password and press enter
   EndIf
   WinWait("[CLASS:#32770]","",5)
   $hdl =WinActivate("[CLASS:#32770]")
   If $hdl <> 0 Then
	  ControlFocus("[CLASS:#32770]","","Edit1")
	  Sleep(500)
	  ControlSetText("[CLASS:#32770]","","Edit1","c:\temp\Test1.pdf")
	  Sleep(500)
	  ControlClick("[CLASS:#32770]","","Button1")
	EndIf
	Sleep(1000)
WEnd

