# todolist

[![Circle CI](https://circleci.com/gh/takatori-task/todolist.svg?style=svg)](https://circleci.com/gh/takatori-task/todolist)


## Description
ScalaとPlayframework練習用アプリケーション
Github認証によるログインが可能

***DEMO:***
- [heroku](https://takatori-todolist.herokuapp.com/)
![Demo](public/images/todolist.png)

## Requirement
- Scala 2.11
- Play 2.4
- sbt 0.13.9
- MySQL 5.6


## Installation(Mac)
### MySQL起動
```
$ mysql.server start
```
以下のようなエラーが発生した場合は
- sudoで実行してみる
- PIDファイルやディレクトリの所有者をmysqlに変更する
- PIDファイルを削除する
```
. ERROR! The server quit without updating PID file
```

### database作成
```
$ mysql
mysql > create database todolist;
```

### リポジトリのクローン
```
$ git clone https://github.com/takatori-task/todolist.git
$ cd todolist
```

### コンパイル
```
$ sbt compile
```

### 実行
```
$ sbt run
```
localhost:9000にアクセス

### テスト実行
```
$ sbt test
```


## Author

[takatori](https://github.com/takatori)

