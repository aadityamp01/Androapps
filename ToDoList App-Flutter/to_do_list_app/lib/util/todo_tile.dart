import 'package:flutter/material.dart';
import 'package:flutter_slidable/flutter_slidable.dart';

class ToDoTile extends StatelessWidget { 
  final String TaskName;
  final bool TaskCompleted;
  Function(bool?)? onChanged;
  Function(BuildContext)? deleteFunction;

  ToDoTile({
    super.key,
    required this.TaskName,
    required this.TaskCompleted,
    required this.onChanged,
    required this.deleteFunction,
  });
  
  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.fromLTRB(20,20,20,0),
      child: Slidable(
        endActionPane: ActionPane(
          motion: StretchMotion(),
          children: [
            SlidableAction(
              borderRadius: BorderRadius.circular(14),
              onPressed: deleteFunction,
              icon: Icons.delete,
              backgroundColor: Colors.red.shade300,
            )
          ],
        ),
        child: Container(
          padding: const EdgeInsets.all(25),
          decoration:  BoxDecoration(
            color: Colors.white,
            borderRadius: BorderRadius.circular(14),
          ),
          child: Row(
            children: [
              //checkbox
              Checkbox(
                value: TaskCompleted, 
                onChanged: onChanged,
                activeColor: Colors.black,
                ),
      
              //text
              Text(
                TaskName,
                style: TextStyle(decoration: TaskCompleted 
                  ? TextDecoration.lineThrough
                  : TextDecoration.none
                  ),
                ),
            ],
          ),
        ),
      )
      
    );
    
  }
}
 