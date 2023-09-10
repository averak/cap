# cap

![CI](https://github.com/averak/cap/workflows/CI/badge.svg)
![Build](https://github.com/averak/cap/workflows/Build/badge.svg)
![version](https://img.shields.io/badge/version-1.0.0--SNAPSHOT-blue.svg)

## 概要

軽量サーバレスアプリケーション群を統合できる API サーバ & マネジメントコンソールです。
API ゲートウェイと異なり、以下の特徴を持ちます。

- Docker コンテナの払い出し/デプロイメント機能を提供
- アプリケーションごとのバッチジョブを cron 式でトリガーできる

汎用的な利用は想定しておらず、作者が自身のために開発しているものです。

## 開発

### 開発環境

- Java OpenJDK 17
- Spring Boot 3.0
- MySQL 8.0
- Redis 7.0
- Docker Compose V2

### ビルド方法

ビルドに成功すると、`build/libs`直下に`.jar`ファイルが生成されます。

```shell
$ ./gradlew bootJar
```

### 起動方法

デフォルトで使用されるポート番号は`8080`です。`-Dserver.port=XXXX`オプション、もしくは`PORT`環境変数をセットすることでポート番号を変更できます。

```shell
$ docker compose up -d
$ ./gradlew bootRun
```

### データベースマイグレーション

[src/main/resource/db/migration](src/main/resources/db/migration) でマイグレーションファイルを管理しています。

```shell
# マイグレーションファイルを実行（アプリケーション/テスト実行時に自動で実行される）
$ ./gradlew flywaymigrate
```

### 依存関係のアップデート

[Gradle Versions Plugin](https://github.com/ben-manes/gradle-versions-plugin) を使って、outdated な依存関係をアップデートします。

下記コマンドの実行後、出力されたレポートに従って [build.gradle.kts](./build.gradle.kts) に記載されたバージョンを書き換えてください。

```shell
$ ./gradlew dependencyUpdates -Drevision=release
```

### コードフォーマット

```shell
$ ./gradlew spotlessApply
```
