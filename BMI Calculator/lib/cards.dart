import 'package:flutter/material.dart';

class Box extends StatelessWidget {
  Box({required this.cardChild, required this.colour});
  final Color colour;
  final Widget cardChild;

  @override
  Widget build(BuildContext context) {
    return GestureDetector(
      child: Container(
        child: cardChild,
        decoration: BoxDecoration(
            borderRadius: BorderRadius.circular(12.0), color: colour),
        margin: EdgeInsets.all(20.0),
      ),
    );
  }
}
