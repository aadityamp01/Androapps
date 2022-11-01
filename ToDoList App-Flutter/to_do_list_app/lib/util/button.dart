import 'package:flutter/material.dart';

class Button extends StatelessWidget{
  final String button_name;
  VoidCallback onPressed;

  Button({
    super.key, 
    required this.button_name, 
    required this.onPressed});

  @override
  Widget build(Object context) {
    return MaterialButton(
      onPressed: onPressed,
      child: Text(button_name),
      color: Colors.blue,
    );
  }

}