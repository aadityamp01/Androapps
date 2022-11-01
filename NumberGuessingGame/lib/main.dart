import 'package:flutter/material.dart';
import 'dart:math';

void main() => runApp(const MyApp());

class MyApp extends StatelessWidget {
  const MyApp({Key? key}) : super(key: key);

  static const String _title = 'Number Guessing Game';

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: _title,
      home: Scaffold(
        appBar: AppBar(title: const Text(_title)),
        body: const MyStatefulWidget(),
        backgroundColor: Colors.amber[50],
      ),
    );
  }
}

class MyStatefulWidget extends StatefulWidget {
  const MyStatefulWidget({Key? key}) : super(key: key);

  @override
  State<MyStatefulWidget> createState() => _MyStatefulWidgetState();
}

Random random = new Random();
int randomNumber = random.nextInt(100);
var result = "Make your Guess";
var counter = 10;
var res = "";

class _MyStatefulWidgetState extends State<MyStatefulWidget> {
  TextEditingController guessController = TextEditingController();

  @override
  Widget build(BuildContext context) {
    return Padding(
        padding: const EdgeInsets.all(10),
        child: ListView(
          children: <Widget>[
            Container(
                alignment: Alignment.center,
                padding: const EdgeInsets.all(10),
                child: const Text(
                  'Number Guessing Game',
                  style: TextStyle(
                      color: Colors.red,
                      fontWeight: FontWeight.w500,
                      fontSize: 30),
                )),
            Container(
              padding: const EdgeInsets.all(10),
              child: TextField(
                controller: guessController,
                decoration: const InputDecoration(
                  border: OutlineInputBorder(),
                  hintText: 'Enter your guess number between 0 to 100',
                ),
              ),
            ),
            ElevatedButton(
                child: const Text('Submit'),
                onPressed: () {
                  setState(() {});
                  if (counter > 0) {
                    if (int.parse(guessController.text) == randomNumber) {
                      result = "You are correct.".toString();
                    } else {
                      if (int.parse(guessController.text) > randomNumber) {
                        counter = counter - 1;
                        result = "Guess lower".toString();
                      } else if (int.parse(guessController.text) <
                          randomNumber) {
                        counter = counter - 1;
                        result = "Guess higher".toString();
                      }
                    }
                  } else {
                    res = "You lost".toString();
                  }
                }),
            const Text(
              "Result:",
              textAlign: TextAlign.center,
              style: TextStyle(
                color: Colors.cyan,
                fontSize: 30,
              ),
            ),
            Text(result.toString(),
                textAlign: TextAlign.center,
                style: const TextStyle(
                  fontSize: 30,
                  color: Colors.lime,
                )),
            const Text(
              "Remaining number of Tries:",
              textAlign: TextAlign.center,
              style: TextStyle(
                fontSize: 30,
              ),
            ),
            Text(counter.toString(),
                textAlign: TextAlign.center,
                style: const TextStyle(
                  fontSize: 30,
                )),
            Text(res.toString(),
                textAlign: TextAlign.center,
                style: const TextStyle(
                  fontSize: 30,
                  color: Colors.red,
                ))
          ],
        ));
  }
}
