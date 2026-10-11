# Notice

**Govind SMS** (`shri.govind.sms`)
Maintained by Shri Govind Jangra - https://github.com/sintujangra20/govindsms

This program is a modified version of **Messages** by **an1ndra**
(https://github.com/an1ndra/Messages, version 1.0.27 source), licensed under the
GNU General Public License v3.0. The full license text is in the `LICENSE` file.
All original copyright and license notices remain in force.

## Modifications (October 2026)

- Application name changed to "Govind SMS"; application id changed to `shri.govind.sms`; version numbering restarted at 1.0.0.
- Notification "Copy code" action for one-time passwords (copies the code, removes the notification and marks the thread read).
- Multi-select, select-all, bulk archive and bulk delete in the conversation list.
- New green + orange colour theme (replaces the original blue palette) in `ui/theme/Theme.kt`, splash colours and launcher icon.
- New launcher icon (Krishna ji artwork on a green-to-orange background; AI-generated image supplied by the maintainer; files `res/mipmap-*/ic_launcher_krishna.png`, `res/drawable/ic_launcher_background.xml`).
- New GitHub Actions workflow (`.github/workflows/build-release.yml`) that builds, signs and publishes the APK with a build-provenance attestation; the original release, F-Droid and scan automation was removed or made manual.

## License

Govind SMS is distributed under the same license as the original program: the
GNU General Public License v3.0 (see `LICENSE`). Modifications are
Copyright (C) 2026 Shri Govind Jangra and are released under the same GPL-3.0
terms. The original copyright and license notices of **an1ndra** and of the
included third-party components remain in force. The `LICENSE` file is not changed.

Source code for every released APK is this repository at the commit recorded in the release's `build-info.json`.
