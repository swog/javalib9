@echo off
javac src/Person/*.java src/Collection/*.java src/Content/*.java src/LibraryFileReader/*.java src/Identifier/*.java
java src/LibraryFileReader/PersonFileReader
pause