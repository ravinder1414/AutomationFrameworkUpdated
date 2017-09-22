REM --------------------------------------------------------------------------------------------------------
REM If we will pass the column list, then both csv will compare based on the column we have passed as argument
EvoExlCompare "C:\CsvFiles\A2.csv" "Job Title,End Date" "C:\CsvFiles\Adhoc.csv" "Job Title3,End Date"

REM --------------------------------------------------------------------------------------------------------
REM Based on row count subset and super set will be decided, as you can see in first command A2.CSV is first parameter and in second it is last paramater 
EvoExlCompare "C:\CsvFiles\A2.csv" "" "C:\CsvFiles\Adhoc.csv" ""
EvoExlCompare "C:\CsvFiles\Adhoc.csv" "" "C:\CsvFiles\A2.csv" ""

REM --------------------------------------------------------------------------------------------------------
REM In Adhoc.csv have "Job Title3" column where as A2.csv "Job Title" and mapping between them store in "ColumnMapping.txt" available under EvoExlCompare appliction folder
EvoExlCompare "C:\CsvFiles\A2.csv" "" "C:\CsvFiles\Adhoc.csv" ""

REM --------------------------------------------------------------------------------------------------------
ECHO Below will show error message if both csv columns are different
EvoExlCompare "C:\CsvFiles\AllSubmittedCandidates.csv" "" "C:\CsvFiles\ExistingExport.csv" ""

REM --------------------------------------------------------------------------------------------------------
ECHO Below is showing correct loading of AllSubmittedCandidates.csv
EvoExlCompare "C:\CsvFiles\AllSubmittedCandidates.csv" "" "C:\CsvFiles\AllSubmittedCandidates_part.csv" ""

REM --------------------------------------------------------------------------------------------------------
ECHO Below is showing correct loading of ExistingExport.csv
EvoExlCompare "C:\CsvFiles\ExistingExport.csv" "" "C:\CsvFiles\ExistingExport_part.csv" ""

REM --------------------------------------------------------------------------------------------------------
ECHO Below is showing correct loading of 20160729095647_JLSB900040.csv
EvoExlCompare "C:\CsvFiles\20160729095647_JLSB900040.csv" "" "C:\CsvFiles\20160729095647_JLSB900040_Part.csv" ""


EvoExlCompare "C:\CsvFiles\20160729095647_JLSB900040.csv" "'Job Title=c:Job Title2'@Next'Age=v:30'@Next'Age>c:Age 2'@Next'Age<v:35'"

EvoExlCompare "C:\CsvFiles\SingleFile.csv" "Name=c:Name2 @Next Age1=v:31 @Next Age1>c:Age2"
EvoExlCompare "C:\CsvFiles\SingleFile.csv" "Name=c:Name2 @Next Age1=v:31" "" ""
EvoExlCompare "C:\CsvFiles\SingleFile.csv" "DOB1=c:DOB2"

Name,Age1,Name2,Age2
=
>
<
>=
<=
!=