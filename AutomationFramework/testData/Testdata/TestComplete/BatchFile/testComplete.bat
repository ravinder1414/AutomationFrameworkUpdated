echo "TestComplete is called for execution"
echo off
set projectId=%1
set releaseID=%2
set executionStartTime=%3
set csvFilePath=%4
set excelFilePath=%5
set integrationFlag=%6

"C:\Program Files\SmartBear\TestComplete 12\Bin\TestComplete.exe" "C:\AutomationFramework\TestComplete\TestCompleteProject\TestProject1\TestProject1.pjs" /r /p:TestProject1 projectId releaseID executionStartTime csvFilePath excelFilePath integrationFlag