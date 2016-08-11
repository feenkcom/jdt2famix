@echo off
setLocal EnableDelayedExpansion
set BASEPATH=%~dp0
echo !BASEPATH!
set CLASSPATH="!BASEPATH!
for %%a in (!BASEPATH!\*.jar) do (
   set CLASSPATH=!CLASSPATH!;%%a
 )
set CLASSPATH=!CLASSPATH!"
echo !CLASSPATH!
java -cp !CLASSPATH! com.feenk.jdt2famix.injava.Main
 