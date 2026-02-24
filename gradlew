#!/usr/bin/env sh
set -e

if [ -x "./gradle/wrapper/gradle-wrapper.jar" ]; then
  :
fi

if command -v gradle >/dev/null 2>&1; then
  exec gradle "$@"
else
  echo "Gradle is required. Install gradle or generate wrapper with 'gradle wrapper'." >&2
  exit 1
fi
