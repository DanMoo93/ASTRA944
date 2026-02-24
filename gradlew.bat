@echo off
where gradle >nul 2>nul
if %ERRORLEVEL% neq 0 (
  echo Gradle is required. Install Gradle or generate wrapper with "gradle wrapper".
  exit /b 1
)
gradle %*
