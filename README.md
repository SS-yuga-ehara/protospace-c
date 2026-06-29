# プロジェクトの立ち上げ方法

```
docker compose up -d
```

# Git 運用

- GitHub Flow
- main protected ルールにより下記を強制
    - main直接push禁止
    - PR必須（1approval以上必須）
    - squash merge

## ブランチ

- feature/＜issue番号＞-＜簡単な機能名＞ のような命名とする
- 日本語は使用しない
- マージ後削除

参考：https://qiita.com/izutani/items/5397dac2b2c1e2079261

## PR

- テンプレートを活用して PR の内容を記述

## コミット

- prefix（feat, fix など）を活用して、どんなコミットかわかるようにすること
例：feat: ログイン画面を実装

参考：https://qiita.com/muranakar/items/20a7927ffa63a5ca226a

## 開発ルール

- 30分詰まったら相談

