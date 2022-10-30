import 'package:flutter/material.dart';
import 'package:hive/hive.dart';
import 'package:to_do/data/database.dart';
import 'package:to_do/util/dialog_box.dart';
import 'package:to_do/util/todo_tile.dart';
import 'package:hive_flutter/hive_flutter.dart';


class HomePage extends StatefulWidget {
  const HomePage({super.key});

  @override
  State<HomePage> createState() => _HomePageState();
}


class _HomePageState extends State<HomePage> {
  
  
  //reference hive box
  final _myBox = Hive.box('mybox');
  ToDoDataBase db = ToDoDataBase();
  
  @override
  void initState() {

    //if its first time ever opening the app then create default data 
    if(_myBox.get("TODOLIST") == null){
      db.createInitialData();
    }
    else {
      //there already exists data
      db.loadData();
    }


    super.initState();
  }
  
  //text controller 
  final _controller = TextEditingController();

  //checkbox tapped

  void checkBoxChanged(bool? value, int index) {
    setState(() {
      db.toDoList[index][1] = !db.toDoList[index][1];
    });
    db.updateDataBase();
  }
  
  // save new task 
  void saveNewTask() {
    setState(() {
      db.toDoList.add(
        [_controller.text, false]
      );
      _controller.clear();
      Navigator.of(context).pop();
    });
    db.updateDataBase();
  }

  //create new task method
  void createNewTask(){
    showDialog(
      context: context,
      builder: (context){
        return DialogBox(
          Controller: _controller,
          onSave: saveNewTask,
          onCancel: () => Navigator.of(context).pop(),
        );
      },
    );
  }

  //delete Task 
  void deleteTask(int index) {
    setState(() {
      db.toDoList.removeAt(index);
    });
    db.updateDataBase();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: Colors.black,
      appBar: AppBar(
        centerTitle: true,
        title: Text('TO DO'),
        elevation: 0,
      ),
      floatingActionButton: FloatingActionButton(
        child: Icon(Icons.add),
        backgroundColor: Colors.blue,
        onPressed: createNewTask
        ),
      body: ListView.builder(
        itemCount: db.toDoList.length,
        itemBuilder: (context, index) {
          return ToDoTile(
            TaskName: db.toDoList[index][0],
            TaskCompleted: db.toDoList[index][1],
            onChanged: (value) => checkBoxChanged(value, index),
            deleteFunction: (context) => deleteTask(index),
            );
        },
      ),
    );
  }
}