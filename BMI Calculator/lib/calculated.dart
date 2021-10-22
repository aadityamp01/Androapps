import 'package:bmi_calculator/main.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'cards.dart';

class result extends StatelessWidget {
  result(
      {required this.BmiResult,
      required this.ResultText,
      required this.FeedbackText});

  final String BmiResult;
  final String ResultText;
  final String FeedbackText;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Center(
            child: Text(
          'BMI Calculator',
          style: TextStyle(
            fontFamily: 'Caveat',
            fontSize: 40,
          ),
        )),
      ),
      body: Column(
        crossAxisAlignment: CrossAxisAlignment.stretch,
        children: [
          Center(
            child: Container(
              padding: EdgeInsets.all(15.0),
              child: Text(
                'Your BMI is',
                style: TextStyle(
                    fontSize: 30.0,
                    fontWeight: FontWeight.w600,
                    color: Colors.grey),
              ),
            ),
          ),
          Expanded(
            flex: 2,
            child: Box(
              colour: Color(0xFF262740),
              cardChild: Column(
                mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                crossAxisAlignment: CrossAxisAlignment.center,
                children: [
                  Text(
                    ResultText,
                    style: TextStyle(
                        color: Color(0xFF00E676),
                        fontWeight: FontWeight.bold,
                        fontSize: 30.0),
                  ),
                  Text(
                    BmiResult,
                    style: TextStyle(
                        fontSize: 75.0,
                        fontWeight: FontWeight.bold,
                        color: Color(0xFFEEEAEA)),
                  ),
                  Text(
                    FeedbackText,
                    style: TextStyle(fontSize: 22.0, color: Colors.white),
                    textAlign: TextAlign.center,
                  ),
                ],
              ),
            ),
          )
        ],
      ),
    );
  }
}
