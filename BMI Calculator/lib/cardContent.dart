import 'package:flutter/material.dart';

class iconMaterial extends StatelessWidget {
  iconMaterial(
      {required this.icon, required this.gender, required this.colour});
  final IconData icon;
  final String gender;
  final Color colour;
  @override
  Widget build(BuildContext context) {
    return Column(
      mainAxisAlignment: MainAxisAlignment.center,
      children: [
        Icon(
          icon,
          size: 85.0,
          color: colour,
        ),
        SizedBox(
          height: 10.0,
        ),
        Text(
          gender,
          style: TextStyle(color: Colors.grey, fontSize: 20.0),
        )
      ],
    );
  }
}
