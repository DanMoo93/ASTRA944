# FinalAstra RS3 Server (Kotlin)

FinalAstra is a Kotlin + Netty RS3 server scaffold with login, packet routing, JS5 cache hooks, and utility tooling.

## Features
- Netty server bootstrap on port `43594`
- TOML server configuration (`data/config/server.toml`)
- RSA key generation tool
- Client download/patch placeholders
- Cache downloader placeholder with multithreading

## Setup
1. Generate RSA keys:
   ```bash
   ./gradlew runTool --args="rsa-key-generator"
   ```
2. Copy original launcher to:
   - `data/launchers/win/original.exe`
3. Patch launcher:
   ```bash
   ./gradlew runTool --args="client-patcher"
   ```
4. Download cache placeholders:
   ```bash
   ./gradlew runTool --args="cache-downloader"
   ```
5. Start server:
   ```bash
   ./gradlew run
   ```

## Troubleshooting
- **Gradle missing**: install Gradle or generate wrapper (`gradle wrapper`).
- **Revision mismatch login**: update `revision` in `data/config/server.toml`.
- **No cache data**: run cache downloader or place cache files in `data/cache`.
