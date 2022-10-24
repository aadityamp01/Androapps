import 'package:flutter/material.dart';
import 'package:to_do/util/button.dart';

class DialogBox extends StatelessWidget {
  final Controller;
  VoidCallback onSave;
  VoidCallback onCancel;

  DialogBox({
    super.key, 
    required this.Controller,
    required this.onSave,
    required this.onCancel,
  });

  @override
  Widget build(BuildContext context) {
    return AlertDialog(
      backgroundColor: Colors.yellow[300],
      content: Container(
        decoration: BoxDecoration(
          borderRadius: BorderRadius.circular(25)
        ),
        height: 120,
        child: Column(
          mainAxisAlignment: MainAxisAlignment.spaceEvenly,
          children: [
            //get user input
            TextField(
              controller: Controller,
              decoration: InputDecoration(
                border: OutlineInputBorder(
                  borderRadius: BorderRadius.circular(10)
                ),
                hintText: "Add a new task"
            ),
            ),
            //buttons - save and cancel
            Row(
              
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                //save button
                Button(button_name: "Save", onPressed: onSave),

                SizedBox(width: 10,),
                //cancel button
                Button(button_name: "Cancel", onPressed: onCancel),
              ],
            )
          ],
        ),
      ),
    );
  }
  
}