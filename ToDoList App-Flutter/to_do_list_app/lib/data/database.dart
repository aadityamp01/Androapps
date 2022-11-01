import 'package:hive/hive.dart';
import 'package:hive_flutter/hive_flutter.dart';

class ToDoDataBase {

  List toDoList = [];

  //reference the box
  final _myBox = Hive.box('mybox');

  // run this method if this is 1st time ever opening this app
  void createInitialData() {
    toDoList = [
    ["Complete DBMS Pracs", false],
    ["Seminar PPT and project", false],
    ["100 Pushups", true],
    ];
  }

  //load data from databasse
  void loadData() {
    toDoList = _myBox.get("TODOLIST");
  }

  //udpate the database 
  void updateDataBase() {
    _myBox.put("TODOLIST", toDoList);
  }
}