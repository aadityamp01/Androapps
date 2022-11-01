// ignore_for_file: prefer_const_constructors

import 'package:flutter/material.dart';

import 'homepage.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      home: HomePage(),
      theme: ThemeData(
        textTheme: TextTheme(
          // bodyText1: TextStyle(
          //   fontSize: 40
          // )
        )
      ),
    );
  }
}