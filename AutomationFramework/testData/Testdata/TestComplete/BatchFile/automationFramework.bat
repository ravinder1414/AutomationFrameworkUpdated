echo "Calling Automation framework for generating report and status update into Spira/Excel"
echo off
set AUTOMATION_FRAMEWORK="C:\AutomationFramework"
set projectId=%1
set releaseID=%2
set executionStartTime=%3
set testCompleteStatus=%4
set csvFilePath=%5
set excelFilePath=%6
set integrationFlag=%7
cd %AUTOMATION_FRAMEWORK%
REM ProjectId,ReleaseID,executionStartTime,testCompleteStatus,csvFilePath,excelFilePath,integrationFlag
java -jar AutomationFramework.jar %projectId% %releaseID% %executionStartTime% %testCompleteStatus% %csvFilePath% %excelFilePath% %integrationFlag%
@pause