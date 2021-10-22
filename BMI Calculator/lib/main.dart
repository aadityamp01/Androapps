import 'package:flutter/material.dart';
import 'First_page.dart';

void main() => runApp(BMICalculator());

class BMICalculator extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      theme: ThemeData(
          appBarTheme: AppBarTheme(color: const Color(0xFF000000)),
          //primaryColor: Color(0xFF45B097),
          scaffoldBackgroundColor: Color(0xFF000000),
          accentColor: Colors.lightGreen),
      home: InputPage(),
    );
  }
}
