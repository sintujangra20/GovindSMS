# Releasing Govind SMS

## One-time setup: signing key

```bash
keytool -genkeypair -v -storetype PKCS12 -keystore release.keystore \
  -alias messages -keyalg RSA -keysize 4096 -validity 10000
base64 -w0 release.keystore > release.keystore.b64     # macOS: base64 -i release.keystore
```

Use the **same password** for the keystore and the key (PKCS12 requires it).
Back up `release.keystore` somewhere safe: if it is lost, installed users can
never receive an update. Never commit it.

GitHub -> repo -> Settings -> Secrets and variables -> Actions -> New repository secret:

| Secret | Value |
|---|---|
| `RELEASE_KEYSTORE_BASE64` | contents of `release.keystore.b64` |
| `KEYSTORE_PASSWORD` | the keystore password |
| `KEY_PASSWORD` | the key password (same as above for PKCS12) |
| `KEY_ALIAS` | optional, only if the alias is not `messages` |

## Build a release

Actions -> **Build & Release APK** -> **Run workflow** (or push a tag `v1.0.1`).
The release gets: the signed APK, `.sha256`, `.build-info.json`, and
`.attestation.json` (GitHub build-provenance bundle).

## Verify an APK came from this source

```bash
gh attestation verify Govind-SMS-v1.0.0.apk --repo sintujangra20/govindsms
sha256sum -c Govind-SMS-v1.0.0.apk.sha256
```
