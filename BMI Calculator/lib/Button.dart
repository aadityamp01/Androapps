import 'package:flutter/material.dart';

class RoundIconButton extends StatelessWidget {
  RoundIconButton({required this.icon, required this.onpress});
  final IconData icon;
  final VoidCallback onpress;
  @override
  Widget build(BuildContext context) {
    return RawMaterialButton(
      child: Icon(icon),
      onPressed: onpress,
      constraints: BoxConstraints.tightFor(width: 50.0, height: 50.0),
      elevation: 19.0,
      shape: CircleBorder(),
      fillColor: Colors.white38,
    );
  }
}
