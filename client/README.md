## 開発

### 開発環境

- Node.js 18
- Angular 16

### ビルド方法

ビルドに成功すると、`dist`直下に静的 HTML ファイルが生成されます。

```sh
$ npm run build
```

### 起動方法

起動に成功すると、[localhost:4200](http://localhost:4200) からアクセスできます。

```sh
$ npm run start
```

### テスト & コードチェック

```sh
# テスト
$ npm run test:ci

# コードチェック
$ npm run check

# コードフォーマット
$ npm run format
```

### 依存関係のアップデート

```sh
# outdatedな依存関係をリストアップ
$ npm outdated

# 依存関係をアップデート
$ npx npm-check --update
```
