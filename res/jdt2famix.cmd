@echo off
setLocal EnableDelayedExpansion

set BASEPATH=%~dp0
set CLASSPATH=!BASEPATH!
for %%a in (!BASEPATH!\*.jar) do (
   set CLASSPATH=!CLASSPATH!;%%a
)

java -cp "!CLASSPATH!" com.feenk.jdt2famix.injava.Main
